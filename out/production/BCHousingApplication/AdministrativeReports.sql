Select distinct BuildingNo, ApartmentSuiteNo, Bed 
from BCHousingDB.Assignment
where CheckOutDate < 2019-01-01 or CheckOutDate is null;

Select SSN, StudentStatus, ApplicationDate
from BCHousingDB.Applicant
Order by ApplicationDate;

Select BuildingNo,ApartmentSuiteNo, Bed, SSN
from BCHousingDB.Assignment
where SSN <> 0
Order by BuildingNo, ApartmentSuiteNo, Bed;

Select RequestNo, SubmissionDate, Employee
from BCHousingDB.MaintenanceRequest
where DateCompleted <> 0;