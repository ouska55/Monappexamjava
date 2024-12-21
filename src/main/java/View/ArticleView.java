package View;
import java.util.Scanner;

import Entity.ArticleEntity;
import Service.ArticleService;

public class ArticleView {

    private final ArticleService articleService;

    public ArticleView(ArticleService articleService) {
        this.articleService = articleService;
    }

    public void afficherArticles() {
        System.out.println("=== Liste des Articles ===");
        articleService.listerArticles().forEach(article ->
                System.out.println("ID=" + article.getId() + ", Nom=" + article.getNom() + ", Prix Unitaire=" + article.getPrixUnitaire() + ", Quantité Disponible=" + article.getQuantiteDisponible()));
    }

    public void creerArticle(Scanner scanner) {
        System.out.println("=== Création d'un nouvel article ===");
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        System.out.print("Prix Unitaire : ");
        double prixUnitaire = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Quantité Disponible : ");
        int quantiteDisponible = scanner.nextInt();
        scanner.nextLine();

        articleService.creerArticle(new Article(nom, prixUnitaire, quantiteDisponible));
        System.out.println("Article créé avec succès !");
    }
}
