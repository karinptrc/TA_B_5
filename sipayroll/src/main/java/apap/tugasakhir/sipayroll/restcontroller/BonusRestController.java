package apap.tugasakhir.sipayroll.restcontroller;

import apap.tugasakhir.sipayroll.rest.BaseResponseLaporan;
import apap.tugasakhir.sipayroll.rest.LaporanDTO;
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
    private BaseResponseLaporan laporanPesertaPelatihan(@Valid @RequestBody LaporanDTO laporan,
                                                        BindingResult bindingResult){
        BaseResponseLaporan response = new BaseResponseLaporan();
        if (bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        }else {
            String username = laporan.getUsername();
            Integer jumlahPelatihan = laporan.getJumlahPelatihan();
            bonusRestService.createLaporan(username, jumlahPelatihan);
            response.setStatus(200);
            response.setMessage("success");
            response.setResult(laporan);
            return response;
        }
    }
}
