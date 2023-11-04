package com.example.projetogerenfacil;

public class ProdutoEntry {
    public static final String TABLE_NAME = "produtos";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOME = "nome";
    public static final String COLUMN_CATEGORIA = "categoria";
    public static final String COLUMN_PRECO = "preco";
    public static final String COLUMN_ESTOQUE = "estoque";
    public static final String COLUMN_PROMOCOES = "promocoes";
    public static final String COLUMN_DESCRICAO = "descricao";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NOME + " TEXT," +
                    COLUMN_CATEGORIA + " TEXT," +
                    COLUMN_PRECO + " REAL," +
                    COLUMN_ESTOQUE + " INTEGER," +
                    COLUMN_PROMOCOES + " TEXT," +
                    COLUMN_DESCRICAO + " TEXT" +
                    ")";
}
