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
    public String addGajiFormPage(HttpServletRequest request, Model model){
        List<UserModel> user = userService.getUserList();
        model.addAttribute("user",user);
        return "form-add-gaji";
    }

    @PostMapping("/gaji/add")
    public String addGajiSubmit(@ModelAttribute GajiModel gaji, Model model){
        UserModel user = userService.findUserByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName());
        gaji.setStatusPersetujuan(0);
        gaji.setPenyetuju(null);
        gaji.setPengaju(user);
        gajiService.addGaji(gaji);
        model.addAttribute("gaji", gaji);
        return "add-gaji";
    }

}
