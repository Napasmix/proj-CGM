import java.util.HashMap;
import java.util.Map;

public class QuestionLibrary {
    private Map<String, String[]> questionsAndAnswers;

    public QuestionLibrary() {
        this.questionsAndAnswers = new HashMap<>();
    }

    public void addQuestionAndAnswers(String question, String[] answers) {
        questionsAndAnswers.put(question, answers);
    }

    public String[] getAnswers(String question) {
        return questionsAndAnswers.get(question);
    }

    public Map<String, String[]> getAllQuestionsAndAnswers() {
        return questionsAndAnswers;
    }
}
