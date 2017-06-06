package rxkotlin.grace.permission

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.support.annotation.RequiresApi
import rxkotlin.grace.permission.launcher.Launcher
import rxkotlin.grace.permission.launcher.LaunchTask
import rxkotlin.grace.permission.ui.Description
import rxkotlin.grace.permission.ui.Template
import java.util.ArrayList


/**
 * Created by hongyang on 17-5-19.
 */
class CorePermission(mContext: Activity) : RequestFragment.OnHasPermissionListener, Template.OnIRxDialogListener, Launcher {

    private val FOREVER_DESCRIPTION = "foreverDescription"

    private val DECLINE_DESCRIPTION = "declineDescription"

    private val TAG = CorePermission::javaClass.name

    private var rxPermissionsFragment: RequestFragment

    private var mContext: Activity? = null;

    private var rxDialog: Template? = null

    private var launcher: LaunchTask? = null

    var  permission:Array<String>?=null

    init {
        this.mContext = mContext
        KtPermissionSetting.isFirst(mContext)
        rxPermissionsFragment = getRxPermissionsFragment(mContext)
    }

    private fun getRxPermissionsFragment(activity: Activity): RequestFragment {
        nullPointerException(mContext!!)
        var rxPermissionsFragment: RequestFragment? = findRxPermissionsFragment(activity)
        val isNewInstance = rxPermissionsFragment == null
        if (isNewInstance) {
            rxPermissionsFragment = RequestFragment()
            val fragmentManager = activity.fragmentManager
            fragmentManager
                    .beginTransaction()
                    .add(rxPermissionsFragment, TAG)
                    .commitAllowingStateLoss()
            fragmentManager.executePendingTransactions()
        }
        return rxPermissionsFragment!!
    }

    private fun findRxPermissionsFragment(activity: Activity): RequestFragment? {
        return activity.fragmentManager.findFragmentByTag(TAG) as RequestFragment?
    }

    fun request(vararg permission: String): Launcher {
        this.permission=permission as Array<String>
        return this
    }

    override fun launcher(launcher: LaunchTask) {
        this.launcher = launcher
        rxPermissionsFragment.rxRequestPermissions(this, *permission!!)
    }

    override
    fun refuse() {
        nullPointerException(launcher!!)
        launcher!!.launch(false)
    }

    override
    fun allow() {
        nullPointerException(launcher!!)
        launcher!!.launch(true)
    }

    override
    fun hide(hideList: ArrayList<String>) {
        nullPointerException(launcher!!)
        showRxToolDialog(FOREVER_DESCRIPTION, hideList)
    }

    override
    fun retry(retryList: ArrayList<String>) {
        showRxToolDialog(DECLINE_DESCRIPTION, retryList)
    }

    override
    fun toAndroidSetting() {
        nullPointerException(mContext!!)
        val intent = Intent(Settings.ACTION_APPLICATION_SETTINGS)
        mContext!!.startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override
    fun onRequest() {
        nullPointerException(rxPermissionsFragment!!)
        rxPermissionsFragment.requestPermissions()
    }

    fun showRxToolDialog(status: String, permissions: ArrayList<String>) {
        this.iRxDialog(mContext!!)
        nullPointerException(rxDialog!!)
        rxDialog!!.setOnIRxDialogListener(this)
        when (status) {
            FOREVER_DESCRIPTION -> rxDialog!!.noPromptPermission(
                     KtPermissionSetting.getSetting().title,
                     Description.foreverDescription(KtPermissionSetting.getSetting(), mContext!!, permissions))
            DECLINE_DESCRIPTION -> rxDialog!!.promptPermission(
                     KtPermissionSetting.getSetting().title,
                     Description.declineDescription(KtPermissionSetting.getSetting(), mContext!!, permissions))
        }
        rxDialog!!.show()
    }

    fun iRxDialog(mContext: Activity) {
        if (rxDialog == null) {
            val c = Class.forName(KtPermissionSetting.getSetting().rxDialog)
            val con = c.getConstructor(Context::class.java)
            rxDialog = con.newInstance(mContext) as Template
        }
    }

    fun <T>nullPointerException(t:T) {
        if (t == null) {
            throw  NullPointerException("CorePermission : NullPointerException")
        }
    }

}