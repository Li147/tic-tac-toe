import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class TicTacToe extends Application implements EventHandler<ActionEvent> {

    Stage primaryStage;
    Pane root = new Pane();
    //ArrayList<Tile> board = new ArrayList<>();
    Tile[] board = new Tile[9];

    private Parent createParent(){

        root.setPrefSize(610,610);

        int pos = 0;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                Tile tile = new Tile();
                tile.setTranslateX(j * 200);
                tile.setTranslateY(i * 200);
                root.getChildren().add(tile);
                tile.setPosition(pos);
                board[pos] = tile;
                pos++;
            }
        }
       return root;
    }



    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("ttt");

        Scene scene = new Scene(createParent());
        primaryStage.setScene(scene);

        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(ActionEvent event){
//        if(event.getSource() == Tile){
//
//
//        }
        if (this.checkWinner() == 1){
            Canvas canvas = new Canvas(500,500);
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.setFill(Color.BLUE);
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(2);
            Font theFont = Font.font("Arial", FontWeight.BOLD, 48);
            gc.setFont(theFont);
            gc.fillText("Hello, World!", 60,50);
            gc.strokeText("Hello, World!", 60,50);



            root.getChildren().add(canvas);
            Scene newScene = new Scene(root);
            primaryStage.setScene(newScene);
            primaryStage.show();

        }
    }




    // check winner
    public int checkWinner(){
        if (checkRowWinner() != 0){
            return checkRowWinner();
        } else if(checkColumnWinner() != 0){
            return checkColumnWinner();
        } else if (checkDiagonalWinner() != 0){
            return checkDiagonalWinner();
        }
        return 0;
    }

    // check rows for winner
    private int checkRowWinner() {
        for (int i = 0; i < 7; i += 3) {
            if (board[i].getState() + board[i + 1].getState() + board[i + 2].getState() == 3) {
                return 1;
            } else if (board[i].getState() + board[i + 1].getState() + board[i + 2].getState() == 15) {
                return 5;
            }
        }
        return 0;
    }

    // check columns for winner
    private int checkColumnWinner(){
        for (int i = 0; i < 3; i++) {
            if (board[i].getState() + board[i + 3].getState() + board[i + 6].getState() == 3) {
                return 1;
            } else if (board[i].getState() + board[i + 3].getState() + board[i + 6].getState() == 15) {
                return 5;
            }
        }
        return 0;
    }

    // check columns for winner
    private int checkDiagonalWinner(){
        if(board[0].getState() + board[4].getState() + board[8].getState() == 3 ||
                board[2].getState() + board[4].getState() + board[6].getState() == 3){
            return 1;
        } else if (board[0].getState() + board[4].getState() + board[8].getState() == 15 ||
                board[2].getState() + board[4].getState() + board[6].getState() == 15){
            return 5;
        }
        return 0;
    }



    private class Tile extends StackPane {
        Text text = new Text();
        int state = 0;
        int position;

        public Tile(){
            Rectangle r = new Rectangle(200,200, Color.WHITE);
            r.setStroke(Color.BLACK);
            r.setStrokeWidth(6);

            setAlignment(Pos.CENTER);

            text.setFont(Font.font(60));

            setOnMouseClicked(e ->{
                if (e.getButton() == MouseButton.PRIMARY){
                    text.setText("X");
                    //text.setText(Integer.toString(this.getPosition()));
                    this.state = 1;


                } else if (e.getButton() == MouseButton.SECONDARY){
                    text.setText("O");
                    this.state = 5;
                }
            });

            getChildren().addAll(r, text);
        }

        public void setPosition(int i){
            this.position = i;
        }

        public int getPosition(){
            return this.position;
        }

        public void setState(int s){
            this.state = s;
        }

        public int getState(){
            return this.state;
        }


    }
}
