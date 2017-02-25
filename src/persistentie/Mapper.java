package persistentie;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

public class Mapper {
    
    try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
    
    }
    
}
