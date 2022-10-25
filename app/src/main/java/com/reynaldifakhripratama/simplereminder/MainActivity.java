package com.reynaldifakhripratama.simplereminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.reynaldifakhripratama.simplereminder.data.room.AppDatabase;

public class MainActivity extends AppCompatActivity {
    AppDatabase db;
    EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = AppDatabase.getInstance(getApplicationContext());
        this.initComponents();

    }
    public void onBtnLoginClick(View view){
        if(validate()){
            Toast.makeText(this, "Berhasil Login", Toast.LENGTH_LONG).show();
//            if(db.movieDao().getAll().size() == 0){
//                Toast.makeText(this,"film kosong",Toast.LENGTH_LONG).show();
//            }else{
//                Toast.makeText(this,"film ada",Toast.LENGTH_LONG).show();
//            }

            Intent i = new Intent(getApplicationContext(), MovieListActivity.class);
            startActivity(i);
        }else{
            Toast.makeText(this, "username/password tidak terdaftar", Toast.LENGTH_LONG).show();
        }
    }

    private void initComponents()
    {
        this.username = this.findViewById(R.id.username);
        this.password = this.findViewById(R.id.password);
    }

    private boolean validate(){
        String u = this.username.getText().toString();
        String p = this.password.getText().toString();

        return (db.userDao().getUser(u,p) != null);
    }

}