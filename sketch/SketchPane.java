package sketch;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
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

class SketchPane extends Pane {
		double startX;
		double startY;

		public SketchPane() {

			this.setOnMousePressed(e -> {

				Line dot = new Line(e.getX(), e.getY(), e.getX(), e.getY());

				this.getChildren().add(dot);
				startX = e.getX();
				startY = e.getY();

			});

			this.setOnMouseDragged(e -> {
				Line line = new Line(startX, startY, e.getX(), e.getY());
				if (SketchApp.color == "BLACK")
					line.setStroke(Color.BLACK);
				else if (SketchApp.color == "RED")
					line.setStroke(Color.RED);
				else if (SketchApp.color == "BLUE")
					line.setStroke(Color.BLUE);
				else if (SketchApp.color == "GREEN")
					line.setStroke(Color.GREEN);
				else if (SketchApp.color == "YELLOW")
					line.setStroke(Color.YELLOW);

				startX = e.getX();
				startY = e.getY();
				this.getChildren().add(line);
			});

			Rectangle clipRect = new Rectangle(this.getWidth(), this.getHeight());

			clipRect.heightProperty().bind(this.heightProperty());
			clipRect.widthProperty().bind(this.widthProperty());

			this.setClip(clipRect);

		}

		public void viewListOfNodes() {
			BorderPane borderPane = new BorderPane();
			TextArea ta = new TextArea();
			ta.setEditable(false);
			borderPane.setCenter(ta);
			Stage dialog = new Stage();
			dialog.setTitle("Line End Points");
			Scene scene = new Scene(borderPane, 600, 400);
			dialog.setScene(scene);

			int i = 0;
			for (Node n : this.getChildren()) {
				Line line = (Line) n;
				i++;
				ta.appendText(String.format("%5d: (%.0f, %.0f) - (%.0f, %.0f)\n", i, line.getStartX(), line.getStartY(),
						line.getEndX(), line.getEndY()));

			}
			dialog.initOwner(SketchApp.primaryStage);
			dialog.initModality(Modality.APPLICATION_MODAL);
			dialog.showAndWait();

		}

	}