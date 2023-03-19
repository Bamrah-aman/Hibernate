package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;


public class DeleteInstructorDetailDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory=new Configuration()
                               .configure("hibernate.cfg.xml")
                               .addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class)
                               .buildSessionFactory();
		//create a session
		Session session=factory.getCurrentSession();
		
		try {
									
			//start the transaction
			session.beginTransaction();			
			
			//get the instructor detail object
			int theId=6;
			InstructorDetail tempInstructorDetail=
					session.get(InstructorDetail.class, theId);
			
			//print the instructor detail
			System.out.println("tempInstructorDetail: "+tempInstructorDetail);
			
			//printing the associated instructor
			System.out.println("The associated instructor: "+
			tempInstructorDetail.getInstructor());
			
			//now let's delete the instructor
			System.out.println("Deleting");
			System.out.println("Deleting tempInstructorDetail: "+tempInstructorDetail);
			tempInstructorDetail.getInstructor().setInstructorDetail(null);
			session.delete(tempInstructorDetail);
			
			//commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		finally {
			//handle connection leak issue
			session.close();
			factory.close();
		}
		
	}

}
