package by.itechart.smg.model.auth.token

import by.itechart.smg.model.base.BaseEntity
import javax.persistence.Entity

@Entity
class AuthorizationToken (
        val token: String
): BaseEntity<Long>()