package apap.tugasakhir.sipayroll.service;

import apap.tugasakhir.sipayroll.model.UserModel;
import apap.tugasakhir.sipayroll.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDb userDb;

    @Override
    public UserModel addUser(UserModel user) {
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return userDb.save(user);
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public UserModel findUserByUsername(String username) {
        return userDb.findByUsername(username);
    }

    @Override
    public List<UserModel> getUserList() {
        return userDb.findAll();
    }

    @Override
    public Boolean checkIfValidOldPassword(UserModel user, String password) {
        return new BCryptPasswordEncoder().matches(password, user.getPassword());
    }

    @Override
    public void changePassword(UserModel user, String password) {
        String hashedPassword = encrypt(password);
        user.setPassword(hashedPassword);
        userDb.save(user);
    }

    @Override
    public Boolean checkIfValidNewPassword(String password) {
        String huruf = ".*[A-Za-z].*";
        String angka = ".*[0-9].*";
        if(password.length()<8 || !password.matches(huruf) || !password.matches(angka)){
            return false;
        }
        return true;
    }
}
