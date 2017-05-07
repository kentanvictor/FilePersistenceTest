package com.example.dell.filepersistencetest.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.filepersistencetest.R;
import com.example.dell.filepersistencetest.utils.CloseUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private EditText edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit = (EditText) findViewById(R.id.edit1);//在onCreate()方法中接收到EditText的实例
        String inputText = load();
        if(!TextUtils.isEmpty(inputText))
        //如果读取的内容不为null，就调用EditText()的setText()方法，将内容填充到EditText中
        //然后调用setSelection()方法，将输入的光标移动到根本的末尾的位置以便继续输入
        {
            edit.setText(inputText);
            edit.setSelection(inputText.length());
            Toast.makeText(this,"Restoring succeeded",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        String inputText = edit.getText().toString();//重写onDestroy()方法，获取EditText的内容
        save(inputText);//调用save()方法，把输入的内容存储到文件中，文件名为data放入后台
    }

    //下面的是java中的IO流，用来写入文件与读取文件的
    public void save(String inputText)  {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try
        {
            out = openFileOutput("data", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
            writer.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }finally {
            CloseUtil.closeQuietly(writer);
        }

    }
    public String load()
    /*
    * 这里的load()方法是用来读取文件的
    *
    * */
    {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try
        {
            in = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null)
            {
                content.append(line);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            CloseUtil.closeQuietly(reader);
        }
        return content.toString();
    }

}
