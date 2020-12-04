package apap.tugasakhir.sipayroll.service;

import apap.tugasakhir.sipayroll.model.LaporanPesertaPelatihanModel;
import apap.tugasakhir.sipayroll.repository.LaporanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BonusRestServiceImpl implements BonusRestService{
    @Autowired
    LaporanDb laporanDb;

    @Override
    public LaporanPesertaPelatihanModel createLaporan(LaporanPesertaPelatihanModel laporan) {
        return laporanDb.save(laporan);
    }
}
