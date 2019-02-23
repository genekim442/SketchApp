package sketch;




import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;

class ControlsPane extends HBox {
		public ControlsPane(SketchPane sp) {
			setSpacing(0);
			setAlignment(Pos.CENTER);
			Button black = new Button("BLACK");
			Button red = new Button("RED");
			Button blue = new Button("BLUE");
			Button green = new Button("GREEN");
			Button yellow = new Button("YELLOW");
			registerButtons(black, red, blue, green, yellow);

			getChildren().addAll(black, red, blue, green, yellow);
		}

		private void registerButtons(Button black, Button red, Button blue, Button green, Button yellow) {
			black.setOnAction(e -> {
				SketchApp.color = "BLACK";
			});

			red.setOnAction(e -> {
				SketchApp.color = "RED";
			});

			blue.setOnAction(e -> {
				SketchApp.color = "BLUE";
			});

			green.setOnAction(e -> {
				SketchApp.color = "GREEN";
			});

			yellow.setOnAction(e -> {
				SketchApp.color = "YELLOW";
			});

		}
	}