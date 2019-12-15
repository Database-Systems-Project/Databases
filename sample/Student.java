package sample;
import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;

public class Student extends User{



    private String email = "'sp.gmail.com'",password = "'hkfhsk'", Grade = "'uni'";
    private int Student_ID, TotalHours = 0;
    Student()
    {
        super();
    }
    Student(String fn,String ln,String cont,String gender,String age,String level,String email,String pass)
    {
        super(fn,ln,cont,gender,age);
        this.Grade = "'"+level+"'";
        this.email = "'"+email+"'";
        this.password = "'"+pass+"'";

    }
    public int getStudent_ID() {
        return Student_ID;
    }
    public void setStudent_ID(int student_ID) {
        Student_ID = student_ID;
    }
    public String getGrade() {
        return Grade;
    }
    public void setGrade(String grade) {
        Grade = grade;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getTotalHours() {
        return TotalHours;
    }
    public void setTotalHours(int totalHours) {
        TotalHours = totalHours;
    }

    public void insertstudent() throws SQLException {
        sqlstatement sql = new sqlstatement();
        String query = "Select max(user_id) from User";
        CachedRowSet rs = sql.getData(query);
        while(rs.next()){
            //Retrieve by column name
            int id  = rs.getInt("max(user_id)");
            //Display values
            this.setStudent_ID(id);
        }
        query = "insert into student(Student_ID, TotalHours, Grade, Email, Password)"
                + "values("+this.getStudent_ID()+","+ this.getTotalHours()+","+this.getGrade()+
                ","+this.getEmail()+","+this.getPassword()+")";
         sql.UpdateDatabase(query);

    }
    public boolean check(String email, String password) throws SQLException {
        sqlstatement sql = new sqlstatement();
        String query = "select email,password from student";
        CachedRowSet rs = sql.getData(query);
        while(rs.next()){
            //Retrieve by column name
            if(email.equals(rs.getString("email")) && password.equals(rs.getString("password")))
            {
                return true;
            }
        }
        return false;
    }

}
