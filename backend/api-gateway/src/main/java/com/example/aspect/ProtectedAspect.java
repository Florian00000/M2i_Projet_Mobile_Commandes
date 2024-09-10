package com.example.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ProtectedAspect {
    @Pointcut("@annotation(com.example.aspect.Protected)")
    public void protectedPointcut() {}

    @Before("protectedPointcut()")
    public void protectedBefore() {
        System.out.println("on protectedBefore");
    }
}
