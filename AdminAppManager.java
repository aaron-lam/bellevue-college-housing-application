import java.sql.Connection;
import java.sql.SQLException;

public class AdminAppManager {
    public static void manageApplicants(Connection conn) throws SQLException {
        printManageApplicants();
        boolean done = false;
        do {
            System.out.print("Type in your option: ");
            System.out.flush();
            String ch = InputReader.readLine();
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
                	checkApplication(conn);
                    break;
                case '5':
                	approveApplication(conn);
                    break;
                case '6':
                    done = true;
                    break;
                default:
                    System.out.println(" Not a valid option.");
            }
        } while (!done);
    }
    
    private static void checkApplication(Connection conn) throws SQLException {
    	
    }
    
    private static void approveApplication(Connection conn) throws SQLException {
    	
    }
    
    private static void printManageApplicants() {
        System.out.println("***********************************************************");
        System.out.println("                     ***************                       ");
        System.out.println("                      Applicant Menu                       ");
        System.out.println("                     ***************                       ");
        System.out.println("***********************************************************");
        System.out.println("          	      1. Add New Applicant				       ");
        System.out.println("                  2. Update Application 	               ");
        System.out.println("              3. Delete Applicant Information              ");
        System.out.println("                4. Check Application Status                ");
        System.out.println("               5. Approve Application Status               ");
        System.out.println("                        6. Quit                            ");	
    }
}
