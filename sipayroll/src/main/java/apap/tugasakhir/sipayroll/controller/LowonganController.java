package apap.tugasakhir.sipayroll.controller;
import apap.tugasakhir.sipayroll.model.UserModel;
import apap.tugasakhir.sipayroll.rest.*;
import apap.tugasakhir.sipayroll.service.LowonganRestService;
import apap.tugasakhir.sipayroll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
// import org.springframework.util.LinkedMultiValueMap;
// import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.*;

@Controller
public class LowonganController {
    @Autowired
    private LowonganRestService lowonganRestService;

    @Autowired
    private UserService userService;
    
    @RequestMapping("/lowongan/add")
    public String addUserPage(Model model){
        model.addAttribute("lowongan", new LowonganDTO());
        return "form-add-lowongan";
    }

    @RequestMapping(value="/lowongan/add", method = RequestMethod.POST)
    public String addLowonganFormPage(@ModelAttribute LowonganDTO lowongan,
                                    RedirectAttributes redir
    ){
        UserModel user = userService.findUserByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName());
        String uuid = user.getId();
        if(lowongan.getDivisi() != null && lowongan.getJenis_lowongan() != null && lowongan.getJumlahKaryawan() != null && lowongan.getPosisi() != null){
            lowongan.setUuid(uuid);
            lowonganRestService.requestLowongan(lowongan);
            redir.addFlashAttribute("berhasil", "Penambahan lowongan berhasil!");
        } else if(lowongan.getDivisi() != null && lowongan.getJenis_lowongan() != null && lowongan.getJumlahKaryawan() != null && lowongan.getPosisi() == null){
            redir.addFlashAttribute("gagal", "Posisi belum terisi!");
        } else if(lowongan.getDivisi() != null && lowongan.getJenis_lowongan() != null && lowongan.getJumlahKaryawan() == null && lowongan.getPosisi() != null){
            redir.addFlashAttribute("gagal", "Jumlah Karyawan belum terisi!");
        } else if(lowongan.getDivisi() != null && lowongan.getJenis_lowongan() == null && lowongan.getJumlahKaryawan() != null && lowongan.getPosisi() != null){
            redir.addFlashAttribute("gagal", "Jenis lowongan belum terisi!");
        } else if(lowongan.getDivisi() == null && lowongan.getJenis_lowongan() != null && lowongan.getJumlahKaryawan() != null && lowongan.getPosisi() != null){
            redir.addFlashAttribute("gagal", "Divisi belum terisi!");
        } else{
            redir.addFlashAttribute("gagal", "Penambahan lowongan gagal!");
        }
        // System.out.println(dataform);
        System.out.println(uuid);
        return "redirect:/lowongan/add";
    }


    
    // @RequestMapping(value="/lowongan/add", method = RequestMethod.POST)
    // public String addLowonganFormPage(@ModelAttribute LowonganDTO lowongan,
    //                                 RedirectAttributes redir
    // ){
    //     // MultiValueMap<String, String> dataform = new LinkedMultiValueMap<>();
    //     UserModel user = new UserModel();
    //     String uuid = user.getId();
    //     // dataform.add("divisi", lowongan.getDivisi());
    //     // dataform.add("posisi", lowongan.getPosisi());
    //     // dataform.add("jenis", lowongan.getJenis_lowongan().toString());
    //     // dataform.add("jumlah", lowongan.getJumlahKaryawan().toString());
    //     // dataform.add("uuid", uuid);
    //     if(lowongan.getDivisi() != null && lowongan.getJenis_lowongan() != null && lowongan.getJumlahKaryawan() != null && lowongan.getPosisi() != null){
    //         // lowonganRestService.addLowongan(dataform);
    //         lowongan.setUuid(uuid);
    //         lowonganRestService.requestLowongan(lowongan);
    //         redir.addFlashAttribute("berhasil", "Penambahan lowongan berhasil!");
    //     } else if(lowongan.getDivisi() != null && lowongan.getJenis_lowongan() != null && lowongan.getJumlahKaryawan() != null && lowongan.getPosisi() == null){
    //         redir.addFlashAttribute("gagal", "Posisi belum terisi!");
    //     } else if(lowongan.getDivisi() != null && lowongan.getJenis_lowongan() != null && lowongan.getJumlahKaryawan() == null && lowongan.getPosisi() != null){
    //         redir.addFlashAttribute("gagal", "Jumlah Karyawan belum terisi!");
    //     } else if(lowongan.getDivisi() != null && lowongan.getJenis_lowongan() == null && lowongan.getJumlahKaryawan() != null && lowongan.getPosisi() != null){
    //         redir.addFlashAttribute("gagal", "Jenis lowongan belum terisi!");
    //     } else if(lowongan.getDivisi() == null && lowongan.getJenis_lowongan() != null && lowongan.getJumlahKaryawan() != null && lowongan.getPosisi() != null){
    //         redir.addFlashAttribute("gagal", "Divisi belum terisi!");
    //     } else{
    //         redir.addFlashAttribute("gagal", "Penambahan lowongan gagal!");
    //     }
    //     // System.out.println(dataform);
    //     System.out.println(uuid);
    //     return "redirect:/lowongan/add";
    // }
}