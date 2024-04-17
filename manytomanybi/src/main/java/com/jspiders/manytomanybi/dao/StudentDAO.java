package com.jspiders.manytomanybi.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jspiders.manytomanybi.dto.Student;
import com.jspiders.manytomanybi.dto.Subject;

public class StudentDAO {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;
	
	public static void main(String[] args) {
		
		Student student1 = new Student();
		student1.setName("pradeep");
		student1.setEmail("pradeep@gmail.com");
		student1.setMobile("9834853757");
		student1.setSubjects(null);
		
		Student student2 = new Student();
		student2.setName("rushi");
		student2.setEmail("rushi@gmail.com");
		student2.setMobile("9876543210");
		student2.setSubjects(null);
		
		Subject subject1 = new Subject();
		subject1.setName("CORE JAVA");
		subject1.setFees(15000);
		subject1.setStudents(null);
		
		Subject subject2 = new Subject();
		subject2.setName("SQL");
		subject2.setFees(10000);
		subject2.setStudents(null);
		
		List<Student> students = new ArrayList<>();
		students.add(student1);
		students.add(student2);
		
		List<Subject> subjects = new ArrayList<>();
		subjects.add(subject1);
		subjects.add(subject2);
		
		student1.setSubjects(subjects);
		student2.setSubjects(subjects);
		
		subject1.setStudents(students);
		subject2.setStudents(students);
		
		openConnection();
		entityTransaction.begin();
		entityManager.persist(student1);
		entityManager.persist(student2);
		entityManager.persist(subject1);
		entityManager.persist(subject2);
		entityTransaction.commit();
		closeConnection();
		
	}

	private static void openConnection() {
		entityManagerFactory = Persistence.createEntityManagerFactory("student");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();

	}

	private static void closeConnection() {
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}
		if (entityManager != null) {
			entityManager.close();
		}
		if (entityTransaction != null) {
			if (entityTransaction.isActive()) {
				entityTransaction.rollback();
			}
		}
	}

}
