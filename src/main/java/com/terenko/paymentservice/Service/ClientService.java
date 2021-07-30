package com.terenko.paymentservice.Service;

import com.terenko.paymentservice.DTO.ClientDTO;
import com.terenko.paymentservice.models.Client;

public interface ClientService {
     Client addClient(ClientDTO clientDTO);
     void remClient(Client client);
     Client getById(long id);
     Client getByLogin(String login);
}
