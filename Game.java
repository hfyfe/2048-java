import java.util.Random;
import java.util.Arrays;

public class Game
{
  final int GAME_SIZE = 4 ;
  Random randomGenerator = new Random();
  public int[][] board = new int[ GAME_SIZE ][ GAME_SIZE] ;
  private int gameScore ;

  public Game( )
  {
    resetBoard( ) ;
  }

  public void displayBoard( )
  {
    System.out.println("---------") ;
    for ( int i = 0 ; i < GAME_SIZE ; i++ ) {
      System.out.print("|") ;
      for ( int j = 0 ; j < GAME_SIZE ; j++ ) {
        System.out.print( board[ i ][ j ] + "|" ) ;
      }
      System.out.println( ) ;
    }
    System.out.println( "---------" ) ;
    System.out.println( ) ;
  }

  public int getScore( )
  {
    System.out.println( "Current Score is " + gameScore ) ;
    return gameScore ;
  }

  public void moveUp()
  {
    shiftUp( ) ;
    combineUp( ) ;
    shiftUp( ) ;
    randomNewNumber( ) ;
  }

  private void shiftUp( )
  {
    for ( int j = 0 ; j < GAME_SIZE ; j++ )
    {
      for ( int i = 0 ; i < GAME_SIZE - 1  ; i++ )
      {
        for ( int k = i ; k < GAME_SIZE - 1; k++ )
        {
          if ( board[ k ][ j ] == 0 && board[ k+1 ][ j ] != 0 )
          {
            board[ k ][ j ]  = board[ k+1 ][ j ] ;
            board[ k+1 ][ j ] = 0;
          }
        }
      }
    }
  }

  private void combineUp( )
  {
    for ( int i = 0 ; i < GAME_SIZE - 1 ; i++ )
    {
      for ( int j = 0 ; j < GAME_SIZE ; j++ )
      {
        if ( board[ i ][ j ] == board[ i+1 ][ j ] )
        {
          board[ i ][ j ]  += board[ i+1 ][ j ] ;
          updateScore(board[ i ][ j ]) ;
          board[ i+1 ][ j ] = 0;
        }
      }
    }
  }

  public void moveDown( )
  {
    shiftDown( ) ;
    combineDown( ) ;
    shiftDown( ) ;
    randomNewNumber( ) ;
  }

  private void shiftDown ( )
  {
    for ( int j = 0 ; j < GAME_SIZE ; j++ )
    {
      for ( int i = GAME_SIZE - 1 ; i > 0 ; i-- )
      {
        for ( int k = i ; k > 0  ; k-- )
        {
          if ( board[ k ][ j ] == 0 && board[ k-1 ][ j ] != 0 )
          {
            board[ k ][ j ]  = board[ k-1 ][ j ] ;
            board[ k-1 ][ j ] = 0;
          }
        }
      }
    }
  }

  private void combineDown ( )
  {
    for ( int i = GAME_SIZE - 1 ; i > 0 ; i-- )
    {
      for ( int j = 0 ; j < GAME_SIZE ; j++ )
      {
        if ( board[ i ][ j ] == board[ i-1 ][ j ] )
        {
          board[ i ][ j ]  += board[ i-1 ][ j ] ;
          updateScore(board[ i ][ j ]) ;
          board[ i-1 ][ j ] = 0;
        }
      }
    }
  }

  public void moveLeft( )
  {
    shiftLeft( ) ;
    combineLeft( ) ;
    shiftLeft( ) ;
    randomNewNumber( ) ;
  }

  private void shiftLeft( )
  {
    for ( int i = 0 ; i < GAME_SIZE ; i++ )
    {
      for ( int j = 0 ; j < GAME_SIZE - 1 ; j++ )
      {
        for ( int k = j ; k < GAME_SIZE - 1  ; k++ )
        {
          if ( board[ i ][ k ] == 0 && board[ i ][ k + 1 ] != 0 )
          {
            board[ i ][ k ]  = board[ i ][ k + 1 ] ;
            board[ i ][ k + 1 ] = 0;
          }
        }
      }
    }
  }

  private void combineLeft( )
  {
    for ( int i = 0 ; i < GAME_SIZE ; i++ )
    {
      for ( int j = 0 ; j < GAME_SIZE - 1 ; j++ )
      {
        if ( board[ i ][ j ] == board[ i ][ j + 1 ] )
        {
          board[ i ][ j ]  += board[ i ][ j + 1 ] ;
          updateScore(board[ i ][ j ]) ;
          board[ i ][ j + 1 ] = 0;
        }
      }
    }
  }

  public void moveRight( )
  {
    shiftRight( ) ;
    combineRight( ) ;
    shiftRight( ) ;
    randomNewNumber( ) ;
  }

  private void shiftRight( )
  {
    for ( int i = 0 ; i < GAME_SIZE ; i++ )
    {
      for ( int j = GAME_SIZE - 1 ; j > 0 ; j-- )
      {
        for ( int k = j ; k > 0 ; k-- )
        {
          if ( board[ i ][ k ] == 0 && board[ i ][ k - 1 ] != 0 )
          {
            board[ i ][ k ]  = board[ i ][ k - 1 ] ;
            board[ i ][ k - 1 ] = 0;
          }
        }
      }
    }
  }

  private void combineRight( )
  {
    for ( int i = 0 ; i < GAME_SIZE ; i++ )
    {
      for ( int j = GAME_SIZE - 1 ; j  > 0  ; j-- )
      {
        if ( board[ i ][ j ] == board[ i ][ j - 1 ] )
        {
          board[ i ][ j ]  += board[ i ][ j - 1 ] ;
          updateScore(board[ i ][ j ]) ;
          board[ i ][ j - 1 ] = 0;
        }
      }
    }
  }

  private void updateScore(int addition)
  {
    gameScore += addition ;
  }

  private void randomNewNumber( )
  {
    boolean needsUpdating = emptySpaces() ;

    while ( needsUpdating )
    {
      int randomInt = randomGenerator.nextInt(15) ;
      if ( board[ randomInt / 4 ] [ randomInt % 4 ] == 0 )
      {
        board[ randomInt / 4 ] [ randomInt % 4 ] = 2 ;
        needsUpdating = false ;
      }
    }
  }

  private boolean emptySpaces() {

    for ( int i = 0 ; i < GAME_SIZE ; i++ )
    {
      for ( int j = 0 ; j < GAME_SIZE ; j++ )
      {
        if ( board[ i ][ j ] == 0)
        {
          return true ;
        }
      }
    }
    return false ;
  }

  public boolean checkBoard( )
  {
    // System.out.println( "Start of Check Method");
    int[ ][ ] boardCopy = deepCopy( board ) ;
    int scoreCopy = gameScore ;
    boolean noMove = true ;

    // System.out.println( "Pre Check ");
    moveUp( ) ;
    noMove = Arrays.deepEquals( board , boardCopy );
    // System.out.println( "Moved Up ; noMove  = " + Boolean.toString(noMove)  );
    moveDown( ) ;
    noMove = Arrays.deepEquals( board , boardCopy );
    moveLeft( ) ;
    noMove = Arrays.deepEquals( board , boardCopy );
    moveRight( ) ;
    noMove = Arrays.deepEquals( board , boardCopy );

    board = boardCopy ;
    gameScore = scoreCopy ;
    return noMove ;
  }

  private static int[][] deepCopy(int[][] original)
  {
    if (original == null) {
        return null;
    }

    final int[ ][ ] result = new int[ original.length ][ ];
    for ( int i = 0 ; i < original.length ; i++ ) {
        result[ i ] = Arrays.copyOf( original[ i ] , original[ i ].length );

    }
    return result;
  }

  public void resetBoard()
  {
    gameScore = 0 ;
    int randomInt = randomGenerator.nextInt(15) ;
    for ( int i = 0 ; i < GAME_SIZE ; i++ )
    {
      for ( int j = 0 ; j < GAME_SIZE ; j++ )
      {
        if ( i * 4 + j == randomInt) {
          board[ i ][ j ] = 2 ;
        }
        else {
          board[ i ][ j ] = 0 ;
        }
      }
    }
  }

  public static void main ( String[ ] args )
  {
    Game newGame = new Game( ) ;
    newGame.displayBoard( );
    newGame.getScore( );
    newGame.moveUp( ) ;
    newGame.displayBoard( );
    newGame.moveDown( ) ;
    newGame.displayBoard( );
    newGame.moveUp( ) ;
    newGame.displayBoard( );
    newGame.moveDown( ) ;
    newGame.displayBoard( );
    System.out.println( newGame.checkBoard( ) ) ;
    newGame.displayBoard( );
  }
}