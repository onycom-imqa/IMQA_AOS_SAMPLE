package imqa.android.sample;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

import cz.msebera.android.httpclient.HttpResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MpmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mpm);

        try {
            Intent intent = getIntent();
            Object lazyTime = intent.getExtras()!=null?intent.getExtras().get("lazyTime"):null;
        if(lazyTime!= null) {
                Thread.sleep((Integer) lazyTime);
        }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        findViewById(R.id.mpmBtn).setOnClickListener(view -> {
            startActivity(new Intent(MpmActivity.this, MpmActivity.class));
        });
        findViewById(R.id.crashBtn).setOnClickListener(view -> {
            startActivity(new Intent(MpmActivity.this, CrashActivity.class));
        });
        findViewById(R.id.webviewBtn).setOnClickListener(view -> {
            startActivity(new Intent(MpmActivity.this, WebviewActivity.class));
        });
        findViewById(R.id.configBtn).setOnClickListener(view -> {
            startActivity(new Intent(MpmActivity.this, ConfigActivity.class));
        });

        findViewById(R.id.mpmNetworkBtn).setOnClickListener(view -> {
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            MpmActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try{
                        OkHttpClient client = new OkHttpClient.Builder()
                                .build();

                        Request request = new Request.Builder().url("http://imqa.io/").build();

                        try (Response response = client.newCall(request).execute()) {
                            Log.i("IMQA", response.body().string());
                        }

                        Toast.makeText(MpmActivity.this, "네트워크를 생성하였습니다",  Toast.LENGTH_LONG).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });



        });


        findViewById(R.id.mpmSlowActivityBtn).setOnClickListener(view -> {

            Intent intent = new Intent(MpmActivity.this, MpmActivity.class);
            intent.putExtra("lazyTime", 5000);

            startActivity(intent);

        });



    }

}
