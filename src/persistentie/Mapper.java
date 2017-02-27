package persistentie;

import java.sql.Connection;
/*import com.mysql.jdbc.Connection;*/
import java.sql.DriverManager;
import java.sql.SQLException;

public class Mapper {
  
    public static Connection getConnection(Connection conn){
        /*Connection conn = null; 
        dit regel moet in de main verschijnen; ook moet het een regel met 
        conn.close()
        */
        try {
            conn = DriverManager.getConnection(Connectie.JDBC_URL);

        }
        catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());

        }
        return conn;
    
    };
    
}

