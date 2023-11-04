package com.example.projetogerenfacil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CadastroProdutoActivity extends AppCompatActivity {
    private static final String TAG = "CadastroProdutoActivity";

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    EditText etProductName, etCategory, etPrice, etStock, etPromotions, etDescription;
    Button btnProductRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_produto);

        etProductName = findViewById(R.id.etFullName);
        etCategory = findViewById(R.id.etCPF_CNPJ);
        etPrice = findViewById(R.id.etAddress);
        etStock = findViewById(R.id.etComplement);
        etPromotions = findViewById(R.id.etProduct);
        etDescription = findViewById(R.id.etCategory);
        btnProductRegister = findViewById(R.id.btnRegister);

        btnProductRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Cadastrar produto: botão de registro clicado");

                String enteredName = etProductName.getText().toString();
                String enteredCategory = etCategory.getText().toString();

                // Substitua vírgulas por pontos
                String enteredPrice = etPrice.getText().toString().replace(",", ".");
                String enteredStock = etStock.getText().toString().replace(",", ".");

                String enteredPromotions = etPromotions.getText().toString();
                String enteredDescription = etDescription.getText().toString();

                if (enteredName.isEmpty() || enteredCategory.isEmpty() || enteredPrice.isEmpty() || enteredStock.isEmpty() || enteredPromotions.isEmpty() || enteredDescription.isEmpty()) {
                    showToast("Preencha todos os campos.");
                } else {
                    // Se os dados forem validados, crie um arquivo com o cadastro do produto

                    // Crie uma instância do banco de dados:
                    AppDatabase database = new AppDatabase(CadastroProdutoActivity.this);

                    // Insira os dados do produto no banco de dados:
                    Produto produto = new Produto();
                    produto.setNome(enteredName);
                    produto.setCategoria(enteredCategory);

                    // Converta as strings para números de ponto flutuante
                    produto.setPreco(Double.parseDouble(enteredPrice));
                    produto.setEstoque(Integer.parseInt(enteredStock));

                    produto.setPromocoes(enteredPromotions);
                    produto.setDescricao(enteredDescription);
                    database.insertProduto(produto);

                    Log.d(TAG, "Produto cadastrado com sucesso: " + enteredName);

                    showToast("Cadastro de produto salvo com sucesso.");

                    // Retorne à tela de navegação após o cadastro bem-sucedido
                    Intent intent = new Intent(CadastroProdutoActivity.this, InterfaceNavegacaoActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });
    }
}