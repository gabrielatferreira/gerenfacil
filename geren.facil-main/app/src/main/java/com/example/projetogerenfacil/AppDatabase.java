package com.example.projetogerenfacil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class AppDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "gerenfacil.db";
    private static final int DATABASE_VERSION = 1;

    public AppDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crie as tabelas do banco de dados aqui
        db.execSQL(ClienteEntry.CREATE_TABLE);
        db.execSQL(ProdutoEntry.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Atualize as tabelas do banco de dados, se necessário
    }

    // Método para inserir um cliente no banco de dados
    public long insertCliente(Cliente cliente) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ClienteEntry.COLUMN_NOME, cliente.getNome());
        values.put(ClienteEntry.COLUMN_CPF_CNPJ, cliente.getCpfCnpj());
        values.put(ClienteEntry.COLUMN_ENDERECO, cliente.getEndereco());
        values.put(ClienteEntry.COLUMN_COMPLEMENTO, cliente.getComplemento());

        // Insira o cliente na tabela
        long id = db.insert(ClienteEntry.TABLE_NAME, null, values);
        db.close();

        return id;
    }

    // Método para inserir um produto no banco de dados
    public long insertProduto(Produto produto) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ProdutoEntry.COLUMN_NOME, produto.getNome());
        values.put(ProdutoEntry.COLUMN_CATEGORIA, produto.getCategoria());
        values.put(ProdutoEntry.COLUMN_PRECO, produto.getPreco());
        values.put(ProdutoEntry.COLUMN_ESTOQUE, produto.getEstoque());
        values.put(ProdutoEntry.COLUMN_PROMOCOES, produto.getPromocoes());
        values.put(ProdutoEntry.COLUMN_DESCRICAO, produto.getDescricao());

        // Insira o produto na tabela
        long id = db.insert(ProdutoEntry.TABLE_NAME, null, values);
        db.close();

        return id;
    }

    // Método para obter todos os clientes
    public List<Cliente> getAllClientes() {
        List<Cliente> clientes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                ClienteEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Cliente cliente = new Cliente();
                cliente.setNome(cursor.getString(cursor.getColumnIndex(ClienteEntry.COLUMN_NOME)));
                cliente.setCpfCnpj(cursor.getString(cursor.getColumnIndex(ClienteEntry.COLUMN_CPF_CNPJ)));
                cliente.setEndereco(cursor.getString(cursor.getColumnIndex(ClienteEntry.COLUMN_ENDERECO)));
                cliente.setComplemento(cursor.getString(cursor.getColumnIndex(ClienteEntry.COLUMN_COMPLEMENTO)));
                clientes.add(cliente);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        db.close();
        return clientes;
    }

    // Método para obter todos os produtos
    public List<Produto> getAllProdutos() {
        List<Produto> produtos = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                ProdutoEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Produto produto = new Produto();
                produto.setNome(cursor.getString(cursor.getColumnIndex(ProdutoEntry.COLUMN_NOME)));
                produto.setCategoria(cursor.getString(cursor.getColumnIndex(ProdutoEntry.COLUMN_CATEGORIA)));
                produto.setPreco(cursor.getDouble(cursor.getColumnIndex(ProdutoEntry.COLUMN_PRECO)));
                produto.setEstoque(cursor.getInt(cursor.getColumnIndex(ProdutoEntry.COLUMN_ESTOQUE)));
                produto.setPromocoes(cursor.getString(cursor.getColumnIndex(ProdutoEntry.COLUMN_PROMOCOES)));
                produto.setDescricao(cursor.getString(cursor.getColumnIndex(ProdutoEntry.COLUMN_DESCRICAO)));
                produtos.add(produto);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        db.close();
        return produtos;
    }
}
