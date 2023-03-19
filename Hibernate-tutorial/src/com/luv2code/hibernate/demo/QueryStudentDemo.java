package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;


public class QueryStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory=new Configuration()
                               .configure("hibernate.cfg.xml")
                               .addAnnotatedClass(Student.class)
                               .buildSessionFactory();
		//create a session
		Session session=factory .getCurrentSession();
		
		try {
			
						//start the transaction
			session.beginTransaction();
			
			//query the students
			List<Student> theStudents=session
					.createQuery("from Student").list();
			
			//display the students
			displayStudents(theStudents);
						
			
			//query students: lastName='Doe'
			theStudents=session
					.createQuery("from Student s where s.lastName='Doe'").list();
			System.out.println("\n\nHaving last name is doe");
			displayStudents(theStudents);
			
			
			//query students: lastName='Doe' or firstName='Daffy'
			theStudents=session
					.createQuery("from Student s where"
							+ " s.lastName='Doe' or s.firstName='Daffy'").list();
			System.out.println("\n\nHaving last name is doe or firstName is Daffy");
			displayStudents(theStudents);
			
			
			//query students: where email LIKE '%gmail.com'
			theStudents=session
					.createQuery("from Student s where"
							+ " s.email like '%gmail.com'").list();
			System.out.println("\n\nHaving email luv2code.com");
			displayStudents(theStudents);
			
			
			
			//commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
		}finally {
			factory.close();
		}
		
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
