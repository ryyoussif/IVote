//package cs356.assignment1;

import java.util.List;

/**
 * Question is used for both types of questions: single and multiple choice questions
 * question will store the question, options will store available options and 
 * correctA will store correct answer(s)
 *
 */
public abstract class Question {

    String question;
    List<String> options;
    List<String> correctA;

    public Question(String question, List<String> options, List<String> correctA) {
        this.question = question;
        this.options = options;
        this.correctA = correctA;
    }

    public String getQuestion() {
        return this.question;
    }

    public List<String> getOptions() {
        return this.options;
    }

    //choice validator
    public boolean inChoices(String submission) {
        return this.options.contains(submission);
    }

    //method to answer(s)
    public abstract String checkAnswer(List<String> submittedAnswers);
}
