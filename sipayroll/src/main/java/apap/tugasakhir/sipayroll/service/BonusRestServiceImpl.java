package apap.tugasakhir.sipayroll.service;

import apap.tugasakhir.sipayroll.model.BonusModel;
import apap.tugasakhir.sipayroll.model.GajiModel;
import apap.tugasakhir.sipayroll.model.UserModel;
import apap.tugasakhir.sipayroll.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
public class BonusRestServiceImpl implements BonusRestService{
    @Autowired
    BonusDb bonusDb;

    @Autowired
    UserDb userDb;

    @Autowired
    GajiDb gajiDb;

    @Autowired
    JenisBonusDb jenisBonusDb;

    @Autowired
    RoleDb roleDb;

    @Autowired
    UserService userService;

    @Override
    public BonusModel createLaporan(String username, Integer jumlah) {
        BonusModel bonus = new BonusModel();
        int jumlahBonus = 150000*jumlah;
        LocalDate tanggalDiterima = LocalDate.now();
        bonus.setJumlahBonus(jumlahBonus);
        bonus.setTanggal(tanggalDiterima);
        UserModel user = userDb.findByUsername(username);
        Optional<GajiModel> gaji = gajiDb.findByUserUsername(username);
        if (user != null){
            if (gaji != null){
                System.out.println("masuk gaji != null, langsung bikin bonus");
                GajiModel gajiUser = gajiDb.findByUserUsername(username).get();
                bonus.setGaji(gajiUser);
                bonus.setJenis(jenisBonusDb.findById(3));
                return bonusDb.save(bonus);
            }
            System.out.println("masuk user != null, bikin gaji baru");
            GajiModel gajiBaru = new GajiModel();
            gajiBaru.setGajiPokok(1000);
            gajiBaru.setPengaju(user);
            gajiBaru.setUser(user);
            gajiBaru.setTanggalMasuk(tanggalDiterima);
            gajiBaru.setStatusPersetujuan(0);
            gajiDb.save(gajiBaru);

            bonus.setGaji(gajiBaru);
            bonus.setJenis(jenisBonusDb.findById(3));
            return bonusDb.save(bonus);
        }
        System.out.println("user == null, bikin user baru");
        user = new UserModel();
        user.setUsername(username);
        user.setPassword("User1234");
        user.setRole(roleDb.findById(6).get());
        userService.addUser(user);

        GajiModel gajiBaru = new GajiModel();
        gajiBaru.setGajiPokok(1000);
        gajiBaru.setPengaju(user);
        gajiBaru.setUser(user);
        gajiBaru.setTanggalMasuk(tanggalDiterima);
        gajiBaru.setStatusPersetujuan(0);
        gajiDb.save(gajiBaru);

        bonus.setGaji(gajiBaru);
        bonus.setJenis(jenisBonusDb.findById(3));
        return bonusDb.save(bonus);
    }
}
