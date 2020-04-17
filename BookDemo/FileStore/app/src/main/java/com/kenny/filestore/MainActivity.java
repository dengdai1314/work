package com.kenny.filestore;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/4/14 14:25
 * @description 6.2文件存储 6.3 SharedPreferences存储
 * //适合存储一些简单的文本数据或二进制数据
 */
public class MainActivity extends JsonStore implements View.OnClickListener {
    EditText editText;
    Button filesave;
    Button fileread;
    Button sharsave;
    Button sharread;
    Button jsonsave;
    Button jsonread;
    TextView showtext;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.edit_data);
        filesave = findViewById(R.id.file_save);
        fileread = findViewById(R.id.file_read);
        sharsave = findViewById(R.id.sharedpreferences_save);
        sharread = findViewById(R.id.sharedpreferences_read);
        jsonsave = findViewById(R.id.jsonsave);
        jsonread = findViewById(R.id.jsonread);
        showtext = findViewById(R.id.showtext);
        filesave.setOnClickListener(this);
        fileread.setOnClickListener(this);
        sharsave.setOnClickListener(this);
        sharread.setOnClickListener(this);
        jsonread.setOnClickListener(this);
        jsonsave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.file_save:
                String inputtext = editText.getText().toString();
                fileSave(inputtext);
                showtext.setText("你保存的数据为：  "+inputtext);
                break;
            case R.id.file_read:
                String readdata = fileRead();
                showtext.setText("你读取的数据为：  "+readdata);
                break;
            case R.id.sharedpreferences_save:
                sharSave();
                showtext.setText("已保存数据");
                break;
            case R.id.sharedpreferences_read:
                String shardata = sharRead();
                showtext.setText("你读取的数据为：  "+shardata);
                break;
            case R.id.jsonsave:
                saveJson("测试","测试数据");
                saveJsonFile();
                Toast.makeText(MainActivity.this,"已保存数据",Toast.LENGTH_SHORT).show();
                break;
            case R.id.jsonread:
                List<Result> results = readJson();
                Toast.makeText(MainActivity.this,results.toString(),Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 文件存储
     * @param inputText
     */
    public void fileSave(String inputText){
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            //将数据存储在指定文件中，"data"为文件名，不可包含路径
            //文件默认存储在设备/data/data/<packagename>/files/目录下
            //MODE_PRIVATE:默认操作模式，当指定同样文件名时，写入的内容将覆盖原文件中的内容
            //MODE_APPEND:如该文件已存在，就往文件里追加内容，不存在就创建新文件
            //返回一个FileOutputStream对象，后续可以使用Java流方式将数据写入文件中

            //通过openFileOutput方法得到一个FileOutputStream对象
            out = openFileOutput("data", Context.MODE_PRIVATE);
            //借助上面构建出一个OutputStreamWriter对象，再使用OutputStreamWriter构建出一个BufferedWriter对象
            writer = new BufferedWriter(new OutputStreamWriter(out,"utf-8"));
            //通过BufferedWriter将文本内容写入到文件中
            writer.write(inputText);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if(writer != null){
                    //关闭对象
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        Toast.makeText(MainActivity.this,"已保存数据",Toast.LENGTH_SHORT).show();
    }

    /**
     * 文件读取
     */
    public String fileRead(){
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try{
            //从/data/data/<package name>/files/文件中读取数据
            //返回FileInputStream对象
            //最终使用的还是BufferedReader对象
            in = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            //遍历StringBuilder对象并拼接字符串
            while ((line = reader.readLine()) != null){
                content.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(reader != null){
                try {
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }

    //SharedPreferences是使用键值对的方式来存储数据的，也就是说，当保存一条数据的时候，需要给这条数据提供一个对应的键
    //文件都是存放在/data/data/<package_name>/shared_prefs/目录下
    //获取SharedPreferences对象有三种方法
    //1. Context类中的getSharedPreference()方法
    //  接收两个参数：第一个参数用于指定文件名称，第二个参数用于指定操作模式，目前只有MODE_PRIVATE可选，表示只有当前的应用程序才可以对文件进行读写
    //2.Activity类中的getPreferences()方法
    //   只接收一个操作模式参数，该方法会自动将当前活动的类名作为文件名
    //3.PreferenceManager类中的getDefaultSharedPreferences()方法
    //  静态方法，接收一个Context参数，并自动使用当前应用程序的包名作为前缀来命名文件
    /**
     * SharedPreferences 保存
     */
    public void sharSave(){
        //获取对象
        //通过getSharedPreferences（）方法指定SharedPreferences的文件名为shardata,并得到SharedPreferences.Editor editor对象
        //apply()方法提交
        SharedPreferences.Editor editor = getSharedPreferences("shardata",MODE_PRIVATE).edit();
        //添加数据
        editor.putString("string","Tom");
        editor.putBoolean("boolean",true);
        editor.putFloat("float",348);
        editor.putInt("int",50);
        editor.putLong("long",5);
        editor.apply();               //一定要加，不然不保存数据
        Toast.makeText(MainActivity.this,"已保存数据",Toast.LENGTH_SHORT).show();
    }

    /**
     * SharedPreferences 读取
     */
    public String sharRead(){
        //读取布尔类型数据使用getBoolean方法
        //读取字符串类型使用getString方法
        //都接收两个参数：键，默认值
        SharedPreferences pref =getSharedPreferences("shardata",MODE_PRIVATE);
        String string = pref.getString("string","");
        Log.d("MainActivity","string="+string);
        return string;
    }
}
