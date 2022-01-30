package by.itechart.smg.model.employee.info

import by.itechart.smg.model.base.BaseEntity
import javax.persistence.Entity

@Entity
class EducationInfo(
        var university: String,
        var faculty: String,
        var major: String,
        var graduationYear: Int
): BaseEntity<Long>()