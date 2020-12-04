package apap.tugasakhir.sipayroll.repository;

import apap.tugasakhir.sipayroll.model.BonusModel;
import apap.tugasakhir.sipayroll.model.GajiModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BonusDb extends JpaRepository<BonusModel, Long> {
    List<BonusModel> getBonusModelsByGaji(GajiModel gaji);
}
