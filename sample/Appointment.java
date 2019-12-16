package sample;
import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class Appointment
{
    private String  time, location, duration,student_ID;
    private String students,teachers;
    private int tutorID,teacher_id;
    private Date date;
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getTutorID() {
        return tutorID;
    }

    public void setTutorID(int tutorID) {
        this.tutorID = tutorID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public void insertappointment()
    {
            String insert = "insert into apppointment(dateappointment, time,location, duration,tutorid)"
                    + "values("+this.getDate()+","+ this.getTime()+","+this.getLocation()+
                    ","+this.getDuration()+","+this.getTutorID()+")";
            sqlstatement sql = new sqlstatement();
            sql.UpdateDatabase(insert);
    }
    public void setStudentID(String email) throws SQLException {
        String getID="select Student_ID from student where email="+"\""+email+"\""+";";
        sqlstatement sql=new sqlstatement();
        CachedRowSet rs=sql.getData(getID);
        while(rs.next()){
            this.student_ID=rs.getString("student_ID");
            System.out.println(this.student_ID);
        }
    }
     public CachedRowSet getCourses() throws SQLException{
        String getCourses="select distinct firstName,lastName,qualification from User join teacher on teacher.teacher_id=user.user_id join appointment on appointment.teacher_id=teacher.teacher_id where appointment.student_id="+this.student_ID+" and appointment.time is not null";
        sqlstatement sql=new sqlstatement();
        CachedRowSet rs=sql.getData(getCourses);
        return rs;
     }
     public void setTeacherID(String firstName,String lastName) throws SQLException {
        String query="select user_id,firstName,lastName from user where firstName="+"'"+firstName+"'"+ " and "+"lastName="+"'"+lastName+"'";
        sqlstatement sql=new sqlstatement();
        CachedRowSet rs=sql.getData(query);
        while(rs.next()){
            tutorID=Integer.parseInt(rs.getString("user_id"));
            System.out.println(tutorID);
        }
     }
     public void selectCourse(){
        String insert="insert into appointment(Teacher_ID,Student_ID) values("+tutorID+","+student_ID+")";
        sqlstatement sql=new sqlstatement();
        sql.UpdateDatabase(insert);
     }
     public void setTeacher_idEmail(String email) throws SQLException{
        sqlstatement sql=new sqlstatement();
         System.out.println("Here");
        String query="select user_id from user join teacher on user.user_id=teacher.teacher_id where teacher.email="+"\""+email+"\"";
        CachedRowSet rs=sql.getData(query);
        while(rs.next()) {
            this.teacher_id = Integer.parseInt(rs.getString("user_id"));
        }
     }
     public CachedRowSet viewNewAppointments() throws SQLException {
        sqlstatement sql=new sqlstatement();
        String query="select firstName,lastName,gender,age,student_id,teacher_id from user join appointment on user.user_id=student_id where teacher_id="+this.teacher_id+" and "+"time is null";
        CachedRowSet rs=sql.getData(query);
         System.out.println(rs);
        return rs;

     }
     public void setAppointment_id(String student_ID,String teacher_id){
        this.teachers=teacher_id;
        this.students=student_ID;
     }
     public void createAppointment(LocalDate date, String time, String location, String duration){
        sqlstatement sql=new sqlstatement();
         System.out.println(date);
        String update="update appointment set dateAppointment="+"\""+date+"\""+","+"time="+"\""+time+"\""+","+"location="+"\""+location+"\""+","+"duration="+duration+" Where student_id="+this.students+" and "+"teacher_id="+teachers;
        sql.UpdateDatabase(update);
         System.out.println("Updated");
     }
     public CachedRowSet getAppointments(){
        sqlstatement sql=new sqlstatement();
        String query="select firstName,lastName,dateAppointment,time,location,duration from appointment join teacher on teacher.teacher_id=appointment.teacher_id join user on user.user_id=appointment.student_id where teacher.teacher_id="+teacher_id+" and time is not null";
        return sql.getData(query);
     }
     public CachedRowSet getSAppointment(){
        sqlstatement sql=new sqlstatement();
        String query="select firstName,lastName,dateAppointment,time,location,duration from appointment join teacher on teacher.teacher_id=appointment.teacher_id join user on user.user_id=teacher.teacher_id where appointment.student_id="+this.student_ID+" and time is not null";
        return sql.getData(query);
     }
     public CachedRowSet getStudentWallet(){
        sqlstatement sql=new sqlstatement();
        String query="select wallet from user where user_id="+this.student_ID;
        return sql.getData(query);
     }
     public CachedRowSet getTeacherWallet(){
        sqlstatement sql=new sqlstatement();
        String query="select wallet from user where user_id="+this.teacher_id;
        return sql.getData(query);
     }
}
