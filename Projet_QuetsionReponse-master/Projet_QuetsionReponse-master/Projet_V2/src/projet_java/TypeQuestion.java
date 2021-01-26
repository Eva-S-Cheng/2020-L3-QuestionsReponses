package projet_java;

public interface TypeQuestion {
    boolean checkAnswer(String answer);
    void afficher();
    void saisir();
    String getType();
}
