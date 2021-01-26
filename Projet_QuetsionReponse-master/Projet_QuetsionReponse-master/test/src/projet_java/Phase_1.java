package projet_java;

import java.util.Random;

public class Phase_1 extends Phase {
    final int NUMBER_JOUEURS_PHASE = 4;
    @Override
    public void selectionnerJoueurs(EnsJoueurs joueursTotal) {
        int numberAjout = 0;
        while (numberAjout!=NUMBER_JOUEURS_PHASE)
        {
            Joueurs joueurTampon = joueursTotal.SelectionnerJoueur();       // Joueurs sélectionné
            if(joueurTampon.getEtat()==5)                                   // Si en attente, sélectionnable
            {
                if (numberAjout==0||!this.participantsDeLaPhase.JoueursVect.contains(joueurTampon)) {
                    this.participantsDeLaPhase.JoueursVect.add(joueurTampon);// Ajouter joueur tampon aux participants
                    numberAjout++;                                          // Nombre de joueurs actifs
                    joueurTampon.changerEtat(3);                            // Deviens sélectionné
                }
            }
        }
        System.out.println("Les joueurs sélectionnés sont : ");
        for (Joueurs e: this.participantsDeLaPhase.JoueursVect) {
            e.Afficher();                                                   // Affichage de vérification
        }
    }

    @Override
    public void jouer(EnsJoueurs joueursTotal, Themes themesList,ListeQuestions questions) {
        this.PhaseDeJeu = 1;
        this.selectionnerJoueurs(joueursTotal);                             // Sélection aléatoire des joueurs
        for (int i = 0; i < NUMBER_JOUEURS_PHASE; i++)                      // Création de chronometres
        {
            this.chronos.add(i, new Chrono());                              // Démarrage du chronomètre
            this.chronos.get(i).start();
            this.chronos.get(i).pause();
        }
        Random rd = new Random();
        int selectInd = (rd.nextInt(100))%10;                       // Sélection alétoire du thème
        themesList.selectionnerTheme(selectInd);
        ListeQuestions copieDefQuestion = new ListeQuestions();
        for (int i = 0; i<questions.getQuestionsList().size(); i++)
        {
            copieDefQuestion.ajouterQuestion(questions.getQuestionsList().get(i));
        }

        int selectedQuestion = (rd.nextInt(1000));                  // Aléatoire

        for (int j = 0; j < NUMBER_JOUEURS_PHASE; j++)
        {
            boolean entreeUtilisateurOK = false;
            String selectedTheme = themesList.selectionnerTheme((themesList.getSelectedIndex()+1)%10);
            System.out.println("------------------ THEME : "+selectedTheme+" ------------------");
            ThemeQuestion questionsPossibles = new ThemeQuestion(selectedTheme, copieDefQuestion);

            int typeQuestion = rd.nextInt(3);                       // 1 pour QCM 2 pour VF et 3 pour RC

            /** Mettre un switch **/

            /** A REMPLACER PAR RECHERCHE DANS LA BDD **/  // SELECT * FROM QCM/RC/VF WHERE THEME = ... AND DIFFICULTE =...

            Question questionChoisie =                                      // Parmi les questions du theme et des questions faciles
                    questionsPossibles.questions.
                            selectionnerQuestion(1,selectedQuestion%
                                    questionsPossibles.questions.getQuestionsList().size());
            /** **/
            System.out.println("\nC'est au tour de :");
            this.participantsDeLaPhase.JoueursVect.get(j).Afficher();       // Appel le joueur à jouer
            System.out.println("Votre question est la suivante : ");
            questionChoisie.afficher();                                     // Afficher la question
            String answer = null;
            copieDefQuestion.getQuestionsList().remove(questionChoisie);    // Enleve de la liste des questions tirables
            this.chronos.get(j).resume();
            if(questionChoisie.getQuestion().getType().equals("QCM"))       // Si QCM
            {
                while (!entreeUtilisateurOK)
                {
                    System.out.println("Sélectionner un des entiers (réponse 1, 2 ou 3) :");
                    try
                    {
                        System.out.println("\nVotre réponse :");            // Demande un entier à l'utilisateur
                        String entreUtilisateur = this.participantsDeLaPhase.JoueursVect.get(j).saisirReponse();
                        int test = Integer.parseInt(entreUtilisateur);

                        if(test>0&&test<4)                                  // Gestion de la saisie
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
            else if(questionChoisie.getQuestion().getType().equals("RC"))   // Réponse courte
            {
                System.out.println("Saisir une réponse courte :");
                System.out.println("\nVotre réponse :");
                String entreUtilisateur = this.participantsDeLaPhase.JoueursVect.get(j).saisirReponse();
                answer = entreUtilisateur;
                entreeUtilisateurOK = true;
            }
            else if (questionChoisie.getQuestion().getType().equals("VF"))  // Question vrai faux
            {
                while (!entreeUtilisateurOK)
                {
                    System.out.println("Ecrire VRAI ou FAUX, V ou F en abrégé :");
                    String entreUtilisateur = this.participantsDeLaPhase.JoueursVect.get(j).saisirReponse();
                    if(!entreUtilisateur.equals("VRAI")&&!entreUtilisateur.equals("V")
                            &&!entreUtilisateur.equals("FAUX")&&!entreUtilisateur.equals("F"))
                        System.out.println("Veuillez saisir VRAI / FAUX / V / F");
                    else
                        entreeUtilisateurOK = true;                         // Saisie validée
                    answer = entreUtilisateur;
                }
            }
            this.participantsDeLaPhase.JoueursVect.get(j).MAJScore(questionChoisie.checkAnswer(answer),this.getPhaseDeJeu());
            this.chronos.get(j).pause();
            System.out.println("Délais de réponse "+this.chronos.get(j).getTemps()+" millisecondes");

        }
        this.gagnantDeLaPhase = this.retournerGagnants();                   // Stocke les gagnants de la phase
        Phase_2 p2 = new Phase_2();
        p2.jouer(this.gagnantDeLaPhase,themesList,copieDefQuestion);        // Enchainer sur la phase 2
    }
}
