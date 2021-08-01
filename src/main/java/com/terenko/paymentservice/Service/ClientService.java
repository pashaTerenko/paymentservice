package com.terenko.paymentservice.Service;

import com.terenko.paymentservice.DTO.AccountDTO;
import com.terenko.paymentservice.DTO.ClientDTO;
import com.terenko.paymentservice.models.Client;

import java.util.List;

public interface ClientService {
     Client addClient(ClientDTO clientDTO);
     void remClient(Client client);
     Client getById(long id);
     boolean isExist(long id);
}
