package rxkotlin.grace.permission

import android.app.Fragment
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import java.util.*


/**
 * Created by hongyang on 17-5-19.
 */
class RequestFragment : Fragment() {

    /**
     * request success flag
     */
    private val REQUEST_CODE = 200

    /**
     *Callback results
     */
    private var onHasPermissionListener: OnHasPermissionListener? = null

    /**
     * Failure Permission
     */
    private var permissions: Array<out String>? = null

    /**
     * Request permission processing result
     */
    interface OnHasPermissionListener {

        /**
         * User authorization
         */
        fun allow()

        /**
         *The user refused to authorize
         */
        fun refuse()

        /**
         * User clicks to try again
         */
        fun retry(retryList: ArrayList<String>)

        /**
         * User does not allow permission prompts
         */
        fun hide(hideList: ArrayList<String>)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    /**
     *User request permission
     */
    fun rxRequestPermissions(onHasPermissionListener: OnHasPermissionListener, vararg permissions: String) {
        this.permissions = permissions
        this.onHasPermissionListener = onHasPermissionListener

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            requestMFollowing(*permissions)
        } else {
            requestAbove(*permissions)
        }

    }

    /**
     * Less than Version 6.0 The default authorization succeeds
     */
    private fun requestMFollowing(vararg permissions: String) {
        onRequestResult(ArrayList<String>())
    }

    /**
     * Version 6.0 the above,Call system permission
     */
    @RequiresApi(Build.VERSION_CODES.M)
    private fun requestAbove(vararg permissions: String) {
        var permissions = getDeniedPermissions(*permissions)
        if (permissions.isEmpty()) onHasPermissionListener!!.allow()
        when (shouldShowRationalePermissions(this, *permissions)) {
           //explain
            true -> this.onHasPermissionListener!!.retry(ArrayList<String>(Arrays.asList(*permissions)))
           //System request permission
            false -> {
                //isSuccess
              requestPermissions(permissions, REQUEST_CODE)
            }
        }
    }

    /**
     * Retry system permissions
     */
    @RequiresApi(Build.VERSION_CODES.M)
    fun requestPermissions() {
        requestPermissions(permissions, REQUEST_CODE)
    }

    /**
     *Permission processing results
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>?, grantResults: IntArray?) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        nullPointerException(permissions!!)
        nullPointerException(grantResults!!)
        nullPointerException(onHasPermissionListener!!)
        val arrayList = onArrayList(permissions, grantResults)
        onRequestResult(arrayList)
    }

    /**
     * Calculate failed permissions
     */
    fun onArrayList(permissions: Array<out String>?, grantResults: IntArray?): ArrayList<String> {
        val arrayList = ArrayList<String>()
        for (i in 0..permissions!!.size - 1) {
            if (grantResults!![i] != PackageManager.PERMISSION_GRANTED) {
                arrayList.add(permissions!![i])
            }
        }
        return arrayList
    }

    /**
     * Call to the higher level
     */
    fun onRequestResult(arrayList: ArrayList<String>) {
        if (!arrayList.isEmpty()) {
            var arrayList = hasAlwaysDeniedPermission(this, arrayList)
            if (!arrayList.isEmpty()) {
                onHasPermissionListener!!.hide(arrayList)
            }
            onHasPermissionListener!!.refuse()
        } else {
            onHasPermissionListener!!.allow()
        }
    }

    /**
     *Whether the user has denied permission
     */
    fun shouldShowRationalePermissions(o: android.app.Fragment, vararg permissions: String): Boolean {
        if (Build.VERSION.SDK_INT < 23) return false
        var rationale = false
        for (permission in permissions) {
            rationale = o.shouldShowRequestPermissionRationale(permission)
            if (rationale) return true
        }
        return false
    }

    /**
     * Whether the user has a permission alert
     */
    fun hasAlwaysDeniedPermission(fragment: android.app.Fragment, deniedPermissions: List<String>): ArrayList<String> {
        val arrayList = ArrayList<String>()
        for (deniedPermission in deniedPermissions) {
            if (!shouldShowRationalePermissions(fragment, deniedPermission)) {
                arrayList.add(deniedPermission)
            }
        }
        return arrayList
    }


    private fun getDeniedPermissions(vararg permissions: String): Array<String> {
        val deniedList = ArrayList<String>()
        for (permission in permissions)
            if (!hasPermission23(permission))
                deniedList.add(permission)
        return deniedList.toTypedArray()
    }

    fun hasPermission23(vararg permissions: String): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return true
        for (permission in permissions) {
            val hasPermission = ContextCompat.checkSelfPermission(context, permission) == PackageManager
                    .PERMISSION_GRANTED
            if (!hasPermission) return false
        }
        return true
    }

    /**
     * Parameter can not be empty
     */
    fun <T> nullPointerException(t: T) {
        if (t == null) {
            throw NullPointerException("RequestFragment : NullPointerException")
        }
    }

}