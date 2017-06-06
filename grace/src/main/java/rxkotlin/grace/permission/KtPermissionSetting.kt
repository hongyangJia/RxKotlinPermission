package rxkotlin.grace.permission

import android.content.Context
import rxkotlin.grace.permission.date.KtRequest

/**
 * Created by hongyang on 17-6-6.
 */
object KtPermissionSetting {

    private var request: KtRequest? = null

    fun Setting(request: KtRequest) {
        this.request = request
    }


    fun isFirst(context:Context) {
        if (request==null){
            this.request = KtRequest.Builder().build(context)
        }
    }

    fun getSetting(): KtRequest {
        if (request==null){
            throw NullPointerException("PermissionSetting  Request : NullPointerException")
        }
        return request!!
    }

}