package com.spring.ex04;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LoggingAdvice implements MethodInterceptor{

	//invoke() 메서드의 MethodInvocation 아규먼트는
	//호출되는 메서드, 대상 조인포인트, AOP 프록시, 메서드의 아규먼트를 노출한다.
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("메서드 호출 전");
		System.out.println(invocation.getMethod()+"메서드 호출 전");
		
		Object object=invocation.proceed();
		
		System.out.println("메서드 호출 후");
		System.out.println(invocation.getMethod()+"메서드 호출 후");
		return object;
	}

	
}
