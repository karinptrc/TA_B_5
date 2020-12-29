package apap.tugasakhir.sipayroll.repository;

import apap.tugasakhir.sipayroll.model.JenisBonusModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface JenisBonusDb extends JpaRepository<JenisBonusModel, Long> {
    JenisBonusModel findById(Integer id);
}
