package sample;
import java.util.Date;

public class Appointment
{
    private String  time, location, duration;
    private int tutorID;
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

}
