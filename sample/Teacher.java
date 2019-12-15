package sample;
import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;

public class Teacher extends User{


    private String accountno, Email, Password,qualific;
    private int Teacher_id, Rate = 0;
    Teacher()
    {
        super();
    }
    Teacher(String fn,String ln,String cont,String gender,String age,String qual,String Acc,String email,String pass)
    {
        super(fn,ln,cont,gender,age);
        this.qualific = "'"+qual+"'";
        this.accountno = "'"+Acc+"'";
        this.Email = "'"+email+"'";
        this.Password = "'"+pass+"'";
    }

    public String getQualific() {
        return qualific;
    }
    public int getTeacher_id() {
        return Teacher_id;
    }
    public void setTeacher_id(int teacher_id) {
        Teacher_id = teacher_id;
    }
    public String getAccountno() {
        return accountno;
    }
    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }
    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        Password = password;
    }
    public int getRate() {
        return Rate;
    }
    public void setRate(int rate) {
        Rate = rate;
    }

    public void insertteacher() throws SQLException {
        sqlstatement sql = new sqlstatement();
        String query = "Select max(user_id) from User";
        CachedRowSet rs = sql.getData(query);
        while(rs.next()){
            //Retrieve by column name
            int id  = rs.getInt("max(user_id)");
            //Display values
            this.setTeacher_id(id);
        }
        String insert = "insert into Teacher(Teacher_ID, AccountNO, Rate,Email, Password, qualification)"
                + "values("+this.Teacher_id+","+ this.getAccountno()+","+this.getRate()+
                ","+this.getEmail()+","+this.getPassword()+","+this.getQualific()+")";

        sql.UpdateDatabase(insert);
    }

    public boolean check(String email, String password) throws SQLException {
        sqlstatement sql = new sqlstatement();
        String query = "select email,password from teacher";
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
