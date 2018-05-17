package example.permission;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rxkotlin.grace.permission.KtPermission;
import rxkotlin.grace.permission.KtPermissionSetting;
import rxkotlin.grace.permission.launcher.LaunchTask;

/**
 * Created by hongyang on 17-6-8.
 */

public class Fragment2 extends android.support.v4.app.Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //KtPermissionSetting.INSTANCE.title("title").message("message").dialog(new DefaultTemplate()).build(this);

        KtPermission permission = new KtPermission(getActivity());
        permission.requestCameraMicroPhoneStorage().launcher(new LaunchTask() {

            @Override
            public void launch(boolean b) {
                Log.e(Fragment2.class.getSimpleName(),b?"true":"false");
                if (b){
                    /**
                     * success
                     */
                }
            }
        });
    }

}
