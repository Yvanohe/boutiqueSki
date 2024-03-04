package fr.lubac.boutiqueSki.dal;

import java.util.List;

import fr.lubac.boutiqueSki.bo.Article;
import fr.lubac.boutiqueSki.bo.Ski;
import fr.lubac.boutiqueSki.bo.Combinaison;

public class AppliTestDAL {

	public static void main(String[] args) {

		// Déclaration et instanciation de la DAO
		ArticleDAO articleDAO = DAOFactory.getArticleDAO();

		// Instanciation du jeu d'essai
		Article a1 = new Combinaison("Bic", "BBOrange", "Bic bille Orange", 1.2f, 20, "bleu");
		Article a2 = new Ski("Clairef", "CRA4S", "Ski A4 Sup", 9f, 20, 80);
		Article a3 = new Combinaison("Stypen", "PlumeS", "Combinaison Plume Stypen", 5.5f, 20, "jaune");

		System.out.println("Ajout des articles... ");
		try {
			articleDAO.insert(a1);
			System.out.println("Article ajouté  : " + a1.toString());
			articleDAO.insert(a2);
			System.out.println("Article ajouté  : " + a2.toString());
			articleDAO.insert(a3);
			System.out.println("Article ajouté  : " + a3.toString());

			// Sélection de l'article par id
			Article a = articleDAO.selectById(a2.getIdArticle());
			System.out.println("Sélection de l'article par id  : " + a.toString());

			// Sélection de tous les articles
			List<Article> articles = articleDAO.selectAll();
			System.out.println("Sélection de tous les articles  : " + articles.toString());

			// Modification d'un article
			System.out.println("Modification d'un article  : ");
			System.out.println("Article avant modification : " + a1.toString());
			((Combinaison) a1).setCouleur("noir");
			((Combinaison) a1).setDesignation("Bic bille noir");
			((Combinaison) a1).setReference("BBNoir");
			articleDAO.update(a1);
			System.out.println("Article après modification  : " + a1.toString());

			// Suppression d'un article
			System.out.println("Suppression de l'article  : " + a1.toString());
			articleDAO.delete(a1.getIdArticle());
			articles = articleDAO.selectAll();
			System.out.println("Liste des articles après suppression : ");
			StringBuffer sb = new StringBuffer();
			for (Article art : articles) {
				if (art instanceof Combinaison) {
					sb.append("Combinaison   [id=");
				} else {
					sb.append("Ski [id=");
				}
				sb.append(art.getIdArticle());
				sb.append(", ref=");
				sb.append(art.getReference()).append("]\n");
			}
			System.out.println(sb.toString());
			System.out.println("---------------------------------------------------------------");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
