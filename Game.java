
public class Game {
  int GAME_SIZE = 4 ;
  int[][] board = new int[GAME_SIZE][GAME_SIZE];
  int gameScore ;

  public Game() {
    for (int i = 0 ; i < GAME_SIZE ; i++) {
      for (int j = 0 ; j < GAME_SIZE ; j++) {
        board[i][j] = 0 ;
      }
    }
    gameScore = 0 ;
  }

  public void displayBoard() {
    System.out.println("---------") ;
    for (int i = 0 ; i < GAME_SIZE ; i++) {
      System.out.print("|") ;
      for (int j = 0 ; j < GAME_SIZE ; j++) {
        System.out.print(board[i][j] + "|") ;
      }
      System.out.println() ;
    }
    System.out.println("---------") ;
  }

  public void getScore() {
    System.out.println( "Current Score is " + gameScore ) ;
  }

  public void moveUp() {

  }

  public void moveDown() {

  }

  public void moveLeft() {

  }

  public void moveRight() {

  }

  private void updateScore(int addition) {
    gameScore += addition ;
  }

  public static void main (String[] args) {
    Game newGame = new Game() ;
    newGame.displayBoard();
    newGame.getScore();
  }
}