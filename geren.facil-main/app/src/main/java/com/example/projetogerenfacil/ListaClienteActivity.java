package com.example.projetogerenfacil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListaClienteActivity extends AppCompatActivity {
    private List<Cliente> listaDeClientes;
    private RecyclerView recyclerView;
    private ClienteAdapter clienteAdapter;
    Button btnAdicionar;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_clientes);

        listaDeClientes = new ArrayList<>();
        appDatabase = new AppDatabase(this); // Inicialize o AppDatabase

        listaDeClientes.addAll(getClientesFromDatabase());

        recyclerView = findViewById(R.id.recyclerUsuarios);
        clienteAdapter = new ClienteAdapter(listaDeClientes);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(clienteAdapter);

        btnAdicionar = findViewById(R.id.fabAdicionar);

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaClienteActivity.this, CadastroClienteActivity.class);
                startActivityForResult(intent, 1);
                Log.d("ListaClienteActivity", "Botão de adicionar clicado");
            }
        });
    }

    @NonNull
    private List<Cliente> getClientesFromDatabase() {
        return appDatabase.getAllClientes();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            String nome = data.getStringExtra("nome");
            String cpf = data.getStringExtra("cpf_cnpj");
            String endereco = data.getStringExtra("endereco");
            String complemento = data.getStringExtra("complemento");

            Cliente novoCliente = new Cliente(nome, cpf, endereco, complemento);
            listaDeClientes.add(novoCliente);
            clienteAdapter.notifyDataSetChanged();
            Log.d("ListaClienteActivity", "Novo cliente adicionado: " + nome);

            appDatabase.insertCliente(novoCliente);
        }
    }
}
