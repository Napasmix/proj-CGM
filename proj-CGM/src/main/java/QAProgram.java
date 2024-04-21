import java.util.Scanner;

public class QAProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        QuestionLibrary questionLibrary = new QuestionLibrary();
  
        
 // Main loop for the program
        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Ask a question");
            System.out.println("2. Add a question and answers");
            System.out.println("3. Show all questions and answers");
            System.out.println("4. Exit");
  // Read user's choice
            int option = Integer.parseInt(scanner.nextLine());
   // Handle user's choice
            switch (option) {
                case 1://ask a question
                    askQuestion(scanner, questionLibrary);
                    break;
                case 2:// Add a question and answers
                    addQuestionAndAnswers(scanner, questionLibrary);
                    break;
                case 3:// Show all questions and answers
                    showAllQuestionsAndAnswers(questionLibrary);
                    break;
                case 4:// Exit the program
                    System.out.println("Thank you for using the program!");
                    return;
                default:
                    System.out.println("Incorrect option selected. Please try again.");
            }
        }
    }

     // Method to ask a question
    private static void askQuestion(Scanner scanner, QuestionLibrary questionLibrary) {
        System.out.println("Enter your question:");
        String question = scanner.nextLine().trim(); 
        
        // Check if the question ends with a question mark
        if (!question.endsWith("?")) {
            System.out.println("Incorrect format. The entered data is not a question.");
            return;
        }
        
        boolean questionFound = false;
    


         // Search for the question in the question library
        for (String key : questionLibrary.getAllQuestionsAndAnswers().keySet()) {
            
            // Check if the question matches partially (case-insensitive)
            if (key.trim().toLowerCase().startsWith(question.toLowerCase())) {
                questionFound = true;
                
              // Display the question
                System.out.println("Question: " + key);
               // Display the answers
                System.out.println("Answers:");
                String[] answers = questionLibrary.getAnswers(key);
                for (String answer : answers) {
                    System.out.println("- " + answer); 
                }
                break;
            }
        }
     // If the question is not found, provide a default answer
        if (!questionFound) {
            System.out.println("The answer to life, universe, and everything is 42");
        }
    }
    
    
    //Limits on the length of the question and the length of the answers
   private static final int MAX_QUESTION_LENGTH = 255;
   private static final int MAX_ANSWER_LENGTH = 255;


  // Method to add a question and its answers
private static void addQuestionAndAnswers(Scanner scanner, QuestionLibrary questionLibrary) {
    System.out.println(
            "Enter the question and its answers in the format <question>? \"<answer1>\" \"<answer2>\" \"<answerX>\" ...");
    String input = scanner.nextLine();

    String[] parts = input.split("\\?\\s*");
// Check if the input format is correct
    if (parts.length != 2) {
        System.out.println(
                "Not correct input format. Make sure you used the '?' character to separate the question from the answers.");
        return;
    }

    // Extract the question and check its length
    String question = parts[0].trim() + "?"; 
    if (question.length() > MAX_QUESTION_LENGTH) {
        System.out.println("Question exceeds maximum length of " + MAX_QUESTION_LENGTH + " characters.");
        return;
    }

    // Extract the answers and check their lengths
    String[] answers = parts[1].trim().split("\" \"");
    for (int i = 0; i < answers.length; i++) {
        answers[i] = answers[i].replaceAll("\"", "");
        if (answers[i].length() > MAX_ANSWER_LENGTH) {
            System.out.println("Answer " + (i+1) + " exceeds maximum length of " + MAX_ANSWER_LENGTH + " characters.");
            return;
        }
    }
// Add the question and answers to the library
    questionLibrary.addQuestionAndAnswers(question, answers);
    System.out.println("Question and answers successfully added!");
}

 // Method to display all questions and answers in the library
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
