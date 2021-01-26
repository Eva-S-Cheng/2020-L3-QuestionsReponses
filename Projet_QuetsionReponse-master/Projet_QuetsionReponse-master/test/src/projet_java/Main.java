package projet_java;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	
	public static void fillQuestionsParTheme(ArrayList<ThemeQuestion> qpt,Themes th,ListeQuestions l) {
		for(int i = 0; i < th.getThemes().size(); i++)
        {
        	ListeQuestions temp = new ListeQuestions();
        	for ( int j = 0 ; j < l.getQuestionsList().size() ; j++ ) {
        		if ( l.getQuestionsList().get(j).getTheme().equals(th.getThemes().get(i)))  {	
        			temp.ajouterQuestion(l.getQuestionsList().get(j));
        		}
        	}
        	ThemeQuestion TQ = new ThemeQuestion(th.getThemes().get(i),temp);
			qpt.add(TQ);
        }
	}
	
    public static void main(String[] args) throws SQLException {

        // Thèmes de base
        ArrayList<ThemeQuestion> questionsParTheme = new ArrayList<>();

        // Initialisation au préalable au cas où
        
        Menu menu = new Menu(); // Initialisation interface graphique du menu
        Themes themes = new Themes(menu.themes); // Ajout d'une liste de th�mes
        ListeQuestions liste = menu.listeQ;     
        fillQuestionsParTheme(questionsParTheme,themes,liste);
        
        EnsJoueurs auto = new EnsJoueurs();
        auto.CreerJoueursAuto();

        //Phase_1 p1 = new Phase_1();
        //p1.jouer(auto,themes,liste);

        int choixUtilisateur = 0;
        boolean ok;
        Scanner sc = new Scanner(System.in);
        while (choixUtilisateur !=8) {                                  // Menu utilisateur
            System.out.println("\n\nVeuillez faire un choix : " +
                    "\n     1  : Afficher les 10 thèmes" +
                    "\n     2  : Ajouter une liste de question pour chaque thème" +
                    "\n     3  : Afficher toutes les questions d'un niveau n donné par thème" +
                    "\n     4  : Ajouter une question à la liste pour un thème donné" +
                    "\n     5  : Supprimer une question de la liste pour un thème donné" +
                    "\n     6  : Créer le tableau de joueurs et l'afficher" +
                    "\n     7  : Lancer une partie avec 4 joueurs choisis en affichant toutes les étapes du déroulement" +
                    "\n     8  : Quitter le jeu" +
                    "\n     9  : Modifier un thème" +
                    "\n    10  : Afficher les règles" +
                    "\n    11  : Afficher toutes les questions" +
                    "\n    12  : Créer les thèmes manuellement"+
                    "\n    13  : Jouer au grand jeu");
            ok = false;
            while (!ok)                                                 // Gestion des exceptions
            {
                try {                                                   // On regarde si ce que l'utilsateur rentre un nombre
                    System.out.print("Entrez un nombre entier : ");
                    choixUtilisateur = Integer.parseInt(sc.nextLine());
                    if(choixUtilisateur>0&&choixUtilisateur<14)
                        ok = true;                                      // On a rentré un nombre
                    else
                        System.out.println("Choisir entre 1 et 13 !");
                } catch (Exception e) {
                    System.out.println("Vous devez saisir un nombre !");
                }
            }
            switch (choixUtilisateur) {                                 // Afficher les thèmes
                case 1:
                    System.out.println("\n-----------------------------------------------------------");
                    System.out.println("                           THEMES                          ");
                    System.out.println("-----------------------------------------------------------\n");
                    themes.afficher();
                    break;
                case 2:
                    System.out.println("\n-----------------------------------------------------------");
                    System.out.println("                     CREER DES QUESTIONS                   ");
                    System.out.println("-----------------------------------------------------------\n");
                    ListeQuestions questionsCrees = new ListeQuestions();
                    for(int i = 0; i < 10; i++)
                    {                                                   // Pour chaque thème, demande à l'utilisateur ce qu'ils veut rentrer
                        System.out.println("\n--------------------------------------------------------------\n");
                        System.out.println("Thèmes "+(i+1)+" : "+themes.getThemes().get(i));
                        System.out.println("Combien de questions voulez vous créer pour ce thème ? (Minimum 10, recommandé)");
                        boolean saisieOk = false;
                        int saisie = 0;
                        int choixQuestion=0;
                        while (!saisieOk)
                        {
                            try {
                                saisie = Integer.parseInt(sc.nextLine());
                                saisieOk = true;                       // On a rentré un nombre
                            } catch (Exception e) {
                                System.out.println("Vous devez saisir un nombre !");
                            }
                        }
                        for (int j = 0; j < saisie; j++)                // Saisir j questions
                        {
                            System.out.println("\nThèmes "+(i+1)+" : "+themes.getThemes().get(i)+", Question "+(j+1)+" :");
                            ok = false;
                            while (!ok){
                                System.out.println("\nQue voulez-vous saisir ? (Sélectionner un entier)" +
                                        "\n 1. QCM" +
                                        "\n 2. Question Vrai / Faux" +
                                        "\n 3. Question à réponse courte");
                                try {
                                    choixQuestion = Integer.parseInt(sc.nextLine());
                                    if (choixQuestion>0&&choixQuestion<4)
                                        ok = true;                       // On a rentré un nombre
                                    else
                                        System.out.println("Saisir un option valable s'il vous plaît !");
                                } catch (Exception e) {
                                    System.out.println("Vous devez saisir un nombre !");
                                }
                            }
                            ok = false;
                            int difficulte = 0;
                            while (!ok)
                            {
                                System.out.println("\nQuelle est la difficulté de la question ? (Sélectionner un entier)" +
                                        "\n 1. Facile" +
                                        "\n 2. Moyen" +
                                        "\n 3. Difficile");
                                try {
                                    difficulte = Integer.parseInt(sc.nextLine());
                                    if (difficulte>0&&difficulte<4)
                                        ok = true;                       // On a rentré un nombre
                                    else
                                        System.out.println("Saisir un option valable s'il vous plaît !");
                                } catch (Exception e) {
                                    System.out.println("Vous devez saisir un nombre !");
                                }
                            }
                            if(choixQuestion==1) {
                                QuestionQCM questionSaisie = new QuestionQCM();
                                questionSaisie.saisir();
                                Question nouvelleQuestion = new Question(themes.getThemes().get(i),difficulte,questionSaisie);
                                liste.ajouterQuestion(nouvelleQuestion);
                            }
                            else if(choixQuestion==2)
                            {
                                QuestionVF questionSaisie = new QuestionVF();
                                questionSaisie.saisir();
                                Question nouvelleQuestion = new Question(themes.getThemes().get(i),difficulte,questionSaisie);
                                liste.ajouterQuestion(nouvelleQuestion);
                            }
                            else if(choixQuestion==3)
                            {
                                QuestionRC questionSaisie = new QuestionRC();
                                questionSaisie.saisir();
                                Question nouvelleQuestion = new Question(themes.getThemes().get(i),difficulte,questionSaisie);
                                liste.ajouterQuestion(nouvelleQuestion);
                            }
                        }
                    }
                    break;
                case 3:
                    System.out.println("\n-----------------------------------------------------------");
                    System.out.println("       AFFICHAGE DE QUESTIONS PAR THEME PAR NIVEAUX        ");
                    System.out.println("-----------------------------------------------------------\n");
                    for(int i = 0; i < themes.getThemes().size(); i++)
                    {
                        ThemeQuestion TQ = new ThemeQuestion(themes.getThemes().get(i),liste);
                        questionsParTheme.add(TQ);
                    }
                    ok = false;
                    int difficulte = 0;                                     // Contient le niveau
                    while (!ok)
                    {
                        System.out.println("\nQuelles questions voulez vous afficher ? (Sélectionner un entier)" +
                                "\n 1. Faciles" +
                                "\n 2. Moyens" +
                                "\n 3. Difficiles");
                        try {
                            difficulte = Integer.parseInt(sc.nextLine());   // Niveau entré
                            if (difficulte>0&&difficulte<4)
                                ok = true;                                  // On a rentré un nombre
                            else
                                System.out.println("Saisir un option valable s'il vous plaît !");
                        } catch (Exception e) {
                            System.out.println("Vous devez saisir un nombre !");
                        }
                    }
                    for(int i = 0; i < questionsParTheme.size(); i++)
                    {
                        System.out.println("\nTHEME : "+questionsParTheme.get(i).theme+" | DIFFICULTE : "+difficulte);
                        questionsParTheme.get(i).questions.afficherParNiveau(difficulte);
                        System.out.println("");
                    }
                    break;
                case 4:
                    System.out.println("\n-----------------------------------------------------------");
                    System.out.println("                      AJOUT DE QUESTION                    ");
                    System.out.println("-----------------------------------------------------------\n");
                    ok = false;
                    int theme=0;
                    while (!ok)
                    {
                        System.out.println("\nSélectionner un thème auquel vous voulez ajouter une question :");
                        themes.afficher();
                        try {
                            theme = Integer.parseInt(sc.nextLine());
                            if (theme>0&&theme<11)
                                ok = true;                       // On a rentré un nombre
                            else
                                System.out.println("Saisir un option valable s'il vous plaît !");
                        } catch (Exception e) {
                            System.out.println("Vous devez saisir un nombre !");
                        }
                    }
                    ok = false;
                    int choixQuestion=0;
                    while (!ok){
                        System.out.println("\nQue voulez-vous saisir ? (Sélectionner un entier)" +
                                "\n 1. QCM" +
                                "\n 2. Question Vrai / Faux" +
                                "\n 3. Question à réponse courte");
                        try {
                            choixQuestion = Integer.parseInt(sc.nextLine());
                            if (choixQuestion>0&&choixQuestion<4)
                                ok = true;                       // On a rentré un nombre
                            else
                                System.out.println("Saisir un option valable s'il vous plaît !");
                        } catch (Exception e) {
                            System.out.println("Vous devez saisir un nombre !");
                        }
                    }
                    ok = false;
                    int diff = 0;
                    while (!ok)
                    {
                        System.out.println("\nQuelle est la difficulté de la question ? (Sélectionner un entier)" +
                                "\n 1. Facile" +
                                "\n 2. Moyen" +
                                "\n 3. Difficile");
                        try {
                            diff = Integer.parseInt(sc.nextLine());
                            if (diff>0&&diff<4)
                                ok = true;                       // On a rentré un nombre
                            else
                                System.out.println("Saisir un option valable s'il vous plaît !");
                        } catch (Exception e) {
                            System.out.println("Vous devez saisir un nombre !");
                        }
                    }
                    if(choixQuestion==1) {
                        QuestionQCM questionSaisie = new QuestionQCM();
                        questionSaisie.saisir();
                        Question nouvelleQuestion = new Question(themes.getThemes().get(theme-1),diff,questionSaisie);
                        liste.ajouterQuestion(nouvelleQuestion);
                    }
                    else if(choixQuestion==2)
                    {
                        QuestionVF questionSaisie = new QuestionVF();
                        questionSaisie.saisir();
                        Question nouvelleQuestion = new Question(themes.getThemes().get(theme-1),diff,questionSaisie);
                        liste.ajouterQuestion(nouvelleQuestion);
                    }
                    else if(choixQuestion==3)
                    {
                        QuestionRC questionSaisie = new QuestionRC();
                        questionSaisie.saisir();
                        Question nouvelleQuestion = new Question(themes.getThemes().get(theme-1),diff,questionSaisie);
                        liste.ajouterQuestion(nouvelleQuestion);
                    }
                    break;
                case 5:
                    System.out.println("\n-----------------------------------------------------------");
                    System.out.println("                  SUPPRESSION DE QUESTION                  ");
                    System.out.println("-----------------------------------------------------------\n");
                    ok = false;
                    theme=0;
                    while (!ok)
                    {
                        System.out.println("\nSélectionner un thème auquel vous voulez ajouter une question :");
                        themes.afficher();
                        try {
                            theme = Integer.parseInt(sc.nextLine());
                            if (theme>0&&theme<11)
                                ok = true;                       // On a rentré un nombre
                            else
                                System.out.println("Saisir un option valable s'il vous plaît !");
                        } catch (Exception e) {
                            System.out.println("Vous devez saisir un nombre !");
                        }
                    }
                    ThemeQuestion selectedThemeQuestions = new ThemeQuestion(themes.getThemes().get(theme-1), liste);
                    System.out.println("Que voulez vous supprimer ?");
                    int supprime = 0;
                    ok = false;
                    while (!ok)
                    {
                        System.out.println("\nSélectionner un numéro de question :");
                        themes.afficher();
                        try {
                            supprime = Integer.parseInt(sc.nextLine());
                            if (supprime>0&&supprime<selectedThemeQuestions.questions.getQuestionsList().size()+1)
                                ok = true;                                                  // On a rentré un nombre
                            else
                                System.out.println("Saisir un option valable s'il vous plaît !");
                        } catch (Exception e) {
                            System.out.println("Vous devez saisir un nombre !");
                        }
                    }
                    liste.getQuestionsList().remove(selectedThemeQuestions.questions.getQuestionsList().get(supprime-1)); // Surpimer de la liste
                    selectedThemeQuestions.questions.supprimerQuestion(supprime-1);     // Supprimer la question
                    System.out.println("\nVotre question a été supprimée !");
                    selectedThemeQuestions.questions.afficherListe();                       // Affiche la nouvelle liste
                    break;
                case 6:
                    System.out.println("\n-----------------------------------------------------------");
                    System.out.println("                    CREATION DE JOUEURS                    ");
                    System.out.println("-----------------------------------------------------------\n");
                    System.out.println("\nCréer vos joueurs :");
                    ok = false;
                    choixUtilisateur = 0;
                    while (!ok)
                    {
                        System.out.println("Que voulez vous faire ? " +
                                "\n1. Créer les joueurs vous même en rentrant les noms vous même" +
                                "\n2. Créer 20 joueurs automatiquement");
                        try {
                            choixUtilisateur = Integer.parseInt(sc.nextLine());             // Rentrer soi même ou par ordinateur
                            if (choixUtilisateur==1||choixUtilisateur==2)
                                ok = true;                                                  // On a rentré un nombre
                            else
                                System.out.println("Saisir un option valable s'il vous plaît !");
                        } catch (Exception e) {
                            System.out.println("Vous devez saisir un nombre !");
                        }
                    }
                    switch (choixUtilisateur)                                               // Il crée les joueurs lui même
                    {
                        case 1:
                            auto = null;
                            auto = new EnsJoueurs();
                            auto.CreerJoueurs();
                            break;
                        default:
                            break;
                    }
                    auto.AfficherTout();
                    break;
                case 7:
                    System.out.println("\n-----------------------------------------------------------");
                    System.out.println("                        JOUER AU JEU                       ");
                    System.out.println("-----------------------------------------------------------\n");
                    boolean valide = true;                                                  // Vérifier qu'il y a suffisamment de questions
                    for (int i = 0; i < themes.getThemes().size(); i++)
                    {
                        questionsParTheme.add(i,new ThemeQuestion(themes.getThemes().get(i),liste));
                        for(int j = 0; j < 3; j++)                                          // 3 questions par niveau par thème
                        {
                            if(questionsParTheme.get(i).questions.returnQuestionParNiveau(j+1).size()<3)
                                valide = false;                                             // sinon faux
                        }
                    }
                    if (!valide)
                    {
                        System.out.println("Veuillez saisir au moins 3 questions de chaque niveau pour chaque thème !");
                    }
                    else
                    {
                        Phase_1 p1 = new Phase_1();                                         // démarrer le jeu
                        p1.jouer(auto,themes,liste);
                    }
                    break;
                case 8:
                    System.out.println("\n-----------------------------------------------------------");
                    System.out.println("                          QUITTER                          ");
                    System.out.println("-----------------------------------------------------------\n");
                    System.out.println("Au revoir et bonne journée !");                     // Quitter le jeu
                    break;
                case 9:
                    System.out.println("\n-----------------------------------------------------------");
                    System.out.println("                     MODIFIER UN THEME                     ");
                    System.out.println("-----------------------------------------------------------\n");
                    ok = false;
                    theme=0;
                    while (!ok)
                    {
                        System.out.println("\nSélectionner un thème que vous voulez modifier :");
                        themes.afficher();
                        try {
                            theme = Integer.parseInt(sc.nextLine());                        // Choisir un thème à modifier
                            if (theme>0&&theme<11)
                                ok = true;                                                  // On a rentré un nombre
                            else
                                System.out.println("Saisir un option valable s'il vous plaît !");
                        } catch (Exception e) {
                            System.out.println("Vous devez saisir un nombre !");
                        }
                    }
                    System.out.println("Quel est le nouveau thème ?");                      // Saisir nouveau thème
                    themes.modifierTheme(themes.getThemes().get(theme-1),sc.nextLine());
                    System.out.println("Votre demande a été prise en compte !\n");
                    themes.afficher();
                    break;
                case 10:
                    System.out.println("\n-----------------------------------------------------------");
                    System.out.println("                           RÈGLES                          ");
                    System.out.println("-----------------------------------------------------------\n");
                    System.out.println("Voici les règles du jeu :" +
                            "\n     1. On a une liste de 20 joueurs" +
                            "\n     2. Parmi ces 20 joueurs, 4 sont sélectionnés aléatoirement pour participer à la phase 1" +
                            "\n     3. PHASE I : Chaque joueur répond à une question facile choisie aléatoire selon un thème généré en séquentiel" +
                            "\n     4. SELECTION : Les joueurs ayant répondu correctement ont 2 points en plus et sont éliminés ceux qui ont mal répondu" +
                            "\n     5. PHASE II : 6 thèmes sont sélectionnés aléatoirement, chaque joueur choisi un thème parmi les 6 et répond à une question de niveau moyenne générée aléatoirement" +
                            "\n     6. SELECTION : Les joueurs ayant répondu correctement ont 3 points en plus par question, le joueur au score minimal est éliminé" +
                            "\n     7. PHASE III : Chaque joueur réponds à 3 questions difficiles de 3 thèmes choisis par le créateur du jeu" +
                            "\n     8. SELECTION : Les joueurs ayant répondu correctement ont 5 points en plus par question et celui au score minimal est éliminé" +
                            "\n     9. En cas de conflit au niveau des scores, on élimine le joueur au score minimal qui aura mis le plus de temps pour répondre" +
                            "\n    10. En cas de conflit pour les délais, on sélectionne aléatoirement" +
                            "\n    11. BON JEU A VOUS !");
                    break;
                case 11:
                    System.out.println("-----------------------------------------------------------");
                    System.out.println("                   AFFICHER LES QUESTIONS                  ");
                    System.out.println("-----------------------------------------------------------\n");
                    liste.afficherListe();
                    break;
                case 12:
                    System.out.println("\n-----------------------------------------------------------");
                    System.out.println("                     CREATION DE THEMES                    ");
                    System.out.println("-----------------------------------------------------------\n");
                    for(int i = 0; i < 10; i++)
                    {
                        System.out.println("Rentrez le thème "+(i+1)+" : ");                        // Modification de tous les thèmes
                        themes.modifierTheme(themes.getThemes().get(i),sc.nextLine());
                    }
                    themes.afficher();
                    break;
                case 13:
                    System.out.println("\n-----------------------------------------------------------");
                    System.out.println("                         GRAND JEU                         ");
                    System.out.println("-----------------------------------------------------------\n");
                    valide = true;
                    for (int i = 0; i < themes.getThemes().size(); i++)
                    {
                        questionsParTheme.add(i,new ThemeQuestion(themes.getThemes().get(i),liste));
                        for(int j = 0; j < 3; j++)                                                  // Vérfier qu'il y a suffisamment de questions
                        {
                            if(questionsParTheme.get(i).questions.returnQuestionParNiveau(j+1).size()<10)
                                valide = false;
                        }
                    }
                    if (!valide)
                    {
                        System.out.println("Veuillez saisir au moins 10 questions de chaque niveau pour chaque thème !");
                    }
                    else
                    {
                        Phase_1 p11 = new Phase_1();
                        p11.jouer(auto,themes,liste);

                        Phase_1 p12 = new Phase_1();
                        p12.jouer(auto,themes,liste);

                        Phase_1 p13 = new Phase_1();
                        p13.jouer(auto,themes,liste);

                        System.out.println("Les gagnants sont : ");
                        for (int i = 0; i < auto.JoueursVect.size(); i++)
                        {
                            if(auto.JoueursVect.get(i).getEtat()==1)
                                auto.JoueursVect.get(i).Afficher();
                        }
                    }
                    break;
                default:
                    System.out.println("Erreur, rentrez une des 13 propositions");
                    break;
            }
        }
    }
}
