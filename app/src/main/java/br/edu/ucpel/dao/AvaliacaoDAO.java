package br.edu.ucpel.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.ucpel.bean.Avaliacao;
import br.edu.ucpel.db.DatabaseHelper;

/**
 * Created by miguel on 11/05/15.
 */
public class AvaliacaoDAO {

    public String TBL_AVALIACAO = "avalicoes";
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public AvaliacaoDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    private SQLiteDatabase getDatabase() {
        if(database == null){
            database = dbHelper.getWritableDatabase();
        }

        return database;
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    private Avaliacao criarAvaliacao(Cursor cursor){
        Avaliacao avaliacaoBean = new Avaliacao(
                cursor.getInt(cursor.getColumnIndex(Avaliacao.AVALIACAO_ID)),
                cursor.getInt(cursor.getColumnIndex(Avaliacao.AVALIACAO_CURSO_ALUNO_ID)),
                cursor.getInt(cursor.getColumnIndex(Avaliacao.AVALIACAO_DISCIPLINA_ID)),
                cursor.getString(cursor.getColumnIndex(Avaliacao.AVALIACAO_DISCIPLINA_NOME)),
                cursor.getString(cursor.getColumnIndex(Avaliacao.AVALIACAO_AVALIACAO)),
                cursor.getString(cursor.getColumnIndex(Avaliacao.AVALIACAO_DATA))
        );

        return  avaliacaoBean;
    }

    public List<Avaliacao> listarAvaliacoes(){
        Cursor cursor = getDatabase().query(DatabaseHelper.Avaliacoes.TBL_AVALIACOES,
                DatabaseHelper.Avaliacoes.COLUNAS, null, null, null,null,null);

        List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();
        while (cursor.moveToNext()){
            Avaliacao avaliacaoBean = criarAvaliacao(cursor);
            avaliacoes.add(avaliacaoBean);
        }

        cursor.close();
        return avaliacoes;
    }

    public void deleteGeral(){
        database = dbHelper.getWritableDatabase();
        String sql = "DELETE FROM "+ TBL_AVALIACAO +";";
        database.execSQL(sql);
    }

    public void insert(Avaliacao a) {
//        dbHelper = new DatabaseHelper(this.context);
        database = dbHelper.getWritableDatabase();
        ContentValues avaliacoes = new ContentValues();
        avaliacoes.put(Avaliacao.AVALIACAO_CURSO_ALUNO_ID, a.getCurso_aluno_id());
        avaliacoes.put(Avaliacao.AVALIACAO_DISCIPLINA_ID, a.getDisciplina_id());
        avaliacoes.put(Avaliacao.AVALIACAO_DISCIPLINA_NOME, a.getDisciplina_nome());
        avaliacoes.put(Avaliacao.AVALIACAO_AVALIACAO, a.getAvaliacao());
        avaliacoes.put(Avaliacao.AVALIACAO_DATA, a.getData());

        database.insert(TBL_AVALIACAO, null, avaliacoes);
        database.close();
    }
}
