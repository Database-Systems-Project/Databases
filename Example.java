
//STEP 1. Import required packages
import javax.sql.rowset.CachedRowSet;
import java.sql.*;
import java.util.Scanner;


public class Example {


    public static void main(String[] args) throws SQLException {
        User std = new User();
        //Teacher tech = new Teacher();
        Student st = new Student();
        sqlstatement sql = new sqlstatement();
        std.insertuser();
        st.insertstudent();
        System.out.println(st.check("sp.gmail.com","hkfhsk"));

    }
}
