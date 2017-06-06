package rxkotlin.grace.permission.ui

import android.app.Activity
import rxkotlin.grace.permission.R
import rxkotlin.grace.permission.date.KtRequest
import java.util.*

/**
 * Created by hongyang on 17-5-24.
 */
object Description {

    private var DECLINE_START: String? = null
    private var DECLINE_END: String? = null
    private var PERMISSIOIN: String? = null
    private var FOREVER_START: String? = null
    private var FOREVER_END: String? = null
    private const val DEFULT = "defult"

    private fun initDescription(mContext: Activity) {
        DECLINE_START = mContext.getString(R.string.decline_start)
        DECLINE_END = mContext.getString(R.string.decline_end)
        PERMISSIOIN = mContext.getString(R.string.permission)
        FOREVER_START = mContext.getString(R.string.forever_start)
        FOREVER_END = mContext.getString(R.string.forever_end)
    }

    fun declineDescription(rxRequest: KtRequest, mContext: Activity, permissions: ArrayList<String>): String {
        var s = rxRequest.message
        initDescription(mContext)
        var message = if (rxRequest.message.equals(DEFULT)) DECLINE_START else rxRequest.message
        val buffer = StringBuffer(message + " ")
        buffer.append(edit(mContext, permissions)).append(" " + DECLINE_END)
        return buffer.toString()
    }

    fun foreverDescription(rxRequest: KtRequest, mContext: Activity, permissions: ArrayList<String>): String {
        initDescription(mContext)
        var message = if (rxRequest.message.equals(DEFULT)) FOREVER_START else rxRequest.message
        val buffer = StringBuffer(message + " ")
        buffer.append(edit(mContext, permissions)).append(" " + FOREVER_END)
        return buffer.toString()
    }

    private fun edit(mContext: Activity, permissions: ArrayList<String>): String {
        val buffer = StringBuffer()
        var status = false
        for (permission in permissions) {
            var p: String = getPermissionName(mContext, permission)
            if (!p.equals(DEFULT)) {
                if (buffer.indexOf(p) == -1) {
                    buffer.append(p).append("、")
                    status = true
                }
            }
        }
        if (status) {
            buffer.deleteCharAt(buffer.length - 1).append(" "+PERMISSIOIN)
        }
        return buffer.toString()
    }

    private fun getPermissionName(mContext: Activity, permissioinName: String): String {
        val stringID = mContext.getResources().getIdentifier(permissioinName,
                "string",
                mContext.packageName)
        return if (stringID != 0) mContext.getResources().getString(stringID) else DEFULT
    }

}