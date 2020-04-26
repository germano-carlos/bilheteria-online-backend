package DBConfig;

import java.sql.*;
public class DB {
    private Connection con;

    public  DB() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String BD_URL = "jdbc:mysql://localhost:3306/bilheteria-digital?useTimezone=true&serverTimezone=UTC";
        this.con=DriverManager.getConnection(BD_URL,"root","root");
    }

    public void __call(String method){
        try{
            /*
            Modelo para buscar dados -> BD

            Statement stmt = this.con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from user");
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));

             */
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public void closeConnection() throws SQLException {
        this.con.close();
    }
    public Connection getConnection() { return this.con; }
}