package apap.tugasakhir.sipayroll.repository;

import apap.tugasakhir.sipayroll.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RoleDb extends JpaRepository<RoleModel, Integer> {
}

