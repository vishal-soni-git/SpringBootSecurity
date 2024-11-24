package in.sp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

import java.util.UUID;

@Configuration
public class MyAuthorizationServerConfig {

    @Bean
    public RegisteredClientRepository registeredClientRepository() {

       RegisteredClient registeredClient= RegisteredClient
                .withId(UUID.randomUUID().toString())
                .clientId("smartprogramming")
                .clientSecret("{noop}sp123")
                .scope("read")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .build();
        return new InMemoryRegisteredClientRepository(registeredClient);
    }
}
