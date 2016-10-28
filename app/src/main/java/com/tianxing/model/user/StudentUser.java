package com.tianxing.model.user;

/**
 * Created by tianxing on 16/10/28.
 */

public class StudentUser extends User {





    @Override
    public UserType getUserType() {
        return UserType.student;
    }
}
