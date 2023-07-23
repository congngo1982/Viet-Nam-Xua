package com.vnxua.reservation.securiry;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Bcrypt {
    private static int workload = 11;
    public static String hashPassword(String password) {
        String salt = BCrypt.gensalt(workload);
        String hashed_password = BCrypt.hashpw(password, salt);

        return(hashed_password);
    }
}
