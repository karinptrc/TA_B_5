package apap.tugasakhir.sipayroll.controller;
import apap.tugasakhir.sipayroll.model.GajiModel;
import apap.tugasakhir.sipayroll.model.UserModel;
import apap.tugasakhir.sipayroll.service.GajiService;
import apap.tugasakhir.sipayroll.service.UserService;
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
public class GajiController {
    @Autowired
    private UserService userService;

    @Autowired
    private GajiService gajiService;


    @GetMapping("/gaji/add")
    public String addGajiFormPage(Model model){
        List<UserModel> listUser = userService.getUserList();
        model.addAttribute("listUser", listUser);
        return "form-add-gaji";
    }
    @PostMapping("/gaji/add")
    public String addGajiSubmit(@ModelAttribute GajiModel gaji, RedirectAttributes redir){
        UserModel user_pengaju = userService.findUserByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName());
        gaji.setPengaju(user_pengaju);
        gaji.setStatusPersetujuan(0);
        gaji.setPenyetuju(null);
        boolean berhasil = true;
        gajiService.addGaji(gaji);
        redir.addFlashAttribute("gaji", gaji);
        redir.addFlashAttribute("berhasil",berhasil);
        return "redirect:/gaji/add";
    }
    @GetMapping("/gaji/change/{id}")
    public String changeGajiFormPage(@PathVariable Integer id, Model model) {
        GajiModel gaji = gajiService.getGajiById(id);
        List<UserModel> listUser = userService.getUserList();
        model.addAttribute("gaji", gaji);
        model.addAttribute("listUser", listUser);
        return "form-update-gaji";
    }

    @PostMapping("/gaji/change")
    public String changeGajiFormSubmit(@ModelAttribute GajiModel gaji, RedirectAttributes redir) {
        GajiModel gajiUpdated = gajiService.updateGaji(gaji);
        boolean berhasil = true;
        redir.addFlashAttribute("gaji", gaji);
        redir.addFlashAttribute("berhasil", berhasil);
        return "redirect:/gaji/change/"+gaji.getId();
    }

    @RequestMapping("gaji/delete/{id}")
    public String deleteGaji(
            @PathVariable(value = "id", required = true) Integer id,
            Model model
    ){
        GajiModel gaji = gajiService.getGajiById(id);
        gajiService.deleteGaji(gaji);
        model.addAttribute("gaji",gaji);
        return "delete-gaji";
    }

}
