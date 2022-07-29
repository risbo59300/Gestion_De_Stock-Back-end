package rca.risbo.gestiondestock.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rca.risbo.gestiondestock.dto.ClientDto;
import rca.risbo.gestiondestock.exception.EntityNotFoundException;
import rca.risbo.gestiondestock.exception.ErrorCodes;
import rca.risbo.gestiondestock.exception.InvalidEntityException;
import rca.risbo.gestiondestock.exception.InvalidOperationException;
import rca.risbo.gestiondestock.model.CommandeClient;
import rca.risbo.gestiondestock.repository.ClientRepository;
import rca.risbo.gestiondestock.repository.CommandeClientRepository;
import rca.risbo.gestiondestock.services.ClientService;
import rca.risbo.gestiondestock.validators.ClientValidator;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;
    private CommandeClientRepository commandeClientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, CommandeClientRepository commandeClientRepository) {
        this.clientRepository = clientRepository;
        this.commandeClientRepository = commandeClientRepository;
    }

    @Override
    public ClientDto save(ClientDto clientDto) {
        List<String> errors = ClientValidator.validate(clientDto);
        if (!errors.isEmpty()) {
            log.error("Client is not valid {}", clientDto);
            throw new InvalidEntityException("Le client n'est pas valide", ErrorCodes.CLIENT_NOT_VALID, errors);
        }

        return ClientDto.fromEntity(
            clientRepository.save(
                ClientDto.toEntity(clientDto)
            )
        );
    }

    @Override
    public ClientDto findById(Integer id) {
        if (id == null) {
            log.error("Client ID is null");
            return null;
        }

        return clientRepository.findById(id)
            .map(ClientDto::fromEntity)
            .orElseThrow(()-> new EntityNotFoundException(
                "Aucun Client avec l;ID = "+ id + " n'a été trouvé dans la BDD",
                ErrorCodes.CLIENT_NOT_FOUND)
            );
    }

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll().stream()
            .map(ClientDto::fromEntity)
            .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Client ID is null");
            return;
        }
        List<CommandeClient> commandeClients = commandeClientRepository.findAllByClientId(id);
        if (!commandeClients.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer un client qui a deja des commande clients",
                ErrorCodes.CLIENT_ALREADY_IN_USE);
        }
    }
}
