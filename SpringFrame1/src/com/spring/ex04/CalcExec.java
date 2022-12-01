package com.spring.ex04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CalcExec {

	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("AOPCalc.xml");
		Calculator calc=(Calculator)context.getBean("proxyCal");
		calc.add(30, 45);
		System.out.println("===========");
	}

}
