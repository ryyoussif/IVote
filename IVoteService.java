//package cs356.assignment1;

import java.util.List;

/**
 * 
 * IVoteService will accept submissions from students. It allows only one submission per 
 * student. If multiple submissions are received from the same student, only the last
 * submission will be counted.It will also output the statistics of the submission results
 *
 */
public interface IVoteService {
	//submission validation
	boolean submit(String id, List<String> submission);

	//display statistics
	String displayStats();

	//total number of submissions
	Integer totalSubmissions();

	//Stop accepting submissions
	void endSubmissions();

}
