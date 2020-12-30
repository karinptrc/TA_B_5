package apap.tugasakhir.sipayroll.service;

import apap.tugasakhir.sipayroll.rest.BaseResponse;

import apap.tugasakhir.sipayroll.rest.BaseResponseGaji;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;

@Service
@Transactional
public class DetailGajiRestServiceImpl implements DetailGajiRestService{
    private final WebClient webClient;

    @Autowired
    private DetailGajiRestService detailGajiRestService;

    public DetailGajiRestServiceImpl(WebClient.Builder webClientBuilder){
//        this.webClient = webClientBuilder.baseUrl("https://6ab55ff4-643b-486a-95c7-287fa161361a.mock.pstmn.io").build();
        this.webClient = webClientBuilder.baseUrl("https://sipelatihan-b4.herokuapp.com").build();
    }

//    @Override
//    public List<DetailGaji> getAllGaji() {
//        return null;
//    }

    @Override
    public Mono<BaseResponseGaji> getListPesertaPelatihan(String username) {
//        return this.webClient.get().uri("/rest/sipelatihan/" + username + "/peserta/").retrieve().bodyToMono(BaseResponseGaji.class);
        return this.webClient.get().uri("api/v1/pelatihan-by-username/" + username).retrieve().bodyToMono(BaseResponseGaji.class);
    }
}
