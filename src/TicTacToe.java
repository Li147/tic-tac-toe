import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.w3c.dom.css.Rect;

public class TicTacToe extends Application {

    Stage primaryStage;
    GridPane root = new GridPane();

    private int currentPlayer = 5;
    private Tile[][] board = new Tile[3][3];
    private Label message = new Label("Welcome to Tic Tac Toe, click in a box to start.");

    @Override
    public void start(Stage primaryStage) {

        Scene scene = new Scene(createBoard(), 450, 300);

        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private Parent createBoard(){

        int pos = 0;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                board[i][j] = new Tile();
                root.add(board[i][j], j, i);
            }
        }

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(root);
        borderPane.setBottom(message);

        return borderPane;
    }

    public boolean isBoardFull(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (board[i][j].getPlayer() == 1 || board[i][j].getPlayer() == 5){
                    return false;
                }
            }
        }
        return true;
    }



    public boolean hasWon(int x){
        // checking rows
        for (int i = 0; i < 3 ; i++){
            if(board[i][0].getPlayer() == x && board[i][1].getPlayer() == x && board[i][2].getPlayer() == x){
                return true;
            }
        }

        // checking columns
        for (int i = 0; i < 3 ; i++){
            if(board[0][i].getPlayer() == x && board[1][i].getPlayer() == x && board[2][i].getPlayer() == x){
                return true;
            }
        }

        // checking diagonal
        if (board[0][0].getPlayer() == x && board[1][1].getPlayer() == x && board[2][2].getPlayer() == x){
            return true;
        }

        // checking diagonal 2
        if (board[0][2].getPlayer() == x && board[1][1].getPlayer() == x && board[2][0].getPlayer() == x){
            return true;
        }

        return false;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(char currentPlayer) {
        this.currentPlayer = currentPlayer;
    }


    public static void main(String[] args) {
        launch(args);
    }



    public class Tile extends Pane {
        private int player = 0;

        public Tile() {

            setStyle("-fx-border-color : black");
            this.setPrefSize(300, 300);
            this.setOnMouseClicked(e -> handleClick());
        }

        public void handleClick(){
            if (player == 0 && currentPlayer != 0){
                setPlayer(currentPlayer);

                if (hasWon(1)){
                    message.setText("Team X has won!");
                    currentPlayer = 0;
                } else if (hasWon(5)){
                    message.setText("Team O has won!");
                    currentPlayer = 0;
                } else if (isBoardFull()){
                    message.setText("It's a draw!");
                    currentPlayer = 0;
                } else {
                    currentPlayer = (currentPlayer == 1) ? 5 : 1;
                    message.setText(Integer.toString(currentPlayer) + " must play.");
                }

            }


        }


        public int getPlayer(){
            return player;
        }

        public void setPlayer(int x){
            player = x;

            if(player == 1){

                Ellipse blue = new Ellipse(this.getWidth()/2, this.getHeight()/2,
                        30,30);
                blue.setFill(Color.BLUE);

                getChildren().add(blue);
            }

            if(player == 5){
                Ellipse ellipse = new Ellipse(this.getWidth()/2, this.getHeight()/2,
                        30,30);
                ellipse.setFill(Color.RED);

                getChildren().add(ellipse);
            }


        }



    }
}
