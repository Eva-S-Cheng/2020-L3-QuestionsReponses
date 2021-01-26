package projet_java;

import java.util.Scanner;

public class QuestionVF implements TypeQuestion{
    private String text;
    private boolean answer;

    public QuestionVF(){}

    public QuestionVF(String text, boolean answer) {
        if(text == null) throw new NullPointerException("Espace mémoire de 'text' non alloué");
        if(text.isBlank()) throw new IllegalArgumentException("Question non définie");

        this.text = text;
        this.answer = answer;
    }



    @Override
    public boolean checkAnswer(String answer) {
        if(answer.toUpperCase().equals("VRAI")||answer.toUpperCase().equals("V"))
            return (this.answer == true);
        else
            return (this.answer == false);
    }

    @Override
    public void afficher() {
        System.out.println(this.text+" (Saisir V, Vrai, F ou Faux) : ");
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

        boolean ok = false;
        while (!ok)
        {
            Scanner scan = new Scanner(System.in);
            System.out.println("Est-ce vrai ou faux ? (Saisir Vrai, Faux, V ou F)");
            String ans = sc.nextLine();

            if(ans.toUpperCase().equals("V")||ans.toUpperCase().equals("VRAI"))
            {
                this.answer = true;
                ok = true;
            }
            else if (ans.toUpperCase().equals("F")||ans.toUpperCase().equals("FAUX"))
            {
                this.answer = false;
                ok = true;
            }
            else
                System.out.println("Saisir une réponse valide !");
        }
    }

    @Override
    public String getType() {
        return "VF";
    }
}
