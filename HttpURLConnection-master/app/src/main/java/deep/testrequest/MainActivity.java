package deep.testrequest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/14 14:30
 * @description httpUrl连接 https://github.com/mymdeep/HttpURLConnection
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.get:
                break;
            case R.id.post:
                break;
        }
    }
    public void get(){

    }
}
