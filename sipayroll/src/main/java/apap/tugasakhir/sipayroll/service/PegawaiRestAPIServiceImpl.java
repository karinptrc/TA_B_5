package apap.tugasakhir.sipayroll.service;


import apap.tugasakhir.sipayroll.rest.BaseResponse;
import apap.tugasakhir.sipayroll.rest.PegawaiDTO;
import apap.tugasakhir.sipayroll.rest.Setting;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;

@Service
@Transactional
public class PegawaiRestAPIServiceImpl implements PegawaiRestAPIService{
    private final WebClient webClient;

    public PegawaiRestAPIServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder
                .baseUrl(Setting.pegawaiAPI).build();
    }

    @Override
    public BaseResponse addPegawai(PegawaiDTO pegawai) {
        return this.webClient
                .post()
                .uri("/pegawai")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(pegawai)
                .retrieve()
                .bodyToMono(BaseResponse.class)
                .block();
    }

    @Override
    public BaseResponse getPegawai(String username) {
        return this.webClient
                .get()
                .uri("/pegawai/" + username)
                .retrieve()
                .bodyToMono(BaseResponse.class)
                .block();
    }
}
