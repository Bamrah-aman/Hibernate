package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;


public class UpdateStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory=new Configuration()
                               .configure("hibernate.cfg.xml")
                               .addAnnotatedClass(Student.class)
                               .buildSessionFactory();
		//create a session
		Session session=factory.getCurrentSession();
		
		try {
			int studentId=3001;
						
						
			//now get a new seesion and start transaction
			session=factory.getCurrentSession();
			session.beginTransaction();
			
			
			//Retrieve
			System.out.println("Gestting student with id: "+studentId);
			
			Student myStudent=session.get(Student.class, studentId);
			System.out.println("Updating student");
			myStudent.setFirstName("Scooby");
			
			
			//commit
			session.getTransaction().commit();
			//New code
			
			session=factory.getCurrentSession();
			session.beginTransaction();
			
			//updating email for all students
			System.out.println("updating email for all students");
			session.createQuery("update Student set email='foo@gmail.com'")
			.executeUpdate();
			
			//commit
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}finally {
			factory.close();
		}
		
	}

}
