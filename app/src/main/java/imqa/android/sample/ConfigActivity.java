package imqa.android.sample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

public class ConfigActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config);

        findViewById(R.id.mpmBtn).setOnClickListener(view -> {
            startActivity(new Intent(ConfigActivity.this, MpmActivity.class));
        });
        findViewById(R.id.crashBtn).setOnClickListener(view -> {
            startActivity(new Intent(ConfigActivity.this, ConfigActivity.class));
        });
        findViewById(R.id.webviewBtn).setOnClickListener(view -> {
            startActivity(new Intent(ConfigActivity.this, WebviewActivity.class));
        });
        findViewById(R.id.configBtn).setOnClickListener(view -> {
            startActivity(new Intent(ConfigActivity.this, ConfigActivity.class));
        });

        findViewById(R.id.configSaveBtn).setOnClickListener(view -> {
            SharedPreferences pref = getSharedPreferences("IMQA_SampleConfig", MODE_PRIVATE);

            EditText serverUrlEditText = findViewById(R.id.collectorServerUrl);
            EditText crashServerUrlEditText = findViewById(R.id.crashCollectorServerUrl);
            EditText projectKeyEditText = findViewById(R.id.projectKey);

            SharedPreferences.Editor editor = pref.edit();

            editor.putString("serverUrl", serverUrlEditText.getText().toString());
            editor.putString("crashServerUrl", crashServerUrlEditText.getText().toString());
            editor.putString("projectKey", projectKeyEditText.getText().toString());
            editor.apply();

            Toast.makeText(this.getApplicationContext(), "재시작 후 반영됩니다",  Toast.LENGTH_LONG).show();
        });


        SharedPreferences pref = getSharedPreferences("IMQA_SampleConfig", MODE_PRIVATE);
        String serverUrl = pref.getString("serverUrl","https://collector.imqa.io");
        String crashServerUrl = pref.getString("crashServerUrl","https://collector.imqa.io");
        String projectKey = pref.getString("projectKey","$2b$05$9fM9lRtWmbNs8lcMha6Hj.aTWzC4UxWhPyWTnDYPNJTTPTNy.7jT.^#1U8389ATPE/szowZGlK27A==");

        EditText serverUrlEditText = findViewById(R.id.collectorServerUrl);
        EditText crashServerUrlEditText = findViewById(R.id.crashCollectorServerUrl);
        EditText projectKeyEditText = findViewById(R.id.projectKey);

        serverUrlEditText.setText(serverUrl);
        crashServerUrlEditText.setText(crashServerUrl);
        projectKeyEditText.setText(projectKey);

    }

}
