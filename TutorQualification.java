public class TutorQualification
{
    private int tutorID;
    private String qualification;

    public int getTutorID() {
        return tutorID;
    }

    public void setTutorID(int tutorID) {
        this.tutorID = tutorID;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
    public void insertQualification()
    {
        String insert = "insert into tutorqalification(TutorID, qualification)"
                + "values("+this.getTutorID()+","+ this.getQualification()+")";
        sqlstatement sql = new sqlstatement();
        sql.UpdateDatabase(insert);
    }
}
