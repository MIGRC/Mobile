package br.edu.ucpel.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Miguel Aguiar Barbosa on 09/04/15.
 */
public class DatabaseHelper  extends SQLiteOpenHelper {
    //Tabela Usuários
    //public static final String TBL_USUARIO = "usuarios";


    private static final String BANCO_DADOS = "unimobile";
    private static final int VERSAO = 1;

    public DatabaseHelper(Context context) {
        super(context, BANCO_DADOS, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tabela de usuários
        db.execSQL("create table usuarios(_id integer primary key autoincrement, "
                +"nome text not null, login text not null, senha text not null, created_at text)");

        //Tabela de Horario
        db.execSQL("create table horarios(_id integer primary key autoincrement, "
                + "disciplina text not null, sala text not null, horario text not null);");

        //Cadastrar um usuário
        db.execSQL("insert into usuarios(nome, login, senha) values('Admin', 'admin', '123');");

        //Cadastro Horarios
        db.execSQL("insert into horarios(disciplina, sala, horario) values('Estrutura de Dados', 'Sala 233C', 'Terça 19:15');");
        db.execSQL("insert into horarios(disciplina, sala, horario) values('DOO III', 'Sala 232C', 'Terça 20:30');");
        db.execSQL("insert into horarios(disciplina, sala, horario) values('DOO II', 'Sala 232C', 'Quarta 19:15');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      //  db.execSQL("DROP TABLE IF EXISTS " + TBL_USUARIO);
      //  db.execSQL("DROP TABLE IF EXISTS " + TBL_HORARIO);
      //  onCreate(db);
    }

    public static class Usuarios{
        public static final String TABELA = "usuarios";
        public static final String _ID = "_id";
        public static final String NOME = "nome";
        public static final String LOGIN = "login";
        public static final String SENHA = "senha";
        public static final String CREATED_AT = "created_at";

        public static final String[] COLUNAS = new String[]{
                _ID, NOME, LOGIN, SENHA, CREATED_AT
        };
    }

    public static class Tarefas{
        public static final String TABELA = "tarefas";
        public static final String _ID = "_id";
        public static final String TAREFA = "tarefa";
        public static final String DT_CRIACAO = "dt_criacao";
        public static final String DT_COMPLETADO = "dt_completado";

        public static final String[] COLUNAS = new String[]{
                _ID, TAREFA, DT_CRIACAO, DT_COMPLETADO
        };
    }

    //Tabela Horarios
    public static class Horarios{
        public static final String TBL_HORARIO = "horarios";
        public static final String HORARIO_ID = "_id";
        public static final String HORARIO_DISCIPLINA = "disciplina";
        public static final String HORARIO_SALA = "sala";
        public static final String HORARIO_HORARIO = "horario";

        public static final String[] COLUNAS = new String[]{
                HORARIO_ID, HORARIO_DISCIPLINA, HORARIO_SALA, HORARIO_HORARIO
        };
    }
}
