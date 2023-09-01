package com.example.projetogerenfacil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    private static final String defaultUser = "12345678900";
    private static final String defaultPass = "password";

    private EditText etUser, etPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = findViewById(R.id.btnLogin);
        etUser = findViewById(R.id.etCPF_CNPJ);
        etPass = findViewById(R.id.etPassword);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lógica para verificar as credenciais e navegar para a próxima tela
                // Se as credenciais estiverem corretas, inicie a próxima atividade

                String enteredUsername = etUser.getText().toString();
                String enteredPassword = etPass.getText().toString();

                if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
                    // Campos não preenchidos
                    showToast("msg_campos_invalidos");
                } else if (enteredUsername.equals(defaultUser) && enteredPassword.equals(defaultPass)) {
                    // Autenticação ok
                    Intent loginOK = new Intent(MainActivity.this, NextActivity.class);
                    startActivity(loginOK);
                } else {
                    // Autenticação falhou
                    showToast("msg_falha_autenticacao");
                }
            }
        });



        TextView forgotPasswordTextView = findViewById(R.id.tvForgotPassword);
        forgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para lidar com o clique no texto "Esqueceu a senha?"
                // Implemente a navegação para a tela de redefinição de senha, se necessário

                //Intent forgot = new Intent(MainActivity.this, ForgotPasswordActivity.class);
                //startActivity(forgot);

                showToast("msg_email_enviado");
            }
        });

        TextView registerTextView = findViewById(R.id.tvRegister);
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para lidar com o clique no texto "Cadastre-se"
                // Implemente a navegação para a tela de cadastro, se necessário

                Intent register = new Intent(MainActivity.this, EscolhaCadastroActivity.class);
                startActivity(register);
            }
        });
    }
}
