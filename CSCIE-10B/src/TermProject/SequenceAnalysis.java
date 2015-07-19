/*
 * SequenceAnalysis.java
 *
 * FINAL PROJECT
 *
 * This program provides a GUI for the user to paste in a sequnce, or a large
 * garbled mess to have a sequence parsed out of. It will provide a sample number,
 * the sequence itself and any primers, cut sites, or codons that can be found within
 * as well as the start and stop position of that feature. 
 *
 * @author Ashkan Bigdeli
 * @email ashbigdeli@gmail.com
 */
import javax.swing.*; //swing components
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class SequenceAnalysis extends JFrame implements ActionListener {
    
    private JLabel description;
    private JTextArea input;
    private JButton analyze;
    
    // ArrayList for addition of analyzed results
    private ArrayList<String> dna;
    private ArrayList<String> compDNA;
    private ArrayList<String> foundPrimers;
    private ArrayList<String> foundCut;
    private ArrayList<String> foundCodon;
    
    //For finding features, update the row and add names to row 0 and corresponding sequences to row1
    int primeRow = 5;
    private String [] [] seqPrimers = { { "M13R", "CMV-F", "SmaI", "EcoRV", "SP6" },
                                   { "CAGGAAACAGCTATGAC", "CGCAAATGGGCGGTAGGCGTG",
                                     "GACTATCATATGCTTACCGT", "TAATACGACTCACTATAGGG",
                                     "ATTTAGGTGACACTATAG" } };
    int cutRow = 5; 
    private String [] [] cutSites = { { "EcoRI", "NotI","BAMHI", "HindIII", "TaqI" },
                                   { "GAATTC", "GCGGCCGC", "GGATCC", "AAGCTT", "TCGA" } };
    int codonRow = 2;
    private String [] [] codonSeq = { { "Start", "Stop" },
                                      { "ATG", "TAG" } };

    // GUI configuartion 
    public SequenceAnalysis() {

        setTitle( "Sequence Analyzer v 1.0");
        setSize ( 900, 700);
        setLocationRelativeTo( null );
        setLayout ( new BorderLayout( 10, 10 ) );
        setDefaultCloseOperation ( EXIT_ON_CLOSE );
        
        // Brief Program Description
        description = new JLabel ( "\nAdd your sequence(s) below. Seperate each with a '>' and press submit!");
        description.setFont( new Font( "Serif",Font.ITALIC, 26 ) );
        description.setHorizontalAlignment( JLabel.CENTER );
        add( description, BorderLayout.NORTH );
        
        // Input area with line wrap and scroll bar for easy viewing
        input = new JTextArea ("Enter your sequence(s) here :) ");
        input.setLineWrap(true);
        JScrollPane scroll = new JScrollPane( input );
        scroll.setVerticalScrollBarPolicy( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        add( scroll, BorderLayout.CENTER );
         
        analyze = new JButton ("Submit");
        analyze.addActionListener( this );

        add (analyze, BorderLayout.SOUTH );

        // Adding a dialog box here forces my user (hopefully) to look at my documenation. 
        JOptionPane.showMessageDialog ( null, "Please see the included documentation for use!" );

        setVisible( true );

    }
    
    /**
    * actionPerformed
    *
    * When Analyze is press all available methods will be called
    * and the text will be set. The user will be notified by a dialog box.
    */ 
    public void actionPerformed ( ActionEvent ae ) {
        parseSeq( input.getText() );
        getCompSeq(dna);
        foundPrimers = findFeature (seqPrimers, primeRow);
        foundCut = findFeature (cutSites, cutRow);
        foundCodon = findFeature (codonSeq, codonRow);
        input.setText(getAnalysis( ));
        JOptionPane.showMessageDialog ( null, "Analysis Complete!" );
    }
    
   /**
    * parseSeq
    *
    * This method can be used to crawl general text and parse out
    * genomic sequences. Up to three trailing characters may be found.
    * At the sequences end. Results will be placed in dna ArrayList.
    */
    public void parseSeq ( String s ) {
        // Coders note: I am proud of this method even if slightly flawed and ugly.
        // With a bit of manipulation this should be able to point at the web
        // or a file and come away with sequences. Very cool :)
        
        dna = new ArrayList();
        // Set text from text area to upperCase for smaller
        // Switch statement later. 
        String input = s.toUpperCase();
        //Common informatics convention to split sequences using > char
        String [] inputSplit = input.split("\\>");
        // Create string storage to parse into
        StringBuilder str = new StringBuilder();
        int count = 0;  
        // Enter the array of  potential sequences and verify them
        for ( int i = 0; i < inputSplit.length; i++ ) {
            String a = inputSplit[i];  
            
            // Enter the string and verify the bases.
            for ( int j = 0; j < a.length(); j++){
                
                // If we do not check intially if we are in a sequence an
                // additional base will be placed at index 3. 
                if ( count >= 4 ) {
                    str.append(a.charAt(j) );
                    if (!isBase(a.charAt(j)) ){
                        count--;
                    }
                }
                // Determine if we are starting a sequence
                if ( count < 4 && isBase( a.charAt(j) ) ) { 
                    str.append(a.charAt(j));
                    count++;
                }
                // If we are not starting a sequence reset our strings length
                // so irrelevant characters are not appended. 
                if ( count < 3 && !isBase( a.charAt(j) ) ) { 
                   str.setLength(0);
                }
            }
            // Check if the string length is worth adding to the arraylist.
            // and trim it of white space. 
            if ( str.length() > 0) {
                String x = str.toString();
                dna.add( x.trim());
                str.setLength(0);
            }   
        }
        
    }
         
   /**
    * isBase
    *
    * This method will return true if the passed char is considered
    * a nucleotide.
    * 
    * @ param a   The character to be tested.
    */
    public boolean isBase ( char a ) {
        if ( a == 'A' || a == 'T' || a == 'G' || a == 'C' || a == 'U' ||a == 'N' ) {
            return true;
        } else {
            return false;
        }
    }
    
   /**
    * getCompBase
    *
    * This method will return the complimentary genomic base
    * 
    * @ param c   The character to be converted.
    */
    public char getCompBase ( char c ) {
        char comp;
        switch ( c ) { 
            case 'A': comp = 'T';
                break;
            case 'T': comp = 'A';
                break;
            case 'G': comp = 'C';
                break;
            case 'C': comp = 'G';
                break;
            case 'U': comp = 'A';
                break;
            default : comp = 'N';
                break;
        }
        return comp;
    }
    
   /**
    * isBase
    *
    * This method will return the complimentary genomic sequence.
    * 
    * @ param al   The ArrayList of sequences to be converted.
    */
    public void getCompSeq ( ArrayList<String> al ) {
        // Create a new string to be appended and conver each base.
        StringBuilder str = new StringBuilder();
        compDNA = new ArrayList();
        for ( int i =0; i < al.size(); i++ ) {
            String s = al.get(i);
            for (int j = 0; j < s.length(); j++) {
                str.append(getCompBase(s.charAt(j)));
            }
            //add our complimentary sequence to compDNA ArrayList and reset string.
            compDNA.add(str.toString( ));
            str.setLength(0);
        }
    }
    
   /**
    * findFeature
    *
    * This method will make use of our parsed DNA sequnces. When passed
    * an array of feaures, it will locate this feature and return it's common
    * name.
    * 
    * @ param array   A 2d array that holds the name at "row"  and the
    *                 feature to be found at "column".
    * @ param row     This represents how the row of the 2d array.
    */
    public ArrayList <String> findFeature ( String [] [] array, int row  ) {
        
        // Create a new array list that is the size of DNA at the very least.
        // This method gave me fits, ideally we would create an ArrayList
        // of ArrayLists as each sequences can have multiple features of each type
        // however I could not get this code to work when I went to itterate through
        // it later and it had to be  removed :( 
  
        // Below is functioning code, but it should be noted that it will only store 1
        // requested feature per sequence ( i.e 1 primer, 1 cut site and 1 codon). 
        ArrayList <String> al = new ArrayList ();
        StringBuilder str = new StringBuilder();
        
        // Grab 1 sequence at a time from our parsed sequences.
        for ( int i = 0; i < dna.size(); i++ ){
            String s = dna.get(i);
            for (int j = 0; j < row; j++ ){
                // squences are all in "row" 1, so we increment the columns
                // and check to see if we have a match.
                if (s.contains(array[1][j])) {
                    
                    // We do? Great, now find the start and stop postion
                    // put it in a fancy format and add this to
                    // our ArrayList to be returned.
                    System.out.println(str);
                    str.append ("\n\t" + array[0][j]);
                    int startIndex = s.indexOf(array[1][j]);
                    int stopIndex = startIndex + array[1][j].length();
                    str.append ("\n\tBase " + startIndex  + " to " + stopIndex );
                    al.add( str.toString());
                    str.setLength(0);
               }
            }
        }
        // As I mentioned, my program can add multiple features per sequences,
        // but sadly does not output them, so I must account for the absence of
        // input now as well. 
        if ( al.size() == 0) {
            al = new ArrayList (Collections.nCopies(dna.size(), "0"));
        }
        return al;
    }
    
   /**
    * isBase
    *
    * This method will set the display for the user for easy reading. DNA
    * sequences printed first, then compliment then features. 
    * 
    */
    public String getAnalysis (  ){
        StringBuilder str = new StringBuilder(0);
        
        // This is where finding multiple primer, or codon, or cut features
        // for each sequence proved difficult. Formatting of output actually
        // caused most of my problems. In the real world this information would
        // likely just be moved to a database for easy storage and recall, but given
        // the format one of each feature for sequence was the way to go. 
        for (int i = 0; i < dna.size(); i++ ) {
            str.append ("\nSequence: " + ( i + 1) + "\n");
            str.append(dna.get(i) + "\n");
            str.append("\nComplimentary Sequence: " + ( i + 1 )  + "\n" );
            str.append(compDNA.get(i) +"\n");
            Iterator<String> it = foundPrimers.iterator();
            str.append("\nPrimers found: ");
            str.append(foundPrimers.get(i) +"\n");
            str.append("\nCut Sites found: ");
            str.append(foundCut.get(i) +"\n");
            str.append("\nCodons found: ");
            str.append(foundCodon.get(i)+"\n");
        }
        return str.toString();
    }
    // Oh hey there you main. 
    public static void main (String [] args ) {
        new SequenceAnalysis();
    }
}
