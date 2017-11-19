package softfruit.solutions.carehack.model

/**
 * Created by ajithvgiri on 17/11/17.
 */

class Slot {
    var slot: Int = 0
    var slot_date: String = ""
    var slot_month: String = ""
    var slot_time: String = ""
    var selected: Boolean = false

    constructor(slot: Int, slot_date: String, slot_month: String, selected: Boolean) {
        this.slot = slot
        this.slot_date = slot_date
        this.slot_month = slot_month
        this.selected = selected
    }

    constructor(slot: Int, slot_time: String, selected: Boolean) {
        this.slot = slot
        this.slot_time = slot_time
        this.selected = selected
    }


}
