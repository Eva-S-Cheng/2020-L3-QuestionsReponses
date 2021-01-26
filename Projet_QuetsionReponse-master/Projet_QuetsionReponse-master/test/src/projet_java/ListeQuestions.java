package projet_java;

import java.util.ArrayList;
import java.util.LinkedList;

public class ListeQuestions {
    private ArrayList<Question<? extends TypeQuestion>> questionsList = new ArrayList<>(); // Au cas où
    private int selectedQuestionIndex = -1;

    public ListeQuestions(){
        this.questionsList = new ArrayList<>();
    }

    public ListeQuestions(ArrayList<Question<? extends TypeQuestion>> questionsList) {
        if(questionsList == null) throw new NullPointerException("L'espace mémoire de la liste n'est pas alloué");
        if(questionsList.isEmpty()) throw new IllegalArgumentException("Rien dans le contenu de la liste");
        this.questionsList = questionsList;
    }

    public void afficherListe() {
        System.out.println("LISTE COMPLETE DES QUESTIONS : ");
        questionsList.forEach(Question::afficher); // Affichage de toutes les questions de la liste
    }

    public boolean ajouterQuestion(Question<? extends TypeQuestion> question) {
        return this.questionsList.add(question);
    }

    public void supprimerQuestion(int n) {
        if(n-1 < 0 || n-1 >= questionsList.size()){ // Si l'index de la question est dans la liste
            return;
        }
        questionsList.remove(n-1);
        selectedQuestionIndex =-1;
    }

    public Question selectionnerQuestion(int difficulte, int index){        // On chercher selon la difficulte
        while(index!=0)                                                     // Si index par difficulté dépasse, on revient à 0
        {
            for (int i = 0; i < questionsList.size(); i++)
            {
                if(questionsList.get(i).getLevel()==difficulte)
                {
                    index--;
                    if(index==0)
                    {
                        this.selectedQuestionIndex = i;
                        return questionsList.get(i);
                    }
                }
            }
        }
        this.selectedQuestionIndex= 0;
        return null;                                                        // Pas trouvé la question
    }

    public void afficherParNiveau(int i)                                    // Afficher toutes les questions d'un niveau
    {
        for(int j = 0; j < questionsList.size(); j++)
        {
            if(questionsList.get(j).getLevel()==i)                          // Si level souhaité, on affiche
                this.questionsList.get(j).afficher();
        }
    }

    public ArrayList<Question> returnQuestionParNiveau(int niveau)          // Retourner toutes les questions d'un niveau
    {
        ArrayList<Question> questionsNiveau = new ArrayList<>();
        for(int j = 0; j < questionsList.size(); j++)
        {
            if(questionsList.get(j).getLevel()==niveau)
                questionsNiveau.add(questionsList.get(j));                  // Si level souhaité, on ajoute
        }
        return questionsNiveau;
    }

    public ArrayList<Question<? extends TypeQuestion>> getQuestionsList() {
        return questionsList;
    }
}
