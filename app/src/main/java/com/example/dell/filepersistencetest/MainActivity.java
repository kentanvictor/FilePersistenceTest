package com.example.dell.filepersistencetest;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private EditText edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit = (EditText) findViewById(R.id.edit1);//在onCreate()方法中接收到EditText的实例
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        String inputText = edit.getText().toString();//重写onDestroy()方法，获取EditText的内容
            save(inputText);//调用save()方法，把输入的内容存储到文件中，文件名为data
    }

    public void save(String inputText)  {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try
        {
            out = openFileOutput("data", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }finally {
            try
            {
                if(writer != null)
                {
                    writer.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }
}
