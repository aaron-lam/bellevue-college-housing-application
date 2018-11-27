import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResidentManager {
    public static void residentLogin(Connection conn) throws SQLException {
        String userID, password, query;
        String studentID = null;
        String name = null;
        String gender, college, department, maritalStatus;
        userID = readEntry("Enter User ID: ");
        password = readEntry("Enter password: ");
        query = "SELECT * FROM Students "
                + "WHERE IdNumber = " + userID;

        PreparedStatement p = conn.prepareStatement(query);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            studentID = r.getString(1);
            name = r.getString(2);
            gender = r.getString(3);
            college = r.getString(4);
            department = r.getString(5);
            maritalStatus = r.getString(6);

//            System.out.println(studentID + " " + name);
        }

        if (studentID != null) {
            openResidentMain(conn, name);
        } else {
        	System.out.println("User ID not found.");
        }
    }
    
    private static void openResidentMain(Connection conn, String name) throws SQLException {
        printApplicantPage(name);
        boolean done = false;
        do {
            System.out.print("Type in your option: ");
            System.out.flush();
            String ch = readLine();
            System.out.println();
            switch (ch.charAt(0)) {
                case '1':
                	System.out.println("Not required to implement this.");
                	break;
                case '2':
                    System.out.println("Not required to implement this.");
                    break;
                case '3':
                    System.out.println("Not required to implement this.");
                    break;
               case '4':
                    done = true;
                    break;
                default:
                    System.out.println(" Not a valid option.");
            }
        } while (!done);
    }
    
    private static void printApplicantPage(String name) {
        System.out.println("***********************************************************");
        System.out.println("                     ***************                       ");
        System.out.println("                      Resident Menu                        ");
        System.out.println("                     ***************                       ");
        System.out.println("***********************************************************");
        System.out.println("                    Welcome " + name + "                   ");
        System.out.println();
        System.out.println("                  1. View Account Balance                  ");
        System.out.println("               2. Submit Maintenace Request			       ");
        System.out.println("               3. Update account Information               ");
        System.out.println("                        4. Quit                            ");
    } 
    
    static String readEntry(String prompt) {
        try {
            StringBuffer buffer = new StringBuffer();
            System.out.print(prompt);
            System.out.flush();
            int c = System.in.read();
            while (c != '\n' && c != -1) {
                buffer.append((char) c);
                c = System.in.read();
            }
            return buffer.toString().trim();
        } catch (IOException e) {
            return "";
        }
    }

    private static String readLine() {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr, 1);
        String line = "";

        try {
            line = br.readLine();
        } catch (IOException e) {
            System.out.println("Error in SimpleIO.readLine: " + "IOException was thrown");
            System.exit(1);
        }
        return line;
    }
}
