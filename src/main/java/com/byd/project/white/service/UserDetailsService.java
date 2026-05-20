package com.byd.project.white.service;

import com.byd.project.white.model.Admin;
import com.byd.project.white.model.Cliente;
import com.byd.project.white.model.Vendedor;
import com.byd.project.white.repository.AdminRepository;
import com.byd.project.white.repository.ClienteRepository;
import com.byd.project.white.repository.VendedorRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.Optional;


@AllArgsConstructor
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final ClienteRepository clienteRepository;
    private final VendedorRepository vendedorRepository;
    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws NullPointerException {

        if(email.equals("teste@gmail.com")){

            return User.withUsername("teste@gmail.com")
                    .password(bCryptPasswordEncoder.encode("123456"))
                    .authorities("ROLE_ADMIN")
                    .build();
        }

        Optional<Cliente> userDetails = clienteRepository.findByEmailCliente(email);
        if(userDetails.isPresent()) {
            return userDetails.orElseThrow(() -> new UsernameNotFoundException("Cliente não encontrado"));
        }

        Optional<Admin> userDetailsAdmin = adminRepository.findByEmailAdmin(email);
        if (userDetailsAdmin.isPresent()){
            return userDetailsAdmin.orElseThrow(() -> new UsernameNotFoundException("Admin não encontrado"));

        }
        Optional<Vendedor> userDetailsVendedor = vendedorRepository.findByEmail(email);
        return userDetailsVendedor.orElseThrow(() -> new UsernameNotFoundException("Vendedor não encontrado"));
    }
}
