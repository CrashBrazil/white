package com.byd.project.white.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

@Entity
@Data
public class Client {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String clientId;
    private String secret;
    @ElementCollection
    private Set<String> redirectUri;
    @ElementCollection
    private Set<String> scope;
    @ElementCollection
    private Set<String> authMethod;
    @ElementCollection
    private Set<String> grantType;

    public static Client converteParaClient(RegisteredClient registeredClient){
        Client client = new Client();
        Set<String> authMe = new HashSet<>();
        Set<String> trype = new HashSet<>();


        client.setClientId(registeredClient.getClientId());
        client.setSecret(registeredClient.getClientSecret());
        client.setRedirectUri(registeredClient.getRedirectUris());
        client.setScope(registeredClient.getScopes());

        for(ClientAuthenticationMethod auth : registeredClient.getClientAuthenticationMethods()){
            authMe.add(auth.getValue());
        }
        for(AuthorizationGrantType authorizationGrantType : registeredClient.getAuthorizationGrantTypes()){
            trype.add(authorizationGrantType.getValue());
        }
        client.setAuthMethod(authMe);
        client.setGrantType(trype);

        return client;
    }
//    public static RegisteredClient converterParaRegistered(Client client){
//
//        return RegisteredClient.withId(String.valueOf(client.getId()))
//                .clientId(client.getClientId())
//                .clientSecret(client.secret)
//                .scope(client.getScope().toString())
//                .redirectUris(uris -> uris.addAll(client.getRedirectUri()))
//                .clientAuthenticationMethod(new ClientAuthenticationMethod(client.getAuthMethod().toString()))
//                .authorizationGrantType(new AuthorizationGrantType(client.getGrantType().toString()))
//                .build();
//    }

    public static RegisteredClient converterParaRegistered(Client client) {
        return RegisteredClient.withId(String.valueOf(client.getId()))
                .clientId(client.getClientId())
                .clientSecret(client.getSecret())

                .scopes(scopes -> scopes.addAll(client.getScope()))

                .redirectUris(uris -> uris.addAll(client.getRedirectUri()))

                .clientAuthenticationMethods(methods ->
                        client.getAuthMethod()
                                .forEach(m -> methods.add(new ClientAuthenticationMethod(m)))
                )

                .authorizationGrantTypes(types ->
                        client.getGrantType()
                                .forEach(t -> types.add(new AuthorizationGrantType(t)))
                )
                .build();
    }
}
