package Projet;

public class test {

	public static void main(String[] args) {
		
		ListeQuestions a = new ListeQuestions();

		a.AjouterQuestion("Comment tu t-appelle ?");
		a.AfficherListe(a.facile);
		System.out.println("");
		a.SupprimerQuestion("Comment tu t-appelle ?");
		a.AfficherListe(a.facile);
		

	}

}
