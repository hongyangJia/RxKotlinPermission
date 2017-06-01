package rxkotlin.grace.permission

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import rxkotlin.grace.permission.ui.IRxDialog
import rxkotlin.grace.permission.ui.RxDialog
import java.util.ArrayList

/**
 * Created by hongyang on 17-5-24.
 */
object RxTool {

    const val TITLE = "DEFULT"
    const val MESSAGE = "DEFULT"

    fun toAndroidSetting(context: Context) {
        val intent = Intent(Settings.ACTION_APPLICATION_SETTINGS)
        context.startActivity(intent)
    }

    fun shouldShowRationalePermissions(o: android.app.Fragment, vararg permissions: String): Boolean {
        if (Build.VERSION.SDK_INT < 23) return false
        var rationale = false
        for (permission in permissions) {
            rationale = o.shouldShowRequestPermissionRationale(permission)
            if (rationale) return true
        }
        return false
    }

    fun hasAlwaysDeniedPermission(fragment: android.app.Fragment, deniedPermissions: List<String>): Boolean {
        for (deniedPermission in deniedPermissions) {
            if (!shouldShowRationalePermissions(fragment, deniedPermission)) {
                return true
            }
        }
        return false
    }

    fun rxNullPointerException(any: Any) {
        if (any == null) {
            NullPointerException("RxFragment : rxNullPointerException")
        }
    }


}