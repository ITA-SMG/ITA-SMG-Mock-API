package by.itechart.smg.repository

import by.itechart.smg.model.employee.Employee
import org.springframework.data.repository.CrudRepository

interface EmployeeRepository: CrudRepository<Employee, Long>