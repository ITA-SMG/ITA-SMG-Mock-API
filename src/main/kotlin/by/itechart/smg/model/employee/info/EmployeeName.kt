package by.itechart.smg.model.employee.info

import by.itechart.smg.model.base.BaseEntity
import by.itechart.smg.model.base.Name
import javax.persistence.Entity
import javax.persistence.OneToOne

@Entity
class EmployeeName(
        @OneToOne
        var en: Name,
        @OneToOne
        var ru: Name
): BaseEntity<Long>()