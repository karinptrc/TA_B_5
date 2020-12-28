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
import java.time.LocalDateTime;
import java.util.*;

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
        model.addAttribute("dateTime", LocalDateTime.now());
        return "form-add-lembur";
    }

    @PostMapping("/lembur/add")
    public String addLemburSubmit(@ModelAttribute LemburModel lembur, RedirectAttributes redir){
        UserModel user = userService.findUserByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName());
        GajiModel gaji = user.getGaji();
        if(lemburService.bandingTanggal(lembur.getWaktuMulai(), lembur.getWaktuSelesai()) == true && lemburService.bandingJam(lembur.getWaktuMulai(), lembur.getWaktuSelesai()) == true){
            lembur.setStatusPersetujuan(0);
            lembur.setGaji(gaji);
            lembur.setKompensasiPerJam(120000);
            lemburService.addLembur(lembur);
            redir.addFlashAttribute("lembur", lembur);
            redir.addFlashAttribute("berhasil", "Penambahan lembur berhasil!");
            return "redirect:/lembur/add";
        } else if(lemburService.bandingTanggal(lembur.getWaktuMulai(), lembur.getWaktuSelesai()) == false){
            redir.addFlashAttribute("gagal", "Tanggal yang diberikan berbeda!");
            return "redirect:/lembur/add";
        } else if(lemburService.bandingJam(lembur.getWaktuMulai(), lembur.getWaktuSelesai()) == false){
            redir.addFlashAttribute("gagal", "Waktu mulai dan selesai tidak sesuai!");
            return "redirect:/lembur/add";
        } else{
            redir.addFlashAttribute("gagal", "ID Gaji belum terdaftar! Penambahan lembur gagal!");
            return "redirect:/lembur/add";
        }
    }
    
    @GetMapping("/lembur/ubah/{id}")
    public String ubahLemburFormPage(@PathVariable Integer id, Model model){
        LemburModel lembur = lemburService.getLemburById(id);
        model.addAttribute("lembur", lembur);
        model.addAttribute("dateTime", LocalDateTime.now());
        if(lembur.getStatusPersetujuan() == 2){
            return "form-ubah-lembur2";
        } else {
            return "form-ubah-lembur";
        }
    }

    @PostMapping("/lembur/ubah")
    public String ubahLemburFormSubmit(@ModelAttribute LemburModel lembur, RedirectAttributes redir){
        LemburModel lemburUpdated = lemburService.updateLembur(lembur);
        redir.addFlashAttribute("lembur", lembur);
        if(lembur.getStatusPersetujuan() == 2){
            redir.addFlashAttribute("berhasil", "Lembur sudah disetujui! Tidak dapat dilakukan perubahan lagi.");
        } else {
            redir.addFlashAttribute("berhasil", "Lembur berhasil diubah!");
        }
        return "redirect:/lembur/ubah/"+lembur.getId();
    }

    @GetMapping("/lembur/hapus/{id}")
    public String hapusLemburFormPage(@PathVariable Integer id, RedirectAttributes redir){
        LemburModel lembur = lemburService.getLemburById(id);
        lemburService.deleteLembur(lembur);
        redir.addFlashAttribute("berhasil", "Lembur berhasil dihapus");
        return "redirect:/lembur/view";
    }

    @GetMapping("lembur/view")
    public String viewLembur(Model model){
        UserModel user = userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user.getRole().getId() == 6){
            List<LemburModel> listLembur = lemburService.getListLemburByGaji(user.getGaji());
            model.addAttribute("listLembur", listLembur);
            model.addAttribute("karyawan", true);
            model.addAttribute("hasLembur",  listLembur.size()>0);
            model.addAttribute("dateTime", LocalDateTime.now());
            return "daftar-lembur";
        }
        List<LemburModel> listLembur = lemburService.getListLembur();
        model.addAttribute("listLembur", listLembur);
        model.addAttribute("karyawan", false);
        model.addAttribute("hasLembur",  listLembur.size()>0);
        model.addAttribute("dateTime", LocalDateTime.now());
        return "daftar-lembur";
    }
}