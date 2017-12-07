package com.example.yellow.lab7_file_editor;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Yellow on 2017-12-7.
 */

public class EditActivity extends Activity {
    private EditText name;
    private EditText content;//ContenOf+filename
    private FileInputStream fileInputStream;
    private FileOutputStream fileOutputStream;
    /*private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;*/

    @Override
    public void onCreate(Bundle savedBundles){
        super.onCreate(savedBundles);
        setContentView(R.layout.editor_layout);

        name=findViewById(R.id.edit_file_name);
        content=findViewById(R.id.edit_file_content);
    }

    public void saveFile(View view){
        String filename=name.getText().toString();
        String filecontent=content.getText().toString();
        if(filename.equals("")){
            Toast.makeText(this,"Please enter a filename",Toast.LENGTH_SHORT).show();
        }
        else if(filecontent.equals("")){
            Toast.makeText(this,"No Empty File!",Toast.LENGTH_SHORT).show();
        }
        else{
            try{
                fileOutputStream=openFileOutput(filename,MODE_PRIVATE);
                fileOutputStream.write(filecontent.getBytes("GBK"));
                fileOutputStream.flush();
                fileOutputStream.close();
                Log.e("TAG","Succeffully saved file.");
                Toast.makeText(this,"Successfully saved file",Toast.LENGTH_SHORT).show();
            }catch(IOException e){
                Log.e("TAG","Fail to save file.");
                Toast.makeText(this,"Fail to save file",Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void loadFile(View view){
        String filename=name.getText().toString();
        if(filename.equals("")){
            Toast.makeText(this,"Please enter a filename",Toast.LENGTH_SHORT).show();
        }
        else{
            try{
                fileInputStream=openFileInput(filename);
                byte[] cont=new byte[fileInputStream.available()];
                fileInputStream.read(cont);
                content.setText(new String(cont,"GBK"));
                fileInputStream.close();
                Toast.makeText(this,"Successfully loaded file",Toast.LENGTH_SHORT).show();
            }catch (IOException e){
                Log.e("TAG","Fail to read file.");
                Toast.makeText(this,"Fail to read file",Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void clearContent(View view){
        content.setText("");
    }
    public void deleteFile(View view){
        String filename=name.getText().toString();
        if(filename.equals("")){
            Toast.makeText(this,"Please enter a filename",Toast.LENGTH_SHORT).show();
        }
        else{
            //File file=new File(System.getProperty("user.dir"),filename);
            //file.delete();
            //getApplicationContext().deleteFile(filename);
            //Context.deleteFile(filename)
            if(getApplicationContext().deleteFile(filename)) Toast.makeText(this,"Successfully delete file",Toast.LENGTH_SHORT).show();
            else Toast.makeText(this,"Delete file failed ",Toast.LENGTH_SHORT).show();
        }
    }


}
