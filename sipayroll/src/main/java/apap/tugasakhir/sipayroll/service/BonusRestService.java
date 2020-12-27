package apap.tugasakhir.sipayroll.service;

import apap.tugasakhir.sipayroll.model.BonusModel;

public interface BonusRestService {
    BonusModel createLaporan(String username, Integer jumlah);
}
