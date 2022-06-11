package sample;
import java.util.Scanner;

public class DefaultMember {
    Scanner sc = new Scanner(System.in);

    private String f_Name;
    String l_Name;
    int membershipNumber;
    String DefaultStartingDate;

    public DefaultMember(String f_Name){
        this.f_Name = f_Name;
    }

    public DefaultMember() {
        
    }

    public void setFirst_Name() {
        while (true) {
            System.out.print("\nEnter First Name : ");
            if (sc.hasNextInt()){
                System.out.println("Invalid ");
                sc.nextLine();
            }
            else {
                this.f_Name = sc.nextLine();
                break;
            }
        }
    }

    public void setLast_Name() {
        while (true) {
            System.out.print("Enter Last Name : ");
            if (sc.hasNextInt()){
                System.out.println("Invalid");
                sc.nextLine();
            }
            else {
                this.l_Name = sc.nextLine();
                break;
            }
        }
    }



    public void setMembershipNumber() {
        while (true) {
            System.out.print("Enter Member ID : ");
            if (!sc.hasNextInt()){
                System.out.println("Invalid ");
                sc.nextLine();
            }
            else {
                this.membershipNumber = sc.nextInt();
                break;
            }
        }
    }

    public void setDefaultStartMembershipDate() {
        while (true) {
            System.out.print("Enter Starting Date : ");
            if (sc.hasNextInt()){
                System.out.println("Invalid ");
                sc.nextLine();
            }
            else {
                this.DefaultStartingDate = sc.next();
                break;
            }
        }
    }

    public String getFirst_Name() {
        return f_Name;
    }
    public String getLast_Name() {
        return l_Name;
    }
    public int getMembershipNumber() {
        return membershipNumber;
    }
    public String getDefaultStartingDate() {
        return DefaultStartingDate;
    }

    public String writeData(){
        return ("-------------------------------\n" +
                "Member ID : " + getMembershipNumber() + "\n" +
                "First Name : " + getFirst_Name() + "\n" +
                "Last Name : " + getLast_Name() + "\n" +
                "Starting Date : " + getDefaultStartingDate() + "\n");
    }
}


