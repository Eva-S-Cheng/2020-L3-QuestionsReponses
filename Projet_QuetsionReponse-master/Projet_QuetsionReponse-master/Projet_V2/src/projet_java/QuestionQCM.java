package projet_java;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class QuestionQCM implements TypeQuestion {
    private String text;
    private ArrayList<String> choice = new ArrayList<>();       // Liste des réponses
    private int answerIndex;                                    // Réponse à la question, rentrer l'index pour soucis de praticité

    public QuestionQCM(){}

    public QuestionQCM(String text, ArrayList<String> choice, int answerIndex) {

        if(text == null) throw new NullPointerException("Pas de question donnée");
        if(choice == null) throw new NullPointerException("Le tableau de réponses est null");
        if(answerIndex<1||answerIndex>choice.size()) throw new NullPointerException("La réponse n'est pas valide");

        // On ne garde que les choix non vide
        choice = (ArrayList<String>)choice.stream().filter((accepted) -> !accepted.isBlank())
                .collect(Collectors.toList());


        if(text.isBlank()) throw new IllegalArgumentException("La question n'est pas définie");
        if(choice.isEmpty()) throw new IllegalArgumentException("Le tableau de choix est vide");
        this.text = text;
        this.choice = choice;
        this.answerIndex = answerIndex;
    }

    @Override
    public String getType(){
        return "QCM";
    }

    @Override
    public void saisir() {                                      // Saisir les choix possibles
        System.out.println("Saisir votre question :");
        Scanner sc = new Scanner(System.in);
        String questionTitle;
        do
        {
            questionTitle = sc.nextLine();
        }while(questionTitle.isBlank());
        this.text = questionTitle;
        System.out.println("Saisir les choix à proposer à l'utilisateur");
        for(int i = 0; i < 3; i++)
        {
            Scanner input = new Scanner(System.in);
            System.out.print("Choix "+(i+1)+" : ");
            choice.add(input.nextLine());
        }
        boolean ok = false;
        int answer = 0;

        System.out.println("---------------------------------------------");
        this.afficher();

        while(!ok)                                                      // Gestion des exceptions
        {
            try {
                Scanner scan = new Scanner(System.in);
                System.out.println("Quelle est la réponse ? (Entrez un entier s'il vous plaît)");
                answer = Integer.parseInt(scan.nextLine());
                if (answer > 0 && answer <= 3) {
                    ok = true;                                          // Meilleur des cas
                } else                                                  // Ne fais pas partie des options proposées
                {
                    System.out.println("Rentrer un des nombres proposés !");
                }
            }
            catch (Exception e)
            {
                System.out.println("Saisir un entier !");
            }
        }
        this.answerIndex = answer;
    }

    @Override
    public boolean checkAnswer(String answer) {
        int ans = 0;
        try {
            ans = Integer.parseInt(answer);
        }
        catch (Exception e)
        {
            System.out.println("La réponse entrée n'est pas un entier donc FAUX !");
            return false;
        }
        return this.answerIndex == ans;
    }

    @Override
    public void afficher() {
        System.out.println(this.text+" (Choisir un entier parmi) :" +
                "\n 1. "+choice.get(0)+
                "\n 2. "+choice.get(1)+
                "\n 3. "+choice.get(2));
    }

}
