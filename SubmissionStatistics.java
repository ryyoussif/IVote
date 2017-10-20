
//package cs356.assignment1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

/**
 * SubmissionStatistics will display statistics for the number of times each answer is 
 * selected, submissionEnd will tell if the the submission is over or not.
 * it will also tell the number of correct answers
 *
 */
public class SubmissionStatistics implements IVoteService {

    private Hashtable<String, List<String>> submissions;
    private Question question;
    private boolean submissionsEnd;
    private Integer numCorrect;

    public SubmissionStatistics(Question question) {
        this.question = question;
        this.submissions = new Hashtable<String, List<String>>();
        this.submissionsEnd = false;
        this.numCorrect = 0;
    }

    
    @Override
	public boolean submit(String id, List<String> submission) {
        if (this.submissionsEnd) {
            return false;
        }

        // validate submission
        if (submission == null) {
            return false;
        }

        List<String> uniqueSubmission = makeUnique(submission);
        List<String> validSubmission = new ArrayList<String>();
        for (String sub : uniqueSubmission) {
            if (this.question.inChoices(sub)) {
                validSubmission.add(sub);
            }
        }
        this.submissions.put(id, validSubmission);
        return true;
    }

   
    private List<String> makeUnique(List<String> submission) {
        final Set<String> temp = new HashSet<String>(submission);
        final List<String> uq = new ArrayList<String>();
        uq.addAll(temp);
        return uq;
    }

    @Override
	public String displayStats() {
        checkAnswers();
        String stats = "Question:   " + this.question.getQuestion() + "\n\n";
        stats += "\nProcessing Answers..............\n";

        List<String> options = this.question.getOptions();
        for (String choice : options) {
            Integer numChoice = 0;
            for (String id : submissions.keySet()) {
                if (this.submissions.get(id).contains(choice)) {
                    numChoice++;
                }
            }
            stats += choice + "\t\t\t" + numChoice.toString() + "\n";
        }

        stats += "----------------------------\n";
        stats += "Total Submissions:      " + this.totalSubmissions().toString();
        stats += "\nTotal Correct Answers:  " + this.numCorrect;
        stats += "\n\n";
        return stats;
    }

    
    @Override
	public Integer totalSubmissions() {
        return this.submissions.size();
    }

    
    @Override
	public void endSubmissions() {
        this.submissionsEnd = true;
    }

    //check answers when submission is done
    private void checkAnswers() {
        if (!this.submissionsEnd) {
            return;
        }
        //if submission not done yet reset number of correct answers for now
        this.numCorrect = 0;
        
        for (Entry<String, List<String>> entry : submissions.entrySet()) {
            String check = question.checkAnswer(entry.getValue());
            if (check.compareTo("Correct!") == 0) {
                this.numCorrect++;
            }
        }
    }
}
