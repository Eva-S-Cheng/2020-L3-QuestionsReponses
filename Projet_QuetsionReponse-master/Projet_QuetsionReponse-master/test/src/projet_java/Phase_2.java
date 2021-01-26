package projet_java;

import java.util.Random;

public class Phase_2 extends Phase {
    final int NUMBER_JOUEURS_PHASE = 3;
    final int NUMBER_OF_QUESTIONS = 6;                                      // 6 questions au total
    @Override
    public void selectionnerJoueurs(EnsJoueurs joueursGagnantsPhase1) {
        this.participantsDeLaPhase = joueursGagnantsPhase1;                 // Joueurs actifs
    }

    @Override
    public void jouer(EnsJoueurs joueursGagnantsPhase1, Themes themesList,ListeQuestions questions) {
        this.PhaseDeJeu = 2;
        this.selectionnerJoueurs(joueursGagnantsPhase1);                    // Sélection aléatoire des joueurs
        for (int i = 0; i < NUMBER_JOUEURS_PHASE; i++)                      // Création de chronometres
        {
            this.chronos.add(i, new Chrono());                              // Création du chronomètre
            this.chronos.get(i).start();                                    // Démarrage fictif
            this.chronos.get(i).pause();
        }
        Random rd = new Random();
        Themes copieDefTheme = new Themes();
        copieDefTheme.getThemes().clear();
        int numberOfSelectedThemes = 0;
        while(numberOfSelectedThemes!=6) {                                  // Choisir 6 thèmes de manière aléatoire
            int alea = (rd.nextInt(100))%10;
            if(!copieDefTheme.getThemes().contains(themesList.getThemes().get(alea)))
            {
                copieDefTheme.getThemes().add(themesList.getThemes().get(alea));
                numberOfSelectedThemes++;                                   // Compte le nombre de thème choisis
            }
        }

        ListeQuestions copieDefQuestion = new ListeQuestions();             // Copie défensive pour ne pas modifier les questions d'origine
        for (int i = 0; i<questions.getQuestionsList().size(); i++)
        {
            copieDefQuestion.ajouterQuestion(questions.getQuestionsList().get(i));
        }

        int selectedQuestion = (rd.nextInt(1000));                  // Aléatoire

        for (int j = 0; j < NUMBER_OF_QUESTIONS; j++)
        {
            int joueurActif = j%3;                                          // Politique Round Robin
            int selectUtilisateur = 0;
            boolean entreeUtilisateurOK = false;
            boolean ok = false;
            while (!ok)
            {
                System.out.println("C'est au tour de : ");
                this.participantsDeLaPhase.JoueursVect.get(joueurActif).Afficher();
                copieDefTheme.afficher();                                   // Affiche les thèmes dispo
                try
                {
                    selectUtilisateur = Integer.parseInt(this.participantsDeLaPhase.JoueursVect.get(joueurActif).saisirReponse());
                    if(selectUtilisateur>0&&selectUtilisateur<copieDefTheme.getThemes().size()+1)
                        ok = true;
                    else
                        System.out.println("Saisir un nombre entre 1 et "+copieDefTheme.getThemes().size());
                }
                catch (Exception e)
                {
                    System.out.println("Veuillez saisir un entier !");      // Exception
                }

            }

            String selectedTheme = themesList.selectionnerTheme(themesList.getThemes().indexOf(copieDefTheme.getThemes().get(selectUtilisateur-1)));
            themesList.selectionnerTheme(themesList.getThemes().indexOf(selectedTheme));
            System.out.println("------------------ THEME : "+selectedTheme+" ------------------");
            ThemeQuestion questionsPossibles = new ThemeQuestion(selectedTheme, copieDefQuestion);

            /** Mettre un switch **/

            /** A REMPLACER PAR RECHERCHE DANS LA BDD **/  // SELECT * FROM QCM/RC/VF WHERE THEME = ... AND DIFFICULTE =...
            Question questionChoisie =
                    questionsPossibles.questions.
                            selectionnerQuestion(2,selectedQuestion%
                                    questionsPossibles.questions.getQuestionsList().size());
            /** **/
            System.out.println("Votre question est la suivante : ");        // Affichage de la question
            questionChoisie.afficher();
            String answer = null;
            this.chronos.get(joueurActif).resume();
            if(questionChoisie.getQuestion().getType().equals("QCM"))       // Cas QCM moyen
            {
                while (!entreeUtilisateurOK)
                {
                    System.out.println("Sélectionner un des entiers (réponse 1, 2 ou 3) :");
                    try
                    {
                        System.out.println("\nVotre réponse :");
                        String entreUtilisateur = this.participantsDeLaPhase.JoueursVect.get(joueurActif).saisirReponse();
                        int test = Integer.parseInt(entreUtilisateur);

                        if(test>0&&test<4)
                        {
                            entreeUtilisateurOK = true;
                            answer = entreUtilisateur;
                        }
                        else
                            System.out.println("\nRentrez 1, 2 ou 3 !\n");
                    }
                    catch (Exception e)
                    {
                        System.out.println("\nVeuillez rentrer un entier !!\n");
                    }
                }
            }
            else if(questionChoisie.getQuestion().getType().equals("RC"))   // Cas RC
            {
                System.out.println("Saisir une réponse courte :");
                System.out.println("\nVotre réponse :");
                String entreUtilisateur = this.participantsDeLaPhase.JoueursVect.get(joueurActif).saisirReponse();
                answer = entreUtilisateur;
                entreeUtilisateurOK = true;
            }
            else if (questionChoisie.getQuestion().getType().equals("VF"))  // Cas VF
            {
                while (!entreeUtilisateurOK)
                {
                    System.out.println("Ecrire VRAI ou FAUX, V ou F en abrégé :");
                    String entreUtilisateur = this.participantsDeLaPhase.JoueursVect.get(joueurActif).saisirReponse();
                    if(!entreUtilisateur.equals("VRAI")&&!entreUtilisateur.equals("V")
                            &&!entreUtilisateur.equals("FAUX")&&!entreUtilisateur.equals("F"))
                        System.out.println("Veuillez saisir VRAI / FAUX / V / F");
                    else
                        entreeUtilisateurOK = true;
                    answer = entreUtilisateur;
                }
            }
            copieDefQuestion.getQuestionsList().remove(questionChoisie);
            copieDefTheme.getThemes().remove(selectedTheme);
            this.participantsDeLaPhase.JoueursVect.get(joueurActif).MAJScore(questionChoisie.checkAnswer(answer),this.getPhaseDeJeu());
            this.chronos.get(joueurActif).pause();                          // Chrono en pause
            System.out.println("Délais de réponse total "+this.chronos.get(joueurActif).getTemps()+" millisecondes");
        }
        this.gagnantDeLaPhase = this.retournerGagnants();
        Phase_3 p3 = new Phase_3();                                         // Création de la phase 3
        p3.jouer(this.gagnantDeLaPhase,themesList,copieDefQuestion);        // Lancement
    }

}
