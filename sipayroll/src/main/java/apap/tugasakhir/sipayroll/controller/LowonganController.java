package apap.tugasakhir.sipayroll.controller;
import apap.tugasakhir.sipayroll.model.UserModel;
import apap.tugasakhir.sipayroll.rest.*;
import apap.tugasakhir.sipayroll.service.LowonganRestService;
import apap.tugasakhir.sipayroll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        if(lowongan.getDivisi() != "" && lowongan.getJenisLowongan() != null && lowongan.getJumlahKaryawan() != null && lowongan.getPosisi() != ""){
            // lowongan.setUuid(uuid);
            lowonganRestService.requestLowongan(lowongan);
            redir.addFlashAttribute("berhasil", "Penambahan lowongan berhasil!");
        } else if(lowongan.getDivisi() == "" && lowongan.getJenisLowongan() != null && lowongan.getJumlahKaryawan() != null && lowongan.getPosisi() != ""){
            redir.addFlashAttribute("gagal", "Divisi belum terisi!");
        } else if(lowongan.getDivisi() != "" && lowongan.getJenisLowongan() != null && lowongan.getJumlahKaryawan() != null && lowongan.getPosisi() == ""){
            redir.addFlashAttribute("gagal", "Posisi belum terisi!");
        } else if(lowongan.getDivisi() != "" && lowongan.getJenisLowongan() == null && lowongan.getJumlahKaryawan() != null && lowongan.getPosisi() != ""){
            redir.addFlashAttribute("gagal", "Jenis lowongan belum terisi!");
        } else if(lowongan.getDivisi() != "" && lowongan.getJenisLowongan() != null && lowongan.getJumlahKaryawan() == null && lowongan.getPosisi() != ""){
            redir.addFlashAttribute("gagal", "Jumlah Karyawan belum terisi!");
        } 
        else{
            redir.addFlashAttribute("gagal", "Penambahan lowongan gagal!");
        }
        // System.out.println(dataform);
        System.out.println(uuid);
        return "redirect:/lowongan/add";
    }
}