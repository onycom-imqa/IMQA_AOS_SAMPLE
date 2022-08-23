package imqa.android.sample;

import android.app.Application;
import android.content.SharedPreferences;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreferences pref = getSharedPreferences("IMQA_SampleConfig", MODE_PRIVATE);
        String serverUrl = pref.getString("serverUrl","http://collector.imqa.io");
        String crashServerUrl = pref.getString("crashServerUrl","http://collector.imqa.io");
        String projectKey = pref.getString("projectKey","<프로젝트 키를 넣어주세요>");



        io.imqa.core.IMQAOption imqaOption = new io.imqa.core.IMQAOption();
        imqaOption.setBuildType(false);
        imqaOption.setUploadPeriod(true);
        imqaOption.setKeepFileAtUploadFail(false);
        imqaOption.setHttpTracing(true);
        imqaOption.setPrintLog(true);
        imqaOption.setBehaviorTracing(true);
        imqaOption.setEventTracing(true);
        imqaOption.setDumpInterval(5000);
        imqaOption.setFileInterval(5);
        imqaOption.setForceHttps(true);

        if(serverUrl != null) {
            imqaOption.setServerUrl(serverUrl);
        }
        if(crashServerUrl != null) {
            imqaOption.setCrashServerUrl(serverUrl);
        }

        io.imqa.mpm.IMQAMpmAgent.getInstance()
                .setOption(imqaOption) // MPM 의 동작 방식을 정하는 옵션을 설정합니다.
                .setContext(this, "") // Application Context 를 초기화 합니다.
                .setProjectKey(projectKey) // IMQA MPM Client 의 Project Key 를 설정합니다.
                .init();



    }
}
