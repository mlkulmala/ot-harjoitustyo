package votingaid.ui;

import java.util.List;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import votingaid.domain.AnswerList;
import votingaid.domain.CandidateLogic;
import votingaid.domain.Question;

/**
 * View for showing a question and the answer options.
 * @author mlkul
 */
public class QuestionView {
    UI ui;
    Question question;
    int listSize;
    Label lbResults;
    CandidateLogic candidateLogic;
    List<AnswerList> results;
    GridPane questionLayout;
    
    /**
     * Construct a view for a question.
     * @param ui parent ui.
     * @param question the question in turn to be answered.
     * @param listSize amount of questions to be answered.
     * @param lbResults label where top results are shown. It is shown 
     * in next QuestionView scene until the new question has been answered.
     * @param candidateLogic class that contains all information of the candidates 
     * and their answers.
     */
    public QuestionView(UI ui, Question question, int listSize, Label lbResults, CandidateLogic candidateLogic) {
        this.ui = ui;
        this.question = question;
        this.listSize = listSize;
        this.lbResults = lbResults;
        this.candidateLogic = candidateLogic;
    }
    
    /**
     * Scene implementing this view.
     * @return Scene
     */
    public Scene getScene() {
        questionLayout = createLayoutForQuestions();
        
        addQuestionNumberLabelToGrid();
        addQuestionLabelToGrid();
        
        addOpinionLabelToGrid("Täysin\neri mieltä", 0);
        addOpinionLabelToGrid("Osittain\neri mieltä", 1);
        addOpinionLabelToGrid("En osaa\nsanoa", 2);
        addOpinionLabelToGrid("Osittain\nsamaa\nmieltä", 3);
        addOpinionLabelToGrid("Täysin\nsamaa\nmieltä", 4);
        
        ToggleGroup rButtons = new ToggleGroup();
        for (int i = 1; i <= 5; i++) {
            addAnswerButtonsToGrid(rButtons, i);
        }
        
        lbResults.setWrapText(true);
        GridPane.setHalignment(lbResults, HPos.CENTER);
        GridPane.setValignment(lbResults, VPos.TOP);

        GridPane buttonView = createLayoutForButtons();
        addNavigationButtonsToGrid(buttonView);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(questionLayout, buttonView);
        vbox.setPrefSize(700, 500);
        vbox.setAlignment(Pos.CENTER);
        
        return new Scene(vbox);
    }
    
    
    public void listAnswers(int answer, Label label) {
        this.results = this.candidateLogic.compareToCandidateAnswers(this.question.getId(), answer);
        String topResults = "";
        for (int i = 0; i <= 2; i++) {
            topResults = topResults + this.results.get(i) + "\n";
        }
        label.setWrapText(true);
        label.setText(topResults);
    }
    
    
    public GridPane createLayoutForQuestions() {
        GridPane gridpane = new GridPane();
        for (int i = 1; i <= 5; i++) {
            gridpane.getColumnConstraints().add(new ColumnConstraints(80));
        } 
        gridpane.getRowConstraints().add(new RowConstraints(80));
        gridpane.getRowConstraints().add(new RowConstraints(150));
        gridpane.getRowConstraints().add(new RowConstraints(50));
        gridpane.getRowConstraints().add(new RowConstraints(30));
        gridpane.setAlignment(Pos.CENTER);
        gridpane.setVgap(10);
        gridpane.setHgap(10);
        gridpane.setPadding(new Insets(10, 20, 20, 10));
        
        return gridpane;
    }
    
    public void addQuestionNumberLabelToGrid() {
        Label qNumber = createLabelForQNumber();
        questionLayout.add(qNumber, 0, 0);
        GridPane.setHalignment(qNumber, HPos.CENTER);
        GridPane.setColumnSpan(qNumber, 5);
    }
    
    public void addQuestionLabelToGrid() {
        Label lbQuestion = createLabelForQuestion();
        questionLayout.add(lbQuestion, 0, 1);
        GridPane.setColumnSpan(lbQuestion, 5);
    }
    
    public void addOpinionLabelToGrid(String labelText, int column) {
        Label opinionLabel = new Label(labelText);
        opinionLabel.setTextAlignment(TextAlignment.CENTER);
        questionLayout.add(opinionLabel, column, 2);
        GridPane.setHalignment(opinionLabel, HPos.CENTER);
    }
    
    public void addAnswerButtonsToGrid(ToggleGroup group, int number) {
        RadioButton answerButton = new RadioButton();
        answerButton.setFocusTraversable(true);
        if (this.question.isAnswered() && this.question.getUserAnswer() == number) {
            answerButton.setSelected(true);
        }
        answerButton.setToggleGroup(group);
        answerButton.setOnAction((event) -> {
            this.question.setUserAnswer(number);
            listAnswers(number, lbResults);
        });
        questionLayout.add(answerButton, number - 1, 3);
        GridPane.setHalignment(answerButton, HPos.CENTER);
    }
    
    public void addNavigationButtonsToGrid(GridPane buttonView) {
        Button prevButton = createButtonForPreviousQuestion();
        Button nextButton = createButtonForNextQuestion();
        Button resultsButton = createButtonForShowResults();

        buttonView.add(lbResults, 1, 0);  
        if (this.question.getId() != 1) {
            buttonView.add(prevButton, 0, 0);
        }
        if (this.question.getId() != listSize) {
            buttonView.add(nextButton, 2, 0);
        } else {
            buttonView.add(resultsButton, 2, 0);
        }
        
        GridPane.setHalignment(prevButton, HPos.LEFT);
        GridPane.setValignment(prevButton, VPos.TOP);
        GridPane.setHalignment(nextButton, HPos.RIGHT);
        GridPane.setValignment(nextButton, VPos.TOP);
        GridPane.setHalignment(resultsButton, HPos.RIGHT);
        GridPane.setValignment(resultsButton, VPos.TOP);
    }
    
    public GridPane createLayoutForButtons() {
        GridPane buttonLayout = new GridPane();
        buttonLayout.getColumnConstraints().add(new ColumnConstraints(120));
        buttonLayout.getColumnConstraints().add(new ColumnConstraints(120));
        buttonLayout.getColumnConstraints().add(new ColumnConstraints(120));
        buttonLayout.getRowConstraints().add(new RowConstraints(100));
        buttonLayout.setAlignment(Pos.TOP_CENTER);
        return buttonLayout;
    }
    
    public Label createLabelForQNumber() {
        Label qNumber = new Label(this.question.getId() + "/" +  listSize);  
        qNumber.setTextAlignment(TextAlignment.CENTER); 
        qNumber.setAlignment(Pos.CENTER);
        qNumber.setPrefHeight(15);
        qNumber.setPrefWidth(55);
        qNumber.setStyle("-fx-font-weight: bold");
        qNumber.setTextFill(Color.WHITE);
        CornerRadii corner10 = new CornerRadii(10);
        qNumber.setBackground(new Background(new BackgroundFill(Color.DEEPPINK, corner10, Insets.EMPTY)));
        qNumber.setPadding(new Insets(10, 10, 10, 10));  
        
        return qNumber;
    }
    
    public Label createLabelForQuestion() {
        Label lbQuestion = new Label(this.question.getQuestionText());
        lbQuestion.setTextAlignment(TextAlignment.CENTER);
        lbQuestion.setAlignment(Pos.CENTER);
        lbQuestion.setWrapText(true);
        lbQuestion.setMaxWidth(500);
        lbQuestion.setPadding(new Insets(10, 10, 10, 10));
        lbQuestion.setFont(new Font("Arial", 20));
        
        return lbQuestion;
    }
    
    public Button createButtonForPreviousQuestion() {
        Button prevButton = new Button("< Edellinen");
        prevButton.setPrefWidth(80);
        prevButton.setOnAction((event) -> {
            ui.showPreviousQuestion(lbResults);
        });
        return prevButton;
    }
    
    public Button createButtonForNextQuestion() {
        Button nextButton = new Button("Seuraava >");
        nextButton.setPrefWidth(80);
        nextButton.setOnAction((event) -> {
            ui.showNextQuestion(lbResults);
        });
        return nextButton;
    }
    
    public Button createButtonForShowResults() {
        Button resultsButton = new Button("Siirry tuloksiin");
        resultsButton.setPrefWidth(100);
        
        resultsButton.setOnAction((event) -> {
            ui.showFinalResults(0);
        });
        return resultsButton;
    }
    
}
