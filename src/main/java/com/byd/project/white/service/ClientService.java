package com.byd.project.white.service;

import com.byd.project.white.model.Client;
import com.byd.project.white.repository.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class ClientService implements RegisteredClientRepository {
    private final ClientRepository clientRepository;


    @Override
    public void save(RegisteredClient registeredClient) {
        clientRepository.save(Client.converteParaClient(registeredClient));

    }

    @Override
    public @Nullable RegisteredClient findById(String id) {
        Client resultado = clientRepository.findById(Integer.valueOf(id))
                .orElseThrow(RuntimeException::new);
        return Client.converterParaRegistered(resultado);
    }

    @Override
    public @Nullable RegisteredClient findByClientId(String clientId) {
        var resultado = clientRepository.findByClientId(clientId)
                .orElseThrow();
        return Client.converterParaRegistered(resultado);
    }
}
