package apap.tugasakhir.sipayroll.controller;
import apap.tugasakhir.sipayroll.model.*;
import apap.tugasakhir.sipayroll.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class LemburController {
    @Autowired
    private LemburService lemburService;

    @Autowired
    private GajiService gajiService;

    @Autowired
    private UserService userService;

    @GetMapping("/lembur/add")
    public String addLemburFormPage(Model model){
        model.addAttribute("gaji", new GajiModel());
        return "form-add-lembur";
    }

    @PostMapping("/lembur/add")
    public String addLemburSubmit(@ModelAttribute LemburModel lembur, Model model){
        UserModel user = userService.findUserByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName());
        try{
            GajiModel gaji = user.getGaji();
            lembur.setStatusPersetujuan(0);
            lembur.setGaji(gaji);
            lembur.setKompensasiPerJam(120000);
            lemburService.addLembur(lembur);
            model.addAttribute("lembur", lembur);
            model.addAttribute("berhasil", "Penambahan lembur berhasil!");
            return "form-add-lembur";
        } catch(Exception e){
            model.addAttribute("gagal", "ID Gaji belum terdaftar! Penambahan lembur gagal!");
            return "form-add-lembur";

        }
    }

    @GetMapping("/lembur/ubah/{id}")
    public String ubahLemburFormPage(
        @PathVariable Integer id,
        Model model
    ){
        LemburModel lembur = lemburService.getLemburById(id);
        model.addAttribute("lembur", lembur);
        return "form-ubah-lembur";
    }

    @PostMapping("/lembur/ubah")
    public String ubahLemburFormSubmit(
        @ModelAttribute LemburModel lembur,
        Model model){
        LemburModel lemburUpdated = lemburService.updateLembur(lembur);
        model.addAttribute("lembur", lembur);
        model.addAttribute("berhasil", "Lembur berhasil diubah!");
        return "form-ubah-lembur";
    }
}