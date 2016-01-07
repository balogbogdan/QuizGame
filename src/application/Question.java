package application;

// This class handles questions and all their information
// All questions are instances of this class


import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;


// This class holds the correct answer and wrong answers for each question
public class Question {

    // static variables (available to any instance)
    static Random rand = new Random();
    public static int score = 0;
    public static int questionCount = 1;
    public static int questionIndex = 0;
    public static int questionsCorrect = 0;

    // instance specific variables
    String question;
    String correct;
    ArrayList<String> wrongs;

    // Constructor; creates "wrongs" ArrayList which stores the wrong/decoy answers
    public Question(String question, String correct, String wrong1, String wrong2, String wrong3) {
        this.question = question;
        this.correct = correct;
        this.wrongs = new ArrayList<>();
        this.wrongs.add(wrong1);
        this.wrongs.add(wrong2);
        this.wrongs.add(wrong3);
    }

    // Creates 10 sample FOSS questions to test on
    public static ArrayList<Question> sampleQuestions() {
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question("When was Linux made?", "1991", "2005", "1955", "1999"));
        questions.add(new Question("Which web server has the largest web share?", "Apache", "Nginx", "IIS", "Facebook"));
        return questions;
    }

    // Takes input of label and buttons and displays the instance question and answers in the GUI
    public void displayQuestion(Label lbl, Button... buttonsArray) {

        ArrayList<Button> buttons = new ArrayList<>(Arrays.asList(buttonsArray));

        // Sets question label to instance variable "question"
        lbl.setText(this.question);

        // Generates random integer from 0 to 3
        int randButton = rand.nextInt(4);

        // Sets correct button to correct answer
        buttons.get(randButton).setText(this.correct);
        buttons.remove(randButton);
        // The correct button is removed so it is easier to set other buttons to wrong answers without checking
        // whether it is the right answer button or not

        // shuffles "wrongs" ArrayList so buttons aren't in predictable order
        Collections.shuffle(this.wrongs);
        for (Button b : buttons) {
            // Sets button to wrong of the same index (not a problem due to the shuffle)
            b.setText(this.wrongs.get(buttons.indexOf(b)));
        }
    }

    // Checks whether the clicked button is the correct answer or not
    public void checkCorrect(Button b, ArrayList<Question> questions, Label scoreLabel, Label correctLabel) {
        // If correct, increments "score" and "questionsCorrect" and changes Score label
        if (this.correct.equals(b.getText())) {
            score += 10;
            scoreLabel.setText("Score: " + score);
            questionsCorrect += 1;
        }

        // Checks if all questions have ben used; if yes, calls "finished" to show score and exit
        if (questions.size() == questionCount) {
            Controller.finished(score, questionsCorrect);
        }
        // If the program has reached this far, means that there more questions
        // Increments "questionCount" and "questionIndex" to keep track of the current question
        // Changes current question label
        questionCount += 1;
        correctLabel.setText("Question " + questionCount);
        questionIndex += 1;
    }
}
