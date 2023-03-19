package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;


public class ReadStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory=new Configuration()
                               .configure("hibernate.cfg.xml")
                               .addAnnotatedClass(Student.class)
                               .buildSessionFactory();
		//create a session
		Session session=factory.getCurrentSession();
		
		try {
			
			//create a student object
			System.out.println("creating a new student object..");
			Student tempStudent=new Student("Daffy", "Duck", "daffy@luv2code.com");
			
			//start the transaction
			session.beginTransaction();
			
			
			//save the student object
			System.out.println("Saving the student...");
			session.save(tempStudent);
			
			
			//commit the transaction
			session.getTransaction().commit();
			
			//My new code
			
			//find out the student't id: primary key
			System.out.println("saved student genetrated id: "+tempStudent.getId());
			
			//now get a new seesion and start transaction
			session=factory.getCurrentSession();
			session.beginTransaction();
			
			
			//retreieve
			System.out.println("Gestting student with id: "+tempStudent.getId());
			
			Student myStudent=session.get(Student.class, tempStudent.getId());
			System.out.println("get complete: "+myStudent);
			
			//commit
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}finally {
			factory.close();
		}
		
	}

}
