package Projet;

import java.util.ArrayList;
import java.util.Scanner;

public class ListeQuestions {
	
	ArrayList<String> facile = new ArrayList<String>();
	ArrayList<String> moyen = new ArrayList<String>();
	ArrayList<String> difficile = new ArrayList<String>();
	
	public ListeQuestions()
	{
		
	}

	
	public void AfficherListe(ArrayList<String> a)
	{
		for(int i = 0; i < a.size(); i++)
		{
			System.out.println(a.get(i));
		}
	}
	
	public void AjouterQuestion(String question)
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Quel est le niveau de la question ? (f : facile, m: moyen, d : difficile)");
		char c = sc.next().charAt(0);
		
		if((c < 97) && (c > 122))
		{
			System.out.println("Veuillez entrer un caractère !");
			System.exit(-1);
		}
		
		else if(c == 'f')
		{
			facile.add(question);
		}
		
		else if(c == 'm')
		{
			moyen.add(question);
		}
		
		else if(c == 'd')
		{
			difficile.add(question);
		}
		
		else
			System.out.println("Veuillez entrer le bon niveau !");
	}
	
	public void SupprimerQuestion(String question)
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Quel est le niveau de la question ? (f : facile, m: moyen, d : difficile)");
		char c = sc.next().charAt(0);
		
		if((c < 97) && (c > 122))
		{
			System.out.println("Veuillez entrer un caractère !");
			System.exit(-1);
		}
		
		else if(c == 'f')
		{
			for(int i = 0; i < facile.size(); i++)
			{
				if(question == facile.get(i))
					facile.remove(i);
			}
		}
		
		else if(c == 'm')
		{
			for(int i = 0; i < moyen.size(); i++)
			{
				if(question == moyen.get(i))
					moyen.remove(i);
			}
		}
		
		else if(c == 'd')
		{
			for(int i = 0; i < difficile.size(); i++)
			{
				if(question == difficile.get(i))
					difficile.remove(i);
			}
		}
		
		else
			System.out.println("Niveau incorrect ou question inexisant !");
	}
	
	/**public String SelectionnerQuestion(int phase)
	{
		if(phase == 1)
		{
			
		}
	}**/
}
