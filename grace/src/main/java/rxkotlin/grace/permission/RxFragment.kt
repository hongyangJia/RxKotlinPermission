package rxkotlin.grace.permission

import android.app.Fragment
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.content.pm.PackageManager
import java.util.*


/**
 * Created by hongyang on 17-5-19.
 */
class RxFragment : Fragment() {

    private val REQUEST_CODE = 200

    private var onHasPermissionListener: OnHasPermissionListener? = null

    private var permissions: Array<out String>? = null

    interface OnHasPermissionListener {

        fun allow(allowList: ArrayList<String>)

        fun refuse(refuseList: ArrayList<String>)

        fun retry(retryList: ArrayList<String>)

        fun hide(hideList: ArrayList<String>)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }


    fun rxRequestPermissions(onHasPermissionListener: OnHasPermissionListener, vararg permissions: String) {
        this.permissions = permissions
        this.onHasPermissionListener = onHasPermissionListener

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            requestMFollowing(*permissions)
        } else {
            requestAbove(*permissions)
        }

    }

    private fun requestMFollowing(vararg permissions: String) {
        val arrayList = ArrayList<String>()
        onRequestResult(arrayList, permissions)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun requestAbove(vararg permissions: String) {
        when (RxTool.shouldShowRationalePermissions(this, *permissions)) {
            true -> this.onHasPermissionListener!!.retry(ArrayList<String>(Arrays.asList(*permissions)))
            false -> requestPermissions(permissions, REQUEST_CODE)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun requestPermissions() {
        requestPermissions(permissions, REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>?, grantResults: IntArray?) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        RxTool.rxNullPointerException(permissions!!)
        RxTool.rxNullPointerException(grantResults!!)
        RxTool.rxNullPointerException(onHasPermissionListener!!)
        val arrayList = onArrayList(permissions, grantResults)
        onRequestResult(arrayList, permissions)
    }

    fun onArrayList(permissions: Array<out String>?, grantResults: IntArray?): ArrayList<String> {
        val arrayList = ArrayList<String>()
        for (i in 0..permissions!!.size - 1) {
            if (grantResults!![i] != PackageManager.PERMISSION_GRANTED) {
                arrayList.add(permissions!![i])
            }
        }
        return arrayList
    }

    fun onRequestResult(arrayList: ArrayList<String>, permissions: Array<out String>?) {
        if (!arrayList.isEmpty()) {
            if (RxTool.hasAlwaysDeniedPermission(this, arrayList)) {
                onHasPermissionListener!!.hide(arrayList)
            }
            onHasPermissionListener!!.refuse(arrayList)

        } else {
            var allowArry = ArrayList<String>()
            for (i in 0..permissions!!.size - 1) {
                allowArry.add(permissions!![i])
            }
            onHasPermissionListener!!.allow(allowArry)
        }
    }

}