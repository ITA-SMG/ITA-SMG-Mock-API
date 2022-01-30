package by.itechart.smg.model.auth

import by.itechart.smg.model.base.BaseEntity
import javax.persistence.Entity

@Entity
class AuthenticationData(
        val username: String,
        val passwordHash: String
): BaseEntity<Long>()