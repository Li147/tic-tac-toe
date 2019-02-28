import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;

public class TTT extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // setting title of the program
        primaryStage.setTitle("Tic Tac Toe");
        Canvas canvas = new Canvas(600, 600);

        //draw the board
        Color white = Color.WHITE;

        Rectangle[] board = new Rectangle[]{};
        double width = (canvas.getWidth() / 3);
        double height = (canvas.getHeight() / 3);

        Rectangle r0 = new Rectangle(0,0, width,height);
        Rectangle r1 = new Rectangle(200,0,width,height);
        Rectangle r2 = new Rectangle(400,0,width,height);
        Rectangle r3 = new Rectangle(0,200,width,height);
        Rectangle r4 = new Rectangle(200,200,width,height);
        Rectangle r5 = new Rectangle(400,200,width,height);
        Rectangle r6 = new Rectangle(0,400,width,height);
        Rectangle r7 = new Rectangle(200,400,width,height);
        Rectangle r8 = new Rectangle(400,400,width,height);
        board[0] = r0;
        board[1] = r1;
        board[2] = r2;
        board[3] = r3;
        board[4] = r4;
        board[5] = r5;
        board[6] = r6;
        board[7] = r7;
        board[8] = r8;

        Group root = new Group();
        Scene theScene = new Scene(root);

        for (Rectangle x: board){
            x.setFill(white);
            x.setStroke(Color.BLACK);
            root.getChildren().add(x);
        }


        // setting the scene of the program
        primaryStage.setScene(theScene);
        root.getChildren().add(canvas);

        ArrayList<String> input = new ArrayList<>();

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLUE);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        Font theFont = Font.font("Arial", FontWeight.BOLD, 48);
        gc.setFont(theFont);
        gc.fillText("Hello, World!", 60,50);
        gc.strokeText("Hello, World!", 60,50);


        theScene.setOnMouseClicked(
                e -> {
                    for (Rectangle x : board) {
                        if (x.contains(e.getX(), e.getY()) && x.getFill() == Color.WHITE) {
                            x.setFill(Color.RED);
                            root.getChildren().add(new Circle ((x.getX() + 100), (x.getY()) + 100, 20));


                        } else if(x.contains(e.getX(), e.getY()) && x.getFill() == Color.RED) {
                            x.setFill(Color.WHITE);
                        }
                    }
                });

        //Creating the mouse event handler
        primaryStage.show();

    }
}
