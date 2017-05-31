package example.permission;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import rxkotlin.grace.permission.RxKotlinPermission;
import rxkotlin.grace.permission.RxInteractive;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    RxKotlinPermission rxKotlinPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rxKotlinPermission = new RxKotlinPermission(this);
        requestCameraObserver();
    }

    /**
     * hide Dialog
     * default show
     */
    public  void hideInteractiveLayout(){
        rxKotlinPermission.setCancelInteractive();
    }

    /**
     *  Consumer impl
     */
    public  void request(){
        rxKotlinPermission.requestCamera().subscribe(new Consumer<RxInteractive>() {
            @Override
            public void accept(@NonNull RxInteractive rxInteractive) throws Exception {
                switch (rxInteractive.getRxMode()) {
                    case GRACE_RECRY:
                        Log.e(TAG, "GRACE_RECRY");
                        break;
                    case GRACE_ALLOW:
                        Log.e(TAG, "GRACE_ALLOW");
                        break;
                    case GRACE_REFUSE:
                        Log.e(TAG, "GRACE_REFUSE");
                        break;
                    case GRACE_HIDE:
                        Log.e(TAG, "GRACE_HIDE");
                        break;
                }
            }
        });
    }

    /**
     * Observer impl
     */
    public  void requestCameraObserver(){
        rxKotlinPermission.requestCamera().subscribe(new Observer<RxInteractive>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(RxInteractive rxLimit) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     *
     */
    public  void requestCustom(){

        rxKotlinPermission.request(Manifest.permission.WRITE_SETTINGS,
                Manifest.permission.WRITE_SETTINGS).subscribe(new Consumer<RxInteractive>() {
            @Override
            public void accept(@NonNull RxInteractive rxInteractive) throws Exception {
                switch (rxInteractive.getRxMode()) {
                    case GRACE_RECRY:
                        Log.e(TAG, "GRACE_RECRY");
                        break;
                    case GRACE_ALLOW:
                        Log.e(TAG, "GRACE_ALLOW");
                        break;
                    case GRACE_REFUSE:
                        Log.e(TAG, "GRACE_REFUSE");
                        break;
                    case GRACE_HIDE:
                        Log.e(TAG, "GRACE_HIDE");
                        break;
                }
            }
        });
    }



}
