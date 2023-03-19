package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;


public class EagerLazyDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory=new Configuration()
                               .configure("hibernate.cfg.xml")
                               .addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class)
                               .addAnnotatedClass(Course.class)
                               .buildSessionFactory();
		//create a session
		Session session=factory.getCurrentSession();
		
		try {
			//start the transaction
			session.beginTransaction();		
			
			//get the instructor from db
			int theId=1;
			Instructor tempInstructor=
					session.get(Instructor.class, theId);
			
			System.out.println("luv2code: Instructor: "+tempInstructor);
			
			System.out.println("Courses: "+tempInstructor.getCourses());

			
			//commit the transaction
			session.getTransaction().commit();
			
			//close the session
			session.close();
			System.out.println("\nluv2code: The session is now closed!\n");
			
			
			//since courses are lazy loaded this should fail
			//get course for the instructor
		System.out.println("Courses: "+tempInstructor.getCourses());

			
			System.out.println("luv2code: Done!");
			
		}finally {
			session.close();
			factory.close();
		}
		
	}

}
