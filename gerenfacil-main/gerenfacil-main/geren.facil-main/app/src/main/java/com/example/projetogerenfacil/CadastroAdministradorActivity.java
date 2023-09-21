package com.example.projetogerenfacil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CadastroAdministradorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_administrador);

        TextView tvLogin = findViewById(R.id.tvLogin);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirecione para a tela de login (MainActivity) quando "Faça login" for clicado
                Intent intent = new Intent(CadastroAdministradorActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
