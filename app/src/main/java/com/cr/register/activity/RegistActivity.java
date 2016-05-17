package com.cr.register.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cr.register.R;
import com.cr.register.activity.model.RegistResult;
import com.cr.retrofit.RegistService;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Blacktea on 2016/5/16.
 */
public class RegistActivity extends Activity implements View.OnClickListener {
    private EditText etUsername, etEmail, etPassword, etPasswordConfirm;
    private Button btnRegist, btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        initView();
        initListener();
    }

    private void initListener() {
        btnRegist.setOnClickListener(this);
        btnReset.setOnClickListener(this);
    }

    private void initView() {
        etUsername = (EditText) findViewById(R.id.et_username);
        etEmail = (EditText) findViewById(R.id.et_email);
        etPassword = (EditText) findViewById(R.id.et_password);
        etPasswordConfirm = (EditText) findViewById(R.id.et_conf_password);
        btnRegist = (Button) findViewById(R.id.btn_regist);
        btnReset = (Button) findViewById(R.id.btn_reset);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //注册
            case R.id.btn_regist:
                String username = etUsername.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String passwordConfirm = etPasswordConfirm.getText().toString();
                if(password.length() < 6) {
                    Toast.makeText(this, "密码不少于6位", Toast.LENGTH_SHORT).show();
                    break;
                }
                if(!password.equals(passwordConfirm)) {
                    Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                    break;
                }

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://www.ebanshu.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                RegistService registService = retrofit.create(RegistService.class);
                Call<RegistResult> call = registService.getRegistResult(username, email, password, passwordConfirm);

                call.enqueue(new Callback<RegistResult>() {

                    @Override
                    public void onResponse(Response<RegistResult> response, Retrofit retrofit) {
                        if(response.body().getOk()) {
                            //TODO Regist Sussceful
                            Toast.makeText(RegistActivity.this, "恭喜！注册成功", Toast.LENGTH_SHORT).show();
                        } else {
                            //TODO Regist Faliure
                            Toast.makeText(RegistActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(RegistActivity.this, "未知错误", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            //重置
            case R.id.btn_reset:
                etUsername.setText("");
                etEmail.setText("");
                etPassword.setText("");
                etPasswordConfirm.setText("");
                break;
            default:
                break;
        }
    }
}

