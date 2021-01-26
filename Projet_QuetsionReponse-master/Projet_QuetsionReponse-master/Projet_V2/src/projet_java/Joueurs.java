package projet_java;

import java.util.Scanner;

public class Joueurs implements Comparable<Joueurs>{
    private int numero;
    private String nom;
    private int score = 0;
    private int etat = 0;                               // 1 super gagnant 2 gagnant 3 sélectionné 4 éliminé 5 en attente

    private static int REGISTRE = 100;                  // Contient le numéro de joueur attribué

    public Joueurs(){                                   // Actuellement rien, servira dans la suite du projet dans des classes autres

    }

    public Joueurs(String nom){
        this.numero = REGISTRE;

        if(nom == null) throw new NullPointerException("Espace mémoire de 'nom' non alloué");
        if(nom.isBlank()) throw new IllegalArgumentException("Nom non défini");
        this.nom = nom;
        this.etat = 5;

        REGISTRE=REGISTRE+10;
    }

    @Override
    public String toString() {
        String statut;
        switch (this.etat){                             // 5 états possibles
            case 1:
                statut = "super gagnant";
                break;
            case 2 :
                statut = "gagnant";
                break;
            case 3:
                statut = "sélectionné";
                break;
            case 4 :
                statut = "éliminé";
                break;
            case 5:
                statut = "en attente";
                break;
            default:
                statut = "non défini";
                break;
        }
        return "Joueur numéro " + this.numero + " : " + this.nom.charAt(0) + " ["+ this.score+"|"+statut+"]";
    }

    void Afficher(){
        System.out.println(this.toString());
    }

    void MAJScore(boolean bonneReponse, int numeroPhase)
    {
        if(bonneReponse){                               // Nombre de points dépend de la phase
            switch (numeroPhase)
            {
                case 1:
                    this.score=+2;
                    break;
                case 2:
                    this.score=+3;
                    break;
                case 3 :
                    this.score=+5;
            }
        }
    }

    public void changerEtat(int etat)
    {
        this.etat = etat;
    }

    public String saisirReponse(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();                           // Saisie utilisateur
    }

    public int getEtat(){
        return etat;
    }

    public int getScore() {
        return score;
    }             // Retourne le score

    @Override
    public int compareTo(Joueurs o) {                   // comparer les joueurs au cas où
        return ( (this.score == o.score) ? 0 :
                ( (score < o.score) ? -1 : 1 ) );
    }
}
