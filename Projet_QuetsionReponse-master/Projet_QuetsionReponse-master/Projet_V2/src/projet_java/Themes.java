package projet_java;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Themes {
    private ArrayList<String> themes = new ArrayList<>();                       // Liste contenant les themes (au nombre de 10)
    private int selectedIndex = -1;                                             // Index sélectionné

    public Themes(){                                                            // Constructeur par défaut
        for(int i = 0; i < 10; i++)
        {
            themes.add(i,"PAS DE THEME");                               // THEME A CHANGER
        }
    }

    public Themes(ArrayList<String> themes){
        if(themes == null) throw new NullPointerException("Espace mémoire de 'themes' non alloué !");

        themes = (ArrayList<String>)themes.stream().filter((theme) -> !theme.isBlank())
                .collect(Collectors.toList());
        if(themes.isEmpty()) throw new IllegalArgumentException("La liste 'themes' passée en paramètres est nulle");
        this.themes = themes;
        for(int i = themes.size(); i < 10; i++)
        {
            themes.set(i,"PAS DE THEME");                                       // Dans le cas où moins de 10 themes
        }

        themes.replaceAll(String::toUpperCase);
    }

    public void modifierTheme(String initTheme, String finalTheme) {
        if(!themes.contains(initTheme.toUpperCase())) {                         // Unicité du format de la chaine de caractere
            System.out.println("Thème initial non trouvé !");
        }
                                                                                // Remplacer la première occurrence
        themes.set(themes.indexOf(initTheme.toUpperCase()), finalTheme.toUpperCase());
    }

    public String selectionnerTheme(int selectedIndex) {
        if(selectedIndex >= themes.size()) {
            System.out.println("Veuillez réessayer, index non trouvé !");       // Theme trop grand
        }
        this.selectedIndex = selectedIndex;                                     // Opération effectuée
        return this.themes.get(selectedIndex);
    }

    public ArrayList<String> selectionnerCinqThemes(boolean optionRand) {        // 1 pour selection utilisateur, 2 pour random

        // Travail sur la copie pour pouvoir éliminer les themes déjà sélectionnés
        ArrayList<String> themeCopy = (ArrayList<String>)themes.stream().collect(Collectors.toList());
        ArrayList<String> selectedThemes = new ArrayList<>();

        while(themeCopy.remove("PAS DE THEME")) { }                         // Remove all "Pas de theme"
        int number = 5;
        if(number>themeCopy.size())
        {
            number = themeCopy.size();                                          // Pour éviter les dépassement de mémoire
        }

        // Choix d'un thème de manière aléatoire
        if(optionRand)
        {
            for(int i = 0; i < number; i++) {
                int randomIndex = (int)(Math.random() * themeCopy.size());      // Valeur aléatoire
                selectedThemes.add(themeCopy.get(randomIndex));                 // Ajouter à la liste
                themeCopy.remove(randomIndex);                                  // Enlever des disponibles
            }
        }
        else
        {
            boolean ok = false;
            int userChoice = 0;
            for(int i = 0; i < number; i++) {
                while(!ok)                                                      // Gestion des exceptions
                {
                    try
                    {
                        Scanner sc = new Scanner(System.in);
                        System.out.println("LISTE DES THEMES DISPONIBLES :");
                        for (int j = 0; j < themeCopy.size(); j++)
                        {
                            System.out.println((j+1) + " )" +themeCopy.get(j)); // Affichage
                        }
                        userChoice = Integer.parseInt(sc.nextLine());
                        if (userChoice > 0 && userChoice <= themeCopy.size()) {
                            if(themes.get(userChoice-1).equals("PAS DE THEME"))
                            {
                                System.out.println("Choisir un theme valide");  // Pas de theme
                            }
                            else
                                ok = true;                                      // Meilleur des cas
                        }
                        else                                                    // Ne fais pas partie des options proposées
                        {
                            System.out.println("Rentrer un des nombres proposés !");
                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println("Saisir un entier !");               // Saisie n'est pas un entier
                    }
                }
                selectedThemes.add(themeCopy.get(userChoice));
                themeCopy.remove(userChoice);                                   // Enlever
            }
        }

        return selectedThemes;                                                  // Tableau des sélectionnés
    }

    public void afficher() {
        System.out.println("LISTE DES THEMES : ");                              // Afficher
        for(int i = 0; i < themes.size(); i++)
        {
            System.out.println((i+1)+" ) "+themes.get(i));
        }
        System.out.print("Thème choisi précédemment : ");
        if(selectedIndex==-1)
            System.out.println("PAS DE THEME CHOISI");
        else
            System.out.println(themes.get(selectedIndex));
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public ArrayList<String> getThemes() {
        return themes;
    }
}
