package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateEmployeeDemo {

	public static void main(String[] args) {
		
		//creating the session factory
		SessionFactory factory= new Configuration().
				configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class)
				.buildSessionFactory();
		
		//create session
		Session session=factory.getCurrentSession();
		
		try {
			//Creating the employee object
			System.out.println("Creating the new employee object...");
			Employee theEmployee=new Employee("Anup", "Joshi", "Ame Inc");
			
			//start the transaction
			session.beginTransaction();
			
			//save the employee object
			System.out.println("Saving the employee object...");
			session.save(theEmployee);
			
			//commit
			session.getTransaction().commit();
			
			System.out.println("Done!!");
			
		}finally {
			factory.close();
		}
		
	}
}
