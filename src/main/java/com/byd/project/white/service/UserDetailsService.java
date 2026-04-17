package com.byd.project.white.service;

import com.byd.project.white.model.Cliente;
import com.byd.project.white.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@AllArgsConstructor
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws NullPointerException {
        Optional<Cliente> userDetails = clienteRepository.findByEmailCliente(email);
        return userDetails.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
}
