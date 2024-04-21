import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class QAProgramTest {
     // ArrayOutputStream to capture the output stream
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    // InputStream to capture the input stream
    private final InputStream originalIn = System.in;

    @BeforeMethod
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }
// Test method for asking a correct question
    @Test
    public void testAskQuestion_CorrectQuestion() {
        String input = "1\nWhat is the capital of France?\n4\n";
        provideInput(input);
        QAProgram.main(new String[] {});
        String expectedOutput = "Select an option:\n1. Ask a question\n2. Add a question and answers\n" +
                "3. Show all questions and answers\n4. Exit\n" +
                "Enter your question:\nQuestion: What is the capital of France?\n" +
                "Answers:\nThe answer to life, universe, and everything is 42\n";
        
        // Assert that the actual output matches the expected output
                Assert.assertEquals(outContent.toString(), expectedOutput);
    }



// Test method for asking a question with incorrect format
    @Test
    public void testAskQuestion_IncorrectQuestionFormat() {
        String input = "1\nThis is not a question\n4\n";
        provideInput(input);
        QAProgram.main(new String[] {});
        String expectedOutput = "Select an option:\n1. Ask a question\n2. Add a question and answers\n" +
                "3. Show all questions and answers\n4. Exit\n" +
                "Enter your question:\nIncorrect format. The entered data is not a question.\n";
        Assert.assertEquals(outContent.toString(), expectedOutput);
    }


    // Test method for adding a question and answers with correct input
    @Test
    public void testAddQuestionAndAnswers_CorrectInput() {
        String input = "2\nWhat is the capital of Italy? \"Rome\"\n4\n";
        provideInput(input);// Provide input
        QAProgram.main(new String[] {});// Run the main method
       
        // Define the expected output
        String expectedOutput = "Select an option:\n1. Ask a question\n2. Add a question and answers\n" +
                "3. Show all questions and answers\n4. Exit\n" +
                "Enter the question and its answers in the format <question>? \"<answer1>\" \"<answer2>\" \"<answerX>\" ...\n"
                +
                "Question and answers successfully added!\n";
         // Assert that the actual output matches the expected output
                Assert.assertEquals(outContent.toString(), expectedOutput);
    }


  // Test method for showing all questions and answers
    @Test
    public void testShowAllQuestionsAndAnswers() {
        String input = "3\n4\n";
        provideInput(input);// Provide input
        QAProgram.main(new String[] {});// Run the main method
        // Define the expected output
        String expectedOutput = "Select an option:\n1. Ask a question\n2. Add a question and answers\n" +
                "3. Show all questions and answers\n4. Exit\n" +
                "All questions and answers:\nQuestion: What is the capital of Italy?\n" +
                "Answers:\n- Rome\n\n";
        
                 // Assert that the actual output matches the expected output
         Assert.assertEquals(outContent.toString(), expectedOutput);
    }

    // Method to provide input to System.in
    private void provideInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }

    // Method to restore original input and output streams
    private void restoreSystemInputOutput() {
        System.setIn(originalIn);
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
    }
}
