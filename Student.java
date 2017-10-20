
//package cs356.assignment1;

import java.util.List;

/**
 * @author reta_
 *
 */
public class Student {

    private static int id = 0;

    //private String id;
    private List<String> answers;

    public Student() {
        //id += 1;
        this.id += 1;
    }

    public String getID() {
        return Integer.toString(id);
    }

    /** Store generated answers (can be random or manually input) */
    public boolean enterAnswers(List<String> input) {
        this.answers = input;
        return true;
    }

    public List<String> getAnswers() {
        return answers;
    }
}
