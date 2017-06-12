package rxkotlin.grace.permission

import android.Manifest
import android.app.Activity
import rxkotlin.grace.permission.launcher.Launcher

/**
 * Created by hongyang on 17-6-5.
 */
class KtPermission(mContext: Activity):Permission<Launcher> {

    var mContext: Activity? = null

    init {
        this.mContext = mContext
    }

    override fun request(vararg permission: String): Launcher {
        return CorePermission(mContext!!).request(*permission)
    }

    override fun requestCamera(): Launcher {
        return CorePermission(mContext!!).request(Manifest.permission.CAMERA)
    }

    override fun requestStorage(): Launcher {
        return CorePermission(mContext!!).request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    override fun requestSMS(): Launcher {
        return CorePermission(mContext!!).request(Manifest.permission.SEND_SMS)
    }

    override fun requestPhone(): Launcher {
        return CorePermission(mContext!!).request(Manifest.permission.CALL_PHONE)
    }

    override fun requestMicroPhone(): Launcher {
        return CorePermission(mContext!!).request(Manifest.permission.RECORD_AUDIO)
    }

    override fun requestLocation(): Launcher {
        return CorePermission(mContext!!).request(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION)
    }

    override fun requestContacts(): Launcher {
        return CorePermission(mContext!!).request(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    override  fun requestCalendar(): Launcher {
        return CorePermission(mContext!!).request(Manifest.permission.READ_CALENDAR)
    }

    override fun requestCameraStorage(): Launcher {
        return CorePermission(mContext!!).request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    override fun requestCameraLocation(): Launcher {
        return CorePermission(mContext!!).request(Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION)
    }

    override  fun requestStorageLocation(): Launcher {
        return CorePermission(mContext!!).request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION)
    }

    override  fun requestCameraMicroPhone(): Launcher {
        return CorePermission(mContext!!).request(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)
    }

    override  fun requestCameraMicroPhoneStorage(): Launcher {
        return CorePermission(mContext!!).request(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

}