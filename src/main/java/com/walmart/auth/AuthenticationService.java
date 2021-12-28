package com.walmart.auth;


import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AuthenticationService {

    private final List<String> validIds = Arrays.asList("1","2","3","4","5");

    public boolean isAuthenticated(String userId) {

        return validIds.contains(userId);
    }
}
