package rxkotlin.grace.permission.date

import android.R.id.edit
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences


/**
 * Created by hongyang on 17-6-6.
 */
object DatePersistence {

    private const val NAME = "DatePersistence"

    fun build(mContext: Context, title: String, message: String, dialog: String) {
        val sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE)
        sp.edit().putString("title", title).putString("message", message).putString("dialog", dialog).commit()
    }

    fun getTitle(mContext: Context): String {
        val sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE)
        val title = sp.getString("title", null)
        nullPointerException(title)
        return title
    }

    fun getMessage(mContext: Context): String {
        val sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE)
        val message = sp.getString("message", null)
        nullPointerException(message)
        return message
    }

    fun getDialog(mContext: Context): String {
        val sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE)
        val dialog = sp.getString("dialog", null)
        nullPointerException(dialog)
        return dialog
    }

    fun <T> nullPointerException(t: T) {
        if (t == null) {
            throw NullPointerException("DatePersistence : NullPointerException")
        }
    }
}