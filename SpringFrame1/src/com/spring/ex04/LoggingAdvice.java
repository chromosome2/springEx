package com.spring.ex04;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LoggingAdvice implements MethodInterceptor{

	//invoke() �޼����� MethodInvocation �ƱԸ�Ʈ��
	//ȣ��Ǵ� �޼���, ��� ��������Ʈ, AOP ���Ͻ�, �޼����� �ƱԸ�Ʈ�� �����Ѵ�.
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("�޼��� ȣ�� ��");
		System.out.println(invocation.getMethod()+"�޼��� ȣ�� ��");
		
		Object object=invocation.proceed();
		
		System.out.println("�޼��� ȣ�� ��");
		System.out.println(invocation.getMethod()+"�޼��� ȣ�� ��");
		return object;
	}

	
}
