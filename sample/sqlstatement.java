package sample;
//STEP 1. Import required packages
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

public class sqlstatement {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/acalink?autoReconnect=true&useSSL=false";
    private Connection conn = null;
    private Statement stmt = null;
    //  Database credentials
    static final String USER = "root";
    static final String PASS = "<3natsume";
    // JDBC driver name and database URL
    public sqlstatement(){
        Connection conn = null;
        Statement stmt = null;
    }

    public void UpdateDatabase(String sql)
    {

        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //STEP 4: Execute a query
            System.out.println("Creating database...");
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");
        }catch(Exception se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }
            catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end main
    public CachedRowSet getData(String sql)
    {
            CachedRowSet crs =null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //STEP 4: Execute a query
            System.out.println("Creating database...");
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(rs);
            System.out.println("Database created successfully...");
        }catch(Exception se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }
            catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Process Ended!");

        return crs;
    }//end main
}

//end JDBCExample

