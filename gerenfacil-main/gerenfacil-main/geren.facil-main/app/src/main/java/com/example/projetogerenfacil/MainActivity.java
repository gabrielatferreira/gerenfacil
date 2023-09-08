package com.example.projetogerenfacil;

import android.content.Intent;
import android.content.SharedPreferences;
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
                String enteredUsername = etUser.getText().toString();
                String enteredPassword = etPass.getText().toString();

                if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
                    // Campos não preenchidos
                    showToast("msg_campos_invalidos");
                } else {
                    // Inicia validação dos dados
                    String nome, email, user, pass;

                    SharedPreferences pref;
                    pref = getSharedPreferences(enteredUsername, MODE_PRIVATE);

                    nome = pref.getString("chNome", "");
                    email = pref.getString("chEmail", "");
                    user = pref.getString("chCPF_CNPJ", "");
                    pass = pref.getString("chPass", "");

                    if (enteredUsername.equals(user) && enteredPassword.equals(pass)) {
                        // Login OK
                        //InterfaceNavegacaoActivity.
                        //InterfaceNavegacaoActivity.tvEmail.setText(user);


                        Intent Intent = new Intent(MainActivity.this, InterfaceNavegacaoActivity.class);
                        Intent.putExtra("Nome", nome);
                        Intent.putExtra("Email", email);
                        startActivity(Intent);
                    } else {
                        // Autenticação falhou
                        showToast("msg_falha_autenticacao");
                    }
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
