package rxkotlin.grace.permission.ui

import android.content.Context
import android.content.DialogInterface
import android.util.Log
import rxkotlin.grace.permission.R
import rxkotlin.grace.permission.RxTool
import rxkotlin.grace.permission.RxTool.TITLE

/**
 * Created by hongyang on 17-5-25.
 */
class RxDialog(context: Context) : IRxDialog {

    private var mBuilder: android.support.v7.app.AlertDialog.Builder? = null
    private var onIRxDialogListener: IRxDialog.OnIRxDialogListener? = null;
    private var defult = true
    private var context: Context? = null

    init {
        this.context = context
        mBuilder = android.support.v7.app.AlertDialog.Builder(context)
    }

    override fun setOnIRxDialogListener(onIRxDialogListener: IRxDialog.OnIRxDialogListener) {
        this.onIRxDialogListener = onIRxDialogListener;
    }

    override
    fun promptPermission(title: String, message: String) {
        this.defult = true
        val title = if (title.equals(TITLE)) context!!.getString(R.string.permission_title_permission_rationale) else title
        mBuilder!!.setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.permission_again, mClickListener)
                .setNegativeButton(R.string.permission_cancel, null)
    }

    override
    fun noPromptPermission(title: String, message: String) {
        this.defult = false
        val title = if (title.equals(TITLE)) context!!.getString(R.string.permission_title_permission_rationale) else title
        mBuilder!!.setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.permission_setting, mClickListener)
                .setNegativeButton("", null)
    }

    override
    fun show() {
        RxTool.rxNullPointerException(mBuilder!!)
        mBuilder!!.show()
    }

    private val mClickListener = DialogInterface.OnClickListener { dialog, which ->
        when (which) {
            DialogInterface.BUTTON_POSITIVE -> if (onIRxDialogListener != null) {
                when (defult) {
                    true -> onIRxDialogListener!!.onRequest()
                    false -> onIRxDialogListener!!.toAndroidSet()
                }
            }
        }
    }
}