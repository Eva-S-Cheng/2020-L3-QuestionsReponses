package Projet;

import java.util.Scanner;
import java.util.Vector;

public class EnsJoueurs {
    Vector<Joueurs> JoueursVect = new Vector<>();
    static char REGISTRE_NOM = 'A';

    public EnsJoueurs() {
        for (int i = 0; i < 20; i++) {
            JoueursVect.add(new Joueurs(String.valueOf(REGISTRE_NOM)));
            REGISTRE_NOM++;
        }
    }

    public void CreerJoueurs() {
        Scanner sc = new Scanner(System.in);
        boolean ok = false;
        for (int i = 0; i < 20; i++) {
            while (!ok) {
                System.out.println("Rentrez le nom d'un joueur :");
                String input = sc.nextLine();
                if (input.chars().allMatch(Character::isLetter)) {
                    ok = true;
                    JoueursVect.add(new Joueurs(input));
                } else {
                    System.out.println("Veuillez ne rentrer que des lettres !");
                }
            }
            ok = false;
        }
    }

    public void AfficherTout() {
        for (Joueurs i : this.JoueursVect) {
            i.Afficher();
        }
    }

    public Joueurs SelectionnerJoueur() {
        int randomNumero = (int) (Math.random() * 20);   // 0 à 19
        this.JoueursVect.get(randomNumero).changerEtat(3);  // Joueur est sélectionné
        return JoueursVect.get(randomNumero);
    }

}