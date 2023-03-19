package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateEmployeeDemo {

	public static void main(String[] args) {
		// create session factory
				SessionFactory factory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Employee.class)
										.buildSessionFactory();
				//create session
				Session session=factory.getCurrentSession();
				
				try {
					int employeeId=1;
					
					session=factory.getCurrentSession();
					session.beginTransaction();
					System.out.println("Getting the employee with the id: "+employeeId);
					Employee myEmployee=session.get(Employee.class, employeeId);
					
					System.out.println("Updating the Employee..");
					myEmployee.setFirstName("Nancy");
					
					//commit the transaction
					session.getTransaction().commit();
					
					//New code
					session=factory.getCurrentSession();
					session.beginTransaction();
					
					System.out.println("Updating company for all employee..");
					session.createQuery("update Employee set company='Top java coders inc'")
					.executeUpdate();
					
					//commit
					session.getTransaction().commit();
					
					System.out.println("Done!");
					
					
					
				}finally {
					factory.close();
				}

	}

}
