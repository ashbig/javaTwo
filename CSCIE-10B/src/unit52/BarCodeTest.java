/**
 * BarCodeTest.java
 *
 * Problem 4
 *
 *
 * This program generates and decodes postal barcodes
 *
 * @author Ashkan Bigdeli
 * @email ashbigdeli@gmail.com
 */

public class BarCodeTest
{
    public static void main (String [] args )
    {
        // #1
        BarCode q1 = new BarCode ("02138");
        System.out.println(q1.getBarCode());

        // #2
        BarCode q2 = new BarCode ("||:|:::|:|:||::::::||:|::|:::|||");
        if(q2.getZipCode().length() > 0 )
        {
             System.out.println(q2.getZipCode());
        }
        else 
        {
            System.out.println("Your barcode was invalid.");
        }
      
        // #3
        BarCode q3 = new BarCode ("19035");
        System.out.println(q3.getBarCode());

        // # 4
        BarCode q4 = new BarCode ("|:::|||:|::||:::::||::|:|:::|:||");
        System.out.println(q4.getZipCode());

        // # 5
        BarCode q5 = new BarCode ("||:::||::|:::|:|:|::|||:::||:::|");

        // # 6
        if(q5.getZipCode().length() > 0 )
        {
             System.out.println(q5.getZipCode());
        }
        else 
        {
            System.out.println("Your barcode was invalid.");
        }
       
        // # 7 
        BarCode q7 = new BarCode ("||:|:::|:|:||::::::||:|::|:::||");
        if(q7.getZipCode().length() > 0 )
        {
             System.out.println(q7.getZipCode());
        }
        else 
        {
            System.out.println("Your barcode was invalid.");
        }

        // # 8
        BarCode q8 = new BarCode ("||:|:::|:|:||::::::||:|::|:::||:");
        if(q7.getZipCode().length() > 0 )
        {
             System.out.println(q7.getZipCode());
        }
        else 
        {
            System.out.println("Your barcode was invalid.");
        }      
     }
}
