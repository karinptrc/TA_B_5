package apap.tugasakhir.sipayroll.service;

import apap.tugasakhir.sipayroll.model.GajiModel;
import apap.tugasakhir.sipayroll.repository.GajiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class GajiServiceImpl implements GajiService{
    @Autowired
    GajiDb gajiDb;

    @Override
    public void addGaji(GajiModel gaji) { gajiDb.save(gaji); }
}
