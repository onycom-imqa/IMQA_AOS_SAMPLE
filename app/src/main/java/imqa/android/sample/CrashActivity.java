package imqa.android.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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
    }

}
