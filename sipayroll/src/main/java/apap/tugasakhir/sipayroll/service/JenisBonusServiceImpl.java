package apap.tugasakhir.sipayroll.service;

import apap.tugasakhir.sipayroll.model.JenisBonusModel;
import apap.tugasakhir.sipayroll.repository.JenisBonusDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class JenisBonusServiceImpl implements JenisBonusService {
    @Autowired
    JenisBonusDb jenisBonusDb;

    @Override
    public List<JenisBonusModel> getListJenisBonus() {
        return jenisBonusDb.findAll();
    }

    @Override
    public JenisBonusModel getJenisBonusById(Integer id) {
        return jenisBonusDb.findById(id);
    }
}
