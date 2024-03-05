package fr.lubac.boutiqueSki.bll;

import java.util.ArrayList;
import java.util.List;

import fr.lubac.boutiqueSki.bo.Article;
import fr.lubac.boutiqueSki.bo.Ski;
import fr.lubac.boutiqueSki.bo.Combinaison;

public class AppliTestBLL {

	public static void main(String[] args) {
		// Instanciation du jeu d'essai
		List<Article> articles = new ArrayList<>();
		Combinaison combinaison = new Combinaison("Bic", "BBOrange", "Bic bille Orange", 1.2f, 20, "bleu");
		articles.add(combinaison);
		articles.add(new Ski("Clairef", "ss", "Ski A4 Sup", 9f, 20, 80));
		articles.add(new Combinaison("Stypen", "PlumeS", "Combinaison Plume Stypen", 5.5f, 20, "jaune"));
		articles.add(new Combinaison("Waterman", "WOBGreen", "Waterman Orion Bille vert", 4.2f, 35, "vert"));
		articles.add(new Ski("ProDesign", "ForLaser", "A4 Special laser", 5.5f, 55, 100));

		CatalogueManager mger = null;
		try {
			mger = CatalogueManager.getInstance();
		} catch (BLLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		// Ajout d'un article au catalogue
		try {
			for (Article art : articles) {
				mger.addArticle(art);
			}
			System.out.println(mger.getCatalogue());

		} catch (BLLException e) {
			e.printStackTrace();
		}

		// Modification d'un article
		try {
			((Combinaison) combinaison).setCouleur("noir");
			((Combinaison) combinaison).setDesignation("Bic bille noir");
			((Combinaison) combinaison).setReference("BBNoir");
			mger.updateArticle(combinaison);
			System.out.println("Article après modification  : " + combinaison.toString());
		} catch (BLLException e) {
			e.printStackTrace();
		}

		// Suppression d'un article
		try {
			mger.removeArticle(combinaison.getIdArticle());
			System.out.println(mger.getCatalogue());
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
