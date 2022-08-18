package imqa.android.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class WebviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        findViewById(R.id.mpmBtn).setOnClickListener(view -> {
            startActivity(new Intent(WebviewActivity.this, MpmActivity.class));
        });
        findViewById(R.id.crashBtn).setOnClickListener(view -> {
            startActivity(new Intent(WebviewActivity.this, CrashActivity.class));
        });
        findViewById(R.id.webviewBtn).setOnClickListener(view -> {
            startActivity(new Intent(WebviewActivity.this, WebviewActivity.class));
        });

        WebView wv = findViewById(R.id.webview);
        wv.loadUrl("https://imqa.io");

    }

}