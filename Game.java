import java.util.Random;

public class Game
{
  int GAME_SIZE = 4 ;
  Random randomGenerator = new Random();
  int[][] board = new int[ GAME_SIZE ][ GAME_SIZE] ;
  int gameScore ;

  public Game( )
  {
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

    gameScore = 0 ;
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
  }

  public void getScore( )
  {
    System.out.println( "Current Score is " + gameScore ) ;
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
          board[ i ][ j - 1 ] = 0;
        }
      }
    }
  }

  private void updateScore(int addition)
  {
    gameScore += addition ;
  }

  private void randomNewNumber( ) {
    boolean needsUpdating = true ;
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


  public static void main ( String[ ] args )
  {
    Game newGame = new Game( ) ;
    newGame.displayBoard( );
    newGame.getScore( );
    newGame.moveLeft( ) ;
    newGame.displayBoard( );
    newGame.moveRight( ) ;
    newGame.displayBoard( );
    newGame.moveLeft( ) ;
    newGame.displayBoard( );
    newGame.moveRight( ) ;
    newGame.displayBoard( );
  }
}