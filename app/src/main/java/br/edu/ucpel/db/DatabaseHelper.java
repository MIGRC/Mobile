package br.edu.ucpel.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Miguel Aguiar Barbosa on 09/04/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String BANCO_DADOS = "unimobile";
    private static final int VERSAO = 2;

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

        //Tabela de Matricula
        db.execSQL("create table matriculas(curso_aluno_id integer, "
                + "disciplina_id integer, disciplina_nome text not null, "
                + "situacao text not null, turma text not null);");

        //Tabela de Avaliações
        db.execSQL("create table avalicoes(_id integer primary key autoincrement,"
                + "disciplina text not null, avaliacao text not null, data text not null);");

        //Cadastro um usuário
        db.execSQL("insert into usuarios(nome, login, senha) values('Admin', 'admin', '123');");

        //Cadastro Horarios
        db.execSQL("insert into horarios(disciplina, sala, horario) values('Estrutura de Dados', 'Sala 233C', 'Terça 19:15');");
        db.execSQL("insert into horarios(disciplina, sala, horario) values('DOO III', 'Sala 232C', 'Terça 20:30');");
        db.execSQL("insert into horarios(disciplina, sala, horario) values('DOO II', 'Sala 232C', 'Quarta 19:15');");

        //Cadastro Matricula
        db.execSQL("insert into matriculas(curso_aluno_id, disciplina_id, disciplina_nome, situacao, turma) values(1,1,'Estrutura de Dados', 'Matriculado', '133');");
        db.execSQL("insert into matriculas(curso_aluno_id, disciplina_id, disciplina_nome, situacao, turma) values(1,2,'DOO III', 'Matriculado', '135');");
        db.execSQL("insert into matriculas(curso_aluno_id, disciplina_id, disciplina_nome, situacao, turma) values(1,3,'DOO II', 'Matriculado', '200');");

        //Cadastro Matricula
        db.execSQL("insert into avalicoes(disciplina, avaliacao, data) values('Estrutura de Dados', '1 avaliação', '12/06/2015');");
        db.execSQL("insert into avalicoes(disciplina, avaliacao, data) values('Estrutura de Dados', '2 avaliação', '22/08/2015');");
        db.execSQL("insert into avalicoes(disciplina, avaliacao, data) values('Estrutura de Dados', 'Trabalho 1', '22/06/2015');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       // db.execSQL("DROP TABLE IF EXISTS matriculas");
      //  db.execSQL("DROP TABLE IF EXISTS " + TBL_HORARIO);
        //onCreate(db);
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

    //Tabela Matricula
    /*public static class Matriculas{
        public static final String TBL_MATRICULA = "matriculas";
        public static final String MATRICULA_ID = "_id";
        public static final String MATRICULA_DISCIPLINA = "disciplina";
        public static final String MATRICULA_SITUACAO = "situacao";
        public static final String MATRICULA_TURMA = "turma";

        public static final String[] COLUNAS = new String[]{
                MATRICULA_ID, MATRICULA_DISCIPLINA, MATRICULA_SITUACAO, MATRICULA_TURMA
        };
    }*/

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

    //Tabela Avalição
    public static class Avaliacoes{
        public static final String TBL_AVALIACOES = "avalicoes";
        public static final String AVALIACOES_ID = "_id";
        public static final String AVALIACOES_DISCIPLINA = "disciplina";
        public static final String AVALIACOES_AVALIACAO = "avaliacao";
        public static final String AVALIACOES_DATA = "data";

        public static final String[] COLUNAS = new String[]{
                AVALIACOES_ID, AVALIACOES_DISCIPLINA, AVALIACOES_AVALIACAO, AVALIACOES_DATA
        };
    }
}
