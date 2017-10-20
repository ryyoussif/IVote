
//package cs356.assignment1;

import java.util.List;


public class SingleChoiceQuestion extends Question {

    public SingleChoiceQuestion(String question, List<String> options, List<String> correct) {
        super(question, options, correct);

        if (correct.size() != 1) {
            throw new IllegalArgumentException("The correct answers array must contain only one answer.");
        }
    }

    //this method should allow only one selection for single choice question
    public String checkAnswer(List<String> submittedAnswers) {
        if (submittedAnswers.size() != 1) {
            return "This is a single choice answer, please submit one answer.";
        } else {
            if (this.correctA.get(0).compareTo(submittedAnswers.get(0)) == 0) {
                return "Correct!";
            } else {
                return "Incorrect Answer! The correct answer is: "+this.correctA.get(0);
            }
        }
    }
}



	