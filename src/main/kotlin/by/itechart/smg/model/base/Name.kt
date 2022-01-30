package by.itechart.smg.model.base

import javax.persistence.Entity

@Entity
class Name(
        var firstName: String,
        var lastName: String,
        var middleName: String?
): BaseEntity<Long>()