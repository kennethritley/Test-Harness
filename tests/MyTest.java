// This is the bare minimum you need - but don't modify this file!
public class MyTest {
    public static void main(String[] args) {
        // Standalone test code here
        testDB("mytest_database", "mytest_username", "mytest_password");
    }

    public static void testDB(Object db, Object user, Object pass) {
        String database = (String) db;
        String username = (String) user;
        String password = (String) pass;
        // Test code here
        System.out.println("Testing database: " + database);
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
    }
}
