import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ODBCExample {
    public static void main(String[] args) {
        // JDBC URL for ODBC
        String jdbcUrl = "jdbc:odbc:YourDataSourceName"; // Replace with your ODBC data source name
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Load the JDBC-ODBC Bridge driver (Java 7 and earlier)
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

            // Establish a connection
            connection = DriverManager.getConnection(jdbcUrl);

            // Create a statement
            statement = connection.createStatement();

            // Execute a query
            resultSet = statement.executeQuery("SELECT * FROM YourTableName"); // Replace with your SQL query

            // Process the result set
            while (resultSet.next()) {
                System.out.println("Column1: " + resultSet.getString("Column1")); // Replace Column1 with your column name
                // Process other columns as needed
            }
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC-ODBC Bridge driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("SQL Exception occurred.");
            e.printStackTrace();
        } finally {
            // Clean up resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
