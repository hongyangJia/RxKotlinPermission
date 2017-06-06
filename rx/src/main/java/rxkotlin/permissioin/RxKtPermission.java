package rxkotlin.permissioin;

import android.Manifest;
import android.app.Activity;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;
import rxkotlin.grace.permission.Permission;

/**
 * Created by hongyang on 17-6-5.
 */

public class RxKtPermission implements Permission<Observable<Boolean>>{

    private Activity mActivity;

    public RxKtPermission(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public Observable<Boolean> request(@NotNull String... permission) {
        return  RxConverter.adapter(true, mActivity, permission);
    }

    public Observable<Boolean> requestCamera() {
        return RxConverter.adapter(true, mActivity, Manifest.permission.CAMERA);
    }

    public Observable<Boolean> requestStorage() {
        return RxConverter.adapter(true, mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    public Observable<Boolean> requestSMS() {
        return RxConverter.adapter(true, mActivity, Manifest.permission.SEND_SMS);
    }

    public Observable<Boolean> requestPhone() {
        return RxConverter.adapter(true, mActivity, Manifest.permission.CALL_PHONE);
    }

    public Observable<Boolean> requestMicroPhone() {
        return RxConverter.adapter(true, mActivity, Manifest.permission.RECORD_AUDIO);
    }

    public Observable<Boolean> requestLocation() {
        return RxConverter.adapter(true, mActivity, Manifest.permission.ACCESS_FINE_LOCATION);
    }

    public Observable<Boolean> requestContacts() {
        return RxConverter.adapter(true, mActivity, Manifest.permission.ACCESS_FINE_LOCATION);
    }

    public Observable<Boolean> requestCalendar() {
        return RxConverter.adapter(true, mActivity, Manifest.permission.READ_CALENDAR);
    }

    public Observable<Boolean> requestCameraStorage() {
        return RxConverter.adapter(true, mActivity, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    public Observable<Boolean> requestCameraLocation() {
        return RxConverter.adapter(true, mActivity, Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION);
    }

    public Observable<Boolean> requestStorageLocation() {
        return RxConverter.adapter(true, mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION);
    }

    public Observable<Boolean> requestCameraMicroPhone() {
        return RxConverter.adapter(true, mActivity, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO);
    }

    public Observable<Boolean> requestCameraMicroPhoneStorage() {
        return RxConverter.adapter(true, mActivity, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

}
