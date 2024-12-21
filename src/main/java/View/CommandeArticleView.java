package View;

import java.util.Scanner;

import Entity.CommandeEntity;
import Service.CommandeService;

public class CommandeArticleView {

    private final CommandeService commandeService;

    public CommandeArticleView(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    public void gererCommandeArticle(Scanner scanner, CommandeEntity commande) {
        System.out.println("=== Gestion des Articles dans la Commande ===");
        boolean continuer = true;
        while (continuer) {
            System.out.println("1. Ajouter un article\n2. Modifier un
        }
            }
            }