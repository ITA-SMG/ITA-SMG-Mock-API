package by.itechart.smg.model.employee

import by.itechart.smg.model.base.BaseEntity
import by.itechart.smg.model.employee.info.ContactInfo
import by.itechart.smg.model.employee.info.CorporateInfo
import by.itechart.smg.model.employee.info.EmployeeName
import by.itechart.smg.model.employee.info.GeneralInfo
import javax.persistence.Entity
import javax.persistence.OneToOne

@Entity
class Employee(
        @OneToOne
        var generalInfo: GeneralInfo,
        @OneToOne
        var nameInfo: EmployeeName,
        @OneToOne
        var contactInfo: ContactInfo,
        @OneToOne
        var corporateInfo: CorporateInfo,
        @OneToOne
        var educationInfo: GeneralInfo
): BaseEntity<Long>()