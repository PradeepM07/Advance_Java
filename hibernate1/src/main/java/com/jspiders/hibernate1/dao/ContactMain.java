package com.jspiders.hibernate1.dao;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jspiders.hibernate1.dto.Contact;

public class ContactMain {
	
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		Contact contact = new Contact();
//		System.out.println("Enter Id:");
//		int id = scanner.nextInt();
//		scanner.nextLine();
//		contact.setId(id);
		
		System.out.println("Enter First Name:");
		String firstName = scanner.nextLine();
		contact.setFirstName(firstName);
		
		System.out.println("Enter last Name:");
		String lastName = scanner.nextLine();
		contact.setLastName(lastName);
		
		System.out.println("Enter email Id:");
		String email = scanner.nextLine();
		contact.setEmail(email);
		
		System.out.println("Enter Mobile Nnumber:");
		String mobile = scanner.nextLine();
		contact.setMobile(mobile);
		
		openConnection();
		entityTransaction.begin();
	
		entityManager.persist(contact);
		entityTransaction.commit();
		closeConnection();
		scanner.close();
		
		
	}
	
	public static void openConnection() {
		
		entityManagerFactory = Persistence.createEntityManagerFactory("contact_manager");
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
