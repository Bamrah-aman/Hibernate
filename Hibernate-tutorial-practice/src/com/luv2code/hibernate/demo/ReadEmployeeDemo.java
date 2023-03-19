package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadEmployeeDemo {
	
	public static void main(String[] args) {
		
		//create the session factory
		SessionFactory factory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();
		
		//create session
		Session session=factory.getCurrentSession();
		
		try {
			
			System.out.println("Creating the new employee object...");
			Employee theEmployee=new Employee("Joseph", "Davis", "Alpha Testing inc");
			
			//start the transaction
			session.beginTransaction();
			
			//save the employee
			session.save(theEmployee);
			
			//commit
			session.getTransaction().commit();
			
			//New code
			
			System.out.println("Saved employee. Generated id: "+theEmployee.getId());
			
			session=factory.getCurrentSession();
			session.beginTransaction();
			System.out.println("\n Getting the employee with id: "+theEmployee.getId() );
			
			//retrieve the employee based on id
			Employee myEmployee=session.get(Employee.class, theEmployee.getId());
			System.out.println("Get complete: "+myEmployee);
			
			//commit
			session.getTransaction().commit();
		    System.out.println("Done!!");
			
		}finally {
			factory.close();
		}
	}

}
