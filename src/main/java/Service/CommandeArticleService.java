package Service;
import java.util.List;
import java.util.Optional;

public class CommandeArticleService {

    private final CommandeArticleRepository commandeArticleRepository;

    public CommandeArticleService(CommandeArticleRepository commandeArticleRepository) {
        this.commandeArticleRepository = commandeArticleRepository;
    }

    public void modifierCommandeArticle(int id, double nouveauPrix, int nouvelleQuantite) {
        CommandeArticle commandeArticle = commandeArticleRepository.findById(id).orElseThrow(() -> new RuntimeException("CommandeArticle non trouvée"));
        commandeArticle.setPrix(nouveauPrix);
        commandeArticle.setQuantiteCommande(nouvelleQuantite);
        commandeArticleRepository.save(commandeArticle);
    }

    public void supprimerCommandeArticle(int id) {
        commandeArticleRepository.deleteById(id);
    }
}