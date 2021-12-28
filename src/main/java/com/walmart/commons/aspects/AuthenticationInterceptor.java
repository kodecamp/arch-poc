package com.walmart.commons.aspects;

import com.walmart.auth.AuthenticationService;
import com.walmart.commons.controller.AuthenticationException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.Optional;

@Aspect
@Component
public class AuthenticationInterceptor {

    @Autowired
    private AuthenticationService service;

    @Around("@annotation(com.walmart.commons.controller.EnableAuthentication)")
    public Object authenticate(ProceedingJoinPoint joinPoint) throws Throwable {

        Boolean isValidUser = getUserId(joinPoint)
                .map((id) -> service.isAuthenticated(id))
                .orElseThrow(() -> new AuthenticationException("Invalid User"));

        if(isValidUser) {
            System.out.println("################## valid user");
            return joinPoint.proceed();
        }
        System.out.println("------------------- invalid user : " + isValidUser);
        throw new AuthenticationException("Invalid User");
    }

    private Optional<String> getUserId(ProceedingJoinPoint joinPoint) {

        MethodSignature methodSig = (MethodSignature) joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        String[] parametersName = methodSig.getParameterNames();

        int index = Arrays.asList(parametersName).indexOf("id");

        if(args.length > index) { // parameter exist
            return Optional.ofNullable(String.valueOf(args[index]));
        } // otherwise your parameter does not exist by given name
        return Optional.empty();
    }
}
