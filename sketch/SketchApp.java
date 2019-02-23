package sketch;

import java.io.File;
import java.io.FileWriter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.shape.Line;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.scene.shape.Rectangle;

public class SketchApp extends Application {
	static Stage primaryStage;
	static SketchPane sp;
	static String color = "BLACK";

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		sp = new SketchPane();
		ControlsPane controlPane = new ControlsPane(sp);
		ButtonPane topButtonPane = new ButtonPane();
		BorderPane borderPane = new BorderPane();

		borderPane.setCenter(sp);
		borderPane.setBottom(controlPane);
		borderPane.setTop(topButtonPane);

		Scene scene = new Scene(borderPane, 600, 400);
		primaryStage.setTitle("Sketch App");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	static void clear() {
		sp.getChildren().clear();
	}

	public static void main(String[] args) {
		launch(args);
	}

	class ButtonPane extends HBox {
		public ButtonPane() {
			HBox hBox = new HBox(35);

			hBox.setAlignment(Pos.CENTER);
			getButtons(hBox, sp);
			this.getChildren().add(hBox);
			this.setAlignment(Pos.CENTER);

		}

		public void getButtons(HBox hBox, SketchPane sp) {
			Button saveBtn = new Button("SAVE");
			Button openBtn = new Button("OPEN");
			Button clearBtn = new Button("CLEAR");
			Button viewLines = new Button("View Lines");
			viewLines.setOnAction(e -> sp.viewListOfNodes());
			registerButtons(saveBtn, openBtn, clearBtn);
			hBox.getChildren().addAll(saveBtn, openBtn, clearBtn, viewLines);
		}

		public void registerButtons(Button saveBtn, Button openBtn, Button clearBtn) {

			saveBtn.setOnAction(e -> {
				saveFile();
			});
			openBtn.setOnAction(e -> {
				openFile();

			});
			clearBtn.setOnAction(e -> {
				SketchApp.clear();

			});

		}

		public void saveFile() {

			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Save Sketch");

			File file = fileChooser.showSaveDialog(SketchApp.primaryStage);
			String filePath = file.toString();
			try {
				File fileSave = new File(filePath);
				fileSave.createNewFile();
				System.out.println("File saved to" + filePath);

			} catch (Exception e) {
				System.out.println("Could not save file");
			}

			writeToFile(filePath);
		}

		public void writeToFile(String filePath) {
			TextArea sketchRec = new TextArea();
			int i = 0;
			for (Node n : SketchApp.sp.getChildren()) {
				Line line = (Line) n;
				i++;
				sketchRec.appendText(String.format("%5d: (%.0f, %.0f) - (%.0f, %.0f)\n", i, line.getStartX(),
						line.getStartY(), line.getEndX(), line.getEndY()));

			}
			String sketchData = sketchRec.getText();

			try {
				FileWriter writeFile = new FileWriter(filePath);

				writeFile.write(sketchData);
				writeFile.close();

			} catch (Exception eee) {
				System.out.println("Could not write to file");
			}

		}

		public void openFile() {

			FileChooser fileChooser = new FileChooser();
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
			fileChooser.getExtensionFilters().add(extFilter);
			fileChooser.setTitle("Open Sketch File");
			fileChooser.showOpenDialog(SketchApp.primaryStage);

		}

	}

	
}
