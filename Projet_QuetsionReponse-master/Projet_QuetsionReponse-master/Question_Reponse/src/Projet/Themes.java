package Projet;

import java.util.Scanner;

public class Themes {

	int indicateur; 
	int n = 10;
	
	//Initialisateur
	
	String t[] = {"histoire","geographie","physique-chimie","svt","litterature","animaux","aliments","musique","cinema","celebrite"};
	
	public void ModifierTheme(String ancienTheme, String nouveauTheme)
	{
		for(int i = 0; i < n; i++)
		{
			if(t[i] == ancienTheme)
			{
				t[i] = nouveauTheme;
			}
		}
	}
	
	public int SelectionnerTheme(String theme)
	{
		for(int i = 0; i < n; i++)
		{
			if(t[i] == theme)
			{
				indicateur = i;
				return indicateur;
			}
		}
		
		return -1;
	}
	
	public void Afficher()
	{
		for(int i = 0; i < n; i++)
		{
			System.out.println(i+". "+t[i]);
		}
	}
	
	public void SelectionnerCinqThemes()
	{
		int choix[] = new int[5], tamp = 0;
		Scanner s = new Scanner(System.in);
		
		Afficher();
		
		for(int j = 0; j < 5; j++)
		{
			
			System.out.println("Choisisez un thème :");
			try {
				tamp  = Integer.parseInt(s.nextLine());
			}catch (NumberFormatException e) {
				System.out.println("entrer un entier !");
				System.exit(-1);
			}
				if((tamp > 0) && (tamp < 10))
				{
					for(int i = 0; i < 5; i++)
					{
						if(tamp == choix[i])
						{
							System.out.println("Vous avez déjà choisi ce thème !");
							System.exit(-1);
						}
					}
					
					choix[j] = tamp;
				}
				
				else 
				{
					System.out.println("Veuillez choisir parmi les thèmes proposés !");
					System.exit(-1);
				}
			
		}
		
	}
	
}
