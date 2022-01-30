package by.itechart.smg.model.employee.info

import by.itechart.smg.model.base.BaseEntity
import by.itechart.smg.model.base.Location
import javax.persistence.Entity
import javax.persistence.OneToOne

@Entity
class CorporateInfo(
        var position: String,
        var unitId: String,
        @OneToOne
        var location: Location
): BaseEntity<Long>()