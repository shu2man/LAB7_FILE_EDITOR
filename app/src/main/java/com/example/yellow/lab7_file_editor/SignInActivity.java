package com.example.yellow.lab7_file_editor;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Yellow on 2017-12-7.
 */

public class SignInActivity extends Activity {
    private SharedPreferences sharedPreferences;
    private boolean isFirstLaunch;
    @Override
    public void onCreate(Bundle savedBundles){
        super.onCreate(savedBundles);
        setContentView(R.layout.sign_in_layout);

        checkShouldRegister();
    }

    public void checkShouldRegister(){
        sharedPreferences=getApplicationContext().getSharedPreferences("MyPreference",MODE_PRIVATE);
        isFirstLaunch=sharedPreferences.getBoolean("isFirstLaunch",true);
        android.support.constraint.ConstraintLayout CL=findViewById(R.id.register_edit_text);
        EditText ET=findViewById(R.id.sign_in_psd);
        if(isFirstLaunch){
            CL.setVisibility(View.VISIBLE);
            ET.setVisibility(View.GONE);
        }
        else{
            CL.setVisibility(View.GONE);
            ET.setVisibility(View.VISIBLE);
        }
    }
    public void RegisterIn(View view){
        if(isFirstLaunch){
            EditText et_confirm=findViewById(R.id.confirm_psd);
            EditText et_new=findViewById(R.id.new_psd);
            if(et_confirm.getText().toString().equals("") || et_new.getText().toString().equals("")){
                Toast.makeText(this,"Password Incomplete",Toast.LENGTH_SHORT).show();
            }
            else if(et_confirm.getText().toString().equals(et_new.getText().toString())){
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putBoolean("isFirstLaunch",false);
                editor.putString("Password",et_confirm.getText().toString());
                editor.apply();

                Intent intent=new Intent(this,EditActivity.class);
                startActivity(intent);
                this.finish();
            }
            else{
                Toast.makeText(this,"Inconsistent Password!",Toast.LENGTH_SHORT).show();
            }
        }
        else{
            String psd=sharedPreferences.getString("Password","null");
            EditText et=findViewById(R.id.sign_in_psd);
            if(et.getText().toString().equals("")){
                Toast.makeText(this,"Empty Password",Toast.LENGTH_SHORT).show();
            }
            else if(et.getText().toString().equals(psd)){
                Intent intent=new Intent(this,EditActivity.class);
                startActivity(intent);
                this.finish();
            }
            else{
                Toast.makeText(this,"Wrong Password",Toast.LENGTH_SHORT).show();
            }
        }


    }
    public void clearEditText(View view){
        if(isFirstLaunch){
            EditText newPsd=findViewById(R.id.new_psd);
            EditText confirmPsd=findViewById(R.id.confirm_psd);
            newPsd.setText("");
            confirmPsd.setText("");
        }
        else{
            EditText psd=findViewById(R.id.sign_in_psd);
            psd.setText("");
        }
    }
}
