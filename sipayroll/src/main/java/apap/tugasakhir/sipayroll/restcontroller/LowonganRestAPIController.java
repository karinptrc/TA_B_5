package apap.tugasakhir.sipayroll.restcontroller;

import apap.tugasakhir.sipayroll.rest.*;
import apap.tugasakhir.sipayroll.service.*;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class LowonganRestAPIController {
    @Autowired
    LowonganRestService lowonganRestService;

    @GetMapping(value = "/lowongan")
    private Mono<String> requestLowongan(@ModelAttribute LowonganDTO lowongan){
        return lowonganRestService.requestLowongan(lowongan);
    }
}
