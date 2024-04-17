package com.jspiders.hibernate1.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.jspiders.hibernate1.dto.Contact;

public class ContactMain6 {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;

	public static void main(String[] args) {
		openConnection();
		Query query = entityManager.createQuery("SELECT contact FROM Contact contact WHERE email = ?1");
		query.setParameter(1, "pradeep@gmail.com");
		try {
			Contact contact = (Contact) query.getSingleResult();
			if (contact != null) {
				System.out.println(contact);
			}
		} catch (Exception e) {
			System.out.println("Contacts does not exist.");
		}

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
