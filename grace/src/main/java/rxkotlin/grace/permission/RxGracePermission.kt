package rxkotlin.grace.permission

import android.app.Activity
import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import io.reactivex.*
import io.reactivex.disposables.Disposable
import org.reactivestreams.Subscriber
import rxkotlin.grace.permission.ui.IRxDialog
import rxkotlin.grace.permission.ui.RxDescription
import java.util.ArrayList


/**
 * Created by hongyang on 17-5-19.
 */
class RxGracePermission(mContext: Activity) : RxFragment.OnHasPermissionListener, IRxDialog.OnIRxDialogListener {

    private val LAMBDA_OBSERVER = "io.reactivex.internal.observers.LambdaObserver"

    private val FOREVER_DESCRIPTION = "foreverDescription"

    private val DECLINE_DESCRIPTION = "declineDescription"

    private val TAG = "RxGrace"

    private var rxPermissionsFragment: RxFragment

    private var rxObserver: Observer<RxInteractive>? = null

    private var mContext: Activity? = null;

    private var rxDialog: IRxDialog? = null

    private var singleRxRequest = SingleRxRequest()

    private var interactive = INTERACTIVE.DEFAULT

    enum class RxMode {
        GRACE_REFUSE, GRACE_ALLOW, GRACE_HIDE, GRACE_RECRY
    }

    enum class INTERACTIVE {
        CUSTOMIZE, LAMBDA, DEFAULT
    }

    init {
        this.mContext = mContext
        rxPermissionsFragment = getRxPermissionsFragment(mContext)
    }

    private fun getRxPermissionsFragment(activity: Activity): RxFragment {
        RxTool.rxNullPointerException(mContext!!)
        var rxPermissionsFragment: RxFragment? = findRxPermissionsFragment(activity)
        val isNewInstance = rxPermissionsFragment == null
        if (isNewInstance) {
            rxPermissionsFragment = RxFragment()
            val fragmentManager = activity.fragmentManager
            fragmentManager
                    .beginTransaction()
                    .add(rxPermissionsFragment, TAG)
                    .commitAllowingStateLoss()
            fragmentManager.executePendingTransactions()
        }
        return rxPermissionsFragment!!
    }

    private fun findRxPermissionsFragment(activity: Activity): RxFragment? {
        return activity.fragmentManager.findFragmentByTag(TAG) as RxFragment?
    }

    fun request(vararg permission: String): Observable<RxInteractive> {
        return Observable.just(permission).compose {
            ObservableSource<RxInteractive> { observer ->
                if (LAMBDA_OBSERVER.equals(observer.javaClass.name)
                        && interactive == INTERACTIVE.DEFAULT) {
                    this.interactive = INTERACTIVE.LAMBDA
                }
                this.rxObserver = observer as Observer<RxInteractive>?
                RxTool.rxNullPointerException(rxPermissionsFragment!!)
                rxPermissionsFragment.rxRequestPermissions(this, *permission)
            }
        }
    }

    override
    fun refuse(refuseList: ArrayList<String>) {
        RxTool.rxNullPointerException(rxObserver!!)
        when (interactive) {
            INTERACTIVE.DEFAULT -> rxObserver!!.onError(Throwable("RxGrace : refuse "))
            INTERACTIVE.LAMBDA ->  rxObserver!!.onComplete()
            INTERACTIVE.CUSTOMIZE -> rxObserver!!.onNext(RxInteractive(refuseList, RxMode.GRACE_REFUSE))
        }
    }

    override
    fun allow(allowList: ArrayList<String>) {
        RxTool.rxNullPointerException(rxObserver!!)
        rxObserver!!.onNext(RxInteractive(allowList, RxMode.GRACE_ALLOW))
    }

    override
    fun hide(hideList: ArrayList<String>) {
        RxTool.rxNullPointerException(rxObserver!!)
        RxTool.rxNullPointerException(mContext!!)
        when (interactive) {
            INTERACTIVE.DEFAULT -> {
                rxObserver!!.onError(Throwable("RxGrace : hide "))
                showRxToolDialog(FOREVER_DESCRIPTION, hideList)
            }
            INTERACTIVE.LAMBDA -> {
                rxObserver!!.onNext(RxInteractive(hideList, RxMode.GRACE_HIDE))
                showRxToolDialog(FOREVER_DESCRIPTION, hideList)
            }
            INTERACTIVE.CUSTOMIZE -> rxObserver!!.onNext(RxInteractive(hideList, RxMode.GRACE_HIDE))
        }
    }

    override
    fun retry(retryList: ArrayList<String>) {
        RxTool.rxNullPointerException(mContext!!)
        when (interactive) {
            INTERACTIVE.DEFAULT -> showRxToolDialog(DECLINE_DESCRIPTION, retryList)
            INTERACTIVE.LAMBDA -> showRxToolDialog(DECLINE_DESCRIPTION, retryList)
            INTERACTIVE.CUSTOMIZE -> rxObserver!!.onNext(RxInteractive(retryList, RxMode.GRACE_RECRY))
        }
    }

    override
    fun toAndroidSet() {
        RxTool.rxNullPointerException(mContext!!)
        RxTool.toAndroidSetting(mContext!!)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override
    fun onRequest() {
        RxTool.rxNullPointerException(rxPermissionsFragment!!)
        rxPermissionsFragment.requestPermissions()
    }

    fun showRxToolDialog(status: String, permissions: ArrayList<String>) {
        this.iRxDialog(mContext!!)
        RxTool.rxNullPointerException(rxDialog!!)
        rxDialog!!.setOnIRxDialogListener(this)
        when (status) {
            FOREVER_DESCRIPTION -> rxDialog!!.noPromptPermission(
                    singleRxRequest.getStting().title,
                    RxDescription.foreverDescription(singleRxRequest.getStting(), mContext!!, permissions))
            DECLINE_DESCRIPTION -> rxDialog!!.promptPermission(
                    singleRxRequest.getStting().title,
                    RxDescription.declineDescription(singleRxRequest.getStting(), mContext!!, permissions))
        }
        rxDialog!!.show()
    }

    fun iRxDialog(mContext: Activity) {
        if (rxDialog == null) {
            val c = Class.forName(singleRxRequest.getStting().rxDialog)
            val con = c.getConstructor(Context::class.java)
            rxDialog = con.newInstance(mContext) as IRxDialog
        }
    }

    fun setInteractive(interactive: INTERACTIVE) {
        this.interactive = interactive
    }

    fun settingRequest(): SingleRxRequest {
        return singleRxRequest
    }

    class SingleRxRequest {
        private var rxRequest: RxRequest? = RxRequest.Builder()
                .title(RxTool.TITLE)
                .message(RxTool.MESSAGE)
                .build()

        fun setting(rxRequest: RxRequest) {
            RxTool.rxNullPointerException(rxRequest)
            this.rxRequest = rxRequest
        }

        fun getStting(): RxRequest {
            return this.rxRequest!!
        }
    }
}