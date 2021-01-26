package projet_java;

import java.util.Random;

public class Phase_3 extends Phase {
    final int NUMBER_JOUEURS_PHASE = 2;
    final int NUMBER_OF_QUESTIONS = 6;
    @Override
    public void selectionnerJoueurs(EnsJoueurs joueursGagnantsPhase2) {
        this.participantsDeLaPhase = joueursGagnantsPhase2;
    }

    @Override
    public void jouer(EnsJoueurs joueursGagnantsPhase2, Themes themesList,ListeQuestions questions) {
        this.PhaseDeJeu = 2;
        this.selectionnerJoueurs(joueursGagnantsPhase2);                    // Sélection aléatoire des joueurs
        for (int i = 0; i < NUMBER_JOUEURS_PHASE; i++)                      // Création de chronometres
        {
            this.chronos.add(i, new Chrono());
            this.chronos.get(i).start();                                    // Démarrage fictif du chronomètre
            this.chronos.get(i).pause();
        }
        Random rd = new Random();
        Themes copieDefTheme = new Themes();                                // Copie défensive
        copieDefTheme.getThemes().clear();
        copieDefTheme.getThemes().add(themesList.getThemes().get(2));       // On choisit les thèmes
        copieDefTheme.getThemes().add(themesList.getThemes().get(4));
        copieDefTheme.getThemes().add(themesList.getThemes().get(6));

        ListeQuestions copieDefQuestion = new ListeQuestions();
        for (int i = 0; i<questions.getQuestionsList().size(); i++)
        {
            copieDefQuestion.ajouterQuestion(questions.getQuestionsList().get(i));
        }

        int selectedQuestion = (rd.nextInt(1000));                  // Aléatoire

        for (int j = 0; j < NUMBER_OF_QUESTIONS; j++)
        {
            int currentTheme = -1;
            int joueurActif = j%2;                                          // Politique Round Robin
            int aleaTheme = (rd.nextInt(100))%copieDefTheme.getThemes().size();
            if (j==0||j==1)
                currentTheme = 0;                                           // Sélection du thème
            else if(j==2||j==3)
                currentTheme = 1;
            else
                currentTheme = 2;
            String selectedTheme = copieDefTheme.selectionnerTheme(currentTheme);
            boolean entreeUtilisateurOK = false;
            System.out.println("------------------ THEME : "+selectedTheme+" ------------------");
            ThemeQuestion questionsPossibles = new ThemeQuestion(selectedTheme, copieDefQuestion);

            /** Mettre un switch **/

            /** A REMPLACER PAR RECHERCHE DANS LA BDD **/  // SELECT * FROM QCM/RC/VF WHERE THEME = ... AND DIFFICULTE =...
            Question questionChoisie =
                    questionsPossibles.questions.
                            selectionnerQuestion(3,selectedQuestion%
                                    questionsPossibles.questions.getQuestionsList().size());
            /** **/

            System.out.println("\nC'est au tour de :");
            this.participantsDeLaPhase.JoueursVect.get(joueurActif).Afficher();
            System.out.println("Votre question est la suivante : ");
            questionChoisie.afficher();
            String answer = null;
            copieDefQuestion.getQuestionsList().remove(questionChoisie);
            this.chronos.get(joueurActif).resume();
            if(questionChoisie.getQuestion().getType().equals("QCM"))
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
            else if(questionChoisie.getQuestion().getType().equals("RC"))
            {
                System.out.println("Saisir une réponse courte :");
                System.out.println("\nVotre réponse :");
                String entreUtilisateur = this.participantsDeLaPhase.JoueursVect.get(joueurActif).saisirReponse();
                answer = entreUtilisateur;
                entreeUtilisateurOK = true;
            }
            else if (questionChoisie.getQuestion().getType().equals("VF"))
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
            this.participantsDeLaPhase.JoueursVect.get(joueurActif).MAJScore(questionChoisie.checkAnswer(answer),this.getPhaseDeJeu());
            this.chronos.get(joueurActif).pause();
            System.out.println("Délais de réponse total "+this.chronos.get(joueurActif).getTemps()+" millisecondes");
        }
        this.gagnantDeLaPhase = this.retournerGagnants();
        this.gagnantDeLaPhase.JoueursVect.get(0).changerEtat(1);
        System.out.println("Super gagnant :");
        this.gagnantDeLaPhase.JoueursVect.get(0).Afficher();
    }

}
