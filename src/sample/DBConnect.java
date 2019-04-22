package sample;

import java.sql.*;

public class DBConnect {

    private Connection con;
    private Statement st;
    private ResultSet rs;

    public DBConnect(){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/giraffe?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","03331655");
            st = con.createStatement();

        }
        catch (Exception e){
            System.out.print("Error " + e.getMessage());}

    }


        public void getData(){
            try{

                String query = "Select * from Employee";
                rs = st.executeQuery(query);
                System.out.print("Employee from Database");
                while(rs.next()){
                    String name = rs.getString("Name");
                    System.out.print(name);
                }
            }

            catch(Exception e){
                System.out.print("Error " + e.getMessage());
            }
        }

}
