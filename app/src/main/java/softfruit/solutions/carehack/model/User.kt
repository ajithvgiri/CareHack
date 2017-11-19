package softfruit.solutions.carehack.model

/**
 * Created by ajithvgiri on 16/11/17.
 */

class User {
    var uid: String = ""
    var fullname: String = ""
    var age: String = ""
    var place: String = ""
    var image_url: String = ""


    constructor(uid: String, fullname: String, age: String,place: String, image_url: String) {
        this.uid = uid
        this.fullname = fullname
        this.age = age
        this.place = place
        this.image_url = image_url
    }

    constructor()

}
