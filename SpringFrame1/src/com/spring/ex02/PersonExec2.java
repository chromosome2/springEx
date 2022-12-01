package com.spring.ex02;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class PersonExec2 {
	
	public static void main(String[] args) {
		BeanFactory factory=new XmlBeanFactory(new FileSystemResource("person.xml"));
		PersonService person=(PersonService)factory.getBean("person1");
		PersonService person1=(PersonService)factory.getBean("person2");
		person.Info();
		person1.Info();
	}

}
