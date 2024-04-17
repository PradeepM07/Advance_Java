package com.jspiders.hibernate1.dao;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jspiders.hibernate1.dto.Contact;

public class ContactMain3 {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter ID:");
		int id = scanner.nextInt();
		scanner.nextLine();
		openConnection();
		Contact contact = entityManager.find(Contact.class, id);
		if (contact != null) {
			System.out.println(contact);
			entityTransaction.begin();
			System.out.println("Enter updated email:");
			String email = scanner.nextLine();
			contact.setEmail(email);
			entityManager.persist(contact);
			entityTransaction.commit();
		} else {
			System.out.println("Contact does not exist.");
		}
		scanner.close();
		closeConnection();

	}

	public static void openConnection() {
		entityManagerFactory = Persistence.createEntityManagerFactory("contact_manager");
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
