package apap.tugasakhir.sipayroll.service;

import apap.tugasakhir.sipayroll.model.BonusModel;
import apap.tugasakhir.sipayroll.model.GajiModel;
import apap.tugasakhir.sipayroll.model.JenisBonusModel;
import apap.tugasakhir.sipayroll.repository.BonusDb;
import apap.tugasakhir.sipayroll.repository.JenisBonusDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BonusServiceImplementation implements BonusService{

    @Autowired
    BonusDb bonusDb;

    @Autowired
    JenisBonusDb jenisBonusDb;

    @Override
    public boolean addBonus(BonusModel bonus, GajiModel gaji, JenisBonusModel jenis) {
        if (jenis.getId() == 1){
            if (validateBonus(gaji, jenis.getId())){
                bonus.setGaji(gaji);
                gaji.getListBonus().add(bonus);
                bonus.setJenis(jenis);
                bonus.setJumlahBonus(gaji.getGajiPokok());
            } else{
                return false;
            }
        }else if (jenis.getId() == 2){
            if (validateBonus(gaji, jenis.getId())){
                bonus.setGaji(gaji);
                gaji.getListBonus().add(bonus);
                bonus.setJenis(jenis);
                bonus.setJumlahBonus(gaji.getGajiPokok()*2);
            } else{
                return false;
            }
        }else{
            bonus.setGaji(gaji);
            gaji.getListBonus().add(bonus);
            bonus.setJenis(jenis);
            bonus.setJumlahBonus(1500000);
        }
        bonusDb.save(bonus);
        return true;
    }

    public boolean validateBonus(GajiModel gaji, Integer jenis){
        if (!(gaji.getListBonus().isEmpty())){
            List<BonusModel> listBonus = gaji.getListBonus();
            for (BonusModel bonus : listBonus){
                System.out.println("tes " + bonus.getJenis().getId());
                if (bonus.getJenis().getId() == jenis){
                    return false;
                }
            }
        }

        return true;
    }
}