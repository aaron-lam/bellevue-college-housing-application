import java.sql.Connection;
import java.sql.SQLException;

public class AdminAppManager {
    public static void manageApplicants(Connection conn) throws SQLException {
        boolean done = false;
        do {
            printManageApplicants();
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
        String query = "select SSN, StudentStatus,ApplicationDate from Applicant " +
                "where ResidentStatus = 'Processing' order by StudentStatus desc, ApplicationDate asc";
            PreparedStatement p = conn.prepareStatement (query);
            p.clearParameters();
            ResultSet r = p.executeQuery();
            while (r.next ()) {
                String SSN = r.getString(1);
                String StudentStatus = r.getString(2);
                String ApplicationDate = r.getString(3);
                System.out.println(SSN + ",  " + StudentStatus + ", " + ApplicationDate);
            }
    }

    private static void approveApplication(Connection conn) throws SQLException {
        String SSN;
        SSN = InputReader.readEntry("Enter SSN of pending student: ");
        System.out.println("Approval? (y/n)");
        System.out.flush();
        String ch = InputReader.readLine();
        System.out.println();
        switch (ch.charAt(0)) {
            case 'y':
                String SuitePreference;
                int BeedroomPreferenceInt;
                String ApartmentPreference;
                String VillagePreference;
                String RequestedName;
                String RequestedSpousebool;
                String RequestedSpouseString
                String empty = "NULL";
                String AptSuite;
                String RoomsOpen = "SELECT SuitePreference,ApartmentPreference,VillagePreference FROM Applicant" +
                        "WHERE SSN =" SSN;
                PreparedStatement x = conn.prepareStatement(RoomsOpen);
                ResultSet r = x.executeQuery();
                while (r.next()) {
                    SuitePreferencee = r.getString(1);
                    if(SuitePreference.equals(empty)){
                        AptSuite = "Apartment";
                    } else{
                        AptSuite = "Suite";
                        if(SuitePreference.charAt(0) == 'O'){
                            BedroomPreferenceInt = 1;
                        } else {
                            BeedroomPreferenceInt = 2;
                        }
                    }
                    ApartmentPreference = r.getString(2);
                    if(ApartmentPreference.charAt(0) == "T"){
                        BeedroomPreferenceInt = 2;
                    } else {
                        BeedroomPreferenceInt = 4;
                    }
                    VillagePreference = r.getString(3);
                }
                String RoommatePreference = "SELECT RequestedName,RequestedSpouse FROM RoommateRequest" +
                        "WHERE " + SSN + "= ApplicantSSN";
                PreparedStatement y = conn.prepareStatement(RoomsOpen);
                ResultSet s = y.executeQuery();
                while (s.next()) {
                    RequestedName = s.getString(1);
                    RequestedSpousebool = s.getString(2);
                    if(RequestedSpousebool.charAt(0) == '0'){
                        RequestedSpouseString = "No";
                    } else {
                        RequestedSpouseString = "Yes";
                    }
                }
                String ApartmentAddress;
                String RoomAddress;
                String empty = "";
                String RoomsOpen = "SELECT BuildingNo,ApartmentSuiteNo FROM Assignment" +
                        "WHERE SSN = NULL,AptSuite = " + ApartmentPreferenceInt + ",Village = /'" + VillagePreference +
                        "/',MarriageEligable = /'" + RequestedSpousebool + "/',Bedroom =" + BeedroomPreferenceInt;
                PreparedStatement k = conn.prepareStatement(RoomsOpen);
                ResultSet g = x.executeQuery();
                while (g.next()) {
                    ApartmentAddress = g.getString(1);
                    RoomAddress = g.getString(2);
                }
                if (ApartmentAddress.equals(empty)){
                    String RoomsOpenAgain = "SELECT BuildingNo,ApartmentSuiteNo FROM Assignment" +
                            "WHERE SSN = NULL";
                }
                PreparedStatement j = conn.prepareStatement(RoomsOpenAgain);
                ResultSet f = j.executeQuery();
                while (f.next()) {
                    ApartmentAddress = f.getString(1);
                    RoomAddress = f.getString(2);
                }
                if(ApartmentAddress.equals(empty)){
                    System.out.println("All rooms full");
                    break;
                }
                String query = "UPDATE Applicant SET ResidentStatus = 'Approved' WHERE SSN =" + SSN;
                p = conn.prepareStatement(query);
                p.executeUpdate();
                break;
            case 'n':
                String query2 = "UPDATE Applicant SET ResidentStatus = 'Rejected' WHERE SSN =" + SSN;
                p = conn.prepareStatement(query2);
                p.executeUpdate();
                break;
            default:
                System.out.println(" Not a valid option.");
        }

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
