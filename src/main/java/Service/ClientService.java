package Service;

import java.util.List;
import java.util.Optional;

public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Optional<Client> rechercherClientParTelephone(String telephone) {
        return clientRepository.findByTelephone(telephone);
    }

    public void ajouterClient(Client client) {
        clientRepository.save(client);
    }
}