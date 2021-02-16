package application;

import java.io.File;
import javafx.stage.FileChooser;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BSTsimulator extends Application {

//	private double width = 1800;
//	private double height = 1200;

	private double width = 1230;
	private double height = 740;
	double x = width / 2;
	double y = height / 15;

	private double hGap = 300;
	private Tree pane = new Tree();
	private Tree treeFname = new Tree();
	private Tree treeLname = new Tree();
	public String fileName = "";

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		BorderPane border = new BorderPane();
		HBox hbox = addHBox();
		border.setTop(hbox);
		border.setLeft(addVBox(primaryStage));
		// border.setRight(addVBoxRight());

		pane = new Tree();
		pane.setMaxSize(width, height);
		// pane.setPrefSize(width,height);
		pane.setStyle("-fx-border-color: black");
		ImageView image1 = new ImageView(new Image(getClass().getResourceAsStream("Binary Search Tree.png")));
		image1.setFitHeight(height);
		image1.setFitWidth(width);
		pane.getChildren().add(image1);
		border.setCenter(pane);
		border.setBottom(addBottomPane());
		Scene scene = new Scene(border, width, height);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		primaryStage.show();
		Alert alert = new Alert(Alert.AlertType.INFORMATION, "Binary Search Tree\n\n"
				+ "First Name Tree Button helps to choose file and displays BST\nUse the precedding Buttons to display functions ",
				ButtonType.OK);
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		alert.show();

	}

	public void createTree(String filename) {
		try {

			Scanner scan = new Scanner(new File(filename));

			while (scan.hasNextLine()) {
				String fName = scan.next().trim();
				String lName = scan.next().trim();
				Person p1 = new Person(fName, lName);
				Person p2 = new Person(fName, lName);
				treeFname.addPersonFName(p1);
				treeLname.addPersonLName(p2);
				scan.nextLine();
			}
			treeFname.getChildren().clear();
			treeLname.getChildren().clear();
			pane.setBackground(
			new Background(new BackgroundFill(Color.web("#" + "D7E7FF"), CornerRadii.EMPTY, Insets.EMPTY)));
			pane.displayTree(treeFname.getRootFname(), x, y, hGap, Color.PINK);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void createTreeLastName(String filename) {
		try {

			Scanner scan = new Scanner(new File(filename));

			while (scan.hasNextLine()) {
				String fName = scan.next().trim();
				String lName = scan.next().trim();
				Person p1 = new Person(fName, lName);
				Person p2 = new Person(fName, lName);
				treeFname.addPersonFName(p1);
				treeLname.addPersonLName(p2);
				scan.nextLine();
			}
			treeLname.getChildren().clear();
			treeFname.getChildren().clear();
			pane.setBackground(
			new Background(new BackgroundFill(Color.web("#" + "D7E7FF"), CornerRadii.EMPTY, Insets.EMPTY)));
			pane.displayTreeLastName(treeLname.getlNameRoot(), x, y, hGap, Color.PINK);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public String fileOpener(Stage primaryStage) {
		// String fileName;
		clearPane();
		pane.setBackground(
		new Background(new BackgroundFill(Color.web("#" + "D7E7FF"), CornerRadii.EMPTY, Insets.EMPTY)));
		treeFname.setRootFname(null);
		{
			FileChooser fil_chooser = new FileChooser();

			File file = fil_chooser.showOpenDialog(primaryStage);

			if (file != null) {
			}
			return fileName = file.getAbsolutePath();
		}
	}

	private HBox addHBox() {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setSpacing(10);
		hbox.setStyle("-fx-background-color: #3A78BA ;");
		hbox.setAlignment(Pos.BASELINE_CENTER);
		Text header = new Text("Binary Search Tree");
		header.setFill(Color.BLACK);
		header.setFont(Font.font("Arial", FontWeight.BOLD, 50));
		hbox.getChildren().addAll(header);
		return hbox;
	}

	public Button addToolTip(Button button, String hoverText) {
		Tooltip tt = new Tooltip();
		tt.setText(hoverText);
		tt.setStyle("-fx-font: normal bold 12 Langdon; " + "-fx-base: #AE3522; " + "-fx-text-fill: orange;");
		button.setTooltip(tt);
		return button;
	}

	public Button buttonStyler(Button buttonName) {
		buttonName.setStyle("-fx-background-color: #F8D568; ");
		buttonName.setStyle("-fx-font-weight: bold;");
		buttonName.setPrefSize(120, 20);
		return buttonName;
	}

	public TextField textFieldStyler(TextField tField, String pText) {
		tField.setPrefColumnCount(5);
		tField.setAlignment(Pos.BASELINE_CENTER);
		// tField.setStyle("-fx-background-color: #F8D568;");
		tField.setPromptText(pText);
		return tField;

	}

	private HBox addBottomPane() {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setSpacing(10);
		hbox.setAlignment(Pos.CENTER);
		hbox.setStyle("-fx-background-color: #3A78BA;");
		Text category = new Text("SWEN502 Algorithms_and_Data_Structures");
		category.setFont(Font.font("Arial", FontWeight.BOLD, 10));
		category.setFill(Color.BLACK);
		hbox.getChildren().add(category);
		return hbox;
	}

	public VBox addVBox(Stage primaryStage) {
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10));
		vbox.setSpacing(12);
		vbox.setStyle("-fx-background-color: #000000;");

		Button buttonFnameTree = new Button("First Name Tree");
		buttonStyler(buttonFnameTree);
		addToolTip(buttonFnameTree, "Choose file and display First Name Tree");
		buttonFnameTree.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			fileName = fileOpener(primaryStage);
			pane.setBackground(
					new Background(new BackgroundFill(Color.web("#" + "D7E7FF"), CornerRadii.EMPTY, Insets.EMPTY)));
			createTree(fileName);
		});

		Button buttonLnameTree = new Button("Last Name Tree");
		buttonStyler(buttonLnameTree);
		addToolTip(buttonLnameTree, "Choose file and display Last Name Tree");
		buttonLnameTree.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			fileName = fileOpener(primaryStage);
			pane.setBackground(
					new Background(new BackgroundFill(Color.web("#" + "D7E7FF"), CornerRadii.EMPTY, Insets.EMPTY)));

			createTreeLastName(fileName);
		});

		Button buttonInOrder = new Button("InOrder");
		buttonStyler(buttonInOrder);
		addToolTip(buttonInOrder, "Displays the First Name Tree IN-Order");
		buttonInOrder.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			pane.setBackground(
					new Background(new BackgroundFill(Color.web("#" + "D7E7FF"), CornerRadii.EMPTY, Insets.EMPTY)));
			pane.strgbl = "";
			pane.clearAllDisplay();
			pane.displayTree(treeFname.getRootFname(), x, y, hGap, Color.PINK);
			pane.inOrder(treeFname.getRootFname());
		});

		Button buttonPreOrder = new Button("PreOrder");
		buttonStyler(buttonPreOrder);
		addToolTip(buttonPreOrder, "Displays the First Name Tree in Pre-Order");
		buttonPreOrder.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			pane.setBackground(
					new Background(new BackgroundFill(Color.web("#" + "D7E7FF"), CornerRadii.EMPTY, Insets.EMPTY)));
			pane.strgbl = "";
			pane.clearAllDisplay();
			pane.displayTree(treeFname.getRootFname(), x, y, hGap, Color.PINK);
			pane.preOrder(treeFname.getRootFname());
		});

		Button buttonPostOrder = new Button("PostOrder");
		buttonStyler(buttonPostOrder);
		addToolTip(buttonPostOrder, "Displays the First Name Tree in Post-Order");
		buttonPostOrder.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			pane.setBackground(
					new Background(new BackgroundFill(Color.web("#" + "D7E7FF"), CornerRadii.EMPTY, Insets.EMPTY)));
			pane.strgbl = "";
			pane.clearAllDisplay();
			pane.displayTree(treeFname.getRootFname(), x, y, hGap, Color.PINK);
			pane.postOrder(treeFname.getRootFname());
		});

		Button buttonBreadthFirst = new Button("BreadthFirst");
		buttonStyler(buttonBreadthFirst);
		addToolTip(buttonBreadthFirst, "Displays the First Name Tree in Breadth-First Order");
		buttonBreadthFirst.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			pane.setBackground(
					new Background(new BackgroundFill(Color.web("#" + "D7E7FF"), CornerRadii.EMPTY, Insets.EMPTY)));
			pane.strgbl = "";
			pane.clearAllDisplay();
			pane.displayTree(treeFname.getRootFname(), x, y, hGap, Color.PINK);
			pane.printLevelOrder(treeFname.getRootFname());
		});

		Button buttonSearch = new Button("Search Name");
		buttonStyler(buttonSearch);
		TextField textBox = new TextField();
		textFieldStyler(textBox, "Enter Name");
		addToolTip(buttonSearch, "Enter Fname in the search field above and push Search");
		buttonSearch.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			pane.setBackground(
					new Background(new BackgroundFill(Color.web("#" + "D7E7FF"), CornerRadii.EMPTY, Insets.EMPTY)));
			pane.strgbl = "";
			String fnametobesearched = textBox.getText();
			pane.clearAllDisplay();
			pane.displayTree(treeFname.getRootFname(), x, y, hGap, Color.PINK);
			pane.searchFname(treeFname.getRootFname(), fnametobesearched);
		});

		Button buttonInsertNewNode = new Button("Insert FName");
		addToolTip(buttonInsertNewNode, "Enter new First Name in the Text field above and push Insert Fname");
		TextField textBox1 = new TextField();
		textFieldStyler(textBox1, "Enter Name");
		buttonStyler(buttonInsertNewNode);
		buttonInsertNewNode.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			pane.setBackground(
					new Background(new BackgroundFill(Color.web("#" + "D7E7FF"), CornerRadii.EMPTY, Insets.EMPTY)));
			String fnametobeinserted = textBox1.getText();
			pane.clearAllDisplay();
			Person p1 = new Person(fnametobeinserted, "");
			treeFname.addPersonFName(p1);
			pane.displayTree(treeFname.getRootFname(), x, y, hGap, Color.PINK);
		});

		Button buttonDelete = new Button("Delete FName");
		addToolTip(buttonDelete, "Enter Name to delete in the Text field above and push Delete Fname");
		buttonStyler(buttonDelete);
		TextField textBox2 = new TextField();
		textFieldStyler(textBox2, "Delete Name");
		buttonDelete.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			pane.setBackground(
					new Background(new BackgroundFill(Color.web("#" + "D7E7FF"), CornerRadii.EMPTY, Insets.EMPTY)));
			String fnametobedeleted = textBox2.getText();
			pane.detetePersonFirstname(treeFname.getRootFname(), fnametobedeleted);
			pane.clearAllDisplay();
			pane.displayTree(treeFname.getRootFname(), x, y, hGap, Color.PINK);
		});

//		Button buttonBalance = new Button("Balance Tree");
//		addToolTip(buttonBalance, "method not implemented- work in progress");
//		buttonStyler(buttonBalance);
//		buttonBalance.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> pane.inOrder(p));

		Button buttonHeight = new Button("Tree Dimension");
		buttonStyler(buttonHeight);
		addToolTip(buttonHeight, "Displays height of the Tree");
		buttonHeight.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			pane.setBackground(
					new Background(new BackgroundFill(Color.web("#" + "D7E7FF"), CornerRadii.EMPTY, Insets.EMPTY)));
			pane.strgbl = "";
			pane.clearAllDisplay();
			pane.displayTree(treeFname.getRootFname(), x, y, hGap, Color.PINK);
			pane.displayHeight(treeFname.getRootFname());
		});

		Button buttonLength = new Button("Length Compare");
		buttonStyler(buttonLength);
		TextField textBox3 = new TextField();
		textFieldStyler(textBox3, "Enter Length");
		addToolTip(buttonLength, "Compares name length to given length");
		buttonLength.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			int length = Integer.parseInt(textBox3.getText());
			pane.setBackground(
					new Background(new BackgroundFill(Color.web("#" + "D7E7FF"), CornerRadii.EMPTY, Insets.EMPTY)));
			pane.strgbl = "";
			pane.clearAllDisplay();
			pane.namelength(treeFname.getRootFname(), length);
			pane.displayTree(treeFname.getRootFname(), x, y, hGap, Color.PINK);
		});

		Button buttonClear = new Button("Clear Screen");
		buttonStyler(buttonClear);
		addToolTip(buttonClear, "Clears Tree");
		buttonClear.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> clearPane());

		Button buttonExit = new Button("Exit");
		addToolTip(buttonExit, "Closes Program");
		buttonStyler(buttonExit);
		buttonExit.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> primaryStage.close());

		vbox.getChildren().addAll(buttonFnameTree, buttonInOrder, buttonPreOrder, buttonPostOrder, buttonBreadthFirst,
				textBox1, buttonInsertNewNode, textBox, buttonSearch, buttonHeight, textBox2, buttonDelete, textBox3,
				buttonLength, buttonLnameTree, buttonClear, buttonExit);

		return vbox;
	}

	private Tree clearPane() {
		pane.getChildren().clear();
		// treeFname.getChildren().clear();
		return pane;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

}