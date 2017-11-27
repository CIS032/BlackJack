/// **
// * Un objeto de tipo Carta representa un naipe de una
// * baraja de poker estándar, incluidos comodines. La tarjeta tiene un traje, que
// * pueden ser espadas, corazones, diamantes, palos o bromista. Una pala, corazón,
// * diamante, o club tiene uno de los 13 valores: as, 2, 3, 4, 5, 6, 7,
// * 8, 9, 10, jota, reina o rey. Tenga en cuenta que se considera que "as" es
// * el valor más pequeño. Un comodín también puede tener un valor asociado;
// * este valor puede ser cualquier cosa y se puede utilizar para realizar un seguimiento de varios
// * diferentes bromistas.
// * /
package blackjack;

/**
 *
 * @author Capa Brothers
 */
public class Carta {
    public final static int ESPADAS = 0;   // Codes for the 4 trajes, plus Joker.
    public final static int CORAZONES = 1;
    public final static int DIAMANTES = 2;
    public final static int CLUBS = 3;
    public final static int JOKER = 4;

    public final static int AS = 1;      // Codes for the non-numeric cards.
    public final static int JACK = 11;    //   Cartas 2 through 10 have their 
    public final static int REINA = 12;   //   numerical valors for their codes.
    public final static int REY = 13;

    /**
     * This card's traje, one of the constants ESPADAS, CORAZONES, DIAMANTES,
     * CLUBS, or JOKER.  The traje cannot be changed after the card is
     * constructed.
     */
    private final int traje; 

    /**
     * The card's valor.  For a normal card, this is one of the valors
     * 1 through 13, with 1 representing AS.  For a JOKER, the valor
     * can be anything.  The valor cannot be changed after the card
     * is constructed.
     */
    private final int valor;

    /**
     * Creates a Joker, with 1 as the associated valor.  (Note that
     * "new Carta()" is equivalent to "new Carta(1,Carta.JOKER)".)
     */
    public Carta() {
        traje = JOKER;
        valor = 1;
    }

    /**
     * Creates a card with a specified traje and valor.
     * @param elValor the valor of the new card.  For a regular card (non-joker),
     * the valor must be in the range 1 through 13, with 1 representing an As.
     * You can use the constants Carta.AS, Carta.JACK, Carta.REINA, and Carta.REY.  
     * For a Joker, the valor can be anything.
     * @param elTraje the traje of the new card.  This must be one of the valors
     * Carta.ESPADAS, Carta.CORAZONES, Carta.DIAMANTES, Carta.CLUBS, or Carta.JOKER.
     * @throws IllegalArgumentException if the parameter valors are not in the
     * permissible ranges
     */
    public Carta(int elValor, int elTraje) {
        if (elTraje != ESPADAS && elTraje != CORAZONES && elTraje != DIAMANTES && 
                elTraje != CLUBS && elTraje != JOKER)
            throw new IllegalArgumentException("Traje de naipes ilegal");
        if (elTraje != JOKER && (elValor < 1 || elValor > 13))
            throw new IllegalArgumentException("Valor ilegal de naipes");
        valor = elValor;
        traje = elTraje;
    }

    /**
     * Returns the traje of this card.
     * @returns the traje, which is one of the constants Carta.ESPADAS, 
     * Carta.CORAZONES, Carta.DIAMANTES, Carta.CLUBS, or Carta.JOKER
     */
    public int getTraje() {
        return traje;
    }

    /**
     * Returns the valor of this card.
     * @return the valor, which is one of the numbers 1 through 13, inclusive for
     * a regular card, and which can be any valor for a Joker.
     */
    public int getValor() {
        return valor;
    }

    /**
     * Returns a String representation of the card's traje.
     * @return one of the strings "Espadas", "Corazones", "Diamantes", "Clubs"
     * or "Joker".
     */
    public String getTrajeComoString() {
        switch ( traje ) {
        case ESPADAS:   return "Espadas";
        case CORAZONES:   return "Corazones";
        case DIAMANTES: return "Diamantes";
        case CLUBS:    return "Clubs";
        default:       return "Joker";
        }
    }

    /**
     * Returns a String representation of the card's valor.
     * @return for a regular card, one of the strings "As", "2",
     * "3", ..., "10", "Jack", "Reina", or "Rey".  For a Joker, the 
     * string is always numerical.
     */
    public String getValorComoString() {
        if (traje == JOKER)
            return "" + valor;
        else {
            switch ( valor ) {
            case 1:   return "As";
            case 2:   return "2";
            case 3:   return "3";
            case 4:   return "4";
            case 5:   return "5";
            case 6:   return "6";
            case 7:   return "7";
            case 8:   return "8";
            case 9:   return "9";
            case 10:  return "10";
            case 11:  return "Jack";
            case 12:  return "Reina";
            default:  return "Rey";
            }
        }
    }

    /**
     * Returns a string representation of this card, including both
     * its traje and its valor (except that for a Joker with valor 1,
     * the return valor is just "Joker").  Sample return valors
     * are: "Reina of Corazones", "10 of Diamantes", "As of Espadas",
     * "Joker", "Joker #2"
     */
    public String toString() {
        if (traje == JOKER) {
            if (valor == 1)
                return "Joker";
            else
                return "Joker #" + valor;
        }
        else
            return getValorComoString() + " de " + getTrajeComoString();
    }

}
