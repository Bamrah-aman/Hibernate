package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;


public class AddCoursesForDuaLipaDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory=new Configuration()
                               .configure("hibernate.cfg.xml")
                               .addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class)
                               .addAnnotatedClass(Course.class)
                               .addAnnotatedClass(Review.class)
                               .addAnnotatedClass(Student.class)
                               .buildSessionFactory();
		//create a session
		Session session=factory.getCurrentSession();
		
		try {
			//start the transaction
			session.beginTransaction();		
			
			//get the student Dua lipa from database
			int studentId=2;
			Student tempStudent=session.get(Student.class, studentId);
			System.out.println("\nLoaded student: "+tempStudent);
			System.out.println("Courses: "+tempStudent.getCourses());
			
			//create more courses
			Course tempCourse=new Course("Rubik's Cube: How to speed Cube");
			Course tempCourse1=new Course("Atari 2600-Game Development");
			
			//add student to courses
			tempCourse.addStudent(tempStudent);
			tempCourse1.addStudent(tempStudent);
			
			//save the courses
			System.out.println("\n Saving the courses...");
			
			session.save(tempCourse);
			session.save(tempCourse1);
			
			//commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
		}finally {
			session.close();
			factory.close();
		}
		
	}

}
