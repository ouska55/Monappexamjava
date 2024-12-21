package Service;
import java.util.List;
import java.util.Optional;

public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> listerArticlesDisponibles() {
        return articleRepository.findAll();
    }

    public Optional<Article> rechercherArticleParId(int id) {
        return articleRepository.findById(id);
    }
}
