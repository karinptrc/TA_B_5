package apap.tugasakhir.sipayroll.repository;

import apap.tugasakhir.sipayroll.model.BonusModel;
import apap.tugasakhir.sipayroll.model.GajiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface BonusDb extends JpaRepository<BonusModel, Integer> {
    List<BonusModel> getBonusModelsByGaji(GajiModel gaji);
    Optional<BonusModel> findById(Integer id);

    List<BonusModel> findByGajiId(Integer id);

//    List<Object> findById(Integer id);
}
