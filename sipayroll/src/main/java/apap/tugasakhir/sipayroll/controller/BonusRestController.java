package apap.tugasakhir.sipayroll.controller;

import apap.tugasakhir.sipayroll.model.LaporanPesertaPelatihanModel;
import apap.tugasakhir.sipayroll.service.BonusRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class BonusRestController {
    @Autowired
    BonusRestService bonusRestService;

    @PostMapping(value = "/laporan")
    private LaporanPesertaPelatihanModel laporanPesertaPelatihan(@Valid @RequestBody LaporanPesertaPelatihanModel laporan,
                                                                 BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        }else {
            return bonusRestService.createLaporan(laporan);
        }
    }
}
