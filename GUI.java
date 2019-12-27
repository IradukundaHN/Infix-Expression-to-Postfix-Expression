import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application {

	public static void main(String [] args)
	{
		launch(args);
	}
	Label infixExpressionLabel;
	Label postfixExpressionLabel1;
	
	RadioButton infixToPostfixbutton;
	RadioButton postfixToInfixbutton;
	
	TextField infixToPostfixField;
	TextField postfixToInfixField;
	
	Button convertButton;
	
	Label postfixExpressionLabel2;
	Label answerLabel;
	
	Button evaluateButton;
	
	TextField evaluatedField;
	
	Button exitButton;
	
	public void start(Stage stage) {
		
		infixExpressionLabel = new Label("Infix Expression:");
		postfixExpressionLabel1 = new Label("\t\t    Postfix Expression:");
		infixToPostfixbutton = new RadioButton("Infix to Postfix");
		
		HBox conversion1 = new HBox(10);
		conversion1.getChildren().addAll(infixToPostfixbutton,infixExpressionLabel,postfixExpressionLabel1);
		
		postfixToInfixbutton = new RadioButton("Postfix to Infix");
		
		infixToPostfixField = new TextField();
		postfixToInfixField = new TextField();
				
		HBox conversion2 = new HBox(10);
		conversion2.getChildren().addAll(postfixToInfixbutton,infixToPostfixField,postfixToInfixField);
		
		convertButton = new Button("Convert");
		convertButton.setDisable(true);
		
		VBox notationConversion = new VBox();
		notationConversion.getChildren().addAll(conversion1,conversion2,convertButton);
		TitledPane conversion = new TitledPane("Notation Conversion",notationConversion);
		
		postfixExpressionLabel2 = new Label("Postfix Expression:");
		answerLabel = new Label("\t\tAnswer:");
		answerLabel.setVisible(false);
		
		evaluateButton = new Button("Evaluate");
		evaluateButton.setAlignment(Pos.BASELINE_RIGHT);
		
		evaluatedField = new TextField();
		
		HBox pane1 = new HBox();
		pane1.getChildren().addAll(postfixExpressionLabel2,answerLabel);
		
		HBox pane = new HBox();
		pane.getChildren().addAll(evaluatedField,evaluateButton);
		
		VBox notationEvaluation = new VBox();
		notationEvaluation.getChildren().addAll(pane1,pane);
		TitledPane evaluation = new TitledPane("Notation Evaluation", notationEvaluation);
		
		exitButton = new Button("Exit");
		exitButton.setAlignment(Pos.CENTER);
		
		VBox mainPane = new VBox();
		mainPane.setPadding(new Insets(30,50,30,50));
		mainPane.setAlignment(Pos.CENTER);
		mainPane.getChildren().addAll(conversion,evaluation,exitButton);
		
		infixToPostfixbutton.setOnAction(new ButtonHandler());
		postfixToInfixbutton.setOnAction(new ButtonHandler());
		convertButton.setOnAction(new ButtonHandler());
		evaluateButton.setOnAction(new ButtonHandler());
		exitButton.setOnAction(new ButtonHandler());
		
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(mainPane);
		
		Scene scene = new Scene(borderPane);
		stage.setTitle("Notation Utility");
		stage.setScene(scene);
		stage.show();
	}
	
	public class ButtonHandler implements EventHandler<ActionEvent>
	{
		String IToP="";
		String PToI="";
		
		@Override
		public void handle(ActionEvent event) 
		{
			Object source = event.getSource();
			
			if(source==infixToPostfixbutton)
			{
				postfixToInfixField.setVisible(false);
				postfixExpressionLabel1.setVisible(false);
				postfixToInfixbutton.setSelected(false);
				infixToPostfixField.setVisible(true);
				infixExpressionLabel.setVisible(true);
				convertButton.setDisable(false);
				answerLabel.setVisible(false);
				infixToPostfixField.clear();
				evaluatedField.clear();
			}
			if(source==postfixToInfixbutton)
			{
				infixToPostfixField.setVisible(false);
				infixExpressionLabel.setVisible(false);
				infixToPostfixbutton.setSelected(false);
				postfixToInfixField.setVisible(true);
				postfixExpressionLabel1.setVisible(true);
				convertButton.setDisable(false);
				answerLabel.setVisible(false);
				postfixToInfixField.clear();
				evaluatedField.clear();
			}
			if(source==convertButton)
			{
				if(infixToPostfixbutton.isSelected()&&(!infixToPostfixField.getText().trim().isEmpty()))
				{
					try
					{
						String s=infixToPostfixField.getText();
						IToP = Notation.convertInfixToPostfix(s);
						infixExpressionLabel.setVisible(true);
						postfixExpressionLabel1.setVisible(true);
						infixToPostfixField.setText(s);
						postfixToInfixField.setText(IToP);
						infixToPostfixField.setVisible(true);
						postfixToInfixField.setVisible(true);
						answerLabel.setVisible(false);
					} catch(InvalidNotationFormatException | QueueOverflowException | StackOverflowException | StackUnderflowException | QueueUnderflowException exception)
					{
						JOptionPane.showMessageDialog(null, exception.getMessage());
					}
					evaluatedField.setText(IToP);
				}
				if(postfixToInfixbutton.isSelected()&&(!postfixToInfixField.getText().trim().isEmpty()))
				{
					String s="";
					try
					{
						s=postfixToInfixField.getText();
						PToI = Notation.convertPostfixToInfix(s);
						postfixExpressionLabel1.setVisible(true);
						infixExpressionLabel.setVisible(true);
						postfixToInfixField.setText(s);
						infixToPostfixField.setText(PToI);
						postfixToInfixField.setVisible(true);
						infixToPostfixField.setVisible(true);
						answerLabel.setVisible(false);
					} catch(InvalidNotationFormatException | QueueOverflowException | StackOverflowException | StackUnderflowException exception)
					{
						JOptionPane.showMessageDialog(null, exception.getMessage());
					}
					evaluatedField.setText(s);
				}	
			}
			if(source==evaluateButton)
			{
				try
				{
					double s = 0.0;
					if(!evaluatedField.getText().trim().isEmpty())
					{
						s = (Notation.evaluatePostfixExpression(evaluatedField.getText()));
						answerLabel.setText("\tAnswer: "+s);
						answerLabel.setVisible(true);
					}
				}
				catch(InvalidNotationFormatException e1)
				{
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
			if(source==exitButton)
			{
				Platform.exit();
				System.exit(0);
			}
			
		}
		
	}
}
