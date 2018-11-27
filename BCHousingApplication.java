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
            user = readEntry("user id : ");
            pass = readEntry("password: ");

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
                        ResidentManager.residentLogin(conn);
                        break;
                    case '2':
                        ApplicantManager.applicantMain(conn);
                        break;
                    case '3':
                        openAdminPage(conn);
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

    private static void openAdminPage(Connection conn) throws SQLException {
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
                	AdminAppManager.manageApplicants(conn);
                	break;
                case '3':
                    System.out.println("Not required to implement this.");
                    break;
                case '4':
                    System.out.println("Not required to implement this.");
                    break;
                case '5':
                    openAdministrativeReports(conn);
                    break;
                case '6':
                    done = true;
                    break;
                default:
                    System.out.println(" Not a valid option.");
            }
        } while (!done);
    }
    


    private static void openAdministrativeReports(Connection conn) throws SQLException {
        printAdministrativeReportPage();
        boolean done = false;
        do {
            System.out.print("Type in your option: ");
            System.out.flush();
            String ch = readLine();
            System.out.println();
            switch (ch.charAt(0)) {
                case '1':
                    printHousingDepartmentReport(conn);
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

    private static void printHousingDepartmentReport(Connection conn) throws SQLException {
        String query = "SELECT AptSuite, ApartmentSuiteNo, Village, BuildingNo " +
                "FROM Assignment " +
                "WHERE SSN IS NULL;";
        PreparedStatement p = conn.prepareStatement(query);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            String aptSuite = r.getString(1);
            String apartmentSuiteNo = r.getString(2);
            String village = r.getString(3);
            String buildingNo = r.getString(4);
            System.out.println("[" + aptSuite + " " + apartmentSuiteNo + ", " + village + " village, Building " + buildingNo + "]");
        }
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
        System.out.println("               2. Applicant Registration/Apply             ");
        System.out.println("                        3. Admin                           ");
        System.out.println("                        4. Quit                            ");
    }

    private static void printAdminPage() {
        System.out.println("***********************************************************");
        System.out.println("         Welcome to Bellevue College Housing System        ");
        System.out.println("                     Administrators Staff                  ");
        System.out.println("***********************************************************");
        System.out.println("                     1. Manage Residents                   ");
        System.out.println("                     2. Manage Applicants                  ");
        System.out.println("                    3. Demographic Studies                 ");
        System.out.println("                 4. Manage Maintenance orders              ");
        System.out.println("                    5. Administrative Reports              ");
        System.out.println("                           6. Quit                         ");
    }

    private static void printAdministrativeReportPage() {
        System.out.println("***********************************************************");
        System.out.println("                  1. Housing department reports            ");
        System.out.println("                   2. Applicants Reports                   ");
        System.out.println("                    3. Resident Reports                    ");
        System.out.println("               4. Maintenance department reports           ");
        System.out.println("                         5. Quit                           ");
    }
}
