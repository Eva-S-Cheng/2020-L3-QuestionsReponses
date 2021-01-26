package Projet;

public class Question<T> {
    private String texte;
    private int numero;
    private String theme;
    private String difficulte;

    private T reponse;
    private String type;
    private static int NUMERO = 1;

    public Question(String texte, String theme, String difficulte, String type)
    {
        this.texte = texte;
        this.theme = theme;
        this.difficulte = difficulte;
        this.numero = NUMERO;
        NUMERO++;
        this.type = type;
    }

    public void saisirReponse(String laReponse)
    {
        
    }


    public void entrerReponse(T reponse)
    {
        this.reponse = reponse;
    }

}
