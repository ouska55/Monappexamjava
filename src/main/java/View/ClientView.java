package View;
import java.util.Scanner;

import Entity.ClientEntity;
import Service.ClientService;


public class ClientView {

    private final ClientService clientService;

    public ClientView(ClientService clientService) {
        this.clientService = clientService;
    }

    public void creerClient(Scanner scanner) {
        System.out.println("=== Création d'un nouveau client ===");
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();
        System.out.print("Ville : ");
        String ville = scanner.nextLine();
        System.out.print("Quartier : ");
        String quartier = scanner.nextLine();
        System.out.print("Numéro de Villa : ");
        String numeroVilla = scanner.nextLine();
        System.out.print("Téléphone : ");
        String telephone = scanner.nextLine();

        clientService.creerClient(new ClientEntity(nom, prenom, ville, quartier, numeroVilla, telephone));
        System.out.println("Client créé avec succès !");
    }
}