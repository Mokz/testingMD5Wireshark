package com.ejb.services.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
 
import com.ejb.services.EmployeeService;
import com.jpa.entities.Employee;
 
@Stateless
public class EmployeeServiceImpl implements EmployeeService {
 
	@PersistenceContext(name = "EmployeeApp")
	private EntityManager em;
 
	@Override
	public void addEmployee(Employee emp) {
 
		em.persist(emp);
 
	}
 
}