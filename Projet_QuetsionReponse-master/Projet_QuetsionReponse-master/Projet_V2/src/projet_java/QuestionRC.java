package projet_java;

import java.util.Scanner;

public class QuestionRC implements TypeQuestion {
    private String text;
    private String answer;

    public QuestionRC(){}

    public QuestionRC(String text, String answer) {
        if(text == null) throw new NullPointerException("Pas de question donnée");
        if(text.isBlank()) throw new IllegalArgumentException("Question vide");

        if(answer == null) throw new NullPointerException("Pas de réponse donnée");
        if(answer.isBlank()) throw new IllegalArgumentException("réponse est vide");

        this.text = text;
    }


    @Override
    public boolean checkAnswer(String answer) {
        return (this.answer.equals(answer));
    }

    @Override
    public void afficher() {
        System.out.println(this.text+" (Saisir une réponse courte)");
    }

    @Override
    public void saisir() {
        System.out.println("Saisir votre question :");
        Scanner sc = new Scanner(System.in);
        String questionTitle;
        do
        {
            questionTitle = sc.nextLine();
        }while(questionTitle.isBlank());
        this.text = questionTitle;

        System.out.println("---------------------------------------------");
        this.afficher();

        System.out.println("Quelle est la réponse ? (Entrez un entier s'il vous plaît)");
        this.answer = sc.nextLine();

    }

    @Override
    public String getType() {
        return "RC";
    }
}
