package sample;
import java.sql.*;

public class User
{

    String fname,lname,contact,gender;
    int age,wallet = 0;
    float rating = 0;
    User()
    {

    }
    User(String fn,String ln,String cont,String gender,String age)
    {
        this.fname = "'"+fn+"'";
        this.lname = "'"+ln+"'";
        this.contact = "'"+cont+"'";
        this.gender = "'"+gender+"'";
        this.age = Integer.valueOf(age);
    }
    public String getFname() {
        return fname;
    }
    public void setFname(String fname) {
        this.fname = fname;
    }
    public String getLname() {
        return lname;
    }
    public void setLname(String lname) {
        this.lname = lname;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getWallet() {
        return wallet;
    }
    public void setWallet(int wallet) {
        this.wallet=wallet;
    }
    public float getRating() {
        return rating;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }
    public void insertuser()
    {
        String insert = "insert into user(FirstName,LastName,contact,age,gender,rating,wallet)"
                + "values("+this.getFname()+","+ this.getLname()+","+this.getContact()+","+this.getAge()+","+this.getGender()+","
                +this.getRating()+","+this.getWallet()+")";
        sqlstatement sql = new sqlstatement();
        sql.UpdateDatabase(insert);
    }
}
