package imqa.android.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class CrashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crash);

        findViewById(R.id.mpmBtn).setOnClickListener(view -> {
            startActivity(new Intent(CrashActivity.this, MpmActivity.class));
        });
        findViewById(R.id.crashBtn).setOnClickListener(view -> {
            startActivity(new Intent(CrashActivity.this, CrashActivity.class));
        });
        findViewById(R.id.webviewBtn).setOnClickListener(view -> {
            startActivity(new Intent(CrashActivity.this, WebviewActivity.class));
        });
        findViewById(R.id.configBtn).setOnClickListener(view -> {
            startActivity(new Intent(CrashActivity.this, ConfigActivity.class));
        });

        findViewById(R.id.crashMakeBtn).setOnClickListener(view -> {
            int a = 1/0;
        });

        findViewById(R.id.anrMakeBtn).setOnClickListener(view -> {
            Toast.makeText(CrashActivity.this, "15초동안 응답없음(ANR)을 생성하였습니다",  Toast.LENGTH_LONG).show();

//            new Thread(){
//                @Override
//                public void run() {
                    try {
                        Thread.sleep(15000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                }
//            }.start();


        });



    }



}
