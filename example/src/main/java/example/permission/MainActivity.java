package example.permission;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import rxkotlin.grace.permission.KtPermission;
import rxkotlin.grace.permission.KtPermissionSetting;
import rxkotlin.grace.permission.date.KtRequest;
import rxkotlin.grace.permission.launcher.LaunchTask;
import rxkotlin.grace.permission.ui.DefaultTemplate;
import rxkotlin.grace.permission.ui.Template;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        KtPermission permission = new KtPermission(this);
        permission.requestCamera().launcher(new LaunchTask() {
            @Override
            public void launch(boolean b) {
                if (b){
                    /**
                     * success
                     */
                }
            }
        });

    }
}
