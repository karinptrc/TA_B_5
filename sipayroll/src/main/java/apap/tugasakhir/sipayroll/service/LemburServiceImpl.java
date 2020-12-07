package apap.tugasakhir.sipayroll.service;

import apap.tugasakhir.sipayroll.model.*;
import apap.tugasakhir.sipayroll.repository.LemburDb;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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

    @Override
    public Boolean bandingTanggal(Date mulai, Date selesai) {
        boolean tanggalValid;
        String pattern = "dd-MM-yyyy";
        DateFormat dateformat = new SimpleDateFormat(pattern);
        String tanggalmulai = dateformat.format(mulai);
        String tanggalselesai = dateformat.format(selesai);
        if (tanggalmulai.equals(tanggalselesai)) {
            tanggalValid = true;
        } else {
            tanggalValid = false;
        }
        return tanggalValid;
    }

    private Integer calculateHours(LemburModel lembur) {
        Date waktuMulai = lembur.getWaktuMulai();
        Date waktuSelesai = lembur.getWaktuSelesai();
        long hours = (waktuSelesai.getTime() - waktuMulai.getTime()) / 3600000;
        return (int) hours;
    }

    @Override
    public Integer totalLemburinMonthByGaji(GajiModel gaji) {
        Integer currentMonth = LocalDate.now().getMonthValue();
        List<LemburModel> listLembur = lemburDb.getLemburModelsByGaji(gaji);
        Integer totalLembur = 0;
        for (LemburModel lembur: gaji.getListLembur()) {
            System.out.println(lembur.getWaktuMulai());
//            System.out.println(lembur.getWaktuMulai().getMonth()+1);
//            System.out.println(lembur.getWaktuMulai());
            // get month of waktuMulai matches with current month
            if(lembur.getWaktuMulai().getMonth()+1 == currentMonth){
                totalLembur += lembur.getKompensasiPerJam() * calculateHours(lembur);
//                System.out.println(calculateAmount(lembur));
//                System.out.println(lembur.getKompensasiPerJam());
//                System.out.println(totalLembur);
            }
        }
        return totalLembur;
    }
}
