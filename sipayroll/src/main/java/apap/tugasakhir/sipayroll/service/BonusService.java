package apap.tugasakhir.sipayroll.service;

import apap.tugasakhir.sipayroll.model.BonusModel;
import apap.tugasakhir.sipayroll.model.GajiModel;
import apap.tugasakhir.sipayroll.model.JenisBonusModel;

import java.util.List;

public interface BonusService {
    boolean addBonus(BonusModel bonus, GajiModel gaji, JenisBonusModel jenis);
    Integer totalBonusinMonthByGaji(GajiModel gaji);
}
