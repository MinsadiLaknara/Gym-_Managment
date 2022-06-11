package sample;
public class StudentMember extends DefaultMember {

    String schoolName;
    String member_Type;

    public void setSchoolName() {
        while (true) {
            System.out.print("Enter School Name : ");
            if (sc.hasNextInt()){
                System.out.println("Invalid ");
                sc.nextLine();
            }
            else {
                this.schoolName = sc.nextLine();
                break;
            }
        }
    }

    public String getSchoolName() {
        return schoolName;
    }

    public StudentMember(){
        this.member_Type = "Student Member";
    }
}
