package com.example.projetogerenfacil;

public class ClienteEntry {
    public static final String TABLE_NAME = "clientes";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOME = "nome";
    public static final String COLUMN_CPF_CNPJ = "cpf_cnpj";
    public static final String COLUMN_ENDERECO = "endereco";
    public static final String COLUMN_COMPLEMENTO = "complemento";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NOME + " TEXT," +
                    COLUMN_CPF_CNPJ + " TEXT," +
                    COLUMN_ENDERECO + " TEXT," +
                    COLUMN_COMPLEMENTO + " TEXT" +
                    ")";
}
