package br.edu.ucpel.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Miguel Aguiar Barbosa on 09/04/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String BANCO_DADOS = "unimobile";
    private static final int VERSAO = 4;

    public DatabaseHelper(Context context) {
        super(context, BANCO_DADOS, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tabela de usuários
/*        db.execSQL("create table usuarios(_id integer primary key autoincrement, "
                +"nome text not null, login text not null, senha text not null, created_at text);");
*/
        //Tabela de Aluno
        db.execSQL("create table alunos(_id integer primary key autoincrement, "
                +  "curso_aluno_id integer not null, chave text not null, cpf text, flg_ativo char default 'N');");

        //Tabela de Horario
        db.execSQL("create table horarios(_id integer primary key autoincrement, "
                + "curso_aluno_id integer, disciplina_id integer, "
                + "disciplina_nome text not null, sala text not null, horario text not null);");

        //Tabela de Matricula
        db.execSQL("create table matriculas(_id integer primary key autoincrement, "
                + "curso_aluno_id integer, disciplina_id integer, "
                + "disciplina_nome text not null, situacao text not null, turma text not null);");

        //Tabela de Avaliações
        db.execSQL("create table avalicoes(_id integer primary key autoincrement,"
                + "curso_aluno_id integer, disciplina_id integer, "
                + "disciplina_nome text not null, avaliacao text not null, data text not null);");

        //Cadastro Horarios
        db.execSQL("insert into horarios(curso_aluno_id, disciplina_id, disciplina_nome, sala, horario) values(1, 1, 'teste1', 'Sala 233C', 'Terça 19:15');");
        db.execSQL("insert into horarios(curso_aluno_id, disciplina_id, disciplina_nome, sala, horario) values(1, 2, 'teste2', 'Sala 232C', 'Terça 20:30');");
        db.execSQL("insert into horarios(curso_aluno_id, disciplina_id, disciplina_nome, sala, horario) values(3, 3, 'teste3', 'Sala 232C', 'Quarta 19:15');");

        //Cadastro Matricula
        db.execSQL("insert into matriculas(curso_aluno_id, disciplina_id, disciplina_nome, situacao, turma) values(1, 1, 'teste1', 'Matriculado', '133');");
        db.execSQL("insert into matriculas(curso_aluno_id, disciplina_id, disciplina_nome, situacao, turma) values(1, 2, 'teste2', 'Matriculado', '135');");
        db.execSQL("insert into matriculas(curso_aluno_id, disciplina_id, disciplina_nome, situacao, turma) values(1, 3, 'teste2', 'Matriculado', '200');");

        //Cadastro Matricula
        db.execSQL("insert into avalicoes(curso_aluno_id, disciplina_id, disciplina_nome, avaliacao, data) values(1, 1, 'DOO III', 'DOO AV1', '13/06/2015');");
        db.execSQL("insert into avalicoes(curso_aluno_id, disciplina_id, disciplina_nome, avaliacao, data) values(1, 2, 'Estrutura de Dados', 'ED AV1', '12/06/2015');");
        db.execSQL("insert into avalicoes(curso_aluno_id, disciplina_id, disciplina_nome, avaliacao, data) values(1, 2, 'Estrutura de Dados', 'ED AV2', '22/08/2015');");
        db.execSQL("insert into avalicoes(curso_aluno_id, disciplina_id, disciplina_nome, avaliacao, data) values(1, 2, 'Estrutura de Dados', 'ED T1', '22/06/2015');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS matriculas");
       // db.execSQL("DROP TABLE IF EXISTS " + Horarios.TBL_HORARIO);
        this.onCreate(db);
    }
}
