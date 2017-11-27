/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

/**
 *
 * @author Capa Brothers
 */
public class ManoBlackjack extends Mano {
     public int getValorBlackjack() {

        int val;      // The value computed for the hand.
        boolean as;  // This will be set to true if the
        //   hand contains an as.
        int cartas;    // Number of cartas in the hand.

        val = 0;
        as = false;
        cartas = getConteoCarta();  // (method defined in class Hand.)

        for ( int i = 0;  i < cartas;  i++ ) {
            // Add the value of the i-th carta in the hand.
            Carta carta;    // The i-th carta; 
            int cartaVal;  // The blackjack value of the i-th carta.
            carta = getCarta(i);
            cartaVal = carta.getValor();  // The normal value, 1 to 13.
            if (cartaVal > 10) {
                cartaVal = 10;   // For a Jack, Queen, or King.
            }
            if (cartaVal == 1) {
                as = true;     // There is at least one as.
            }
            val = val + cartaVal;
        }

        // Now, val is the value of the hand, counting any as as 1.
        // If there is an as, and if changing its value from 1 to 
        // 11 would leave the score less than or equal to 21,
        // then do so by adding the extra 10 points to val. 

        if ( as == true  &&  val + 10 <= 21 )
            val = val + 10;

        return val;

    }  // end getValorBlackjack()
}
