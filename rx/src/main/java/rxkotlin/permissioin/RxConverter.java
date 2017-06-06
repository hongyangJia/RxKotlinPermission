package rxkotlin.permissioin;

import android.app.Activity;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import rxkotlin.grace.permission.CorePermission;
import rxkotlin.grace.permission.launcher.LaunchTask;

/**
 * Created by hongyang on 17-6-5.
 */

public class RxConverter {

    private static final String LAMBDA_OBSERVER = "io.reactivex.internal.observers.LambdaObserver";
    private static final String THROWABLE = " RxGracePermission : The current permission is denied";

    public static <T> Observable<T> adapter(final T t, final Activity mActvity, final String... permission) {
        return Observable.just(t).compose(new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return new ObservableSource<T>() {
                    @Override
                    public void subscribe(final Observer<? super T> observer) {
                        new CorePermission(mActvity).request(permission).launcher(new LaunchTask() {
                            @Override
                            public void launch(boolean b) {
                                if (b) {
                                    observer.onNext(t);
                                } else {
                                    if (LAMBDA_OBSERVER.equals(observer.getClass().getName()))
                                        observer.onComplete();
                                    else observer.onError(new Throwable(THROWABLE));
                                }
                            }
                        });
                    }
                };
            }
        });
    }
}

