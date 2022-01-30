package by.itechart.smg.model.employee.info

import by.itechart.smg.model.base.BaseEntity
import javax.persistence.Entity

@Entity
class ContactInfo(
        var skype: String,
        var email: String,
        var phone: String,
        var emergencyIngo: String?
): BaseEntity<Long>()