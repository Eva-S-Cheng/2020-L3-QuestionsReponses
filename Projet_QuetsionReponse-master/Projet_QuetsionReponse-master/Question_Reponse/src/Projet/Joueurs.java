package Projet;

import java.util.Scanner;

public class Joueurs implements Comparable<Joueurs>{
    private int numero;
    private String nom;
    private int score = 0;
    private int etat = 0; // 1 super gagnant 2 gagnant 3 sélectionné 4 éliminé 5 en attente

    private static int REGISTRE = 100; // Contient le numéro de joueur attribué

    public Joueurs(String nom){
        this.numero = REGISTRE;
        this.nom = nom;

        REGISTRE=+10;
    }

    @Override
    public String toString() {
        String statut;
        switch (this.etat){ // Comme cela car expression lambda non supportée
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
        this.toString();
    }

    void MAJScore(boolean bonneReponse, int numeroPhase)
    {
        if(bonneReponse){
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

    public String saisirReponse(String typeQuestion){      // QCM VF ou reponse courte
        Scanner sc = new Scanner(System.in);        // Conversion faite dans le main
        return sc.nextLine();
    }


    @Override
    public int compareTo(Joueurs o) {                   // comparer les joueurs au cas où
        return ( (this.score == o.score) ? 0 :
                ( (score < o.score) ? -1 : 1 ) );
    }

}
