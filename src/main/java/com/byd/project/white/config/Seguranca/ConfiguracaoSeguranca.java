package com.byd.project.white.config.Seguranca;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.web.cors.CorsConfiguration;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.List;
import java.util.UUID;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class ConfiguracaoSeguranca {


    @Value("${jwk}")
    private String jwk;

    @Bean
    @Order(1)
    public SecurityFilterChain ausecurityFilterChain(HttpSecurity httpSecurity){
        httpSecurity.cors(c -> c.configurationSource(
                request -> {
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowedOrigins(List.of("*"));
                    corsConfiguration.setAllowCredentials(true);
                    return corsConfiguration;
                }
        ));
        httpSecurity
                .securityMatcher("/oauth2/**", "/.well-known/**")
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
    public SecurityFilterChain resourceServerFilterChain(HttpSecurity httpSecurity){
        httpSecurity.cors(c -> c.configurationSource(
                request -> {
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowedOrigins(List.of("*"));
                    corsConfiguration.setAllowCredentials(true);
                    return corsConfiguration;
                }
        ));

        httpSecurity
                .oauth2ResourceServer(
                        oauth2 -> oauth2.jwt(
                                jwt -> jwt.jwkSetUri(jwk)
                        )
                );
        httpSecurity
                .securityMatcher("/white/**","/swagger-ui/**","/v3/api-docs/**")
                .authorizeHttpRequests(authorize -> authorize
                        //Swagger
                        .requestMatchers(HttpMethod.GET,"/swagger-ui/**","/v3/api-docs/**").permitAll()

                        //Cliente
                        .requestMatchers(HttpMethod.POST,"/white/cliente/registrar/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/white/cliente/buscarporid/**","/white/cliente/listar/**").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/white/cliente/atualizar/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/white/cliente/deletar/**").permitAll()

                        //Teste
                        .requestMatchers(HttpMethod.GET,"/white/teste").permitAll()

                        //Vendedor
                        .requestMatchers(HttpMethod.POST,"/white/vendedor/registrar/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/white/vendedor/listar/**", "/white/vendedor/buscarporid/**").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/white/vendedor/atualizar/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/white/vendedor/deletar/**").permitAll()

                        //Comissao
                        .requestMatchers(HttpMethod.POST,"white/comissao/criar/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"white/comissao/listar/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"white/comissao/buscarporid/**").permitAll()
                        .requestMatchers(HttpMethod.PUT,"white/comissao/atualizar/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"white/comissao/deletar/**").permitAll()

                        //Veiculos
                        .requestMatchers(HttpMethod.POST,"white/veiculo/criar/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"white/veiculo/listar/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"white/veiculo/buscarporid/**").permitAll()
                        .requestMatchers(HttpMethod.PUT,"white/veiculo/atualizar/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"white/veiculo/deletar/**").permitAll()

                        //Venda
                        .requestMatchers(HttpMethod.POST,"white/venda/criar/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"white/venda/listar/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"white/venda/buscarporid/**").permitAll()
                        .requestMatchers(HttpMethod.PUT,"white/venda/atualizar/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"white/venda/deletar/**").permitAll()

                )
                .sessionManagement(configurer ->
                        configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer((resourceServer) -> resourceServer
                        .jwt(Customizer.withDefaults()));

        return httpSecurity.build();
    }
    @Bean
    @Order(3)
    public SecurityFilterChain whitesecurityFilterChain(HttpSecurity httpSecurity) throws Exception{
         httpSecurity
                .formLogin(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()
                );
        return httpSecurity.build();
    }



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
    public OAuth2TokenCustomizer<JwtEncodingContext> oAuth2TokenCustomizer(){
        return context -> {
            var autho = context.getPrincipal().getAuthorities();

            context.getClaims().claim("authorities", autho.stream().map(a -> a.getAuthority()).toList());
        };


    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
}
