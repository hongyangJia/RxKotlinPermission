package example.permission;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import rxkotlin.grace.permission.KtPermission;
import rxkotlin.grace.permission.KtPermissionSetting;
import rxkotlin.grace.permission.launcher.LaunchTask;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(new Fragment2(),"MainActivity").commit();
    }


}
