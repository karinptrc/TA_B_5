package apap.tugasakhir.sipayroll;

import apap.tugasakhir.sipayroll.model.UserModel;
import apap.tugasakhir.sipayroll.service.RoleService;
import apap.tugasakhir.sipayroll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class SipayrollStartupData implements ApplicationRunner {
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(userService.findUserByUsername("admin")==null){
            UserModel user = new UserModel();
            user.setUsername("admin");
            user.setPassword("admin1234");
            user.setRole(roleService.findRoleById(1));

            userService.addUser(user);
        }
    }
}
