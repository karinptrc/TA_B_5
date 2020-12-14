package apap.tugasakhir.sipayroll.restcontroller;

import apap.tugasakhir.sipayroll.rest.BaseResponse;
import apap.tugasakhir.sipayroll.rest.PegawaiDTO;
import apap.tugasakhir.sipayroll.service.PegawaiRestAPIService;
import apap.tugasakhir.sipayroll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class PegawaiRestAPIController {
    @Autowired
    PegawaiRestAPIService pegawaiRestAPIService;

    @Autowired
    UserService userService;

    @PostMapping(value = "/pegawai")
    private BaseResponse addPegawai(@ModelAttribute PegawaiDTO pegawai){
        return pegawaiRestAPIService.addPegawai(pegawai);
    }
}
