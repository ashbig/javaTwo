/*
 * MailLayout.java
 *
 * PROBLEM 2
 *
 * This program provides a GUI to send input fields and a message
 * to an output.txt file listed in home directory of MailLayout.java.
 * 
 *
 * @author Ashkan Bigdeli
 * @email ashbigdeli@gmail.com
 */


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class MailLayout extends JFrame implements ActionListener, FocusListener {

    private JTextField to;
    private JTextField cc;
    private JTextField bcc;
    private JTextField subject;
    private JComboBox from;
    private JTextArea compose;
    private JButton send;

    //Constructor to set user interface layout
    public MailLayout() {

        // Jframe settings
        setTitle ( "New Message" );
        setSize ( 500, 500 );
        setLocation ( 200, 200 );
        setLayout ( new BorderLayout () );
        setDefaultCloseOperation ( EXIT_ON_CLOSE );


        //JPanel of labels
        JPanel j1 = new JPanel( );
        j1.setLayout ( new GridLayout ( 5, 1 ) );

        j1.add ( new JLabel ( "To: " ) );
        j1.add ( new JLabel ( "Cc: " ) );
        j1.add ( new JLabel ( "Bcc: " ) );
        j1.add ( new JLabel ( "Subject: " ) );
        j1.add ( new JLabel ( "From: " ) );

        //JPanel of text fields. Focus Listener on subject field.
        JPanel j2 = new JPanel( );
        j2.setLayout ( new GridLayout ( 5, 1 ) );
        to = new JTextField();
        j2.add ( to );
        cc = new JTextField();
        j2.add( cc );
        bcc = new JTextField();
        j2.add( bcc );
        subject = new JTextField ();
        subject.addFocusListener( this );
        j2.add ( subject );
        String [] data = { "Henry Leitner <leitner@harvard.edu>", "Mishal Rahman <rahman@college.harvard.edu>", "Ashkan Bigdeli <ashbigdeli@gmail.com>" };
        from = new JComboBox ( data );
        j2.add ( from );


        // Create a new jpanel to house labels and text fields.
        JPanel north  = new JPanel ( new BorderLayout ( ) );
        north.add ( j1, BorderLayout.WEST );
        north.add ( j2, BorderLayout.CENTER );

        // Add properly aligned labels and textfields to jframe north border.
        add ( north, BorderLayout.NORTH );
        
        // Add composition area to center of jrame border.
        compose = new JTextArea ();
        add ( compose, BorderLayout.CENTER );
        
        // Add send but to south of jframe bored and add action listner.
        send = new JButton ( "Send" );
        send.addActionListener ( this );
        add (send, BorderLayout.SOUTH );
        setVisible( true );
     }

     // Implementation of ActionListener
     public void actionPerformed ( ActionEvent ae ) {

         // Upon pressing send write contents of message to output file
         try {
             FileWriter message = new FileWriter ( "output.txt", true );
             message.append( "\n" + to.getText( ) + "\n" );
             message.append( cc.getText( ) + "\n" );
             message.append( bcc.getText( ) + "\n" );
             message.append( subject.getText( ) + "\n" );
             String sender = ( String ) from.getSelectedItem( );
             message.append( sender + "\n");
             message.append( compose.getText( ) );
             message.flush( );
         } catch ( IOException e ) {
               JOptionPane.showMessageDialog ( null, "Message not sent! Invalid recipient." ); 
         }
 
         // Reset field values and let user know message is sent.
         to.setText( "" );
         cc.setText( "" );
         bcc.setText( "" );
         subject.setText( "" );
         compose.setText( "" );
         setTitle ( "New Message" );
         JOptionPane.showMessageDialog ( null, "Message sent!" ); 
         
     }

     // Implementation of focus listener.
     public void focusLost ( FocusEvent e ) {
         // Using focusLost allows user to type before changing jframe title. 
         setTitle( subject.getText( ) );
     }
     public void focusGained ( FocusEvent e ) {
         // not used.
     }

    // Main creates new MailLayout object.
     public static void main ( String [] args ) {
         new MailLayout( );
     }

}
