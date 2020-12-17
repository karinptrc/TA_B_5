package apap.tugasakhir.sipayroll.service;

import apap.tugasakhir.sipayroll.model.BonusModel;
import apap.tugasakhir.sipayroll.model.GajiModel;
import apap.tugasakhir.sipayroll.model.JenisBonusModel;

import java.util.List;
import java.util.Optional;

public interface BonusService {
    boolean addBonus(BonusModel bonus, GajiModel gaji, JenisBonusModel jenis);
    Integer totalBonusinMonthByGaji(GajiModel gaji);

    BonusModel getBonusById(Integer id);

//    Optional<BonusModel> getBonusByUsername(String username);

    List<BonusModel> getBonusByIdGaji(Integer id);
}
