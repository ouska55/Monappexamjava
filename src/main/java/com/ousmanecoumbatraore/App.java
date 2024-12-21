package com.ousmanecoumbatraore;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gestion des Commandes");

        TextArea textArea = new TextArea();
        
        BorderPane root = new BorderPane();
        root.setCenter(textArea);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private String getProjectDetails() {
        return "=== Gestion des Commandes ===\n\n" +
               "1. Gestion des clients\n" +
               "- Ajouter un client\n" +
               "- Voir les détails d'un client\n\n" +
               "2. Gestion des articles\n" +
               "- Ajouter un article\n" +
               "- Voir les détails d'un article\n\n" +
               "3. Gestion des commandes\n" +
               "- Ajouter une commande\n" +
               "- Voir les détails d'une commande\n\n" +
               "(Note: Cette interface est en lecture seule et aucune modification n'est possible.)";
    }
}
