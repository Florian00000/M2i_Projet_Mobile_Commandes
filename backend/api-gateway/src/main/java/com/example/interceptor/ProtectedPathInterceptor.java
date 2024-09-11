package com.example.interceptor;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@ProtectedPath
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class ProtectedPathInterceptor {

//    @Inject
//    ProtectedPath protectedPath;

//    @AroundInvoke
    public void protectedBefore(InvocationContext invocationContext) throws Throwable {
//        System.out.println(joinPoint.getSignature().getName());
        System.out.println("on protectedBefore");
    }
}

//@ApplicationScoped
//@Aspect
//    @Pointcut("@annotation(com.example.interceptor.Protected)")
//    public void protectedPointcut() {}

//    @Before("protectedPointcut()")