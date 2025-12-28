package utils;

import data.models.User;
import dtos.requests.UserSignUpRequest;


public class Mapper {

    public static User mapSignUpUser(UserSignUpRequest request) {
            User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhone(request.getPhone());

        return user;
    }


}
