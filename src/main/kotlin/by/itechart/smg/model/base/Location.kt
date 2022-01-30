package by.itechart.smg.model.base

import javax.persistence.Entity

@Entity
class Location(
        var city: String,
        var office: String,
        var room: String
): BaseEntity<Long>()