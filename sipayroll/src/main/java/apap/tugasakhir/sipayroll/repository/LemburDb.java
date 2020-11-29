package apap.tugasakhir.sipayroll.repository;

import apap.tugasakhir.sipayroll.model.LemburModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LemburDb extends JpaRepository<LemburModel, Integer>  {
    Optional<LemburModel> findById(Long id);
}
