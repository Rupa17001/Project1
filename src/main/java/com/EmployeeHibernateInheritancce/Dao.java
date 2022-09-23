package com.EmployeeHibernateInheritancce;




import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



public class Dao {
	Configuration cnfg = null;
    SessionFactory sf ;
    Session ses;
    Transaction tx;
 
    public void connect()
    {
    	cnfg = new Configuration().configure().addAnnotatedClass(Employee.class).addAnnotatedClass(Fresher.class).addAnnotatedClass(Experienced.class);
    	sf = cnfg.buildSessionFactory();
    	ses = sf.openSession();
    	tx = ses.beginTransaction();
    }
    public void disconnect() {
    	tx.commit();
    	sf.close();
    	
    }
    public Session addExpEmp(Experienced exEmp) {
    	ses.save(exEmp);
    	return ses;
    }
    public Session addEmp(Employee emp) {
    	ses.save(emp);
    	return ses;
    }
    public Session addFrsEmp(Fresher fEmp) {
    	ses.save(fEmp);
    	return ses;
    }
    public int getEmployee(int id) {
    	int count = (int) ses.createQuery("from Employee emp where emp.empId =: Id").setParameter("id", id).uniqueResult();
    	return count;
    }
    public List getAll()
    {	Query q =  ses.createQuery(" from Employee");
		List emp1 = (List) q.getResultList();
		return emp1;
    	
    	}
    public int delEmployee(int id) {
    	Query q  = ses.createQuery("delete from Employee emp where emp.empId =: id").setParameter("id", id);
    	int count =q.executeUpdate();
    	return count;
    }
    }
