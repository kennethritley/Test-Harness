import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * This is a "test harness." The idea is that there will be a set of 
 * class files located in a directory /tests. This application will print
 * out that list, allow the user to pick one of those test files, it will then
 * compile that test file and execute it.
 *
 * @version 1.0
 * @since 2023-04-29
 * @author Dr. Ken
 */
 public class SQL {

     public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException, SecurityException {
         File dir = new File("./tests/");
         File[] files = dir.listFiles(new FilenameFilter() {
             public boolean accept(File dir, String name) {
                 return name.toLowerCase().endsWith(".java");
             }
         });
 
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (files != null && files.length > 0) {
                // List available test files
                System.out.println("\n\n----------");
                for (int i = 0; i < files.length; i++) {
                    System.out.println((i + 1) + ": " + files[i].getName());
                }
        
                // Prompt user for test file selection
                System.out.print("Enter the number of the test file to run (or 'q' to quit): ");
                String input = scanner.nextLine();
        
                if (input.equalsIgnoreCase("q")) {
                    // User wants to quit
                    scanner.close();
                    break;
                }
        
                int selection = Integer.parseInt(input);
        
                // Load and execute selected test file
                File selectedFile = files[selection - 1];
                String className = selectedFile.getName().replace(".java", "");
                Class<?> testClass = Class.forName(className);
                Method testMethod = testClass.getDeclaredMethod("testDB", Object.class, Object.class, Object.class);
                testMethod.invoke(null, "jdbc:postgresql://127.0.0.1:5431/postgres", "postgres", "kenpostgres");
            } else {
                System.err.println("No test files found in " + dir.getAbsolutePath());
                break;
            }
        }
        

    }
 }
 