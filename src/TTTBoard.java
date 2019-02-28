
public class TTTBoard {

    public int[] board;

    public TTTBoard(){
        this.board = new int[]{0,0,0,0,0,0,0,0,0};
    }

    public void clickNaught(int i){
        board[i] = 3;
    }

    public void clickCross(int i){
        board[i] = 5;
    }

    @Override
    public String toString(){
        return(
                board[0] + "    " + board[1] + "    " + board[2] + "\n" +
                        board[3] + "    " + board[4] + "    " + board[5] + "\n" +
                        board[6] + "    " + board[7] + "    " + board[8] + "\n"
        );
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
        return (0);
    }

    // check rows for winner
    private int checkRowWinner() {
        for (int i = 0; i < 7; i += 3) {
            if (board[i] + board[i + 1] + board[i + 2] == 3) {
                return 3;
            } else if (board[i] + board[i + 1] + board[i + 2] == 15) {
                return 5;
            }
        }
        return 0;
    }

    // check columns for winner
    private int checkColumnWinner(){
        for (int i = 0; i < 3; i++) {
            if (board[i] + board[i + 3] + board[i + 6] == 3) {
                return 3;
            } else if (board[i] + board[i + 3] + board[i + 6] == 15) {
                return 5;
            }
        }
        return 0;
    }

    // check columns for winner
    private int checkDiagonalWinner(){
        if(board[0] + board[4] + board[8] == 3 || board[2] + board[4] + board[6] == 3){
            return 3;
        } else if (board[0] + board[4] + board[8] == 15 || board[2] + board[4] + board[6] == 15){
            return 5;
        }
        return 0;
    }


    public static void main(String [] args){
        TTTBoard board = new TTTBoard();
        System.out.println(board);
        System.out.println(board.checkWinner());

    }

}