package apap.tugasakhir.sipayroll.service;

import apap.tugasakhir.sipayroll.model.*;
import apap.tugasakhir.sipayroll.repository.LemburDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.List;

@Service
@Transactional
public class LemburServiceImpl implements LemburService {
    @Autowired
    LemburDb lemburDb;

    @Override
    public void addLembur(LemburModel lembur) { 
        lemburDb.save(lembur); 
    }

    @Override
    public LemburModel getLemburById(Integer id){
        return lemburDb.findById(id).get();
    }

    @Override
    public LemburModel updateLembur(LemburModel lembur){
        LemburModel targetLembur = lemburDb.findById(lembur.getId()).get();

        try{
            targetLembur.setStatusPersetujuan(lembur.getStatusPersetujuan());
            targetLembur.setWaktuMulai(lembur.getWaktuMulai());
            targetLembur.setWaktuSelesai(lembur.getWaktuSelesai());
            lemburDb.save(targetLembur);
            return targetLembur;
        }catch(NullPointerException nullException){
            return null;
        }
    }
}
