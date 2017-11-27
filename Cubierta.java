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
public class Cubierta {
      /**
     * An array of 52 or 54 cards.  A 54-card cubierta contains two Jokers,
     * in addition to the 52 cards of a regular poker cubierta.
     */
    private Carta[] cubierta;

    /**
     * Keeps track of the number of cards that have been dealt from
     * the cubierta so far.
     */
    private int cartasUsadas;

    /**
     * Constructs a regular 52-card poker cubierta.  Initially, the cards
     * are in a sorted order.  The barajar() method can be called to
     * randomize the order.  (Note that "new Cubierta()" is equivalent
     * to "new Cubierta(false)".)
     */
    public Cubierta() {
        this(false);  // Just call the other constructor in this class.
    }

    /**
     * Constructs a poker cubierta of playing cards, The cubierta contains
     * the usual 52 cards and can optionally contain two Jokers
     * in addition, for a total of 54 cards.   Initially the cards
     * are in a sorted order.  The barajar() method can be called to
     * randomize the order.
     * @param incluirJokers if true, two Jokers are included in the cubierta; if false,
     * there are no Jokers in the cubierta.
     */
    public Cubierta(boolean incluirJokers) {
        if (incluirJokers)
            cubierta = new Carta[54];
        else
            cubierta = new Carta[52];
        int cartaCt = 0; // How many cards have been created so far.
        for ( int traje = 0; traje <= 3; traje++ ) {
            for ( int valor = 1; valor <= 13; valor++ ) {
                cubierta[cartaCt] = new Carta(valor,traje);
                cartaCt++;
            }
        }
        if (incluirJokers) {
            cubierta[52] = new Carta(1,Carta.JOKER);
            cubierta[53] = new Carta(2,Carta.JOKER);
        }
        cartasUsadas = 0;
    }

    /**
     * Put all the used cards back into the cubierta (if any), and
     * barajar the cubierta into a random order.
     */
    public void barajar() {
        for ( int i = cubierta.length-1; i > 0; i-- ) {
            int rand = (int)(Math.random()*(i+1));
            Carta temp = cubierta[i];
            cubierta[i] = cubierta[rand];
            cubierta[rand] = temp;
        }
        cartasUsadas = 0;
    }

    /**
     * As cards are dealt from the cubierta, the number of cards left
     * decreases.  This function returns the number of cards that
     * are still left in the cubierta.  The return valor would be
     * 52 or 54 (depending on whether the cubierta includes Jokers)
     * when the cubierta is first created or after the cubierta has been
     * barajard.  It decreases by 1 each time the tratoCarta() method
     * is called.
     */
    public int cartasIzquierda() {
        return cubierta.length - cartasUsadas;
    }

    /**
     * Removes the next card from the cubierta and return it.  It is illegal
     * to call this method if there are no more cards in the cubierta.  You can
     * check the number of cards remaining by calling the cartasIzquierda() function.
     * @return the card which is removed from the cubierta.
     * @throws IllegalStateException if there are no cards left in the cubierta
     */
    public Carta tratoCarta() {
        if (cartasUsadas == cubierta.length)
            throw new IllegalStateException("No quedan cartas en la cubierta");
        cartasUsadas++;
        return cubierta[cartasUsadas - 1];
        // Programming note:  Cartas are not literally removed from the array
        // that represents the cubierta.  We just keep track of how many cards
        // have been used.
    }

    /**
     * Test whether the cubierta contains Jokers.
     * @return true, if this is a 54-card cubierta containing two jokers, or false if
     * this is a 52 card cubierta that contains no jokers.
     */
    public boolean tieneJokers() {
        return (cubierta.length == 54);
    }
}
