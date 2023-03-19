package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;


public class CreateDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory=new Configuration()
                               .configure("hibernate.cfg.xml")
                               .addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class)
                               .buildSessionFactory();
		//create a session
		Session session=factory.getCurrentSession();
		
		try {
			//create the object
			Instructor tempInstructor=
					new Instructor("Dua", "Lipa", "Dua@luv2code.com");
			
			InstructorDetail tempInstructorDetail=new 
					InstructorDetail("www.futureNostlagia", "Singing !!");
			
			//associate the object
			tempInstructor.setInstructorDetail(tempInstructorDetail);
						
			//start the transaction
			session.beginTransaction();			
			
			//save the instructor
			//Note: this will save the details object because of cascade all
			System.out.println("saving the intructor: "+tempInstructor );
			session.save(tempInstructor);
			
			//commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
		}finally {
			factory.close();
		}
		
	}

}
