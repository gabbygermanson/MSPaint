
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
* This class represents Microsoft Paint but in a simple version called CSPaint
* @author 903400815
* @version 1.0
*/
public class CSPaint extends Application {
    private int count = 0;
    private Label shapeLabel;
    private static Color colorWanted = Color.BLACK;
    private static int oneTime = 0;

    /**
    * This start method builds everyting from nodes to the stage, and runs it
    * @param primaryStage takes in a stage to set the title and run the program
    */
    @Override
    public void start(Stage primaryStage) {

        BorderPane pane = new BorderPane();

        Pane p = new Pane();
        p.setStyle("-fx-background-color: white");
        p.setPrefSize(650, 450);
        pane.setCenter(p);

        VBox vBox = new VBox(10);
        vBox.setStyle("-fx-background-color: lightgrey");
        RadioButton draw = new RadioButton("Draw");
        RadioButton erase = new RadioButton("Erase");
        RadioButton makeCircle = new RadioButton("Circle");
        TextField colorSelect = new TextField();
        Label info = new Label("Type in color & press enter");
        Button clear = new Button("Clear");
        Button block = new Button("Artist's Block?");
        vBox.getChildren().addAll(draw, erase, makeCircle, colorSelect, info, clear, block);
        pane.setLeft(vBox);

        ToggleGroup group = new ToggleGroup();
        draw.setToggleGroup(group);
        erase.setToggleGroup(group);
        makeCircle.setToggleGroup(group);


        HBox hBox = new HBox(10);
        Label coords = new Label("( , )");
        shapeLabel = new Label("Number of shape: 0");
        hBox.getChildren().addAll(coords, shapeLabel);
        pane.setBottom(hBox);

        p.setOnMouseMoved(h -> {
                coords.setText("(" + h.getX() + ", " + h.getY() + ")");
            });


        draw.setOnAction(a -> {
                if (draw.isSelected()) {
                    p.setOnMouseDragged(b -> {
                            Circle drawing = new Circle(2, colorWanted);
                            drawing.setCenterX(b.getX());
                            drawing.setCenterY(b.getY());
                            p.getChildren().add(drawing);
                            coords.setText("(" + b.getX() + ", " + b.getY() + ")");
                        });
                    p.setOnMouseClicked(e -> { });
                }
            });

        erase.setOnAction(c -> {
                if (erase.isSelected()) {
                    p.setOnMouseDragged(d -> {
                            Circle erasure = new Circle(10, Color.WHITE);
                            erasure.setCenterX(d.getX());
                            erasure.setCenterY(d.getY());
                            p.getChildren().add(erasure);
                            coords.setText("(" + d.getX() + ", " + d.getY() + ")");
                        });
                    p.setOnMouseClicked(e -> { });
                }
            });

        makeCircle.setOnAction(e -> {
                if (makeCircle.isSelected()) {
                    p.setOnMouseDragged(ev -> { });
                    p.setOnMouseClicked(f -> {
                            Circle circle = new Circle(15, colorWanted);
                            circle.setCenterX(f.getX());
                            circle.setCenterY(f.getY());
                            p.getChildren().add(circle);
                            incrCounter();
                        });
                }
            });

        colorSelect.setOnAction(g -> { //color has different setOnAction waiting for enter
                try {
                    String string = colorSelect.getText();
                    colorWanted = Color.valueOf(string);
                } catch (RuntimeException re) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Invalid Color");
                    alert.setContentText("Invalid color entered!");

                    alert.showAndWait();
                }
            });

        clear.setOnAction(m -> {
                Rectangle rec = new Rectangle(650, 450, Color.WHITE);
                p.getChildren().add(rec);
            });

        block.setOnAction(n -> {
                if (oneTime == 0) {
                    oneTime++;
                    Image image = new Image("hamster.jpg");
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(100);
                    imageView.setFitWidth(133);
                    Button again = new Button("Again?");
                    vBox.getChildren().addAll(imageView, again);

                    again.setOnAction(o -> {
                            Image image2 = new Image("yougotit.jpg");
                            imageView.setImage(image2);
                        });
                }
            });

        Scene scene = new Scene(pane);
        primaryStage.setTitle("CSPaint");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    private void incrCounter() {
        count++;
        shapeLabel.setText("Number of shape: " + count);
    }

}