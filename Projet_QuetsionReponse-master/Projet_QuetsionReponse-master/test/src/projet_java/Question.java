package projet_java;

import java.util.Scanner;

public class Question<T extends TypeQuestion> {
    private int number = 0;
    private String theme;
    private int level = 0;
    private T question;                                             // Ne peut pas h�riter de la classe question car field T dedans

    private static int NUMBER_QUESTIONS = 0;                        // Nombre total de questions

    public Question(String theme, int level, T question)
    {
        NUMBER_QUESTIONS++;
        this.number = NUMBER_QUESTIONS;
        if(theme == null) throw new NullPointerException("Espace mémoire pour thème non alloué");
        if(theme.isBlank()) throw new IllegalArgumentException("Thème vide !");
        this.theme = theme;
        if(level < 1 || level > 3) throw new IllegalArgumentException("Niveau rentré non valide");
        this.level = level;
        if(question == null) throw new NullPointerException("Espace mémoire de question non alloué");
        this.question = question;
    }

    public void afficher() {
        System.out.println(this.number+" : Thème : "+this.theme+"        |       Niveau : "+this.levelToString());
        this.question.afficher();                                   // Implémenté plus tard
    }

    public void saisir(){                                           // Saisir les informations de la questions / la réponse attendue
        System.out.println("Saisir votre question :");
        question.saisir();                                          // Selon le type de question
    }

    public boolean checkAnswer(String s){
        if(this.question.checkAnswer(s))
        {
            System.out.println("Bravo !");
            return true;
        }
        System.out.println("Dommage !");
        return false;
    }

    public int getLevel() {                                         // 1 2 ou 3
        return level;
    }

    public String levelToString()                                   // Converti le niveau en langage humain adapté
    {
        if (this.level==1)
            return "Facile";
        if (this.level==2)
            return "Moyen";
        if (this.level==3)
            return "Difficile";
        return "Non défini";
    }

    public String getTheme() {
        return theme;
    }

    public T getQuestion() {
        return question;
    }
}
