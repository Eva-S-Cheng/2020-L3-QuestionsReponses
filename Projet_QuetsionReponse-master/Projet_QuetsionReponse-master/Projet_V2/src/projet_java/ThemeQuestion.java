package projet_java;

public class ThemeQuestion {                                // Classe tampon qui sert à regrouper les questions par theme
    String theme;
    ListeQuestions questions = new ListeQuestions();
    ListeQuestions bibliotheque;

    public ThemeQuestion(String theme, ListeQuestions questions)
    {
        this.theme = theme;

        this.bibliotheque = questions;

        for(int i = 0; i< questions.getQuestionsList().size(); i++)
        {
            if(questions.getQuestionsList().get(i).getTheme().equals(theme))
                this.questions.ajouterQuestion(questions.getQuestionsList().get(i));
        }
    }



    public void MAJliste(){                                 // A utiliser quand nouvelle question est ajoutée
        if(questions.getQuestionsList().get(this.bibliotheque.getQuestionsList().size()-1).getTheme().equals(theme))
            this.questions.ajouterQuestion(this.bibliotheque.getQuestionsList().get(this.bibliotheque.getQuestionsList().size()-1));
    }
}
