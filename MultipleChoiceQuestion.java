
//package cs356.assignment1;

import java.util.ArrayList;
import java.util.List;


public class MultipleChoiceQuestion extends Question {

    public MultipleChoiceQuestion(String question, List<String> options, List<String> correct) {
        super(question, options, correct);
    }

    //this method will check if at least one option selected
    public String checkAnswer(List<String> submittedAnswers) {
        if (submittedAnswers.size() < 1) {
            return "Please select at least one option.";
        } else {
            boolean correct = true;
            final List<String> temp = new ArrayList<String>(submittedAnswers);
            for (Integer i = 0; i < this.correctA.size(); i++) {
                correct = temp.remove(this.correctA.get(i));
                if (!correct) {
                    break;
                }
            }
            if (!correct || temp.size() != 0) {
                return "Incorrect Answer! The correct answer is "+this.correctA.toString();
            } else {
                return "Correct!";
            }
        }
    }
}

