package objects;

import java.util.List;

public class Question {
	public long id,questionTypeId,testHeaderId;
	public String name;
	public List<PossibleAnswer> possibleAnswers;
	
	 public String toString() {
			return name;
		}
}
