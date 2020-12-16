package apap.tugasakhir.sipayroll.service;

import apap.tugasakhir.sipayroll.rest.BaseResponseGaji;
import reactor.core.publisher.Mono;

public interface DetailGajiRestService {
    Mono<BaseResponseGaji> getListPesertaPelatihan(String username);
//    List<DetailGaji>getAllGaji();
}
