package by.itechart.smg.service.impl

import by.itechart.smg.repository.EmployeeRepository
import by.itechart.smg.service.EmployeeService
import org.springframework.stereotype.Service

@Service
class EmployeeServiceImpl constructor(
    private val employeeRepository: EmployeeRepository
): EmployeeService