package apap.tugasakhir.sipayroll.service;

import apap.tugasakhir.sipayroll.model.UserModel;

import java.util.List;

public interface UserService {
    UserModel addUser(UserModel user);
    String encrypt(String password);
    UserModel findUserByUsername(String username);
    List<UserModel> getUserList();
    Boolean checkIfValidOldPassword(UserModel user, String password);
    void changePassword(UserModel user, String password);
    Boolean checkIfValidNewPassword(String password);
    Boolean checkIfUsernameIsUsed(String username);
}
