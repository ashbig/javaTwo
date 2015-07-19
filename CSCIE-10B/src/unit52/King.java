/**
 * King.java
 *
 * Problem 6
 *
 * EXTRA CREDIT
 * 
 * This class extends Piece to show how a kind may attack.
 * Only line 23 was modified by co-author.
 *
 * @author Henry Leitner
 * @author2 Ashkan Bigdeli
 * @email ashbigdeli@gmail.com
 */

class King extends Piece
{
    boolean attackingThisLocation (int indexRow, int indexColumn)
    {
      int columnDiff = pieceColumn - indexColumn;
      int rowDiff = pieceRow - indexRow;
 
      // attack if columnDiff & rowDiff are 1, -1, or 0.
      if (( rowDiff < 2 && rowDiff > -2) && (columnDiff < 2 && columnDiff > -2) )
           return true;
      else return false; 
    }
}
