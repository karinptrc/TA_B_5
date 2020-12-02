package apap.tugasakhir.sipayroll.service;

import apap.tugasakhir.sipayroll.model.JenisBonusModel;

import java.util.List;

public interface JenisBonusService {
    List<JenisBonusModel> getListJenisBonus();
    JenisBonusModel getJenisBonusById(Integer id);
}
