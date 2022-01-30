package by.itechart.smg.model.employee.info

import by.itechart.smg.model.base.BaseEntity
import by.itechart.smg.util.Gender
import javax.persistence.Entity

@Entity
class GeneralInfo(
        var birthday: String,
        var gender: Gender
): BaseEntity<Long>()