
import java.sql.*;

public class User
{
    String fname="'HAMIZ'",lname="'asdsad'",address="'GSAJADs'",contact="'GASHDGJS'",gender="'GSADGJASGD'";
    int age = 19,wallet = 0;
    float rating = 0;
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
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
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
        String insert = "insert into user(FirstName,LastName,Address,contact,age,gender,rating,wallet)"
                + "values("+this.getFname()+","+ this.getLname()+","+this.getAddress()+
                ","+this.getContact()+","+this.getAge()+","+this.getGender()+","
                +this.getRating()+","+this.getWallet()+")";
        sqlstatement sql = new sqlstatement();
        sql.UpdateDatabase(insert);
    }
}
