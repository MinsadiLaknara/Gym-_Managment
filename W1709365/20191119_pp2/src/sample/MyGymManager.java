package sample;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCommandException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.*;
import java.util.Scanner;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.where;

import java.util.Scanner;

public class MyGymManager {

    public static String textFirst_Name;
    public static String textLast_Name;
    public static String textMembershipNumber;
    // connected to the mongodb
    public static MongoClient mongoClient = new MongoClient("localhost", 27017);
    public static MongoDatabase database = mongoClient.getDatabase("gymDB");
    public static MongoCollection<Document> collection = database.getCollection("gymMember");

    public static void main(String[] args) throws IOException {
        Writer ID = new FileWriter("count.txt", true);//File handling part
        Writer data = new FileWriter("data.txt");
        try{
            database.createCollection("gymMember");
        } catch (MongoCommandException e){
            System.out.println("Collection exists");
        }
        Scanner sc =  new Scanner(System.in);
        int C = 0;
        int n = 0;
        int n1 = 0;

        while (true) {

            if (C==100){ //if the count is exceeded the massage will print
                System.out.println("You have Exceeded the number");

            }

            else if(C<100) {//if condition for selection
                while (true) {
                    System.out.println("\n--------------Selection-------------- \n\n1. Add New Member \n2. Delete a Member \n3. Print the List of Members \n4. Sort Members \n5. Write/Save in a File \n6. Open Member List in GUI \n7. Exit \n");
                    System.out.print("Enter a Number : ");
                    if (!sc.hasNextInt()){
                        System.out.println("Invalid");
                        sc.nextLine();
                    }
                    else {
                        n = sc.nextInt();
                        break;
                    }
                }

                if (n == 1) {//if condition for member type
                    C++;
                    while (true) {
                        System.out.println("\nWhich type of member are you adding? \n1. Student Member \n2. Over 60 Member");
                        System.out.print("\nEnter your selection : ");
                        if (!sc.hasNextInt()){
                            System.out.println("Invalid");
                            sc.nextLine();
                        }
                        else {
                            n1 = sc.nextInt();
                            break;
                        }
                    }

                    if (n1 == 1) {//if condition for Student member type
                        StudentMember addMember = new StudentMember();
                        addMember.setFirst_Name();
                        addMember.setLast_Name();
                        addMember.setSchoolName();
                        addMember.setMembershipNumber();
                        addMember.setDefaultStartMembershipDate();

                        Document document = new Document();
                        document.put("memberID", addMember.getMembershipNumber());
                        document.put("memberType", addMember.member_Type);
                        document.put("firstName", addMember.getFirst_Name());
                        document.put("lastName", addMember.getLast_Name());
                        document.put("startingDate", addMember.getDefaultStartingDate());

                        collection.insertOne(document);

                        ID.write(C + "\n");
                        ID.flush();


                    } else if (n1 == 2) {//if conndition for over 60member
                        Over60Member addMember = new Over60Member();
                        addMember.setFirst_Name();
                        addMember.setLast_Name();
                        addMember.setAge();
                        addMember.setMembershipNumber();
                        addMember.setDefaultStartMembershipDate();

                        Document document = new Document();
                        document.put("memberID", addMember.getMembershipNumber());
                        document.put("member type", addMember.memberType);
                        document.put("firstName", addMember.getFirst_Name());
                        document.put("lastName", addMember.getLast_Name());
                        document.put("startingDate", addMember.getDefaultStartingDate());
                        document.put("age", addMember.getAge());

                        collection.insertOne(document);

                        ID.write(C + "\n");
                        ID.flush();

                    } else {
                        System.out.println("Invalid Entry");
                    }

                }

                else if (n==2){
                    C--;
                    while (true) {
                        System.out.print("Enter member ID number : ");
                        if (!sc.hasNextInt()){
                            System.out.println("Invalid Entry");
                            sc.nextLine();
                        }
                        else {
                            int IdNumber = sc.nextInt();

                            BasicDBObject query = new BasicDBObject();
                            query.put("memberID", IdNumber);

                            collection.deleteOne(query);
                            System.out.println("Member details successfully removed.");
                            break;
                        }
                    }

                }

                else if (n==3){
                    for (Document document : collection.find()) {
                        System.out.println(document);
                    }

                }

                else if (n==4){
                    FindIterable<Document> cursor = collection.find().sort(new BasicDBObject("firstName",1));
                    for (Document document : cursor) {
                        System.out.print(document.get("firstName") + " " + document.get("lastName") + "\n");
                    }
                }

                else if (n==5){
                    data.write("");
                    StringBuilder text = new StringBuilder();
                    for (Document document : collection.find()) {
                        text.append("Member ID : ").append(document.get("memberID"))
                                .append("\nMember Type : ").append(document.get("memberType"))
                                .append("\nFirst Name : ").append(document.get("firstName"))
                                .append("\nLast Name : ").append(document.get("lastName"))
                                .append("\nAddress : ").append(document.get("address"))
                                .append("\nStarting Date : ").append(document.get("startingDate"))
                                .append("\n\n");
                    }
                    data.write(String.valueOf(text));
                    textFirst_Name = String.valueOf(text);
                    data.flush();
                }

                else if (n==6){//shows the Gui
                    GUI.main(args);
                }

                else if(n==7){
                    ID.close();
//                    cont = false;
                    break;
                }

                else {
                    System.out.println("Invalid ");
                }


            }

        }

    }


}
