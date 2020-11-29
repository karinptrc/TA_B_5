package apap.tugasakhir.sipayroll.controller;

import apap.tugasakhir.sipayroll.model.UserModel;
import apap.tugasakhir.sipayroll.service.UserRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserRestController {
    @Autowired
    UserRestService userRestService;

    @RequestMapping(value = "/karyawan-lama")
    private List<UserModel> retrieveKaryawanLama(){
        return userRestService.retrieveKaryawanLama();
    }
}
