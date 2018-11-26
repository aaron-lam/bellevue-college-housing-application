import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class BCHousingApplication {
    public static void main(String args[]) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/BCHousingDB";
            String user, pass;
//            user = readEntry("user id : ");
//            pass = readEntry("password: ");
            user = "root";
            pass = "Spartan117*";
            conn = DriverManager.getConnection(url, user, pass);

            boolean done = false;
            do {
                printMenu();
                System.out.print("Type in your option: ");
                System.out.flush();
                String ch = readLine();
                System.out.println();
                switch (ch.charAt(0)) {
                    case '1':
                        residentLogin(conn);
                        break;
                    case '2':
                        applicantRegistration(conn);
                        break;
                    case '3':
                        openAdminPage();
                        break;
                    case '4':
                        done = true;
                        System.out.println("Exiting application.");
                        break;
                    default:
                        System.out.println(" Not a valid option.");
                }
            } while (!done);
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load the driver");
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }
    }

    private static void residentLogin(Connection conn) throws SQLException {
        String userID, password, query;
        String studentID = null;
        String name = null;
        String gender, college, department, maritalStatus;
        userID = readEntry("Enter User ID: ");
        password = readEntry("Enter password: ");
//        System.out.println("Implement resident login here");
		query = "SELECT * FROM Students "
		 + "WHERE IdNumber = " + userID;
	 
		PreparedStatement p = conn.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next() ) {
			studentID = r.getString(1);
			name = r.getString(2);
			gender = r.getString(3);
			college = r.getString(4);
			department = r.getString(5);
			maritalStatus = r.getString(6);
			
			System.out.println(studentID + " " + name);
		}
		
		if (studentID != null) {
			System.out.println("Welcome " + name);
		}
    }

    private static void applicantRegistration(Connection conn) throws SQLException {
    	boolean done = false;
        printApplicantPage();
        System.out.print("Type in your option: ");
        System.out.flush();
        String ch = readLine();
        System.out.println();
        switch (ch.charAt(0)) {
            case '1':
            	addNewApplicant(conn);
                break;
            case '2':
                break;
            case '3':
                break;
            case '4':
                done = true;
                break;
            default:
                System.out.println(" Not a valid option.");
        }
    }
    
    public static void printApplicantPage() {
        System.out.println("***********************************************************");
        System.out.println("                     ***************                       ");
        System.out.println("                      Applicant Menu                       ");
        System.out.println("                     ***************                       ");
        System.out.println("***********************************************************");
        System.out.println("                     1. New Applicant                      ");
        System.out.println("                 2. Update Applicant Information           ");
        System.out.println("                 3. Delete Applicant Information           ");
        System.out.println("                          4. Quit                          ");	
    }
    
    private static void addNewApplicant(Connection conn) throws SQLException {
    	String idNumber = null;
    	String name, gender, college, department, maritalStatus;
    	name = readEntry("Enter your name: ");
    	gender = readEntry("Enter your gender: ");
    	college = readEntry("Enter your college: ");
    	department = readEntry("Enter your department: ");
    	maritalStatus = readEntry("Enter marital status: ");
    	
    	String ssn, suitePreference, apartmentPreference, villagePreference, date;
        ssn = readEntry("Enter ssn: ");
        suitePreference = readEntry("Enter suite preference (One Bedroom or Two Bedroom): ");
        apartmentPreference = readEntry("Enter apartment preference (Two Bedroom or Four Bedroom): ");
		villagePreference = readEntry("Enter village preference (East or West): ");
//        date = readEntry("Enter today's date (MM/DD/YYYY): ");
        
        String maxIdQuery = "SELECT MAX(IdNumber) FROM Students";
        		
		PreparedStatement p = conn.prepareStatement(maxIdQuery);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			idNumber = r.getString(1);
		}

		String query = "INSERT INTO Students VALUES "
        		+ "(1 + " + idNumber + ",'" + name + "','" + gender + "','" 
        		+ college + "','" + department + "','" + maritalStatus + "')";
		
		p = conn.prepareStatement(query);
		p.executeUpdate();
        
        query = "INSERT INTO Applicant VALUES "
        		+ "(" + ssn + ",'Applied','"  + suitePreference + "','" +  apartmentPreference
        		+  "','" + villagePreference + "'," + "NULL" + ")";
        
        p = conn.prepareStatement(query);
        p.executeUpdate();
		
		System.out.println("Application submitted");
    }

    private static void openAdminPage() {
        printAdminPage();
        boolean done = false;
        do {
            printAdminPage();
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
                    System.out.println("Not required to implement this.");
                    break;
                case '5':
                    openAdministrativeReports();
                    break;
                case '6':
                    done = true;
                    break;
                default:
                    System.out.println(" Not a valid option.");
            }
        } while (!done);
    }

    private static void openAdministrativeReports() {
        printAdministrativeReportPage();
        boolean done = false;
        do {
            System.out.print("Type in your option: ");
            System.out.flush();
            String ch = readLine();
            System.out.println();
            switch (ch.charAt(0)) {
                case '1':
                    printHousingDepartmentReport();
                    break;
                case '2':
                    System.out.println("Not required to implement this.");
                    break;
                case '3':
                    System.out.println("Not required to implement this.");
                    break;
                case '4':
                    System.out.println("Not required to implement this.");
                    break;
                case '5':
                    done = true;
                    break;
                default:
                    System.out.println(" Not a valid option.");
            }
        } while (!done);
    }

    private static void printHousingDepartmentReport() {
        System.out.println("Housing department reports function not yet implemented.");
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

    private static void printMenu() {
        System.out.println("***********************************************************");
        System.out.println("                     ***************                       ");
        System.out.println("               Welcome to the Housing System               ");
        System.out.println("                     ***************                       ");
        System.out.println("***********************************************************");
        System.out.println("                     1. Resident Login                     ");
        System.out.println("                 2. Applicant Registration/Apply           ");
        System.out.println("                           3. Admin                        ");
        System.out.println("                           4. Quit                         ");
    }

    private static void printAdminPage() {
        System.out.println("***********************************************************");
        System.out.println("         Welcome to Bellevue College Housing System        ");
        System.out.println("                     Administrators Staff                   \n");
        System.out.println("***********************************************************");
        System.out.println("                     1. Manage Residents                   ");
        System.out.println("                     2. Manage Applicants                  ");
        System.out.println("                    3. Demographic Studies                 ");
        System.out.println("                 4. Manage Maintenance orders              ");
        System.out.println("                    5. Administrative Reports              ");
        System.out.println("                           6. Quit                         ");
    }

    private static void printAdministrativeReportPage() {
        System.out.println("                  1. Housing department reports            ");
        System.out.println("                   2. Applicants Reports                   ");
        System.out.println("                    3. Resident Reports                    ");
        System.out.println("               4. Maintenance department reports           ");
        System.out.println("                         5. Quit                           ");
    }
}
