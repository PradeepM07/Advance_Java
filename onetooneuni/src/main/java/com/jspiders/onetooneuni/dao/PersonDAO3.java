package com.jspiders.onetooneuni.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jspiders.onetooneuni.dto.Aadhar;
import com.jspiders.onetooneuni.dto.Person;

public class PersonDAO3 {
	
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;
	
	public static void main(String[] args) {
		openConnection();
//		entityTransaction.begin();
		Person person = entityManager.find(Person.class, 1);
		Aadhar aadhar = entityManager.find(Aadhar.class, 1);
		if(person != null && aadhar != null) {
			System.out.println(person);
			System.out.println(aadhar);
		}else {
			System.out.println("Person does not exist.....");
		}
//		entityTransaction.commit();
		closeConnection();
		
	
	}
	
	public static void openConnection() {
		entityManagerFactory = Persistence.createEntityManagerFactory("person");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
	}

	public static void closeConnection() {
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
