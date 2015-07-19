/**
 * Fifteen.java
 *
 * Problem 6
 *
 *
 * This program creates a GUI to play the popular
 * Fifteen puzzle game. 
 *
 * @author Ashkan Bigdeli
 * @email ashbigdeli@gmail.com
 */


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;



public class Fifteen extends JFrame implements ActionListener {

    //class variables
    private JPanel tilesPanel;
    private JPanel controls;
    private JButton tileArray [] [];
    private JButton start;
    private boolean playing;
    private JButton shuffle;
    private JButton exit;
    private JButton blank;
    private int row = 4;
    private int col = 4;

    // Decrease this number to decrease the difficulty of the game. 
    // 500 is very difficult. 100 is very easy. 
    private int difficulty = 500;

    
    public static void main ( String [] args ) {
        new Fifteen();
    
     }

    // Constructor 
    public Fifteen( ) {
      setTitle( "Fifteen Puzzle" );
      setSize( 400, 400 );
      setLocation( 200, 200 );
      setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

      tileArray = new JButton[ 4 ] [ 4 ];
      tilesPanel = new JPanel( new GridLayout ( 4, 4 ) );

      int tileNum = 1;
      for ( int i = 0; i < row; i++ ) {
          for (int  j = 0; j < col; j++ ) {
          
              // Fill 2d array with Jbuttons and name them, add listener.
              tileArray[ i ][ j ] = new JButton( Integer.toString ( tileNum) );
              tileArray[ i ][ j ].addActionListener( this );
              tileNum ++;
              tilesPanel.add( tileArray[ i ][ j ] );
           }
      }

      // set the blank in the array.
      blank = tileArray [ 3 ] [ 3 ];
      blank.setText ( "" );
      add( tilesPanel, BorderLayout.CENTER );
      setVisible( true );

      controls = new JPanel( new GridLayout ( 1, 3 ) );
     
      start = new JButton( "Start" );
      start.addActionListener( this );
      controls.add( start );

      shuffle = new JButton( "Shuffle" );
      shuffle.addActionListener( this );
      controls.add( shuffle );

      exit = new JButton( "Exit" );
      exit.addActionListener( this );
      controls.add( exit );
  
      add( controls, BorderLayout.SOUTH );
      
     }

    /**
     * swapAdjacent
     *
     * This method determines if the button pressed is
     * adjacent to the blank. If it is, the two tiles are swapped. 
     * 
     * @param represents the string of the pressed button.
     */
     public void swapAdjacent ( String s ) {

         // Variables for row and column difference of blank and pressed
         int colDiff;
         int rowDiff;

         // Adjacent button variables
         int pressedRow = 0;
         int pressedCol = 0;
         int blankRow = 0;
         int blankCol= 0;
         
         for ( int i = 0; i < row; i ++ ) {
             for ( int j = 0; j < col; j++ ) {
             
                 // Record index of pressed button. Set temp.
                 if ( tileArray[ i ][ j ].getText( ).equals ( s ) ) {
                     pressedRow = i;
                     pressedCol = j;
                 }
                 else if ( tileArray[ i ][ j ].getText( ).equals( "" ) ) {
                     blankRow = i;
                     blankCol = j;
                 }
              }
         }
         
         // Determine column and row difference. 
         rowDiff = blankRow - pressedRow;
         colDiff = blankCol - pressedCol;

         // If the tile is within 1 of blank and within the same column or the same row, swap the text. 
       if ( ( ( rowDiff < 2 && rowDiff > -2) && ( blankCol == pressedCol ) ) || ( ( colDiff < 2 && colDiff > -2 ) && (blankRow == pressedRow ) ) ) {
         ///if ( ( ( rowDiff == 0 ) && ( blankRow == pressedRow ) ) || ( (colDiff == 0 ) && ( blankCol == pressedCol ) ) ) {
               // Swap the text of the adjacent array elements. 
               tileArray[ pressedRow ][ pressedCol ].setText( ""  );
               tileArray[ blankRow ][ blankCol ].setText( s );
         }
         
     }
     
    /**
     * shuffle
     *
     * This method determines picks a random tileArray location
     * and attempts to swap it. It repeats this "difficulty" number
     * of times to ensure proper shuffling.
     * 
     */
     public void shuffle ( ) {

         // 500 the magic number of times this loop will shuffle. 
         for ( int i = 0; i < difficulty; i++ ) {

             // pick two random integers within our tileArray boundries
             int random1 = (int)( Math.random( ) * row ) ;
             int random2 = (int)( Math.random( ) * col ) ;

             // Get this text of this random picked tile.
             String s = tileArray [ random1 ][ random2 ].getText();
             
             // If the random tile retrieved is not a blank, attempt swap. 
             if ( !s.equals( "" ) ) {
                 swapAdjacent( s );
             }
         } 
     }

    /**
     * checkWin
     *
     * After begin is pressed this method is called on every 
     * tile button press to determine if the puzzle is solved.
     *
     */
     public void checkWin ( ) {
     
         // Text on buttons starts at one, so counter does as well. 
         int tileWin = 1;
         boolean blank = true;
         for ( int i = 0; i < row; i ++ ) {
             for ( int j = 0; j < col; j++ ) { 
                String s = tileArray [ i ][ j ].getText( );
                // This is odd logic, but all I could get to work 
                // when I found a bug, essentially if a blank is found, do not 
                // increment. I tried !s.equals"" and wanted to increment based on this
                // condition, is there something special about handling empty strings
                // in conditionals?
                if ( s.equals ( "" ) ) {
                    blank = false;
                }
                if ( blank ){
                    if ( Integer.parseInt( tileArray[ i ][ j ].getText( ) ) == tileWin ) {
                        // Will only increment if text on buttons is sequential order ( 1 to 15 ).
                        tileWin++;
                    }
                }
             }
          }
         
         if ( tileWin == 16 ) {
             JOptionPane.showMessageDialog ( null, "You Win!" );
             // If the user has won, turn the game off until "Begin" is pressed again.
             playing = false;
         }
     }

     // Implemtation of action listeners
     public void actionPerformed (ActionEvent ae ) {
     
         if ( ae.getActionCommand( ).equals( "Exit" ) ) {
             System.exit ( 0 );
         } else if ( ae.getActionCommand( ).equals( "Shuffle" ) ) {
               shuffle( );
           // A begin feature was added to shuffle the board and determine
           // if checking and tile movement is necessary.
         } else if ( ae.getActionCommand( ).equals ( "Start" ) ) {
             shuffle ( );
             playing  = true;
         } else if ( playing ) {
             swapAdjacent ( ae.getActionCommand( ) );
             checkWin();
         }

     }
}
      
