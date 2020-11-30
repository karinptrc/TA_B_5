package apap.tugasakhir.sipayroll.controller;
import apap.tugasakhir.sipayroll.model.*;
import apap.tugasakhir.sipayroll.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String addLemburSubmit(@ModelAttribute LemburModel lembur, RedirectAttributes redir){
        UserModel user = userService.findUserByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName());
        try{
            GajiModel gaji = user.getGaji();
            lembur.setStatusPersetujuan(0);
            lembur.setGaji(gaji);
            lembur.setKompensasiPerJam(120000);
            lemburService.addLembur(lembur);
            redir.addFlashAttribute("lembur", lembur);
            redir.addFlashAttribute("berhasil", "Penambahan lembur berhasil!");
            return "redirect:/lembur/add";
        } catch(Exception e){
            redir.addFlashAttribute("gagal", "ID Gaji belum terdaftar! Penambahan lembur gagal!");
            return "redirect:/lembur/add";

        }
    }

    @GetMapping("/lembur/ubah/{id}")
    public String ubahLemburFormPage(@PathVariable Integer id, Model model){
        LemburModel lembur = lemburService.getLemburById(id);
        model.addAttribute("lembur", lembur);
        return "form-ubah-lembur";
    }

    @PostMapping("/lembur/ubah")
    public String ubahLemburFormSubmit(@ModelAttribute LemburModel lembur, RedirectAttributes redir){
        LemburModel lemburUpdated = lemburService.updateLembur(lembur);
        redir.addFlashAttribute("lembur", lembur);
        redir.addFlashAttribute("berhasil", "Lembur berhasil diubah!");
        return "redirect:/lembur/ubah/"+lembur.getId();
    }
}