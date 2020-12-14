package apap.tugasakhir.sipayroll.service;

import apap.tugasakhir.sipayroll.model.RoleModel;
import apap.tugasakhir.sipayroll.repository.RoleDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleDb roleDb;

    @Override
    public List<RoleModel> findAll() {
        return roleDb.findAll();
    }

    @Override
    public RoleModel findRoleById(Integer id) {
        return roleDb.findById(id).get();
    }
}
