import java.awt.* ;
import java.awt.event.* ;
import java.awt.Color;
import java.util.concurrent.TimeUnit.* ;
import javax.swing.* ;
// TimeUnit.SECONDS.sleep(1);

public class GameView extends JFrame
        implements KeyListener,
        ActionListener
{
  final int GAME_SIZE = 4 ;
  private Font myFont = new Font( "Serif" , Font.BOLD , 48 ) ;
  private Object[][] colorSettings = {
                                          { 2 , Color.RED } ,
                                          { 4 , Color.YELLOW } ,
                                          { 8 , Color.GREEN } ,
                                          { 16 , Color.BLUE } ,
                                          { 32 , Color.DARK_GRAY } ,
                                          { 64 , Color.ORANGE } ,
                                          { 128 , Color.MAGENTA } ,
                                          { 256 , Color.PINK } ,
                                          { 512 , Color.CYAN } ,
                                          { 1024 , Color.GREEN } ,
                                        } ;

  final int INITIAL_APP_SIZE = 600 ;
  private JPanel newGamePanel, gamePanel ;
  private JLabel[] cells ;
  private JLabel score ;
  private JFrame mainFrame ;
  private JButton newGameButton ;
  private Game newGame ;


  public GameView ( Game game )
  {
    cells = new JLabel[ GAME_SIZE * GAME_SIZE ] ;
    newGame = game ;

    createMainFrame( ) ;
    createNewGamePanel( ) ;
    createGamePanel( ) ;
    mainFrame.add( gamePanel ) ;
    mainFrame.add( newGamePanel ) ;
    mainFrame.setVisible( true ) ;
  }

  private void createNewGamePanel()
  {
    newGamePanel = new JPanel() ;
    newGamePanel.setLayout( new FlowLayout( ) ) ;

    newGameButton =  new JButton( "New Game" ) ;

    newGameButton.addActionListener( new ActionListener( ) {
      public void actionPerformed( ActionEvent e )
        {
          newGame.resetBoard() ;
          updateGameBoard() ;
          gamePanel.requestFocusInWindow( );
        }
    }) ;

    newGamePanel.add( newGameButton ) ;
    score = new JLabel( "CURRENT SCORE: " + Integer.toString( newGame.getScore( ) ) ) ;
    newGamePanel.add( score ) ;
  }

  private void createGamePanel()
  {
    gamePanel = prepareGameBoard( );
    gamePanel.setFocusable( true );
    gamePanel.requestFocusInWindow( );
    gamePanel.addKeyListener( this ) ;
  }

  private void createMainFrame() {
    mainFrame = new JFrame( "2048" );
    mainFrame.setSize( INITIAL_APP_SIZE , INITIAL_APP_SIZE );
    mainFrame.setLayout( new GridLayout( 2 , 1 ) );
  }

  @Override
  public void keyTyped( KeyEvent e )
  {

  }

  @Override
  public void keyPressed( KeyEvent e )
  {
    if ( e.getKeyCode( ) == KeyEvent.VK_RIGHT )
    {
      System.out.println( "Moving Right" ) ;
      newGame.moveRight( ) ;
    }
    if ( e.getKeyCode( ) == KeyEvent.VK_LEFT )
    {
      System.out.println( "Moving Left" ) ;
      newGame.moveLeft( ) ;
    }
    if ( e.getKeyCode( ) == KeyEvent.VK_UP)
    {
      System.out.println( "Moving Up" ) ;
      newGame.moveUp( ) ;
    }
    if ( e.getKeyCode( ) == KeyEvent.VK_DOWN )
    {
      System.out.println( "Moving Down" );
      newGame.moveDown( );
    }
    updateGameBoard( );
    if ( newGame.checkBoard( ) )
    {
      System.out.println( "Failed Check" );
      newGame.resetBoard( ) ;
    }
  }

  @Override
  public void keyReleased(KeyEvent e)
  {

  }

  @Override
  public void actionPerformed(ActionEvent e)
  {

  }

  private JPanel prepareGameBoard()
  {
    JPanel panelLabels = new JPanel( new GridLayout( 4, 4 ) ) ;
    for ( int i = 0 ; i < GAME_SIZE ; i++ )
    {
      for ( int j = 0 ; j < GAME_SIZE ; j++ )
      {
        cells[ 4 * i + j ] = new JLabel( Integer.toString( newGame.board[ i ][ j ] ) , JLabel.CENTER ) ;
        cells[ 4 * i + j ].setFont(myFont) ;
        panelLabels.add(cells[ 4 * i + j ]);
      }
    }
    return panelLabels ;
  }

  private void updateGameBoard()
  {
    for ( int i = 0 ; i < GAME_SIZE ; i++ )
    {
      for ( int j = 0 ; j < GAME_SIZE ; j++ )
      {
        gamePanel.remove(cells[ 4 * i + j ]);
        cells[ 4 * i + j ] = new JLabel( Integer.toString( newGame.board[ i ][ j ] ) , JLabel.CENTER ) ;
        cells[ 4 * i + j ].setFont( myFont ) ;
        cells[ 4 * i + j ] = setColor( cells[ 4 * i + j ],  newGame.board[ i ][ j ] ) ;
        gamePanel.add( cells[ 4 * i + j ] ) ;
      }
    }
    score.setText( "CURRENT SCORE: " + Integer.toString( newGame.getScore( ) ) );
    newGamePanel.revalidate() ;
    gamePanel.revalidate() ;
  }

  private JLabel setColor(JLabel aLabel, Integer value) {
    for ( int i = 0 ; i < colorSettings.length ; i++ )
    {
      if ( colorSettings[i][0] == value )
      {
        aLabel.setOpaque(true);
        aLabel.setBackground((Color)colorSettings[i][1]) ;
      }
    }
    return aLabel ;
  }

  public static void main ( String[] args )
  {
    Game newGame = new Game ( ) ;
    GameView newGameView = new GameView ( newGame ) ;
  }
}
