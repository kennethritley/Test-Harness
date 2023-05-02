import java.sql.*;

/******************************************************************************
 * You can use this file to experiment with JDBC. As long as it contains
 * the "testDB" method, then the instructor can automatically test
 * this file, e.g. in a midterm examination or final examination.
 * 
 * @author Dr. Ken and his little green friend
 * @version 1.0
 * @since 2023-04-29
 */
public class TestTemplate_Myname {
    public static void main(String[] args) {
        // YOU NEED TO MODIFY THIS FOR YOUR OWN CONNECTION DETAILS!
        testDB(
            "jdbc:postgresql://127.0.0.1:5431/postgres", // DB connection URL
            "postgres", // DB username
            "kenpostgres"); // DB password
    }

/******************************************************************************
 * This is where you put your JDBC code. Feel free to call other methods.
 * This is the method that will be invoked from a different application
 * for testing or grading.
 * 
 * @author Dr. Ken and his little green friend
 * @version 1.0
 * @since 2023-04-29
 */
    public static void testDB(Object db_url, Object user, Object pass) {
        String url = (String) db_url;
        String username = (String) user;
        String password = (String) pass;
        System.out.println("Testing url: " + url);
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        // Test to see if the SQLite driver is installed correctly
        System.out.println("\n--------------------");
        System.out.println("Executing test: testSQLiteDriver");
        testSQLiteDriver("jdbc:sqlite::memory");

        // Test to see if the SQLite driver is installed correctly
        System.out.println("\n--------------------");
        System.out.println("Executing test: testPostgresDriver");
        testPostgresSQLDriver( url, username, password);

        // Print out some metadata about the database
        System.out.println("\n--------------------");
        System.out.println("Executing test: testPostgresShowMetadata");
        testPostgresShowMetadata( url, username, password);

        // Execute the test
        System.out.println("\n--------------------");
        System.out.println("Executing test1");
        test1( url, username, password);

        // Execute the test
        System.out.println("\n--------------------");
        System.out.println("Executing test2");
        test2( url, username, password);

        // Execute the test
        System.out.println("\n--------------------");
        System.out.println("Executing test3");
        test3( url, username, password);

        // Execute the test
        System.out.println("\n--------------------");
        System.out.println("Executing test4");
        test4( url, username, password);

        // Execute the test
        System.out.println("\n--------------------");
        System.out.println("Executing test5");
        test5( url, username, password);

    } // End of method


/******************************************************************************
 * This is a little method that uses the "in memory" version of SQLite
 * and prints out the time.
 * 
 * @author Dr. Ken and his little green friend
 * @version 1.0
 * @since 2023-04-29
 */
    public static void testSQLiteDriver(String url) {

        try {
            // Load the SQLite JDBC driver class explicitly
            // I put this code in here for you to get
            // some additional information in case Java
            // is not finding your driver file! :-)
            Class.forName("org.sqlite.JDBC");
            System.out.println("SQLite JDBC driver found!");
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC driver not found");
            return;
        } // End of try

        try (            
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("SELECT datetime('now')")
        )
        {
            // Display the current time
            if (rs.next()) {
                System.out.println("Current time: " + rs.getString(1));
            } else {
                System.out.println("Could not retrieve the current time.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } // End of try

        } // End of method



/******************************************************************************
 * This is a little method that tests if the PostgreSQL driver is working
 * by printing out the time
 * 
 * @author Dr. Ken and his little green friend
 * @version 1.0
 * @since 2023-04-29
 */
public static void testPostgresSQLDriver( String url, String username, String password) {

    try {
        // Load the PostgreSQL JDBC driver class explicitly
        // I put this code in here for you to get
        // some additional information in case Java
        // is not finding your driver file! :-)
        Class.forName("org.postgresql.Driver");
        System.out.println("PostgreSQL JDBC driver found!");
    } catch (ClassNotFoundException e) {
        System.out.println("PostgreSQL JDBC driver not found");
        return;
    } // End of try

    try {
        // Connect to the database
        Connection conn = DriverManager.getConnection(url, username, password);

        // Create a statement
        Statement stmt = conn.createStatement();

        // Execute a SQL query to get the current timestamp
        ResultSet rs = stmt.executeQuery("SELECT CURRENT_TIMESTAMP");

        // Print out the result
        if (rs.next()) {
            Timestamp timestamp = rs.getTimestamp(1);
            System.out.println("Current time: " + timestamp);
        }

        // Close the result set, statement, and connection
        rs.close();
        stmt.close();
        conn.close();
        conn = null; // this is good practice

    } catch (SQLException ex) {
        ex.printStackTrace();
    } // End of try
} // End of method



/******************************************************************************
 * This is a little method that tests if the PostgreSQL driver is working
 * by printing out the time
 * 
 * @author Dr. Ken and his little green friend
 * @version 1.0
 * @since 2023-04-29
 */
public static void testPostgresShowMetadata( String url, String username, String password) {

    Connection conn = null;
    try {
        // Connect to the database
        conn = DriverManager.getConnection(url, username, password);
        
        // Get metadata about the database
        DatabaseMetaData meta = conn.getMetaData();
        String dbName = meta.getDatabaseProductName();
        String dbVersion = "*** TO-DO: get the database version ***";
        String driverName = meta.getDriverName();
        String driverVersion = "*** TO-DO: get the driver version ***";
        System.out.println("DB product: " + dbName);
        System.out.println("DB version: " + dbVersion);
        System.out.println("Driver name: " + driverName);
        System.out.println("Driver version: " + driverVersion);
        
    } catch (SQLException e) {
        System.out.println("Error connecting to the database: " + e.getMessage());
    } finally {
        // Close the connection when we're done
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error closing the database connection: " + e.getMessage());
        }
    } // End of try

} // End of method


 /******************************************************************************
 * You can use this for JDBC tests.  This example uses try-with-resources
 * e.g. try (...) {}. Within the parenthesis you can create your connection
 * object. Java will automatically guarantee that this will be closed when
 * you exit the try block, without you needing to explicity close the connection
 * e.g. Java will do this for you. It is a way to handle the connection well
 * but keep your code clean!*   
 * @author (your name here)
 * @version 1.0
 * @since (your date here)
 */
public static void test1( String url, String username, String password) {

    try (Connection conn = DriverManager.getConnection(url, username, password);) 
    {        
        // add your code here

    } catch (SQLException ex) {
        ex.printStackTrace();
    } // End of try
} // End of method



/******************************************************************************
 * You can use this for JDBC tests
 *   
 * @author (your name here)
 * @version 1.0
 * @since (your date here)
 */
public static void test2( String url, String username, String password) {

    // try-with-resources
    try (Connection conn = DriverManager.getConnection(url, username, password);) 
    {        
        // add your code here

    } catch (SQLException ex) {
        ex.printStackTrace();
    } // End of try
} // End of method



/******************************************************************************
 * You can use this for JDBC tests
 *   
 * @author (your name here)
 * @version 1.0
 * @since (your date here)
 */
public static void test3( String url, String username, String password) {

    // try-with-resources
    try (Connection conn = DriverManager.getConnection(url, username, password);) 
    {        
        // add your code here

    } catch (SQLException ex) {
        ex.printStackTrace();
    } // End of try
} // End of method



/******************************************************************************
 * You can use this for JDBC tests
 *   
 * @author (your name here)
 * @version 1.0
 * @since (your date here)
 */
public static void test4( String url, String username, String password) {

    // try-with-resources
    try (Connection conn = DriverManager.getConnection(url, username, password);) 
    {        
        // add your code here

    } catch (SQLException ex) {
        ex.printStackTrace();
    } // End of try
} // End of method



/******************************************************************************
 * You can use this for JDBC tests.  This example uses try-with-resources
 * e.g. try (...) {}. Within the parenthesis you can create your connection
 * object. Java will automatically guarantee that this will be closed when
 * you exit the try block, without you needing to explicity close the connection
 * e.g. Java will do this for you. It is a way to handle the connection well
 * but keep your code clean!
 *   
 * @author (your name here)
 * @version 1.0
 * @since (your date here)
 */
public static void test5( String url, String username, String password) {

     // try-with-resources
     try (Connection conn = DriverManager.getConnection(url, username, password);) 
    {        
        // add your code here

    } catch (SQLException ex) {
        ex.printStackTrace();
    } // End of try
} // End of method



} // End of class

