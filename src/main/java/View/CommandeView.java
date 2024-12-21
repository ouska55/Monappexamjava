package View;

import java.util.Scanner;

import Entity.ClientEntity;
import Entity.CommandeEntity;
import Service.ArticleService;
import Service.ClientService;
import Service.CommandeService;

public class CommandeView {

    private final ClientService clientService;
    private final CommandeService commandeService;
    private final ArticleService articleService;

    public CommandeView(ClientService clientService, CommandeService commandeService, ArticleService articleService) {
        this.clientService = clientService;
        this.commandeService = commandeService;
        this.articleService = articleService;
    }

    /**
     * 
     */
    public void afficherMenu() {
        Scanner scanner = new Scanner(System.in);
        int choix;
        do {
            System.out.println("\n=== Menu Gestion des Commandes ===");
            System.out.println("1. Rechercher un client");
            System.out.println("2. Ajouter une commande");
            System.out.println("3. Afficher les commandes d'un client");
            System.out.println("4. Quitter");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne

            switch (choix) {
                case 1 -> rechercherClient(scanner);
                case 2 -> ajouterCommande(scanner);
                case 3 -> afficherCommandesClient(scanner);
                case 4 -> System.out.println("Au revoir !");
                default -> System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choix != 4);
    }

    private void rechercherClient(Scanner scanner) {
        System.out.print("Entrez le numéro de téléphone du client : ");
        String telephone = scanner.nextLine();
        clientService.rechercherClientParTelephone(telephone)
                .ifPresentOrElse(client -> {
                    System.out.println("Client trouvé : " + client.getNom() + " " + client.getPrenom());
                    System.out.println("Adresse : Ville=" + client.getVille() + ", Quartier=" + client.getQuartier() + ", Villa=" + client.getNumeroVilla());
                },
                () -> System.out.println("Aucun client trouvé avec ce numéro de téléphone."));
    }

    private void ajouterCommande(Scanner scanner) {
        System.out.print("Entrez le numéro de téléphone du client : ");
        String telephone = scanner.nextLine();
        var clientOpt = clientService.rechercherClientParTelephone(telephone);

        if (clientOpt.isEmpty()) {
            System.out.println("Client introuvable. Veuillez d'abord créer le client.");
            return;
        }

        ClientEntity client = clientOpt.get();
        CommandeEntity commande = commandeService.creerCommande(client);
        System.out.println("Commande créée avec l'ID : " + commande.getId());

        boolean continuer = true;
        while (continuer) {
            System.out.println("1. Ajouter un article\n2. Terminer la commande");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne

            switch (choix) {
                case 1 -> ajouterArticleACommande(scanner, commande);
                case 2 -> continuer = false;
                default -> System.out.println("Choix invalide.");
            }
        }
        System.out.println("Commande terminée.");
    }

    private void ajouterArticleACommande(Scanner scanner, CommandeEntity commande) {
        System.out.println("Liste des articles disponibles :");
        articleService.listerArticles().forEach(article ->
                System.out.println("ID=" + article.getId() + ", Nom=" + article.getNom() + ", Prix Unitaire=" + article.getPrixUnitaire() + ", Quantité Disponible=" + article.getQuantiteDisponible()));

        System.out.print("Entrez l'ID de l'article : ");
        int articleId = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        var articleOpt = articleService.rechercherArticleParId(articleId);

        if (articleOpt.isEmpty()) {
            System.out.println("Article introuvable.");
            return;
        }

        ArticleEntity article = articleOpt.get();
        System.out.print("Entrez la quantité : ");
        int quantite = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Entrez le prix : ");
        double prix = scanner.nextDouble();
        scanner.nextLine();

        try {
            CommandeService.ajouterArticleACommande(commande, article, quantite, prix);
            System.out.println("Article ajouté à la commande.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    private void afficherCommandesClient(Scanner scanner) {
        System.out.print("Entrez le numéro de téléphone du client : ");
        String telephone = scanner.nextLine();
        var clientOpt = ClientService.rechercherClientParTelephone(telephone);

        if (clientOpt.isEmpty()) {
            System.out.println("Client introuvable.");
            return;
        }

        ClientEntity client = clientOpt.get();
        System.out.println("Commandes pour le client : " + client.getNom() + " " + client.getPrenom());
        commandeService.listerCommandesParClient(client).forEach(commande -> {
            System.out.println("ID Commande=" + commande.getId() + ", Date=" + commande.getDateCommande());
            System.out.println("Articles :");
            commandeService.listerArticlesParCommande(commande).forEach(commandeArticle ->
                    System.out.println("- Nom=" + commandeArticle.getArticle().getNom() + ", Prix=" + commandeArticle.getPrix() + ", Quantité=" + commandeArticle.getQuantiteCommande()));
        });
    }
}