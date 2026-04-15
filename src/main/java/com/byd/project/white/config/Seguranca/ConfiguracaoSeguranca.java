package com.byd.project.white.config.Seguranca;

import com.byd.project.white.repository.ClienteRepository;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class ConfiguracaoSeguranca {

    private final ClienteRepository clienteRepository;
    @Bean
    @Order(1)
    public SecurityFilterChain ausecurityFilterChain(HttpSecurity httpSecurity){
        httpSecurity
                .securityMatcher("/oauth2/**", "/.well-known/**", "/Login")
                .oauth2AuthorizationServer((authorizationServer) -> authorizationServer
                .oidc(Customizer.withDefaults())
        );

        httpSecurity.exceptionHandling(
                e -> e.authenticationEntryPoint(
                        new LoginUrlAuthenticationEntryPoint("/Login")
                )
        );
        return httpSecurity.build();
    }
    @Bean
    @Order(2)
    public SecurityFilterChain whitesecurityFilterChain(HttpSecurity httpSecurity) throws Exception{
         httpSecurity
                .formLogin(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET,"/White/teste").permitAll()
                        .anyRequest().authenticated()
                );
        return httpSecurity.build();
    }



//    @Bean
//    public UserDetailsService userDetailsService(){
//
//        UserDetails user = User.builder()
//                .username("user")
//                .password(bCryptPasswordEncoder().encode("senha"))
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }
//    @Bean
//    public UserDetailsService userDetailsService(){
//        //
//    }
//    @Bean
//    public RegisteredClientRepository registeredClientRepository() {
//
//        RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
//                .clientId("usuario")
//                .clientSecret(bCryptPasswordEncoder().encode("secret"))
//                .scope(OidcScopes.OPENID)
//                .scope(OidcScopes.EMAIL)
//                .redirectUri("http://localhost:8080/login/oauth2/code/Cliente_id")
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//                .build();
//
//        //adaptar para um banco depois
//        return new InMemoryRegisteredClientRepository(registeredClient);
//    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings (){
        return AuthorizationServerSettings.builder()
                .build();
    }

    @Bean
    public JWKSource<SecurityContext> securityContextJWKSource() throws NoSuchAlgorithmException {
        KeyPair keyPair = keyPair();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();

        RSAKey build = new RSAKey.Builder(rsaPublicKey)
                .privateKey(rsaPrivateKey)
                .keyID(UUID.randomUUID().toString())
                .build();

        JWKSet jwkSet = new JWKSet(build);
        return new ImmutableJWKSet<>(jwkSet);
    }

    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> securityContextJWKSource){
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(securityContextJWKSource);
    }
    private static KeyPair keyPair(){
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return keyPair;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
}
