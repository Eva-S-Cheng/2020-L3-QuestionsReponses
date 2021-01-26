package projet_java;

import java.util.Scanner;
import java.util.Vector;

public class EnsJoueurs {
    Vector<Joueurs> JoueursVect = new Vector<>();                           // Vecteur avec tous les joueurs
    static char REGISTRE_NOM = 'A';                                         // De A à Z

    public EnsJoueurs()
    {
                                                                            // Sans paramètre, création de nouvel ensemble de joueurs vide
    }

    public void CreerJoueursAuto() {
        for (int i = 0; i < 20; i++) {                                      // Automatique de A à Z
            JoueursVect.add(new Joueurs(String.valueOf(REGISTRE_NOM)));     // Conversion en String
            REGISTRE_NOM++;
        }
    }

    public void CreerJoueurs() {
        Scanner sc = new Scanner(System.in);
        boolean ok = false;
        for (int i = 0; i < 20; i++) {
            while (!ok) {
                System.out.println("Rentrez le nom d'un joueur :");         // Rentrer des noms à chaque fois
                String input = sc.nextLine();
                if (input.chars().allMatch(Character::isLetter)) {          // Uniquement des lettres
                    ok = true;
                    JoueursVect.add(new Joueurs(input));
                } else {
                    System.out.println("Veuillez ne rentrer que des lettres !");
                }
            }
            ok = false;                                                     // OK redevient faux
        }
    }

    public void AfficherTout() {
        for(int i = 0; i<this.JoueursVect.size();i++)
            JoueursVect.get(i).Afficher();
    }

    public Joueurs SelectionnerJoueur() {
        int randomNumero = (int) (Math.random() * 20);                      // 0 à 19
        return JoueursVect.get(randomNumero);
    }
}
