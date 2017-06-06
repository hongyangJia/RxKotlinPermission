package rxkotlin.grace.permission.ui

import android.content.Context
import android.content.DialogInterface
import rxkotlin.grace.permission.R
/**
 * Created by hongyang on 17-5-25.
 */
class DefaultTemplate(context: Context) : Template {

    private var mBuilder: android.support.v7.app.AlertDialog.Builder? = null
    private var onIRxDialogListener: Template.OnIRxDialogListener? = null;
    private var defult = true
    private var context: Context? = null
    private var DESCRIPTION_DEFULT="defult";

    init {
        this.context = context
        mBuilder = android.support.v7.app.AlertDialog.Builder(context)
    }

    override fun setOnIRxDialogListener(onIRxDialogListener: Template.OnIRxDialogListener) {
        this.onIRxDialogListener = onIRxDialogListener;
    }

    override
    fun promptPermission(title: String, message: String) {
        this.defult = true
        val title = if (title.equals(DESCRIPTION_DEFULT)) context!!.getString(R.string.permission_title_permission_rationale) else title
        mBuilder!!.setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.permission_again, mClickListener)
                .setNegativeButton(R.string.permission_cancel, mClickListener)
    }

    override
    fun noPromptPermission(title: String, message: String) {
        this.defult = false
        val title = if (title.equals(DESCRIPTION_DEFULT)) context!!.getString(R.string.permission_title_permission_rationale) else title
        mBuilder!!.setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.permission_setting, mClickListener)
                .setNegativeButton("", null)
    }

    override
    fun show() {
        nullPointerException(context);
        mBuilder!!.show()
    }

    fun <T>nullPointerException(t:T) {
        if (t == null) {
            NullPointerException("DefaultTemplate : NullPointerException")
        }
    }

    private val mClickListener = DialogInterface.OnClickListener { dialog, which ->
        when (which) {
            DialogInterface.BUTTON_POSITIVE -> if (onIRxDialogListener != null) {
                when (defult) {
                    true -> onIRxDialogListener!!.onRequest()
                    false -> onIRxDialogListener!!.toAndroidSetting()
                }
            }
            DialogInterface.BUTTON_NEGATIVE -> if (onIRxDialogListener != null) {
                onIRxDialogListener!!.refuse()
            }
        }
    }
}