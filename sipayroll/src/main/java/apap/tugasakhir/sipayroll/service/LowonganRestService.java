package apap.tugasakhir.sipayroll.service;

import org.springframework.util.MultiValueMap;

import apap.tugasakhir.sipayroll.rest.*;
import reactor.core.publisher.Mono;

public interface LowonganRestService {
    Mono<String> requestLowongan(LowonganDTO lowongan);
}