package com.springboot.employee.crudemployee.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.employee.crudemployee.entity.Employee;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {
	
	// define field for entitymanager
	
	private EntityManager entityManager;
		
	// set up constructor injection
	
	@Autowired
	public EmployeeDAOJpaImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}	
	
	@Override
	public List<Employee> findAll() {
		
		// create query
		Query theQuery = entityManager.createQuery("from Employee", Employee.class);
		
		// execute query and get result list
		List<Employee> employees = theQuery.getResultList();
		
		// return the results		
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		
		// get the employee
		Employee theEmployee = entityManager.find(Employee.class, theId);
		
		// return the employee		
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		
		// save or update the employee		
		Employee dbEmployee = entityManager.merge(theEmployee);
		
		// update with id from db ... so we can get generated id for save/ insert
		
		theEmployee.setId(dbEmployee.getId());		
	}

	@Override
	public void deleteById(int theId) {
		
		// delete the employee
		Query theQuery = entityManager.createQuery("delete from Employee where id=:employeeId");
		
		theQuery.setParameter("employeeId", theId);
		
		theQuery.executeUpdate();

	}

}
