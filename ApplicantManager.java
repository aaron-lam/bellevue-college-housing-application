import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicantManager {
    public static void applicantMain(Connection conn) throws SQLException {
        boolean done = false;
        printApplicantPage();
        System.out.print("Type in your option: ");
        System.out.flush();
        String ch = InputReader.readLine();
        System.out.println();
        do {
			switch (ch.charAt(0)) {
				case '1':
					checkHousingAvailability(conn);
					done = true;
					break;
				case '2':
					addNewApplicant(conn);
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
        } while(!done);
    }

    private static void printApplicantPage() {
        System.out.println("***********************************************************");
        System.out.println("                     ***************                       ");
        System.out.println("                      Applicant Menu                       ");
        System.out.println("                     ***************                       ");
        System.out.println("***********************************************************");
        System.out.println("               1. Check Housing Availability               ");
        System.out.println("                2. Submit New Application                  ");
        System.out.println("              3. Update Applicant Information              ");
        System.out.println("              4. Delete Applicant Information              ");
        System.out.println("                        5. Quit                            ");
    }
    
    private static void checkHousingAvailability(Connection conn) throws SQLException {
    	System.out.println("HELLO");
		boolean done = false;
		do {
			printHousingOptions();
			String query;
			System.out.print("Type in your option: ");
			System.out.flush();
			String ch = InputReader.readLine();
			System.out.println();
			switch (ch.charAt(0)) {
				case '1':
					query = "SELECT BuildingNo, ApartmentSuiteNo, Bedroom, Bed, AptSuite, Village, MarriageEligable FROM Assignment "
							 + "WHERE SSN = 'NULL'";
					housingQuery(conn, query);
					done = true;
					break;
				case '2':
					String suitePreference = InputReader.readEntry("Enter arrangement preference (Apartment or Suite): ");
					String bedroomPreference = "";
					if (suitePreference.equals("Apartment")) {
						bedroomPreference = InputReader.readEntry("Enter number of bedrooms (1, 2, or 4): ");
					} else {
						bedroomPreference = InputReader.readEntry("Enter number of bedrooms (1 or 2): ");
					}
					String villagePreference = InputReader.readEntry("Enter village preference (East or West): ");
					String marriagePreference = "Yes";
					boolean cont = false;
					do {
						String input = InputReader.readEntry("Accomodations for married couples? (y or n): ").toLowerCase();
						switch (input.charAt(0)) {
							case 'y':
								marriagePreference = "Yes";
								cont = false;
								break;
							case 'n':
								marriagePreference ="No";
								cont = false;
								break;
							default:
								cont = true;
								System.out.println(" Not a valid option.");
						}
					} while (cont);
				   
				   query = "SELECT BuildingNo, ApartmentSuiteNo, Bedroom, Bed, AptSuite, Village, MarriageEligable FROM Assignment "
							 + "WHERE SSN = 'NULL' AND AptSuite = '" + suitePreference + "'"
							 + "AND Village = '" + villagePreference + "'"
							 + "AND MarriageEligable = '" + marriagePreference + "'"
							 + "AND Bedroom =" + bedroomPreference;
				   housingQuery(conn, query);
				   break;
				case '3':
					done = true;
					break;
				default:
					System.out.println(" Not a valid option.");
			}
			System.out.println();
		}  while (!done);
    }
    
    private static void printHousingOptions() {
        System.out.println("***********************************************************");
        System.out.println("                     ***************                       ");
        System.out.println("                Check Housing Availability                 ");
        System.out.println("                     ***************                       ");
        System.out.println("***********************************************************");
        System.out.println("                      1. Check All                         ");
        System.out.println("                   2. Filtered Search                      ");
        System.out.println("                        3. Quit                            ");
    }

    private static void housingQuery(Connection conn, String query) throws SQLException {
    	PreparedStatement p = conn.prepareStatement(query);
    	ResultSet r = p.executeQuery();
    	System.out.println("Building No   Apartment No    No Bedrooms     Bed No    "
    			+ " Apartment/Suite?     Village      Marriage Eligible?");
    	while (r.next()) {
    		String buildNo = r.getString(1);
    		String aptNo = r.getString(2);
    		String bedroomNo = r.getString(3);
    		String bedNo = r.getString(4);
    		String aptSuite = r.getString(5);
    		String village = r.getString(6);
    		String marriage = r.getString(7);
    		
    		System.out.println("     " + buildNo + "             " + aptNo + "             " +
    				bedroomNo + "             " + bedNo + "           " + aptSuite + "           "
    						+ village + "                " + marriage);
    	}
    }


    private static void addNewApplicant(Connection conn) throws SQLException {
        String idNumber = null;
        String name, gender, college, department, studentStatus, maritalStatus;
        String roomSSN = null, spouse = null;
        
        String familyHeadSSN = null, headID = null, headCollege = null, headDept = null;
        name = InputReader.readEntry("Enter your name: ");
        gender = InputReader.readEntry("Enter your gender: ");
        studentStatus = InputReader.readEntry("Enter your standing (Student or Alumni): ");
        college = InputReader.readEntry("Enter your college: ");
        department = InputReader.readEntry("Enter your department: ");
        maritalStatus = InputReader.readEntry("Enter marital status: ");

        String ssn, suitePreference, apartmentPreference, villagePreference;
        ssn = InputReader.readEntry("Enter ssn: ");
        suitePreference = InputReader.readEntry("Enter suite preference (One Bedroom or Two Bedroom): ");
        apartmentPreference = InputReader.readEntry("Enter apartment preference (Two Bedroom or Four Bedroom): ");
        villagePreference = InputReader.readEntry("Enter village preference (East or West): ");
        
        boolean done = false;
        do {
        	System.out.println();
            String famPrompt = InputReader.readEntry("Is your head of family a current student? (y or n?): ");
			switch (famPrompt.charAt(0)) {
				case 'y':
			        familyHeadSSN = InputReader.readEntry("Enter your family head's SSN: ");
					headID = InputReader.readEntry("Enter family head's student ID: ");       
					headCollege = InputReader.readEntry("Enter family head's college SSN: ");       
					headDept = InputReader.readEntry("Enter family head's department: ");
					
					done = true;
					break;                                                         
				case 'n':
					done = true;
					break;
				default:
					System.out.println(" Not a valid option.");
			}
        } while(!done);


        System.out.println();
        String ch = InputReader.readEntry("Would you like to request a roommate (y/n)?: ").toLowerCase();
        
		switch (ch.charAt(0)) {
			case 'y':
				roomSSN = InputReader.readEntry("Enter roommate SSN: ");          
				spouse = InputReader.readEntry("Is requested a spouse? (0 for no, 1 for yes) :");
				break;                                                         // Should Spouse field be a boolean instead?
			case 'n':
				break;
			default:
				System.out.println(" Not a valid option.");
		}

        String query = "SELECT MAX(IdNumber) FROM Students";

        PreparedStatement p = conn.prepareStatement(query);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            idNumber = r.getString(1);
        }
        
        insertApplicant(conn, idNumber, ssn, name, gender, college, department, maritalStatus, studentStatus, suitePreference,
        		apartmentPreference, villagePreference);
        
		addFamilyHead(conn, familyHeadSSN, headID, headCollege, headDept);
	
		addRoommate(conn, roomSSN, ssn, spouse);


        System.out.println();
        System.out.println("              Application successfully submitted           ");
        System.out.println();
    }
    
    private static void insertApplicant(Connection conn, String idNumber, String ssn, String name, String gender, String college, String department,
    		String maritalStatus, String studentStatus, String suitePreference, String apartmentPreference, String villagePreference) throws SQLException {
        String query = "INSERT INTO Students VALUES "
                + "(1 + " + idNumber + ",'" + ssn + "','" + name + "','" + gender + "','"
                + college + "','" + department + "','" + maritalStatus + "')";

        PreparedStatement p = conn.prepareStatement(query);
        p.executeUpdate();

        query = "INSERT INTO Applicant VALUES "
                + "('" + ssn +  "','" + name + "','" + studentStatus + "','Applied','" + suitePreference + "','" + apartmentPreference
                + "','" + villagePreference + "'," + "NOW()" + ")";                // NOW() gets current date

        p = conn.prepareStatement(query);
        p.executeUpdate();
    }

    private static void addRoommate(Connection conn, String roomSSN, String ssn, String spouse) throws SQLException {
    	if (roomSSN != null) {
    		String query = "INSERT INTO RoommateRequest VALUES"
                + "(" + roomSSN + "," + ssn + "," + spouse + ")";	
			PreparedStatement p = conn.prepareStatement(query);
			p.executeUpdate();
    	}
	}
    
    private static void addFamilyHead(Connection conn, String familyHeadSSN, String headID, String headCollege, String headDept) throws SQLException {
        String query = null;
    	if (headID != null) {
            query = "INSERT INTO RoommateRequest VALUES"
                    + "('" + familyHeadSSN + "','" + headID + ",'" + headCollege + "','" + headDept + "')";
            PreparedStatement p = conn.prepareStatement(query);
            p.executeUpdate();
    	}
    }
}
