package rxkotlin.grace.permission

import android.content.Context
import rxkotlin.grace.permission.ui.DefaultTemplate
import rxkotlin.grace.permission.ui.Template

/**
 * Created by hongyang on 17-6-7.
 * Custom settings
 */
object KtPermissionSetting {

    /**
     * defult dialog title
     */
    private const  val TITLE="title"

    /**
     * defult dialog message
     */
    private const  val MESSAGE="message"

    /**
     * defult dialog
     */
    private const  val DIALOG="dialog"
    private const  val DEFULT = "defult"
    private const val NAME = "KtPermissionSetting"

    /**
     * Create a default class
     */
    private var title = DEFULT

    /**
     * Create a default class
     */
    private var message = DEFULT

    /**
     *Create a default class
     */
    private var rxDialog = DefaultTemplate::class.java.canonicalName

    /**
     * user setting status
     */
    private var isFirst = true

    /**
     * setting title title
     */
    fun title(title: String?): KtPermissionSetting {
        if (title == null) throw NullPointerException("title: NullPointerException")
        this.title = title
        return this
    }

    /**
     * setting message title
     */
    fun message(message: String?): KtPermissionSetting {
        if (message == null) throw NullPointerException("message: NullPointerException")
        this.message = message
        return this
    }

    /**
     * setting dialog title
     */
    fun dialog(dialog: Template): KtPermissionSetting {
        if (dialog == null) throw NullPointerException("dialog: NullPointerException")
        this.rxDialog = Template::class.java.canonicalName
        return this
    }

    /**
     * context:Recommend ApplicatioinContext
     * reason:Applicatioin Will not be destroyed
     */
    fun build(context: Context) {
        this.isFirst = false
        KtPermissionSetting.Date.build(context, title, message, rxDialog)
    }

    /**
     * Get user-defined data
     */
    object Value {

        fun getTitle(): String {
            return KtPermissionSetting.Date.getValue(TITLE)
        }

        fun getMessage(): String {
            return KtPermissionSetting.Date.getValue(MESSAGE)
        }

        fun getDialog(): String {
            return KtPermissionSetting.Date.getValue(DIALOG)
        }

        fun isFirst(context: Context){
            if (isFirst){
              KtPermissionSetting.build(context)
            }
        }
    }

    /**
     *  User data is persistent
     */
   private object Date {

        /**
         * Recommend ApplicatioinContext
         * reason:Applicatioin Will not be destroyed
         */
        private var mContext: Context? = null

        /**
         * Save the data
         */
        fun build(mContext: Context, title: String, message: String, dialog: String) {
            this.mContext = mContext;
            val sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE)
            sp.edit().putString(TITLE, title).putString(MESSAGE, message).putString(DIALOG, dialog).commit()
        }

        /**
         * get the data
         */
        fun getValue(key:String): String {
            val sp = mContext!!.getSharedPreferences(NAME, Context.MODE_PRIVATE)
            val value = sp.getString(key, null)
            if (value == null) throw NullPointerException("Date: NullPointerException")
            return value
        }

    }

}