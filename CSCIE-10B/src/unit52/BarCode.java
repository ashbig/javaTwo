/**
 * BarCode.java
 *
 * Problem 4
 *
 *
 * This class is used for encoding
 * and decoding postal barcodes.
 *
 * @author Ashkan Bigdeli
 * @email ashbigdeli@gmail.com
 */

public class BarCode
{
    String myBarCode;
    String myZipCode;
    // each element's index corresponds to the correct zip code digit
    public static final String [] codes = { "||:::", ":::||", "::|:|", "::||:", ":|::|", ":|:|:", ":||::", "|:::|", "|::|:", "|:|::" };
    
    //constructor method. creates objects if valid barcode exsists
    public BarCode ( String input ) 
    {
         if ( input.length() == 5 )
         {    
             if ( isValidBarCode (encode(input)) )
             {
                 this.myBarCode = encode (input);
             }
             else
             {
                 this.myBarCode = "";
             }
         }
         else if ( input.length() == 32 )
         {
             if ( isValidBarCode(input) )
             {
                 this.myZipCode = decode (input);
             }
             else
             {
                 this.myZipCode = "";
             }
         }
         else
         {
                  this.myBarCode = "";
                  this.myZipCode = "";   
         }
    }

  /**
    * getZipCode
    *
    * This accessor method enables a zip code
    * to be retreived outside of this class
    * 
    */
    public String getZipCode()
    {
        return myZipCode;
    }
   
   /**
    * getBarcode
    *
    * This accessor method enables a Barcode
    * to be retreived outside of this class
    * 
    */
    public String getBarCode()
    {
        return myBarCode;
    }
   
   /**
    * digitToCode
    *
    * This method takes a char parameter and through
    * a switch statement returns the corresponding
    * 5 character barcode.
    * 
    * @param1 represents a char input.
    */
    private String digitToCode ( char digit )
    {
        String s = "";
        switch ( digit )
        {
            case '0': s = codes [0];
                break;
            case '1': s = codes [1];
                break;
            case '2': s = codes [2];
                break;
            case '3': s = codes [3];
                break;
            case '4': s = codes [4];
                break;
            case '5': s = codes [5];
                break;
            case '6': s = codes [6];
                break;
            case '7': s = codes [7];
                break;
            case '8': s = codes [8];
                break;
            case '9': s = codes [9];
                break;
            // returning non zip code characters as empty for error checking
            default: s = "";
       }
       return s;
    }

   /**
    * codeToDigit
    *
    * This method takes a string parameter and cross
    * refrences to final codes array returning any matches.
    * 
    * @param1 represents a 5 character string input.
    */
    private int codeToDigit ( String code )
    {     
        int digit = 0;
        //take the 5 character barcode string. itterate over
        //array of codes. if a match, return the index of codes array.
        for ( int i = 0; i < codes.length; i++ )
        {
            if ( code.equals(codes[i]) )
            {
                digit = i; 
            }
        }
        return digit;
    }

    /* getCheckDigit
    *
    * This method takes a string parameter and creates a corresponding digit
    * which is summed and used to calculate the check digit.
    * 
    * @param1 represents a 5 character zipcode string input.
    */
    private int getCheckDigit ( String input )
    {   
        int sum = 0;
        for ( int i = 0; i < input.length(); i++)
        {
            // use digitToCode to convert char to 5 barcode string
            // then convert 5 barcode string to an integer via codetoDigit.
            sum += codeToDigit(digitToCode(input.charAt(i)));
        }
        // check digit = the sum % 10 to get 1 place. subtract ten by 1's place.
        sum = 10 - (sum % 10);
        return sum;
    }

   /**
    * isValidBarCode
    *
    * This method takes a string parameter tests its
    * delimiters, check digit and length. This error
    * checking method will be called by the constructor
    * so that is may use encode/decode freely.
    * 
    * @param1 represents a barcode string input.
    */
    private boolean isValidBarCode ( String barcode )
    {
        // This will give us the check digit integer of the
        // given barcode.
        int a = codeToDigit((barcode.substring(26,31)));

        // This will run the index 1-25 convert it to a zip
        // and obtain the check digit.
        int b = getCheckDigit(decode(barcode));        
        
        // If the two do not equal, the check digit of this barcode is invalid.
        if ( a != b)
        {
            return false;
        }
        //check for correct delimiters
        else if (barcode.charAt(0) != '|' && barcode.charAt(31) != '|')
        {
            return false;
        }
        //use decode to check if the digit patterns are correct
        else if ( decode(barcode).length() != 5)
        {
           return false;
        }
        else return true;        
    }

   /**
    * encode
    *
    * This method takes a string parameter representing the zipcode
    * and returns the corresponding 32 digit barcode.
    * 
    * @param1 represents a 5 character zipcode string input.
    */
    private String encode ( String input )
    {
        // create appendable 32 character string
        StringBuilder str = new StringBuilder ( 32 );
        str.append("|");
        
        //itterate through each character of input and append string with correct code
        for ( int i = 0; i < input.length (); i++)
        {
            str.append(digitToCode(input.charAt(i)));
        }

        // add check digit and final delminiting bar
        str.append(codes[getCheckDigit(input)] + "|");
        return str.toString();
    }

   /**
    * decode
    *
    * This method takes a string paramter and parses out
    * the characters needed for decoding. It then crosses references
    * our codes array and sequentially returns the index returning the zipcode.
    * 
    * @param1 represents a string barcode input.
    */
    private String decode ( String input )
    {
        // create an array where each element represents relevant decoding
        // from the 32 character barcode string (char's 1-26 inclusive).
        String [] result = { input.substring(1,6), input.substring(6,11), input.substring(11,16), input.substring(16,21), input.substring(21,26)};

        // create an appendable string that is a of unknown length for future
        // error checking (zipcodes must be 5).
        StringBuilder str = new StringBuilder ( );
    
        // for every element in result array, check the final codes array.  
        // if matched add the index of code to our string to be returned later. 
        for (int i = 0; i < result.length; i++ )
        {
            for ( int j = 0; j < codes.length; j++ )
            {
                if ( result[i].equals(codes[j]) )
                {
                    str.append(j);
                }
             }
         }
        return str.toString();
     }
}      
