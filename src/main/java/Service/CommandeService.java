package Service;
import java.util.List;
import java.util.Optional;

public class CommandeService {

    private final CommandeRepository commandeRepository;
    private final ArticleRepository articleRepository;
    private final CommandeArticleRepository commandeArticleRepository;

    public CommandeService(CommandeRepository commandeRepository, ArticleRepository articleRepository, CommandeArticleRepository commandeArticleRepository) {
        this.commandeRepository = commandeRepository;
        this.articleRepository = articleRepository;
        this.commandeArticleRepository = commandeArticleRepository;
    }

    public void creerCommande(Commande commande, List<CommandeArticle> commandeArticles) {
        commandeRepository.save(commande);
        for (CommandeArticle commandeArticle : commandeArticles) {
            commandeArticleRepository.save(commandeArticle);
            mettreAJourStock(commandeArticle);
        }
    }

    private void mettreAJourStock(CommandeArticle commandeArticle) {
        Article article = articleRepository.findById(commandeArticle.getArticleId()).orElseThrow(() -> new RuntimeException("Article non trouvé"));
        int nouvelleQuantite = article.getQuantiteDisponible() - commandeArticle.getQuantiteCommande();
        if (nouvelleQuantite < 0) {
            throw new RuntimeException("Stock insuffisant pour l'article " + article.getNom());
        }
        article.setQuantiteDisponible(nouvelleQuantite);
        articleRepository.save(article);
    }

    public List<Commande> listerCommandesParClient(int clientId) {
        return commandeRepository.findByClientId(clientId);
    }
}
