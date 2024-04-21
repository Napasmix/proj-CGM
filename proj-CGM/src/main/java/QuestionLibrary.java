import java.util.HashMap;
import java.util.Map;

public class QuestionLibrary {
    // A map to store questions as keys and arrays of answers as values
    private Map<String, String[]> questionsAndAnswers;


    // Method to add a question and its answers to the library
    public QuestionLibrary() {
        this.questionsAndAnswers = new HashMap<>();
    }


     // Method to retrieve answers for a given question
    public void addQuestionAndAnswers(String question, String[] answers) {
        questionsAndAnswers.put(question, answers);
    }


     // Method to retrieve answers for a given question
    public String[] getAnswers(String question) {
        return questionsAndAnswers.get(question);
    }


      // Method to retrieve all questions and their answers
    public Map<String, String[]> getAllQuestionsAndAnswers() {
        return questionsAndAnswers;
    }
}
