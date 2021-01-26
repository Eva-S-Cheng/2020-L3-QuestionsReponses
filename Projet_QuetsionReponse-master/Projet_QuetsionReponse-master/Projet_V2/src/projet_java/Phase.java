package projet_java;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public abstract class Phase {
    protected int PhaseDeJeu;
    protected EnsJoueurs participantsDeLaPhase = new EnsJoueurs();          // Les joueurs actifs de la phase
    protected Vector<Chrono> chronos = new Vector<>();                      // Liste de chrono de chaque joueurs actifs de la phase
    protected EnsJoueurs gagnantDeLaPhase = new EnsJoueurs();               // L'ensemble des gagnants

    /** METHODES NON-ABSTRAITES **/
    public EnsJoueurs retournerGagnants(){
        boolean elimineJoueur = false;                                      // Devient vrai une fois qu'un joueur est éliminé
        int numberMinimal = 1;                                              // Nomnbre de personnes qui ont le score minimal
        ArrayList<Joueurs> joueursAEliminer = new ArrayList<>();
        Joueurs joueurElimine = new Joueurs();                              // Rien en tant qu'argument, servira uniquement de tampon

        if(this.participantsDeLaPhase.JoueursVect == null) throw new NullPointerException("L'espace mémoire des participants n'est pas alloué");
        if(this.participantsDeLaPhase.JoueursVect.isEmpty()) throw new IllegalArgumentException("Pas de participants");
        for(int i = 0; i < participantsDeLaPhase.JoueursVect.size(); i++)   // Attention au passage d'argument par référence
        {
            this.gagnantDeLaPhase.JoueursVect.add(i,participantsDeLaPhase.JoueursVect.get(i));
        }

        /** PROCEDONS A L'ELIMINATION **/
        int scoreMin = this.gagnantDeLaPhase.JoueursVect.get(0).getScore(); // A l'initial on suppose que le minimal est le premier
        joueursAEliminer.add(this.gagnantDeLaPhase.JoueursVect.get(0));     // Fait partie des joueurs à éliminer
        Vector<Chrono> chronoDeJoueursAEliminer = new Vector<>();           // Chrono des joueurs à éliminer
        chronoDeJoueursAEliminer.add(chronos.get(0));
        for(int j = 1; j < this.gagnantDeLaPhase.JoueursVect.size(); j++){  // Parcours du vecteur des joueurs
            if (this.gagnantDeLaPhase.JoueursVect.get(j).getScore()==scoreMin)
            {
                numberMinimal++;                                            // Augmente le nombre de joueurs succeptibles d'être éliminés
                joueursAEliminer.add(this.gagnantDeLaPhase.JoueursVect.get(j));
                chronoDeJoueursAEliminer.add(chronos.get(j));
            }
            else if(this.gagnantDeLaPhase.JoueursVect.get(j).getScore()<scoreMin)
            {
                joueursAEliminer.clear();                                   // Tout effacer car ce ne sont plus des joueurs à éliminer
                chronoDeJoueursAEliminer.clear();
                joueursAEliminer.add(this.gagnantDeLaPhase.JoueursVect.get(j));
                chronoDeJoueursAEliminer.add(chronos.get(j));
                numberMinimal = 1;                                          // Retour à 1
            }
        }
        if (joueursAEliminer.size()==1)
        {
            gagnantDeLaPhase.JoueursVect.remove(joueursAEliminer.get(0));   // Marche car en java, passe de paramètre en référence
            joueurElimine = joueursAEliminer.get(0);
            elimineJoueur = true;
            // Elimination donc chagement d'état
            this.participantsDeLaPhase.JoueursVect.get(
                    participantsDeLaPhase.JoueursVect.indexOf(
                            joueursAEliminer.get(0))).changerEtat(4);       // Elimination, possible car passage de référence
        }
        else
        {
            long tempsMax = this.chronos.get(0).getTemps();                 // A l'initial on suppose que le minimal est le premier
            int numberTempsMax = 1;                                         // Nombre de joueurs à temps maximal parmis les candidats à l'élimination
            ArrayList<Joueurs> copieDefJoueursAEliminer = new ArrayList<>();// Copie défensive
            for(int i = 0; i < joueursAEliminer.size(); i++)                // Attention au passage d'argument par référence
            {
                copieDefJoueursAEliminer.add(i,joueursAEliminer.get(i));
                if(tempsMax<chronoDeJoueursAEliminer.get(i).getTemps())
                {                                                           // Get le maximal
                    tempsMax = chronoDeJoueursAEliminer.get(i).getTemps();
                }
            }

            for(int j = 0; j < joueursAEliminer.size(); j++)
            {
                if(chronoDeJoueursAEliminer.get(j).getTemps()<tempsMax)// Si plus rapide que le temps max
                {
                    copieDefJoueursAEliminer.remove(joueursAEliminer.get(j));// Enlève des éliminés ceux qui ont un temps inférieur
                }
            }
            if (copieDefJoueursAEliminer.size()==1)                         // Si un seul à éliminer, on l'élimine
            {
                this.gagnantDeLaPhase.JoueursVect.remove(copieDefJoueursAEliminer.get(0));
                joueurElimine = copieDefJoueursAEliminer.get(0);
                this.participantsDeLaPhase.JoueursVect.get(
                        participantsDeLaPhase.JoueursVect.indexOf(
                                copieDefJoueursAEliminer.get(0))).changerEtat(4);
                elimineJoueur = true;
            }
            else
            {
                Random rn = new Random();                                   // Si même temps, cas rare, on retourne le minimal
                int rand = rn.nextInt(copieDefJoueursAEliminer.size())%copieDefJoueursAEliminer.size();
                this.gagnantDeLaPhase.JoueursVect.remove(copieDefJoueursAEliminer.get(rand));
                joueurElimine = copieDefJoueursAEliminer.get(rand);
                copieDefJoueursAEliminer.get(rand).changerEtat(4);
                elimineJoueur = true;
            }
        }

        System.out.println("Les gagnant de la phase sont : ");              // Affiche les gagnants et celui qui a été éliminé
        for(int k = 0; k < this.participantsDeLaPhase.JoueursVect.size(); k++)
        {
            if (this.participantsDeLaPhase.JoueursVect.get(k)!=joueurElimine)
                this.participantsDeLaPhase.JoueursVect.get(k).changerEtat(2);
            this.participantsDeLaPhase.JoueursVect.get(k).Afficher();
        }

        System.out.println("\nLe joueur qui a été éliminé pendant cette phase est : ");
        joueurElimine.Afficher();                                           // Affiche éliminé
        return this.gagnantDeLaPhase;
    }


    public EnsJoueurs getParticipantsDeLaPhase() {
        return participantsDeLaPhase;
    }

    public int getPhaseDeJeu() {
        return PhaseDeJeu;
    }                       // Obtenir la phase du jeu

    /** METHODES ABSTRAITES **/
    public abstract void selectionnerJoueurs(EnsJoueurs joueursCandidats);
    public abstract void jouer(EnsJoueurs joueursCandidats, Themes listeThemes,ListeQuestions listeQuestions);
}
