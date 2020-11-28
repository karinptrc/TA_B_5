package apap.tugasakhir.sipayroll.repository;

import apap.tugasakhir.sipayroll.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDb extends JpaRepository<RoleModel, Integer> {
}

