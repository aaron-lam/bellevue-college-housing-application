import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminReports {
    public static void Reports(Connection conn) throws SQLException {
        printReportScreen();
        boolean done = false;
        do {
            System.out.print("Type in your option: ");
            System.out.flush();
            String ch = InputReader.readLine();
            System.out.println();
            switch (ch.charAt(0)) {
                case '1':
                        HousingDepartmentReport(conn);
                    break;
                case '2':
                    ApplicantReport(conn);
                    break;
                case '3':
                    ResidentReport(conn);
                    break;
                case '4':

                    break;
                case '5':
                    done = true;
                    break;
                default:
                    System.out.println(" Not a valid option.");
            }
        } while (!done);
    }

    private static void printReportScreen() {
        System.out.println("***********************************************************");
        System.out.println("                     ***************                       ");
        System.out.println("                      Report Menu                          ");
        System.out.println("                     ***************                       ");
        System.out.println("***********************************************************");
        System.out.println("           	 1. Housing Department Reports			       ");
        System.out.println("                  2. Applicant Reports  	               ");
        System.out.println("                   3. Resident Reports                     ");
        System.out.println("                 4. Maintenance Reports                    ");
        System.out.println("                        5. Quit                            ");
    }

    private static void HousingDepartmentReport(Connection conn) throws SQLException {
        String BuildingNo;
        String ApartmentSuiteNo;
        String Bed;
        String HousingReport = "Select distinct BuildingNo, ApartmentSuiteNo, Bed" +
                "from BCHousingDB.Assignment" +
                "where CheckOutDate < 2019-01-01 or CheckOutDate is null";
        PreparedStatement p = conn.prepareStatement(HousingReport);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            BuildingNo = r.getString(1);
            ApartmentSuiteNo = r.getString(2);
            Bed = r.getString(3);
            System.out.println(BuildingNo + ", " + ApartmentSuiteNo + ", " + Bed);
        }
    }

    private static void ApplicantReport(Connection conn) throws SQLException {
        String SSN;
        String Name;
        String StudentStatus;
        String ApplicationDate;
        String AppReport = "Select SSN, Name, StudentStatus, ApplicationDate" +
                "from BCHousingDB.Applicant" +
                "Order by ApplicationDate";
        PreparedStatement p = conn.prepareStatement(AppReport);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            SSN = r.getString(1);
            Name = r.getString(2);
            StudentStatus = r.getString(3);
            ApplicationDate = r.getString(4);
            System.out.println(SSN + ", " + Name + ", " + StudentStatus + ", " + ApplicationDate);
        }
    }

    private static void ResidentReport(Connection conn) throws SQLException {
        String BuildingNo;
        String ApartmentSuiteNo;
        String Bed;
        String SSN;
        String AppReport = "Select BuildingNo,ApartmentSuiteNo, Bed, SSN" +
                "from BCHousingDB.Assignment" +
                "where SSN <> 0" +
                "Order by BuildingNo, ApartmentSuiteNo, Bed";
        PreparedStatement p = conn.prepareStatement(AppReport);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            BuildingNo = r.getString(1);
            ApartmentSuiteNo = r.getString(2);
            Bed = r.getString(3);
            SSN = r.getString(4);
            System.out.println(BuildingNo + ", " + ApartmentSuiteNo + ", " + Bed + ", " + SSN);
        }
    }

    private static void MaintenanceReport(Connection conn) throws SQLException {
        String RequestNo;
        String SubmissionDate;
        String Employee;
        String MainReport = "Select RequestNo, SubmissionDate, Employee" +
                "from BCHousingDB.MaintenanceRequest" +
                "where DateCompleted <> 0";
        PreparedStatement p = conn.prepareStatement(MainReport);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            RequestNo = r.getString(1);
            SubmissionDate = r.getString(2);
            Employee = r.getString(3);
            System.out.println(RequestNo + ", " + SubmissionDate + ", " + Employee);
        }
    }

}
