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

public class ListaProdutoActivity extends AppCompatActivity {
    private List<Produto> listaDeProdutos;
    private RecyclerView recyclerView;
    private ProdutoAdapter produtoAdapter;
    Button btnAdicionarProduto;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_produto);

        listaDeProdutos = new ArrayList<>();
        appDatabase = new AppDatabase(this); // Inicialize o AppDatabase

        listaDeProdutos.addAll(getProdutosFromDatabase());

        recyclerView = findViewById(R.id.recyclerProdutos);
        produtoAdapter = new ProdutoAdapter(listaDeProdutos);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(produtoAdapter);

        btnAdicionarProduto = findViewById(R.id.fabAdicionarProduto);

        btnAdicionarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaProdutoActivity.this, CadastroProdutoActivity.class);
                startActivityForResult(intent, 1);
                Log.d("ListaProdutoActivity", "Botão de adicionar clicado");
            }
        });
    }

    @NonNull
    private List<Produto> getProdutosFromDatabase() {
        return appDatabase.getAllProdutos();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            String nome = data.getStringExtra("nome");
            String categoria = data.getStringExtra("categoria");
            double preco = Double.parseDouble(data.getStringExtra("preco"));
            int estoque = Integer.parseInt(data.getStringExtra("estoque"));
            String promocoes = data.getStringExtra("promocoes");
            String descricao = data.getStringExtra("descricao");

            Produto novoProduto = new Produto(nome, categoria, preco, estoque, promocoes, descricao);
            listaDeProdutos.add(novoProduto);
            produtoAdapter.notifyDataSetChanged();
            Log.d("ListaProdutoActivity", "Novo produto adicionado: " + nome);

            appDatabase.insertProduto(novoProduto);
        }
    }
}
