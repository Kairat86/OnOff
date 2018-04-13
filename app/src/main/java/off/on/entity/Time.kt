package off.on.entity

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

/**
 * Created by kairatdoshekenov on 3/6/18.
 */
@Entity
class Time(@Id var id: Long = 0, val time: Long, var on: Boolean = false) {
    override fun toString(): String {
        return id.toString()
    }
}