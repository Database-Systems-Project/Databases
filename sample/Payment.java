package sample;
public class Payment
{
    private int amount,studentID,appointmentID;
    private String tutorAccount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public String getTutorAccount() {
        return tutorAccount;
    }

    public void setTutorAccount(String tutorAccount) {
        this.tutorAccount = tutorAccount;
    }
    public void insertPayment()
    {

            String insert = "insert into Payment(Amount, tutoraccount, studentID,appoinmentID)"
                    + "values("+this.getAmount()+","+ this.getTutorAccount()+","+this.getStudentID()+
                    ","+this.getAppointmentID()+")";
            sqlstatement sql = new sqlstatement();
            sql.UpdateDatabase(insert);
    }

}
