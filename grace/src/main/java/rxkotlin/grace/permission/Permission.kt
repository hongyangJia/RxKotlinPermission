package rxkotlin.grace.permission

import android.Manifest
import rxkotlin.grace.permission.launcher.Launcher

/**
 * Created by hongyang on 17-6-5.
 */
interface Permission<T> {

    /**
     * custom request permission
     * @param permission not null
     * @return T Generic
     */
    fun request(vararg permission: String): T

    /**
     * request camera permissions
     * @return T Generic
     */
    fun requestCamera(): T

    /**
     * request storage permissions
     * @return T Generic
     */
    fun requestStorage(): T

    /**
     * request SMS permissions
     * @return T Generic
     */
    fun requestSMS(): T

    /**
     * request phone permissions
     * @return T Generic
     */
    fun requestPhone(): T

    /**
     * request MicroPhone permissions
     * @return T Generic
     */
    fun requestMicroPhone(): T

    /**
     * request Location permissions
     * @return T Generic
     */
    fun requestLocation(): T

    /**
     * request Contacts permissions
     * @return T Generic
     */
    fun requestContacts(): T

    /**
     * request Calendar permissions
     * @return T Generic
     */
    fun requestCalendar(): T

    /**
     * request CameraStorage permissions
     * @return T Generic
     */
    fun requestCameraStorage(): T

    /**
     * request CameraLocation permissions
     * @return T Generic
     */
    fun requestCameraLocation(): T

    /**
     * request StorageLocation permissions
     * @return T Generic
     */
    fun requestStorageLocation(): T

    /**
     * request CameraMicroPhone permissions
     * @return T Generic
     */
    fun requestCameraMicroPhone(): T

    fun requestBluetoothLocation():T

    /**
     * request CameraMicroPhoneStorage permissions
     * @return T Generic
     */
    fun requestCameraMicroPhoneStorage(): T



}