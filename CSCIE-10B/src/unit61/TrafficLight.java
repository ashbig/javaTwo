/*
 * TrafficLight.java
 *
 * PROBLEM 3
 *
 * This program asks simulates a traffic light. 
 * When a light is clicked it will appear brighter
 * as the other lights darken.
 * 
 *
 * @author Ashkan Bigdeli
 * @email ashbigdeli@gmail.com
 */


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


class TrafficLight extends JFrame {

    //class variables
    public JPanel red;
    public JPanel yellow;
    public JPanel green;
    
    public static void main ( String [] args ) {
        TrafficLight tl = new TrafficLight( );
    }

    // Constructor 
    public TrafficLight( ) {
    
        setTitle( "Traffic Light" );
        setLayout ( new GridLayout ( 3, 1 ) );
        setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );

        red = new StopLightPanel( 100, Color.RED );
        red.setPreferredSize( new Dimension ( 120, 120 ) ); 
        red.addMouseListener ( new MouseClicked( ) );
        add( red );

        yellow = new StopLightPanel( 100, Color.YELLOW );
        yellow.setPreferredSize( new Dimension ( 120, 120 ) );
        yellow.addMouseListener ( new MouseClicked( ) );
        add( yellow );        
        
        green = new StopLightPanel( 100, Color.GREEN );
        green.addMouseListener ( new MouseClicked( ) );
        green.setPreferredSize( new Dimension ( 120, 120 ) ); 
        add ( green );        

        pack();
        setLocationRelativeTo(null);
        setVisible( true );
      }
      // Extending mouse adapter so I dont need to re-write unecessary methods. 
      class MouseClicked extends MouseAdapter {
         public void mouseClicked ( MouseEvent me ) {
             // I reached out to stack overflow to solve how to 
             // make my classes interact. I am not certain I understand this
             // fully though. Am I essentially type casting my panels to a class?
             
             
             // Turn all the lights off first
            ((StopLightPanel) red).setState( 2 );
            ((StopLightPanel) yellow).setState( 2 );
            ((StopLightPanel) green).setState( 2 );

            // Turn clicked light on
            ((StopLightPanel) me.getSource()).setState( 1 );
          }
      }
     }
    // Creating my own Jpanel class
    class StopLightPanel extends JPanel {

        private int diameter;
        public Color color;
        // State will be how we track which colors to use.
        // 0 will be the starting state.
        // 1 will be lighter.
        // 2 will be darker. 
        public int state;
        
       /* This method will return if a light
        * is on or off. 
        */
        public int getState( ) {
            return state;
        }
       /* This method allows us to interact with
        * our parent class to determine color. 
        */
        public void setState ( int state ) {
           this.state = state;
           repaint();
        }

        // Constructor for class
        public StopLightPanel ( int d, Color c) {

            this.diameter = d;
            this.color = c;
            this.state = 0;
        }

        // Overriding paint component
        public void paintComponent ( Graphics g ) {

            super.paintComponent( g );
            
            //This is a bit of a bug. If you keep
            // Clicking eventually the colors will 
            // go white or black. I tried adding a state
            // but no dice in re-setting to its original state. 
            // I left as is so you can help me, I need this interaction explained.
            // a littl better. 
            if ( state == 1 ) {
                color = color.brighter();
                state = 0;
            }
            else if ( state == 2 ) {
                color = color.darker();
                state = 0;
            }
            else color = color;
            g.setColor ( color );
            g.fillOval ( 10, 10, diameter, diameter );
       }
     }
