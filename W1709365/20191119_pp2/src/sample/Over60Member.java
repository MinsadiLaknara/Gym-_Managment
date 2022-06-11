package sample;
public class Over60Member extends DefaultMember{
    int age;
    String memberType;

    public void setAge() {
        while (true) {
            System.out.print("Enter Age : ");
            if (!sc.hasNextInt()){
                System.out.println("Invalid ");
                sc.nextLine();
            }
            else {
                this.age = sc.nextInt();
                if (age>=59){
                    break;
                }
                break;
            }
        }
    }

    public int getAge() {

        return age;
    }

    public Over60Member(){
        super();

        memberType = "Over 60 Member";
    }
}
