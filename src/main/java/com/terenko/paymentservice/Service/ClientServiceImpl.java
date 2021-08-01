package com.terenko.paymentservice.Service;

import com.terenko.paymentservice.DTO.ClientDTO;
import com.terenko.paymentservice.models.Client;
import com.terenko.paymentservice.repositories.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@Service
public class ClientServiceImpl implements ClientService {
    final ClientRepository clientRepository;
    final AccountService accountService;

    public ClientServiceImpl(ClientRepository clientRepository, AccountService accountService) {
        this.clientRepository = clientRepository;
        this.accountService = accountService;
    }

    @Override
    @Transactional
    public Client addClient(ClientDTO clientDTO) {
        Client client = ClientDTO.from(clientDTO);
        clientRepository.save(client);
        assert clientDTO.getAccounts() != null;

        clientDTO.getAccounts().forEach(x ->
                client.getAccounts().add(accountService.addAccount(x, client))
        );
        return client;
    }

    @Override
    public void remClient(Client client) {

    }

    @Override

    public  Client getById(long id) throws IllegalArgumentException  {
        Client client =clientRepository.findByClientId(id);
        if(client==null)throw new IllegalArgumentException("client not found");

        return client;
    }

    @Override
    public boolean isExist(long id) {
        return clientRepository.existsByClientId(id);
    }

}
