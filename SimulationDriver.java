/**
 * Reta Youssif
 * Object Oriented Programming
 */
//package cs356.assignment1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 * Simulation Driver will basically do the following:
 * 1) create a question type and configure the answers 
 * 2) configure the question for iVote Service 
 * 3) randomly generate a number students and the answers
 * 4) submit all the students’ answers to iVote Service
 * 5) call the iVote Service output function to display the result
 *
 */
public class SimulationDriver {

	public static void main(String[] args) {
		//singleChoices and multipleChoices will generate available choices to select from
		//singleAnswer and multipleAnswers will store the correct answer(s) from the choices
        List<String> singleChoices = new ArrayList<String>();
        List<String> singleAnswer = new ArrayList<String>();
        List<String> multipleChoices = new ArrayList<String>();
        List<String> multipleAnswers = new ArrayList<String>();

        singleChoices.add("Yes");
        singleChoices.add("No");
        //Selecting correct answer from singleChoices
        singleAnswer.add(singleChoices.get(0));

        multipleChoices.add("Java");
        multipleChoices.add("C++");
        multipleChoices.add("Python");
        
      //Selecting correct answers from multipleChoices
        multipleAnswers.add(multipleChoices.get(1));
        multipleAnswers.add(multipleChoices.get(2));

        Simulator("SingleChoice", "Will you graduate by Spring 2018?", singleChoices, singleAnswer, 45);

        Simulator("MultipleChoice", "What is your favorate programming language?", multipleChoices, multipleAnswers, 45);
    }

    //Starting simulation
    public static void Simulator(String questionType, String question, List<String> options, List<String> answers, int numOfStudents) {
        
    	Question questionT;
        Student[] students = new Student[numOfStudents];
        IVoteService SubmissionStatistics;

        
        //  1)create a question type and configure the answers
        if(questionType == "MultipleChoice")
          questionT = new MultipleChoiceQuestion(question, options, answers);
        else
          questionT = new SingleChoiceQuestion(question, options, answers);

        //  2)configure the question for iVoteService 
        
        SubmissionStatistics = new SubmissionStatistics(questionT);

        //  3) randomly generate a number students and the answers
        
        for (int i = 0; i < students.length; i++) {
            students[i] = new Student();
            students[i].enterAnswers(randAnswersGenerator(options, questionType));
            
        //  4) submit all the students’ answers to iVote Service
            SubmissionStatistics.submit(students[i].getID(), students[i].getAnswers());
        }
        
        if (SubmissionStatistics.totalSubmissions() != numOfStudents) {
            System.out.println("Number of submissions:    " + SubmissionStatistics.totalSubmissions());
        }
        System.out.println("Before ending submissions:");
        System.out.println("--------------------------\n" + SubmissionStatistics.displayStats());

        // for duplicate submissions
        for (int i = 0; i < students.length; i += 5) {
            students[i].enterAnswers(randAnswersGenerator(options, questionType));
            // submit most recent answer
            SubmissionStatistics.submit(students[i].getID(), students[i].getAnswers());
        }
        
        //submissions are not done yet
        if (SubmissionStatistics.totalSubmissions() != numOfStudents) {
            System.out.println("Number of submissions:  " + SubmissionStatistics.totalSubmissions().toString());
        }

        // end of submissions
        SubmissionStatistics.endSubmissions();

        // 5) display statistics
        System.out.println("Checking correct answer");
        System.out.println("------------------------\n" + SubmissionStatistics.displayStats());
    }

    /**
      * Randomly generate answers for multiple choice questions
      */
    public static List<String> randAnswersGenerator(List<String> choices, String type) {
        int numAnswers = 1;
        Random rand = new Random();
        List<String> answers = new ArrayList<String>();
        if (type == "MultipleChoice") {
            numAnswers = rand.nextInt(choices.size() - 1) + 1;
        }
        for (int i = 0; i < numAnswers; i++) {
            answers.add(choices.get(rand.nextInt(choices.size())));
        }
        return answers;
    }
}

