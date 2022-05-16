package bstorm.akimts.gateway2.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class ReactUserService implements ReactiveUserDetailsService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        log.info("ON PASSE ICI");
        return webClientBuilder.build().get()
                .uri(
                        uriBuilder -> uriBuilder.host("user-service")
                            .queryParam("username", username)
                            .build())
                .exchangeToMono((response) -> {
                    if(response.statusCode().is2xxSuccessful())
                        return response.bodyToMono(BasicUserDetails.class);
                    return null; // TODO modifier
                });
    }
}
