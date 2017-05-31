package rxkotlin.grace.permission

import java.util.ArrayList

/**
 * Created by hongyang on 17-5-25.
 */
class RxInteractive {

    val rxArray: ArrayList<String>

    val rxMode: RxGracePermission.RxMode

    var rxDate: Any

    constructor(arrayList: ArrayList<String>, rxMode: RxGracePermission.RxMode) {
        this.rxArray = arrayList;
        this.rxMode = rxMode;
        this.rxDate = Any();
    }

}