package rxkotlin.grace.permission

import android.Manifest
import android.app.Activity
import io.reactivex.Observable

/**
 * Created by hongyang on 17-5-27.
 */
class RxKotlinPermission(mContext: Activity) {

    private var rxGrace: RxGracePermission? = null

    init {
        this.rxGrace = RxGracePermission(mContext)
    }

    protected fun request(vararg permission: String) {
        RxTool.rxNullPointerException(rxGrace!!)
        rxGrace!!.request(*permission)
    }

    fun requestCamera(): Observable<RxInteractive> {
        RxTool.rxNullPointerException(rxGrace!!)
        return rxGrace!!.request(Manifest.permission.CAMERA)
    }

    fun requestStorage(): Observable<RxInteractive> {
        RxTool.rxNullPointerException(rxGrace!!)
        return rxGrace!!.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    fun requestSMS(): Observable<RxInteractive> {
        RxTool.rxNullPointerException(rxGrace!!)
        return rxGrace!!.request(Manifest.permission.SEND_SMS)
    }

    fun requestPhone(): Observable<RxInteractive> {
        RxTool.rxNullPointerException(rxGrace!!)
        return rxGrace!!.request(Manifest.permission.CALL_PHONE)
    }

    fun requestMicroPhone(): Observable<RxInteractive> {
        RxTool.rxNullPointerException(rxGrace!!)
        return rxGrace!!.request(Manifest.permission.RECORD_AUDIO)
    }

    fun requestLocation(): Observable<RxInteractive> {
        RxTool.rxNullPointerException(rxGrace!!)
        return rxGrace!!.request(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    fun requestContacts(): Observable<RxInteractive> {
        RxTool.rxNullPointerException(rxGrace!!)
        return rxGrace!!.request(Manifest.permission.ACCESS_FINE_LOCATION)
    }


    fun requestCalendar(): Observable<RxInteractive> {
        RxTool.rxNullPointerException(rxGrace!!)
        return rxGrace!!.request(Manifest.permission.READ_CALENDAR)
    }


    fun requestCameraStorage(): Observable<RxInteractive> {
        RxTool.rxNullPointerException(rxGrace!!)
        return rxGrace!!.request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }


    fun requestCameraLocation(): Observable<RxInteractive> {
        RxTool.rxNullPointerException(rxGrace!!)
        return rxGrace!!.request(Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION)
    }

    fun requestStorageLocation(): Observable<RxInteractive> {
        RxTool.rxNullPointerException(rxGrace!!)
        return rxGrace!!.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION)
    }

    fun requestCameraMicroPhone(): Observable<RxInteractive> {
        RxTool.rxNullPointerException(rxGrace!!)
        return rxGrace!!.request(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)
    }

    fun requestCameraMicroPhoneStorage(): Observable<RxInteractive> {
        RxTool.rxNullPointerException(rxGrace!!)
        return rxGrace!!.request(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    fun setCancelInteractive() {
        RxTool.rxNullPointerException(rxGrace!!)
        rxGrace!!.setInteractive(RxGracePermission.INTERACTIVE.CUSTOMIZE)
    }

    fun settingRequest(): RxGracePermission.SingleRxRequest {
        return rxGrace!!.settingRequest()
    }

}