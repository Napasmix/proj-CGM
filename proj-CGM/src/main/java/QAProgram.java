import java.util.Scanner;

public class QAProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        QuestionLibrary questionLibrary = new QuestionLibrary();

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Ask a question");
            System.out.println("2. Add a question and answers");
            System.out.println("3. Show all questions and answers");
            System.out.println("4. Exit");

            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    askQuestion(scanner, questionLibrary);
                    break;
                case 2:
                    addQuestionAndAnswers(scanner, questionLibrary);
                    break;
                case 3:
                    showAllQuestionsAndAnswers(questionLibrary);
                    break;
                case 4:
                    System.out.println("Thank you for using the program!");
                    return;
                default:
                    System.out.println("Incorrect option selected. Please try again.");
            }
        }
    }
    private static void askQuestion(Scanner scanner, QuestionLibrary questionLibrary) {
        System.out.println("Enter your question:");
        String question = scanner.nextLine().trim(); // Введене питання без знаку питання
        
        // Перевірка, чи питання закінчується знаком питання
        if (!question.endsWith("?")) {
            System.out.println("Incorrect format. The entered data is not a question.");
            return;
        }
        
        boolean questionFound = false;
    
        for (String key : questionLibrary.getAllQuestionsAndAnswers().keySet()) {
            // Порівнюємо частково, без знаку "?"
            if (key.trim().toLowerCase().startsWith(question.toLowerCase())) {
                questionFound = true;
                
                // Виводимо питання
                System.out.println("Question: " + key);
                // Виводимо відповіді
                System.out.println("Answers:");
                String[] answers = questionLibrary.getAnswers(key);
                for (String answer : answers) {
                    System.out.println("- " + answer); // Виводимо кожну відповідь з префіксом "-"
                }
                break;
            }
        }
    
        if (!questionFound) {
            System.out.println("The answer to life, universe, and everything is 42");
        }
    }
    
    
    
    
    

   private static final int MAX_QUESTION_LENGTH = 255;
private static final int MAX_ANSWER_LENGTH = 255;

private static void addQuestionAndAnswers(Scanner scanner, QuestionLibrary questionLibrary) {
    System.out.println(
            "Enter the question and its answers in the format <question>? \"<answer1>\" \"<answer2>\" \"<answerX>\" ...");
    String input = scanner.nextLine();

    String[] parts = input.split("\\?\\s*");

    if (parts.length != 2) {
        System.out.println(
                "Not correct input format. Make sure you used the '?' character to separate the question from the answers.");
        return;
    }

    // Перевірка довжини питання
    String question = parts[0].trim() + "?"; // Додавання знаку питання до питання
    if (question.length() > MAX_QUESTION_LENGTH) {
        System.out.println("Question exceeds maximum length of " + MAX_QUESTION_LENGTH + " characters.");
        return;
    }

    // Перевірка довжини відповідей
    String[] answers = parts[1].trim().split("\" \"");
    for (int i = 0; i < answers.length; i++) {
        answers[i] = answers[i].replaceAll("\"", "");
        if (answers[i].length() > MAX_ANSWER_LENGTH) {
            System.out.println("Answer " + (i+1) + " exceeds maximum length of " + MAX_ANSWER_LENGTH + " characters.");
            return;
        }
    }

    questionLibrary.addQuestionAndAnswers(question, answers);
    System.out.println("Question and answers successfully added!");
}


    private static void showAllQuestionsAndAnswers(QuestionLibrary questionLibrary) {
        System.out.println("All questions and answers:");
        for (String key : questionLibrary.getAllQuestionsAndAnswers().keySet()) {
            System.out.println("Question: " + key);
            System.out.println("Answers:");
            String[] answers = questionLibrary.getAnswers(key);
            for (String answer : answers) {
                System.out.println("- " + answer);
            }
            System.out.println();
        }
    }
}
