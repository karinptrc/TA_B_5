package apap.tugasakhir.sipayroll.controller;

import apap.tugasakhir.sipayroll.model.GajiModel;
import apap.tugasakhir.sipayroll.service.GajiRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class GajiRestController {
    @Autowired
    private GajiRestService gajiRestService;

    @RequestMapping(value = "/gaji/karyawan-lama")
    private List<GajiModel> retrieveGajiFromKaryawanLama(){
        return gajiRestService.getGajiFromKaryawanLama();
    }
}
