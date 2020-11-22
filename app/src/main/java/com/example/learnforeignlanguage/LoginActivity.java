package com.example.learnforeignlanguage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.learnforeignlanguage.DAO.UserDao;
import com.example.learnforeignlanguage.mode.User;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    UserDao userDao;
    EditText edt_userName,edt_pass;
    List<User> list;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        anhXa();

    }

    private void anhXa() {
        userDao= new UserDao(this);
        edt_userName = findViewById(R.id.edt_login_userName);
        edt_pass = findViewById(R.id.edt_login_password);
        sharedPreferences = getSharedPreferences("phong",MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void  login(View view){
        list = userDao.timUserName(edt_userName.getText().toString());
         if(list.size()==0){
             Toast.makeText(this,"Tài khoản không tồn tại",Toast.LENGTH_LONG).show();
         }else {
                if(list.get(0).getPassword().equals(edt_pass.getText().toString())){
                    editor.clear();
                    editor.putString("userName", edt_userName.getText().toString());
                    editor.putString("password", edt_pass.getText().toString());
                    editor.commit();
                    Intent intent = new Intent(this,MenuActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(this,"Sai mật khẩu",Toast.LENGTH_LONG).show();
                }

         }
    }
    public void signUp(View view){
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }
}