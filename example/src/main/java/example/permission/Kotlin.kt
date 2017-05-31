package example.permission

import android.Manifest
import android.app.Activity
import android.util.Log
import io.reactivex.Observer
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import rxkotlin.grace.permission.RxGracePermission
import rxkotlin.grace.permission.RxInteractive
import rxkotlin.grace.permission.RxKotlinPermission

/**
 * Created by hongyang on 17-5-31.
 */
class Kotlin {
    private val TAG = "Kotlin"

    fun request(actvity:Activity) {
         var rxKotlinPermission: RxKotlinPermission = RxKotlinPermission(actvity)
         rxKotlinPermission.requestCamera().subscribe { rxInteractive ->
        }
    }

    /**
     * Consumer impl
     */
    fun requestCamearaConsumer(actvity:Activity) {
        var rxKotlinPermission: RxKotlinPermission = RxKotlinPermission(actvity)
        rxKotlinPermission.requestCamera().subscribe(Consumer<RxInteractive> { rxInteractive ->
            when (rxInteractive.rxMode) {
                RxGracePermission.RxMode.GRACE_RECRY -> Log.e(TAG, "GRACE_RECRY")
                RxGracePermission.RxMode.GRACE_ALLOW -> Log.e(TAG, "GRACE_ALLOW")
                RxGracePermission.RxMode.GRACE_REFUSE -> Log.e(TAG, "GRACE_REFUSE")
                RxGracePermission.RxMode.GRACE_HIDE -> Log.e(TAG, "GRACE_HIDE")
            }
        })
    }


    fun requestCameraObserver(actvity:Activity) {
        var rxKotlinPermission: RxKotlinPermission = RxKotlinPermission(actvity)
        rxKotlinPermission.requestCamera().subscribe(object : Observer<RxInteractive> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(rxLimit: RxInteractive) {

            }

            override fun onError(e: Throwable) {

            }

            override fun onComplete() {

            }
        })
    }

}