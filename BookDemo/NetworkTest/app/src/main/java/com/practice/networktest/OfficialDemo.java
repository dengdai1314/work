package com.practice.networktest;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Authenticator;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.Route;
import okio.BufferedSink;

public class OfficialDemo extends AppCompatActivity implements View.OnClickListener {
    public static final  String TAG= MainActivity.class.getSimpleName();
    Button get;
    Button post;
    Button synchronousget;
    Button asynchronousget;
    Button accessingheaders;
    Button postingstring;
    Button poststreaming;
    Button postingfile;
    Button postingformparameters;
    Button postingmultipartrequest;
    Button jsonresponsewithmoshi;
    Button cancelingcall;
    Button timeouts;
    Button percallconfiguration;
    Button handlingauthentication;

    OkHttpClient okHttpClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_official_demo);
        get = findViewById(R.id.get);
        post = findViewById(R.id.post);
        synchronousget = findViewById(R.id.synchronousget);
        asynchronousget = findViewById(R.id.asynchronousget);
        accessingheaders = findViewById(R.id.accessingheaders);
        postingstring = findViewById(R.id.postingstring);
        poststreaming = findViewById(R.id.poststreaming);
        postingfile = findViewById(R.id.postingfile);
        postingformparameters = findViewById(R.id.postingformparameters);
        postingmultipartrequest = findViewById(R.id.postingmultipartrequest);
        jsonresponsewithmoshi = findViewById(R.id.jsonresponsewithmoshi);
        cancelingcall = findViewById(R.id.cancelingcall);
        timeouts = findViewById(R.id.timeouts);
        percallconfiguration = findViewById(R.id.percallconfiguration);
        handlingauthentication = findViewById(R.id.handlingauthentication);
        get.setOnClickListener(this);
        post.setOnClickListener(this);
        synchronousget.setOnClickListener(this);
        asynchronousget.setOnClickListener(this);
        accessingheaders.setOnClickListener(this);
        postingstring.setOnClickListener(this);
        poststreaming.setOnClickListener(this);
        postingfile.setOnClickListener(this);
        postingformparameters.setOnClickListener(this);
        postingmultipartrequest.setOnClickListener(this);
        jsonresponsewithmoshi.setOnClickListener(this);
        cancelingcall.setOnClickListener(this);
        timeouts.setOnClickListener(this);
        percallconfiguration.setOnClickListener(this);
        handlingauthentication.setOnClickListener(this);
        okHttpClient = new OkHttpClient();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.get:
                getUrl();
                break;
            case R.id.post:
                try {
                    postUrl("https://raw.github.com/square/okhttp/master/README.md","cshi");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.synchronousget:
                SynchronousGet();
                break;
            case R.id.asynchronousget:
                AsynchronousGet();
                break;
            case R.id.accessingheaders:
                AccessingHeaders();
                break;
            case R.id.postingstring:
                PostString();
                break;
            case R.id.poststreaming:
                Poststreaming();
                break;
            case  R.id.postingfile:
                Postingfile();
                break;
            case R.id.postingformparameters:
                Postingformparameters();
                break;
            case R.id.postingmultipartrequest:
                Postingmultipartrequest();
                break;
            case R.id.jsonresponsewithmoshi:
                Jsonresponsewithmoshi();
                break;
            case R.id.cancelingcall:
                Cancelingcall();
                break;
            case R.id.timeouts:
                Timeouts();
                break;
            case R.id.percallconfiguration:
                Percallconfiguration();
                break;
            case R.id.handlingauthentication:
                Handlingauthentication();
                break;
                default:break;
        }
    }

    //get a url
    public void getUrl(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Request request = new Request.Builder()
                            .url("https://raw.github.com/square/okhttp/master/README.md")
                            .build();
                    Response response = okHttpClient.newCall(request).execute();
                    Log.e("GET:  ",response.body().toString());
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //post to a server
    public static final MediaType JSON = MediaType.get("application/json;charset=utf-8");
    private void postUrl(final String url,final String json) throws IOException {
        final RequestBody body = RequestBody.create(json, JSON);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();
                try (Response response = okHttpClient.newCall(request).execute()) {
                    Log.e("post to a server ",request.body().toString());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    //同步get
    private void SynchronousGet(){
        new Thread(new Runnable() {
            @Override
            public void run(){
                Request request = new Request.Builder()
                        .url("http://publicobject.com/helloworld.txt")
                        .build();
                try{
                    Response response = okHttpClient.newCall(request).execute();
                    if(!response.isSuccessful()){
                        throw new IOException("Unexpected code"+response);
                    }
                    Headers responseHeaders = response.headers();
                    for(int i = 0;i<responseHeaders.size();i++){
                        Log.e("SynchronousGet:  ",responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }
                    Log.e("SynchronousGet:  ",response.body().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    //异步get
    private void  AsynchronousGet(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url("http://publicobject.com/helloworld.txt")
                        .build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        try (ResponseBody responseBody = response.body()) {
                            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                            Headers responseHeaders = response.headers();
                            for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                                Log.e("AsynchronousGet:  ",responseHeaders.name(i) + ": " + responseHeaders.value(i));
                            }

                            Log.e("AsynchronousGet:  ",responseBody.string());
                        }
                    }
                });
            }
        }).start();
    }

    //Accessing Headers
    private void AccessingHeaders(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url("https://api.github.com/repos/square/okhttp/issues")
                        .header("User-Agent", "OkHttp Headers.java")
                        .addHeader("Accept", "application/json; q=0.5")
                        .addHeader("Accept", "application/vnd.github.v3+json")
                        .build();
                try{
                    Response response = okHttpClient.newCall(request).execute();
                    Log.e("AccessingHeaders:  ","Server: " + response.header("Server"));
                    Log.e("AccessingHeaders:  ","Date: " + response.header("Date"));
                    Log.e("AccessingHeaders:  ","Vary: " + response.header("Vary"));
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }).start();
    }

    //Posting a String
    private void PostString(){
        final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
        new Thread(new Runnable() {
            @Override
            public void run() {
                String postBody = ""
                        + "Releases\n"
                        + "--------\n"
                        + "\n"
                        + " * _1.0_ May 6, 2013\n"
                        + " * _1.1_ June 15, 2013\n"
                        + " * _1.2_ August 11, 2013\n";
                Request request = new Request.Builder()
                        .url("https://api.github.com/markdown/raw")
                        .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, postBody))
                        .build();
                try (Response response = okHttpClient.newCall(request).execute()) {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                    Log.e(TAG,"PostString:  "+response.body().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //Post Streaming
    private void Poststreaming(){
        final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestBody requestBody = new RequestBody(){
                    @Override public MediaType contentType() {
                        return MEDIA_TYPE_MARKDOWN;
                    }

                    @Override public void writeTo(BufferedSink sink) throws IOException {
                        sink.writeUtf8("Numbers\n");
                        sink.writeUtf8("-------\n");
                        for (int i = 2; i <= 997; i++) {
                            sink.writeUtf8(String.format(" * %s = %s\n", i, factor(i)));
                        }
                    }

                    private String factor(int n) {
                        for (int i = 2; i < n; i++) {
                            int x = n / i;
                            if (x * i == n) return factor(x) + " × " + i;
                        }
                        return Integer.toString(n);
                    }
                };

                Request request = new Request.Builder()
                        .url("https://api.github.com/markdown/raw")
                        .post(requestBody)
                        .build();
                try{
                    Response response = okHttpClient.newCall(request).execute();
                    if(response.isSuccessful()){
                        Log.e(TAG,"PostStreaming:  "+response.body().toString());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void Postingfile(){
        final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown;charset=utf-8");
        new Thread(new Runnable() {
            @Override
            public void run() {
                File file = new File("/sdcard/result.json");
                Request request = new Request.Builder()
                        .url("https://api.github.com/markdown/raw")
                        .post(RequestBody.create(MEDIA_TYPE_MARKDOWN,file))
                        .build();
                try(Response response = okHttpClient.newCall(request).execute()){
                    Log.e(TAG,"PostingFile:  "+response.body().toString());
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void Postingformparameters(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestBody formBody = new FormBody.Builder()
                        .add("search","park")
                        .build();
                Request request = new Request.Builder()
                        .url("https://www.baidu.com")
                        .post(formBody)
                        .build();
                try(Response response = okHttpClient.newCall(request).execute()){
                    Log.e(TAG,"Postingformparameters:  "+response.body().toString());
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void Postingmultipartrequest(){ //文件问题
        final String IMGUR_CLIENT_ID = "...";
        final MediaType MEDIA_TYPE_PNG = MediaType.parse("json");
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("title","Square Logo")
                        .addFormDataPart("json","/sdcard/result.json",
                                RequestBody.create(MEDIA_TYPE_PNG,new File("/sdcard/result.json")))
                        .build();
                Request request = new Request.Builder()
                        .header("Authorization", "Client-ID " + IMGUR_CLIENT_ID)
                        .url("https://www.baidu.com")
                        .post(requestBody)
                        .build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    if(response.isSuccessful()){
                        Log.e(TAG,"Postingmultipartrequest:  "+response.body().toString());
                    }
                    else {Log.e(TAG,"Postingmultipartrequest:  "+response.body().toString());}
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }).start();
    }

    //Parse a JSON Response With Moshi
    private void Jsonresponsewithmoshi(){
        final Moshi moshi = new Moshi.Builder().build();
        final JsonAdapter<Gist> gistJsonAdapter = moshi.adapter(Gist.class);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url("https://api.github.com/gists/c2a7c39532239ff261be")
                        .build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    if (response.isSuccessful()){
                        Gist gist = gistJsonAdapter.fromJson(response.body().source());
                        for (Map.Entry<String,GistFile> entry : gist.files.entrySet()){
                            Log.e(TAG,"Jsonresponsewithmoshi:  "+entry.getKey());
                            Log.e(TAG,"Jsonresponsewithmoshi:  "+entry.getValue().content);
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    static class Gist {
        Map<String, GistFile> files;
    }

    static class GistFile {
        String content;
    }

    //Cancelling a call
    private void Cancelingcall(){
        final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        new Thread(new Runnable() {
            @SuppressLint("LongLogTag")
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url("http://httpbin.org/delay/2")
                        .build();
                final long startNanos = System.nanoTime();
                final Call call = okHttpClient.newCall(request);

                executorService.schedule(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("%.2f Canceling call.%n", String.valueOf((System.nanoTime() - startNanos) / 1e9f));
                        call.cancel();
                        Log.e("%.2f Canceling call.%n", String.valueOf((System.nanoTime() - startNanos) / 1e9f));
                    }
                },1, TimeUnit.SECONDS);

                Log.e("%.2f Executing call.%n", String.valueOf((System.nanoTime() - startNanos) / 1e9f));
                try {
                    Response response = call.execute();
                    Log.e("%.2f Call was expected to fail, but completed: %s%n", String.valueOf((System.nanoTime() - startNanos) / 1e9f));
                }catch (Exception e){
                    System.out.printf("%.2f Call failed as expected: %s%n", (System.nanoTime() - startNanos) / 1e9f, e);
                }
            }
        }).start();
    }

    //Timeouts
    private void Timeouts(){
        final OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url("http:httpbin.org/delay/2")
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    Log.e("Timeouts",response.body().toString());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //Per-call Configuration
    private void Percallconfiguration(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url("http://www.baidu.com") // This URL is served with a 1 second delay.
                        .build();
                OkHttpClient client1 = okHttpClient.newBuilder()
                        .readTimeout(500,TimeUnit.MILLISECONDS)
                        .build();
                try {
                    Response response= client1.newCall(request).execute();
                    Log.e(TAG+"Response 1 succeeded: ",response.body().toString());
                }catch (Exception e){
                    Log.e(TAG+"Response 1 failed: ",e.toString());
                    e.printStackTrace();
                }

                OkHttpClient client2 = okHttpClient.newBuilder()
                        .readTimeout(3000,TimeUnit.MILLISECONDS)
                        .build();
                try {
                    Response response= client2.newCall(request).execute();
                    Log.e(TAG+"Response 2 succeeded: ",response.body().toString());
                }catch (Exception e){
                    Log.e(TAG+"Response 2 failed: ",e.toString());
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //Handling authentication
    public void Authenticate(){
        okHttpClient = new OkHttpClient.Builder()
            .authenticator(new Authenticator() {
                @Override public Request authenticate(Route route, Response response) throws IOException {
                    if (response.request().header("Authorization") != null) {
                        return null; // Give up, we've already attempted to authenticate.
                    }

                    System.out.println("Authenticating for response: " + response);
                    System.out.println("Challenges: " + response.challenges());
                    String credential = Credentials.basic("jesse", "password1");
                    return response.request().newBuilder()
                            .header("Authorization", credential)
                            .build();
                }
            }).build();
    }
    public void Handlingauthentication(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url("http://publicobject.com/secrets/hellosecret.txt")
                        .build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    Log.e(TAG,response.body().toString());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 默认信任所有证书
     * @return
     */
    @SuppressLint("TrulyRandom")
    private static javax.net.ssl.SSLSocketFactory createSSLSocketFactory() {

        SSLSocketFactory sSLSocketFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllManager()},
                    new SecureRandom());
            sSLSocketFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return sSLSocketFactory;
    }

    private static class TrustAllManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)

                throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
}

