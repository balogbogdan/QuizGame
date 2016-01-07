package application;

// This file controls and interacts with all the UI objects
// Contains all event handlers


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;


public class Controller {

    // Declaring all UI objects
    @FXML
    private Label questionLabel;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Label questionNum;

    @FXML
    private Label scoreLabel;

    // variables
    public static ArrayList<Question> questions;
    static double percent;


    @FXML
    void initialize() {  // This gets run once to initialize the event handlers and such

        // Creates 10 samples questions
        questions = Question.sampleQuestions();

        // Checks to make sure there are actually questions in the ArrayList; otherwise alerts and exits
        if (questions.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "There are no questions!").showAndWait();
            Platform.exit();
            System.exit(0);
        }

        // Displays the first question in the GUI
        questions.get(Question.questionIndex).displayQuestion(questionLabel, button1, button2, button3, button4);

        // Event handler for the first button using lambda
        button1.setOnAction((event) -> {
            // Checks to see if user pressed the correct button; changes score
            // Also checks if all questions exhausted; exits if yes
            questions.get(Question.questionIndex).checkCorrect(button1, questions, scoreLabel, questionNum);
            // Displays next question
            questions.get(Question.questionIndex).displayQuestion(questionLabel, button1, button2, button3, button4);
        });

        // Event handler for the second button using lambda
        button2.setOnAction((event) -> {
            questions.get(Question.questionIndex).checkCorrect(button2, questions, scoreLabel, questionNum);
            questions.get(Question.questionIndex).displayQuestion(questionLabel, button1, button2, button3, button4);
        });

        // Event handler for the third button using lambda
        button3.setOnAction((event) -> {
            questions.get(Question.questionIndex).checkCorrect(button3, questions, scoreLabel, questionNum);
            questions.get(Question.questionIndex).displayQuestion(questionLabel, button1, button2, button3, button4);
        });

        // Event handler for the fourth button using lambda
        button4.setOnAction((event) -> {
            questions.get(Question.questionIndex).checkCorrect(button4, questions, scoreLabel, questionNum);
            questions.get(Question.questionIndex).displayQuestion(questionLabel, button1, button2, button3, button4);
        });
    }

    // This method is run when all questions have been answered and displays score
    public static void finished(int score, int questionsCorrect) {
        // Calculates percent value
        if (questionsCorrect == 0) { percent = 0; }
        else { percent = (double)questionsCorrect/(double)questions.size() * 100; }

        // Creates alert; tells score and questions correct
        Alert finish = new Alert(Alert.AlertType.INFORMATION);
        finish.setTitle("You Win!");
        finish.setHeaderText("Score: " + score + " (" + percent + "%)");
        finish.setContentText("Questions Correct: " + questionsCorrect);
        finish.showAndWait();

        // Exits
        Platform.exit();
        System.exit(0);
    }
}
