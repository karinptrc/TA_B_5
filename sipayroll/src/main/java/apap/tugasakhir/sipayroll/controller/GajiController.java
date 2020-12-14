package apap.tugasakhir.sipayroll.controller;
import apap.tugasakhir.sipayroll.model.GajiModel;
import apap.tugasakhir.sipayroll.model.UserModel;
import apap.tugasakhir.sipayroll.service.BonusService;
import apap.tugasakhir.sipayroll.service.GajiService;
import apap.tugasakhir.sipayroll.service.LemburService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GajiController {
    @Autowired
    private UserService userService;

    @Autowired
    private GajiService gajiService;

    @Autowired
    private LemburService lemburService;

    @Autowired
    private BonusService bonusService;

    @GetMapping("/gaji/add")
    public String addGajiFormPage(Model model){
        List<UserModel> listUser = userService.getUserList();
        model.addAttribute("listUser", listUser);
        return "form-add-gaji";
    }
    @PostMapping("/gaji/add")
    public String addGajiSubmit(@ModelAttribute GajiModel gaji, RedirectAttributes redir){
        UserModel user_pengaju = userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<GajiModel> listGaji = gajiService.getGajiList();
        for(int i = 0; i < listGaji.size(); i++){
            if(listGaji.get(i).getUser() == gaji.getUser()){
                System.out.print("masuk sini");
                boolean gagal = true;
                redir.addFlashAttribute("gagal",gagal);
                return "redirect:/gaji/add";
            }
        }
        gaji.setPengaju(user_pengaju);
        gaji.setStatusPersetujuan(0);
        gaji.setPenyetuju(null);
        boolean berhasil = true;
        gajiService.addGaji(gaji);
        redir.addFlashAttribute("gaji", gaji);
        redir.addFlashAttribute("berhasil",berhasil);
        return "redirect:/gaji/add";
    }
    @GetMapping(value = "/gaji/update/{id}", params = {"update"})
    public String updateGajiFormPage(@PathVariable Integer id, Model model) {
        GajiModel gaji = gajiService.getGajiById(id);
        List<UserModel> listUser = userService.getUserList();
        model.addAttribute("gaji", gaji);
        model.addAttribute("listUser", listUser);
        return "form-update-gaji";
    }

    @PostMapping("/gaji/update")
    public String updateGajiFormSubmit(@ModelAttribute GajiModel gaji, RedirectAttributes redir) {
        GajiModel gajiUpdated = gajiService.updateGaji(gaji);
        boolean berhasil = true;
        redir.addFlashAttribute("gaji", gaji);
        redir.addFlashAttribute("berhasil", berhasil);
        return "redirect:/gaji/update/"+gaji.getId();
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

    @RequestMapping("/gaji")
    public String listGaji(
            Model model
    ){
        List<GajiModel> listGaji = gajiService.getGajiList();
        Integer gajiTambahan = 0;
        for (GajiModel gaji:listGaji) {
            gajiTambahan += lemburService.totalLemburinMonthByGaji(gaji);
            gajiTambahan += bonusService.totalBonusinMonthByGaji(gaji);
            gajiTambahan += gaji.getGajiPokok();
            gaji.setTotalPendapatan(gajiTambahan);
            gajiTambahan = 0;
        }
        model.addAttribute("listGaji", listGaji);
        model.addAttribute("hasGaji", listGaji.size()>0);
        return "daftar-gaji";
    }

    @RequestMapping(value = "/gaji/setujui/{id}")
    public String setujuiGaji(
            @PathVariable(value = "id") Integer id,
            RedirectAttributes redir
    ){
        List<GajiModel> listGaji = gajiService.getGajiList();
        GajiModel gaji = gajiService.updateStatusGaji(id, 2);
        redir.addFlashAttribute("gajiUpdated", gaji);
        redir.addFlashAttribute("changedStatus", true);
        redir.addFlashAttribute("statusGaji", true); // if gaji disetujui
        redir.addFlashAttribute("listGaji", listGaji);
        return "redirect:/gaji";
    }

    @RequestMapping(value = "/gaji/tolak/{id}")
    public String tolakGaji(
            @PathVariable(value = "id") Integer id,
            RedirectAttributes redir
    ){
        List<GajiModel> listGaji = gajiService.getGajiList();
        GajiModel gaji = gajiService.updateStatusGaji(1, 1);
        redir.addFlashAttribute("gajiUpdated", gaji);
        redir.addFlashAttribute("changedStatus", true);
        redir.addFlashAttribute("statusGaji", false); // if gaji ditolak
        redir.addFlashAttribute("listGaji", listGaji);
        return "redirect:/gaji";
    }
}
