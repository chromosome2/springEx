package com.spring.ex04;

public class Calculator {
	public void add(int a, int b) {
		int result=a+b;
		System.out.println("두 수의 합 = "+result);
	}
	
	public void substract(int a, int b) {
		int result=a-b;
		System.out.println("두 수의 차 = "+result);
	}
	
	public void multiply(int a, int b) {
		int result=a*b;
		System.out.println("두 수의 곱 = "+result);
	}
	
	public void divide(int a, int b) {
		int result=a/b;
		System.out.println("두 수의 몫 = "+result);
	}
}
