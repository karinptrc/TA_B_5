package apap.tugasakhir.sipayroll.service;

import apap.tugasakhir.sipayroll.rest.*;
import reactor.core.publisher.Mono;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;

@Service
@Transactional
public class LowonganRestServiceImpl implements LowonganRestService {
    private final WebClient webClient;

    public LowonganRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.lowonganAPI).build();
    }

    @Override
    public Mono<String> requestLowongan(LowonganDTO lowongan){
        System.out.println("berhasil kirim");
        return this.webClient
                    .post()
                    .uri("/lowongan/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(lowongan)
                    .retrieve()
                    .bodyToMono(String.class);
                    // .block();
    }
}
