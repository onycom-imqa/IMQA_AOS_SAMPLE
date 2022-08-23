# Quickstart for IMQA Aos


## Introduction
IMQA를 사용하기 앞서 Android SDK 연동된 샘플 앱으로 연동 샘플 코드 구성을 확인하고, 동작시켜 볼 수 있습니다


## IMQA 시작하기
IMQA에 회원가입/로그인을 진행하시기 바랍니다

[IMQA 시작하기](https://imqa-onycom.gitbook.io/imqa-guide/installation/imqa)

### IMQA 프로젝트 생성
IMQA에 로그인하여 프로젝트를 생성해주세요

[IMQA Lite 프로젝트 시작하기](https://imqa-onycom.gitbook.io/imqa-guide/installation/imqa/project-lite)

프로젝트를 생성 후 발급받은 `Project Key`를 이용하여 SDK 연동을 진행합니다

[<img src="https://842389420-files.gitbook.io/~/files/v0/b/gitbook-x-prod.appspot.com/o/spaces%2FzFyCopc6yAp3UcEYW6la%2Fuploads%2FZCGQMpp55OSw14VeJCBG%2FUntitled.png?alt=media&token=c635d854-7d83-48cc-bf87-56d52924ac7a" width="800"/>](image.png)

## IMQA Sample 앱을 이용하여 서비스 연동하기

### IMQA SDK 설치
https://docs.imqa.io/installation/aos-sdk/mpm-sdk-install

1. Project gradle 설정

Project Root에 위치한 build.gradle에 다음과 같이 buildscript를 추가합니다

```
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath 'io.imqa:imqa-mpm-injector:2.24.3.k'
    }
}
```


2. App Gradle 설정

App에 있는 build.gradle 에 다음과 같이 imqa라이브러리 dependency를 추가 후 gradle action을 추가합니다

```
dependencies {
    implementation 'io.imqa:imqa-core:2.24.1.r'
    implementation 'io.imqa:imqa-mpm-client:2.24.3.a.i'
    implementation 'io.imqa:imqa-crash-client:2.24.3.e'
}


io.imqa.IMQAPlugin imqaPlugin = new io.imqa.IMQAPlugin()
imqaPlugin.init(project)
new io.imqa.injector.GJavacAction(project.name).setConfiguration(project)
android.applicationVariants.all { variant ->
    variant.javaCompile.doLast { task ->
        new io.imqa.injector.CompileAction(
                io.imqa.injector.util.BuildOption.BUILD_LOCATION_TYPE.javacClasses,
                project.name,
                io.imqa.injector.GJavacAction.convertBuildType(variant.getBuildType()),
                io.imqa.injector.GJavacAction.makeFlavor("imqa", variant)
        ).execute(task)
    }
}
```

kotlin의 경우 설정이 다를 수 있으니 설치 가이드를 꼭 참고하세요

3. application 설정

Android Application에 IMQA Agent 코드를 삽입합니다

```
io.imqa.mpm.IMQAMpmAgent.getInstance()
       .setOption(imqaOption) // MPM 의 동작 방식을 정하는 옵션을 설정합니다.
       .setContext(this, "") // Application Context 를 초기화 합니다.
       .setProjectKey(projectKey) // IMQA MPM Client 의 Project Key 를 설정합니다.
       .init();
```

이때 샘플 앱에서는 다음과 같이 프로젝트키만 넣도록 되어 있습니다

>imqa.android.sample.App.java
```
SharedPreferences pref = getSharedPreferences("IMQA_SampleConfig", MODE_PRIVATE);
String serverUrl = pref.getString("serverUrl","http://collector.imqa.io");
String crashServerUrl = pref.getString("crashServerUrl","http://collector.imqa.io");
String projectKey = pref.getString("projectKey","<프로젝트 키를 넣어주세요>");
```

프로젝트를 생성하여 발급받은 `Project Key`를 projectKey 코드에 넣어주세요

`Project Key` 삽입 후 앱을 빌드하여 `Run` 하면 다음과 같이 앱이 실행됩니다


[<img src="https://imqa.io/theme/responsive_onycom/include/img/aos_sample_00.png" width="250"/>](image.png)

-----

### 기본 사용방법
IMQA는 성능 데이터를 수집후 수집 주기에 따라 데이터를 전송하거나, 앱이 재기동 되는 시점에 데이터를 전송합니다
데이터를 생성 후(앱을 사용 후) 설정된 수집주기만큼 기다리거나, 앱을 껏다 켜면 앱이 IMQA 수집서버로 전송됩니다

### MPM Activity
`Mpm Activity` 에서는
기본적으로 화면 로딩시간, 네트워크 응답시간을 테스트 할수 있는 기능이 있습니다

[<img src="https://imqa.io/theme/responsive_onycom/include/img/aos_sample_01.png" width="250"/>](image.png)

| Make Network : 네트워크 응답을 생성합니다
| Show Slow Activity : 기본적으로 메뉴 버튼을 누르면 화면 로딩시간이 측정되나, `Show Slow Activity`를 클릭하면 강제로 화면을 늦게 로딩시킵니다. 화면을 늦게 로딩시키는 기능이기 때문에 `Show Slow Activity` 기능을 누르면 약 5초동안 화면 로딩이 되면서 이벤트가 동작하지 않습니다

`Mpm Activity`를 통해 수집된 데이터는 `서비스 콘솔`을 통해 확인할 수 있으며
다음 페이지에서 상세한 설명을 제공하고 있습니다

https://imqa-onycom.gitbook.io/imqa-guide/user-guide/mpm

### Crash Activity
`Crash Activity` 에서는
 Crash를 발생시키고, Anr를 발생시켜보는 테스트 기능이 있습니다

[<img src="https://imqa.io/theme/responsive_onycom/include/img/aos_sample_02.png" width="250"/>](image.png)


| Make Crash : Crash를 발생시킵니다. 앱이 꺼집니다
| Show Slow Activity : Anr(Application Not Responding)을 발생시킵니다. Anr이 발생하는 15초동안 아무런 이벤트가 입력되지 않습니다


`Crash Activity`를 통해 수집된 데이터는 `서비스 콘솔`을 통해 확인할 수 있으며
다음 페이지에서 상세한 설명을 제공하고 있습니다

https://imqa-onycom.gitbook.io/imqa-guide/user-guide/crash



### Webview Activity
`Webview Activity` 에서는
 IMQA Webview Agent가 이미 적용되어 있는 imqa.io 페이지를 통해 웹뷰 성능 데이터를 수집합니다

[<img src="https://imqa.io/theme/responsive_onycom/include/img/aos_sample_03.png" width="250"/>](image.png)

`IMQA Webview Agent`는 앱이 아닌 웹 페이지에 js파일을 삽입해야 하며, 자세한 설치 가이드는 다음 문서에 있습니다

https://imqa-onycom.gitbook.io/imqa-guide/installation/aos-sdk/mpm-sdk-setting/mpm-webview-guide


### Setting Activity

`Setting Activity`에서는 서버 설정, Project Key 변경과 같은 기능을 할 수 있습니다

[<img src="https://imqa.io/theme/responsive_onycom/include/img/aos_sample_04.png" width="250"/>](image.png)


## Release Note
[릴리즈 노트 페이지](https://imqa-onycom.gitbook.io/imqa-guide/release-note/update)


## IMQA 포럼
포럼으로 바로가기 (IM Dev)

http://imdev.imqa.io/
