package sample;
public class TutorSubject
{
    private String name,description;
    private int tutorID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTutorID() {
        return tutorID;
    }

    public void setTutorID(int tutorID) {
        this.tutorID = tutorID;
    }
    public void insertSubject()
    {
        String insert = "insert into tutorsubject(name, description, tutorID)"
                + "values("+this.getName()+","+ this.getDescription()+","+this.getTutorID()+
                ")";
        sqlstatement sql = new sqlstatement();
        sql.UpdateDatabase(insert);
    }
}
