package apap.tugasakhir.sipayroll.controller;


import apap.tugasakhir.sipayroll.model.BonusModel;
import apap.tugasakhir.sipayroll.model.GajiModel;
import apap.tugasakhir.sipayroll.model.JenisBonusModel;
import apap.tugasakhir.sipayroll.model.UserModel;
import apap.tugasakhir.sipayroll.service.BonusService;
import apap.tugasakhir.sipayroll.service.JenisBonusService;
import apap.tugasakhir.sipayroll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class BonusController {
    @Autowired
    private UserService userService;

    @Autowired
    private BonusService bonusService;

    @Autowired
    private JenisBonusService jenisBonusService;

    @GetMapping("/bonus/add")
    public String addBonusFormPage(Model model){
        List<UserModel> listUser = userService.getUserList();
        List<JenisBonusModel> listJenisBonus = jenisBonusService.getListJenisBonus();
        model.addAttribute("listUser", listUser);
        model.addAttribute("listJenisBonus", listJenisBonus);
        return "form-add-bonus";
    }

    @PostMapping("bonus/add")
    public String addBonusSubmit(@ModelAttribute BonusModel bonus,
                                 String uuid,
                                 Integer jenisBonus,
                                 RedirectAttributes redir){
        System.out.println(uuid);
        System.out.println(jenisBonus);
        try{
            Optional<UserModel> user = userService.findUserByUuid(uuid);
            GajiModel gaji = user.get().getGaji();
            JenisBonusModel jenis =  jenisBonusService.getJenisBonusById(jenisBonus);
            boolean flag = bonusService.addBonus(bonus, gaji, jenis);
            String pesan = "";
            if(flag){
                pesan = "Bonus berhasil ditambahkan!";
            }else {
                pesan = "User telah mendapatkan bonus tersebut!";
            }
            redir.addFlashAttribute("pesan",pesan);
            return "redirect:/bonus/add";
        }catch (NullPointerException nullPointerException){
            redir.addFlashAttribute("pesan","ID Gaji belum terdaftar! Penambahan bonus gagal!");
            return "redirect:/bonus/add";
        }
    }

}
