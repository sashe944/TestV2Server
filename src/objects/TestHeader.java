package objects;

public class TestHeader {

		public long id,gradeSingleAnswer,gradeMultipleAnswer,gradeFreeTextAnswer,subjectID,userID;
		public String testName,fromDate,toDate;
		
		@Override
		public String toString() {
			
		return testName;
		}
}
