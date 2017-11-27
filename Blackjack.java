/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import javax.swing.JOptionPane;

import java.util.IllegalFormatException;
import blackjack.TextIO;
/**
 *
 * @author Capa Brothers
 */
public class Blackjack {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int dinero;
        int apuesta;
        boolean usuarioGanador;
        System.out.println("Bienvenido al juego de blackjack");
        System.out.println();
        dinero = 100;
        while (true) {
             System.out.println ("Tiene" + dinero + "dólares");
            do {
                System.out.println("¿Cuántos dólares quieres apostar? (Ingresa 0 para finalizar)");
                System.out.print("?");
                
                apuesta = TextIO.getlnInt();
                if (apuesta < 0 || apuesta > dinero) {
                    System.out.println("Su respuesta debe estar entre 0 y" + dinero + '.');
                }
            } while (apuesta < 0 || apuesta > dinero);
            if (apuesta == 0) {
                break;
            }
            usuarioGanador = playBlackjack();
            if (usuarioGanador) {
                dinero = dinero + apuesta;
            } else {
                dinero = dinero - apuesta;
            }
            System.out.println();
            if (dinero == 0) {
               System.out.println ("Parece que te has quedado sin dinero");
                break;
            }
        }
        System.out.println();
        System.out.println("Sales con $" + dinero + '.');

    }

    static boolean playBlackjack() {
        Cubierta cubierta;
        ManoBlackjack manoRepartidor;   // The dealer's hand.
        ManoBlackjack manoUsuario;     // The user's hand.

        cubierta = new Cubierta();
        manoRepartidor = new ManoBlackjack();
        manoUsuario = new ManoBlackjack();

        /*  Shuffle the cubierta, then deal two cards to each player. */
        cubierta.barajar();
        manoRepartidor.añadirCarta(cubierta.tratoCarta());
        manoRepartidor.añadirCarta(cubierta.tratoCarta());
        manoUsuario.añadirCarta(cubierta.tratoCarta());
        manoUsuario.añadirCarta(cubierta.tratoCarta());

        System.out.println();
        System.out.println();

        /* Check if one of the players has Blackjack (two cards totaling to 21).
         The player with Blackjack wins the game.  Dealer wins ties.
         */
        if (manoRepartidor.getValorBlackjack() == 21) {
            System.out.println("El distribuidor tiene el " + manoRepartidor.getCarta(0)
                    + " y el " + manoRepartidor.getCarta(1) + ".");
            System.out.println("El usuario tiene el " + manoUsuario.getCarta(0)
                    + " y el " + manoUsuario.getCarta(1) + ".");
            System.out.println();
            System.out.println("El distribuidor tiene Blackjack. El distribuidor gana");
            return false;
        }

        if (manoUsuario.getValorBlackjack() == 21) {
            System.out.println("El Distribuidor tiene el" + manoRepartidor.getCarta(0)
                    + " y el " + manoRepartidor.getCarta(1) + ".");
            System.out.println("El usuario tiene el " + manoUsuario.getCarta(0)
                    + " y el " + manoUsuario.getCarta(1) + ".");
           System.out.println ();
           System.out.println ("Tienes Blackjack. Tú ganas");
            return true;
        }

        /*  If neither player has Blackjack, play the game.  First the user 
          gets a chance to draw cards (i.e., to "Hit").  The while loop ends 
          when the user chooses to "Stand".  If the user goes over 21,
          the user loses immediately.
         */
        while (true) {

            /* Display user's cards, and let user decide to Hit or Stand. */
            System.out.println();
            System.out.println();
            System.out.println("Tus cartas son:");
            for (int i = 0; i < manoUsuario.getConteoCarta(); i++) {
                System.out.println("    " + manoUsuario.getCarta(i));
            }
            System.out.println("Tu total es " + manoUsuario.getValorBlackjack());
            System.out.println();
            System.out.println("El concesionario muestra el " + manoRepartidor.getCarta(0));
            System.out.println();
            System.out.print("Hit (H) or Stand (S)? ");
            char accionUsuario;  // User's response, 'H' or 'S'.
            do {
                accionUsuario = Character.toUpperCase(TextIO.getlnChar());
                if (accionUsuario != 'H' && accionUsuario != 'S') {
                    System.out.print("Porfavor responda H o S:  ");
                }
            } while (accionUsuario != 'H' && accionUsuario != 'S');

            /* If the user Hits, the user gets a card.  If the user Stands,
              the loop ends (and it's the dealer's turn to draw cards).
             */
            if (accionUsuario == 'S') {
                // Loop ends; user is done taking cards.
                break;
            } else {  // accionUsuario is 'H'.  Give the user a card.  
                // If the user goes over 21, the user loses.
                Carta nuevaCarta = cubierta.tratoCarta();
                manoUsuario.añadirCarta(nuevaCarta);
                System.out.println();
                System.out.println("Aciertos del Usuario.");
                System.out.println("Tu carta es " + nuevaCarta);
                System.out.println("Tu total es ahora " + manoUsuario.getValorBlackjack());
                if (manoUsuario.getValorBlackjack() > 21) {
                    System.out.println();
                    System.out.println("Usted rompió yendo por encima de 21. Pierde.");
                    System.out.println("La otra tarjeta del distribuidor era la "
                            + manoRepartidor.getCarta(1));
                    return false;
                }
            }

        } // end while loop

        /* If we get to this point, the user has Stood with 21 or less.  Now, it's
         the dealer's chance to draw.  Dealer draws cards until the dealer's
         total is > 16.  If dealer goes over 21, the dealer loses.
         */
        System.out.println();
        System.out.println("Soportes del Usuario.");
        System.out.println("Cartas del Distribuidor son");
        System.out.println("    " + manoRepartidor.getCarta(0));
        System.out.println("    " + manoRepartidor.getCarta(1));
        while (manoRepartidor.getValorBlackjack() <= 16) {
            Carta nuevaCarta = cubierta.tratoCarta();
            System.out.println("El distribuidor golpea y obtiene el" + nuevaCarta);
            manoRepartidor.añadirCarta(nuevaCarta);
            if (manoRepartidor.getValorBlackjack() > 21) {
                System.out.println();
                System.out.println("Distribuidor detenido por pasar de 21. Usted gana.");
                return true;
            }
        }
        System.out.println("El total del distribuidor es " + manoRepartidor.getValorBlackjack());

        /* If we get to this point, both players have 21 or less.  We
         can determine the winner by comparing the values of their hands. */
        System.out.println();
        if (manoRepartidor.getValorBlackjack() == manoUsuario.getValorBlackjack()) {
            System.out.println("El distribuidor gana en un empate. Tú pierdes.");
            return false;
        } else if (manoRepartidor.getValorBlackjack() > manoUsuario.getValorBlackjack()) {
            System.out.println("Distirbuidor gana, " + manoRepartidor.getValorBlackjack()
                    + " puntos a " + manoUsuario.getValorBlackjack() + ".");
            return false;
        } else {
            System.out.println("Tu ganas, " + manoUsuario.getValorBlackjack()
                    + " puntos a " + manoRepartidor.getValorBlackjack() + ".");
            return true;
        }

    }  // end playBlackjack()

}
