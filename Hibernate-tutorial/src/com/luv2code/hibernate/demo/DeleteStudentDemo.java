package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;


public class DeleteStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory=new Configuration()
                               .configure("hibernate.cfg.xml")
                               .addAnnotatedClass(Student.class)
                               .buildSessionFactory();
		//create a session
		Session session=factory.getCurrentSession();
		
		try {
			int studentId=3000;
						
						
			//now get a new session and start transaction
			session=factory.getCurrentSession();
			session.beginTransaction();
			
			
			//Retrieve
			System.out.println("Gestting student with id: "+studentId);
			
			Student myStudent=session.get(Student.class, studentId);
			
			//delete the student
			//System.out.println("Deleteing the STudent: "+myStudent);
			//session.delete(myStudent);
			
			
			//delete the student id:3002
			System.out.println("\n\ndelete the student id:3002");
			session.createQuery("delete from Student where id=3002")
			.executeUpdate();
			
			//commit
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}finally {
			factory.close();
		}
		
	}

}
