/**
 * Calculator.java
 *
 * Problem 7
 *
 *
 * This program creates a GUI caclulator.
 *
 * @author Ashkan Bigdeli
 * @email ashbigdeli@gmail.com
 */


// I have implented 2 additional extra option beyond what was required of graduate students. 
// I added positive and negative change, ticker tape and memory functions.
// I attempted to parenthesize expressions, but feel somewhat short. Please ignore these buttons. 
// A note on style, I added some returns after my elses to not crowd the code. 

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.*;



public class Calculator extends JFrame implements ActionListener {

     private JButton buttonArray[ ][ ];
     private String buttonText [ ] [ ];
     private JTextField display;
     private JTextArea tape;
     private JDialog d;
     private JButton tapeOn;
     private JButton tapeOff;
     private boolean inCalc = false;
     private boolean firstPress = true;
     private boolean firstDecimal = true;
     private boolean firstZero = true;
     private boolean parenLock = false;
     private boolean record = false;
     private String operand;
     private String answer;
     private String mS = "0";
     // This temp will represent intial values to be calculated. 
     private double temp;
     // Using Decimal format will give us the ideal calculator look.
     private DecimalFormat df = new DecimalFormat ("#.##########");

    public static void main ( String [] args ) {
        new Calculator( );
    }

    // GUI constructor
     public Calculator( ) {
   
     setTitle ( "Caclulator" );
     setSize ( 400, 400 );
     setLocationRelativeTo(null);
     setResizable(false);
     setLayout ( new  BorderLayout( ) );
     setDefaultCloseOperation ( EXIT_ON_CLOSE );

     display = new JTextField( );
     display.setFont( new Font ( "Times new Roman", Font.BOLD, 24 ) );
     display.setEditable(false);
     display.setPreferredSize( new Dimension ( 400, 50 ) ); 
     display.setHorizontalAlignment(JTextField.RIGHT);
     display.setText( "0" );
     
     add ( display, BorderLayout.NORTH );

     // Create 2 Arrays, one for strings one for Jbuttons
     JPanel buttons = new JPanel ( new GridLayout ( 6, 4 ) );
     buttonText = new String [] [] { { "MC", "MR", "MS", "C", },
                                     { "(",  ")", "√", "*", },
                                     { "7", "8", "9", "/", },
                                     { "4", "5", "6", "+", },
                                     { "1", "2", "3", "-", },
                                     { "0", ".", "=", "+/-" } };

     buttonArray = new JButton [ 6 ][ 4 ];

     // Add string text and listeners to Jbuttons.
     for ( int i = 0; i < 6; i++ ) {
         for ( int j = 0; j < 4; j++ ) {
             // Fill 2d array with Jbuttons and name them, add listener.
             buttonArray[ i ][ j ] = new JButton( buttonText[ i ] [ j ] );
             buttonArray[ i ][ j ].addActionListener( this );
             buttons.add( buttonArray[ i ][ j ] );
         }
     }
     add ( buttons, BorderLayout.CENTER );
 
     //Adding buttons for our Ticker Tape. 
     JPanel j2 = new JPanel ( new GridLayout ( 1, 2 ) );
     tapeOn = new JButton ( "Paper Tape On" );
     tapeOn.addActionListener ( this );
     j2.add ( tapeOn );
     tapeOff = new JButton ("Paper Tape Off" );
     tapeOff.addActionListener ( this );

     // create our JtextArea for pop up, but do not set visible.
     d = new JDialog( );
     d.setSize ( 200, 300 );
     d.setLocation ( 200, 200);
     tape = new JTextArea();
     tape.setEditable(false);
     JScrollPane scrollPane = new JScrollPane ( tape );
     scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
     scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
     d.add ( scrollPane );
     
     j2.add ( tapeOff );

     add ( j2, BorderLayout.SOUTH );
   
     setVisible( true );

    }
   /**
    * result
    *
    * This method performs calculations for our calculator.
    * Only arithmetic is contained below. 
    * 
    * @param represents the string of the pressed button.
    */
    public void result ( String s ) {
        
        double result = 0;
        // try catch here will handle any Number formats and set
        // the display to zero if divided by zero. 
        try {
            if ( operand.equals( "*" ) ) {
                result =  ( temp * Double.valueOf( s ) );
            } 
            else if ( operand.equals( "/" ) && !s.equals( "0" ) ) {
                result =  temp / Double.valueOf( s );
            } 
            else if ( operand.equals( "+" ) ) {
                result =  temp + Double.valueOf( s );
            } 
            else if ( operand.equals( "-" ) ) {
                result =  temp - Double.valueOf( s );
            }
        } catch( NumberFormatException e) {
               display.setText( "0" );
          }      
                        
        inCalc = false;
        // Using Decimal format will give us the ideal calculator look.
        
        answer = df.format( result );
    }
   /**
    * getSgrt
    *
    * This method performs the square root calculation.
    * 
    * @param represents the text in the display.
    */
    public void getSqrt ( String s ) {
        
        // The try catch does not catch our negative input
        // so I placed an if. 
        if ( Double.valueOf( s ) < 0 ) {
          display.setText("ERROR");
        }
        else {
            try {
                double sqrt = Math.sqrt(Double.valueOf( s) );
                display.setText(df.format( sqrt ));
        
            } catch ( NumberFormatException e ) {
                display.setText("ERROR");
            }
       }
    }
   /**
    * getSgrt
    *
    * This method flips the state of the displayed integer.
    * 
    * @param represents the text in the display.
    */
    public void posNeg ( String s ) {
       double posNeg = Double.valueOf( display.getText( ) );
       // try catch is here incase paren's or other invalid formats
       // are entered. 
       try {
            if ( posNeg != 0) {
                posNeg = posNeg * -1;
                display.setText( df.format(posNeg) );
            }
       } catch (NumberFormatException e ) {
               display.setText( "ERROR");
       } 
    }
    // Implements Action listneer
    public void actionPerformed ( ActionEvent ae ) {
    
        // Record every key stroke if ticker is on.
        if ( record ) {
            tape.append (ae.getActionCommand( ));
            if (!inCalc) {
                tape.append( answer );
            }
        }
        
        // I used if elses here to avoid typing as many button 
        // actions as possible. I could have done these in an array,
        // and memorized button spots.
        //while this seems longer I think its easier to understand.
        if ( ae.getActionCommand( ).equals( "*" ) ) {
            if ( parenLock ) {
                display.setText( display.getText( ) +  ae.getActionCommand( ) );
            }
            else {
                inCalc = true;
                temp = Double.valueOf( display.getText( ) );
                operand = ae.getActionCommand( );
            }
        }
        else if ( ae.getActionCommand( ).equals( "/" ) ) {
            if ( parenLock  ) {
                display.setText( display.getText( ) +  ae.getActionCommand( ) );
            }
            else {
                inCalc = true;
                temp = Double.valueOf( display.getText( ) );
                operand = ae.getActionCommand( );
            }
        }
        else if ( ae.getActionCommand( ).equals( "+" ) ) {
            if ( parenLock ) {
                display.setText( display.getText( ) +  ae.getActionCommand( ) );
            }
            else {
                inCalc = true;
                temp = Double.valueOf( display.getText( ) );
                operand = ae.getActionCommand( );
            }
        }
        else if ( ae.getActionCommand( ).equals( "-" ) ) {
            if ( parenLock ) {
                display.setText( display.getText( ) +  ae.getActionCommand( ) );
            }
            else {
                inCalc = true;
                temp = Double.valueOf( display.getText( ) );
                operand = ae.getActionCommand( );
            }
        }
        // The pset asked for equals to peform this way.
        // I had previously set it to display whenever a computation
        // was done. Now equals must be pressed to get any result. 
        else if ( ae.getActionCommand( ).equals( "=" ) ) {
            display.setText( answer );
        }
        else if ( ae.getActionCommand( ).equals( "√" ) ) {
            getSqrt( display.getText( ) );
        }
        else if ( ae.getActionCommand( ).equals( "C" )) {
            display.setText ( "0" );
            firstPress = true;
            firstDecimal = true;
        }
        else if ( ae.getActionCommand( ).equals( "MS" )) {
            mS = display.getText( );
            // I debated this feature, but this mimics
            // the behavior of my windows calc. So it clears
            // on the next button press. 
            inCalc = true;
        }
        else if ( ae.getActionCommand( ).equals( "MR" )) {
            display.setText( mS );
        }
        else if ( ae.getActionCommand( ).equals( "MC" )) {
            mS = "0";
        }
        else if ( ae.getActionCommand( ).equals( "+/-" )) {
            double posNeg = Double.valueOf( display.getText( ) );
            if ( posNeg != 0) {
                posNeg = posNeg * -1;
                display.setText( df.format(posNeg) );
            }
        }
        else if ( ae.getActionCommand( ).equals( "0" )) {
            if ( !display.getText( ).equals ("0") ) {
                display.setText( display.getText( ) +  ae.getActionCommand( ) );
            }
        }
        else if ( ae.getActionCommand( ).equals( "." )) {
            if ( firstDecimal) {
                display.setText( display.getText( ) +  ae.getActionCommand( ) );
                firstDecimal = false;
            }
        }
       // I believe when I get the valueOf any text it will peform a computation if needed.
       // So I'm just going to add a paren lock to keep equation calcs going as neeed.
       // UPDATE: The above is not true. At all. 
       else if ( ae.getActionCommand( ).equals( "(" )) {
           if ( firstPress ) {
               parenLock = true;
               firstPress = false;
               display.setText( ae.getActionCommand( ));
           } else {
            parenLock = true;
            display.setText( display.getText( ) + ae.getActionCommand( ) );
           }
        }
        else if ( ae.getActionCommand( ).equals( ")" )) {
            parenLock = false;
            display.setText( display.getText( ) + ae.getActionCommand( ) );
        }
       // It is messy having my Pop up located in the action commands
       else if ( ae.getActionCommand( ).equals( "Paper Tape On" )) {
             record = true;
             d.setVisible (true);
        }
        else if ( ae.getActionCommand( ).equals( "Paper Tape Off" )) {
             d.setVisible (false);
             record = false;
        }
        // All integer actions are contained within this else.
        else {
            if ( inCalc) {
                result (ae.getActionCommand( ) ) ;
                display.setText( ae.getActionCommand( ) );
            }
            else if ( firstPress ) {
                display.setText (ae.getActionCommand( ));
                firstPress = false;
            }
            else display.setText( display.getText( ) +  ae.getActionCommand( ) );

        }
    }
}
      
