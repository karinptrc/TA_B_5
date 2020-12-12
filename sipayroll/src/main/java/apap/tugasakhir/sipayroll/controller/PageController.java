package apap.tugasakhir.sipayroll.controller;

import apap.tugasakhir.sipayroll.service.LemburService;
import apap.tugasakhir.sipayroll.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
public class PageController {
    @Autowired
    RoleService roleService;

    @RequestMapping("/")
    private String home(Model model){
        model.addAttribute("listRole", roleService.findAll());
        model.addAttribute("dateTime", LocalDateTime.now());
        return "halaman-utama";
    }

    @RequestMapping("/login")
    private String login(){
        return "login";
    }
}