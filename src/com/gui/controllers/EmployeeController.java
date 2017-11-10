package com.gui.controllers;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.ejb.services.EmployeeService;
import com.jpa.entities.Employee;

import java.security.MessageDigest;
 
@ManagedBean
public class EmployeeController {
 
	private Employee employee = new Employee();
 
	@EJB
	private EmployeeService service;
 
	public Employee getEmployee() {
		return employee;
	}
 
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public void hashMD5(Employee emp) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(emp.getPwd().getBytes());
		
		byte byteData[] = md.digest();
		
		StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        emp.setPwd(sb.toString());
	}
 
	public void saveEmployee(Employee emp) {
		try{
			hashMD5(emp);
			service.addEmployee(emp);
			FacesContext context = FacesContext.getCurrentInstance();
	         
	        context.addMessage(null, new FacesMessage("Successful",  "Se realizó el Login") );
		} catch(Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Error", "Ha ocurrido un problema!") );
			e.printStackTrace();
		}
		
	}
 
}

