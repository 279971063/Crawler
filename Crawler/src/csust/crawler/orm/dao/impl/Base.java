package csust.crawler.orm.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Base {
	private SessionFactory sessionFactory;

	public Session getSession() {
		
		return this.sessionFactory.getCurrentSession();
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
}
