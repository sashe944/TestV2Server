package objects;

public class StudentAnswer {

   public long id, questioId, possibleAnswerId, studentId, testHeaderId;
   public String freeText;
	
   @Override
	public String toString() {
		return "StudentAnswer [id=" + id + ", questioId=" + questioId + ", possibleAnswerId=" + possibleAnswerId
				+ ", studentId=" + studentId + ", testHeaderId=" + testHeaderId + ", freeText=" + freeText + "]";
	}

   
}
