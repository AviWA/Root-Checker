package com.example.whatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rooted.R;

import java.io.File;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    Button toastBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toastBtn = findViewById(R.id.toastBtn);

        toastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isRooted = RootUtil.isDeviceRooted();

                if (isRooted)
                    Toast.makeText(MainActivity.this, "This device is Rooted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "This device is not Rooted", Toast.LENGTH_SHORT).show();

            }
        });

    }
}

class RootUtil {
    public static boolean isDeviceRooted() {
        return checkRootMethod1() || checkRootMethod2() || checkRootMethod3();
    }

    private static boolean checkRootMethod1() {
        String buildTags = android.os.Build.TAGS;
        return buildTags != null && buildTags.contains("test-keys");
    }

    private static boolean checkRootMethod2() {
        File file = new File("/system/app/Superuser.apk");
        return file.exists();
    }

    private static boolean checkRootMethod3() {
        try {
            Process process = Runtime.getRuntime().exec("su");
            InputStream inputStream = process.getInputStream();
            inputStream.close();
            process.destroy();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
