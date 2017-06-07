package rxkotlin.grace.permission.ui

/**
 * Created by hongyang on 17-5-25.
 */
interface Template {

    interface OnIRxDialogListener {

        fun onRequest()

        fun toAndroidSetting()

        fun refuse()
    }

    fun promptPermission(title: String, message: String)

    fun noPromptPermission(title: String, message: String)

    fun setOnIRxDialogListener(onIRxDialogListener: OnIRxDialogListener)

    fun show()

}