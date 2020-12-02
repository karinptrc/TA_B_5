package apap.tugasakhir.sipayroll.service;

import java.util.Date;

import apap.tugasakhir.sipayroll.model.*;

public interface LemburService {
    void addLembur(LemburModel lembur);
    LemburModel getLemburById(Integer id);
    LemburModel updateLembur(LemburModel lembur);
    Boolean bandingTanggal(Date mulai, Date selesai);
}
