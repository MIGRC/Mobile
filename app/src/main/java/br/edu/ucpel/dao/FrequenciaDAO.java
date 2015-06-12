package br.edu.ucpel.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.ucpel.bean.Frequencia;
import br.edu.ucpel.db.DatabaseHelper;

/**
 * Created by Miguel Aguiar Barbosa on 12/06/15.
 */
public class FrequenciaDAO {
    public String TBL_FREQUENCIA = "frequencias";
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public FrequenciaDAO(Context context) {
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

    private Frequencia criarFrequencia(Cursor cursor){
        Frequencia frequenciaBean = new Frequencia(
                cursor.getInt(cursor.getColumnIndex(Frequencia.FREQUENCIA_ID)),
                cursor.getInt(cursor.getColumnIndex(Frequencia.FREQUENCIA_CURSO_ALUNO_ID)),
                cursor.getInt(cursor.getColumnIndex(Frequencia.FREQUENCIA_DISCIPLINA_ID)),
                cursor.getString(cursor.getColumnIndex(Frequencia.FREQUENCIA_DISCIPLINA_NOME)),
                cursor.getString(cursor.getColumnIndex(Frequencia.FREQUENCIA_DTFALTA)),
                cursor.getString(cursor.getColumnIndex(Frequencia.FREQUENCIA_HRFALTA))

        );

        return  frequenciaBean;
    }

    public List<Frequencia> listarFrequencia(){
        Cursor cursor = getDatabase().query(TBL_FREQUENCIA,
                Frequencia.COLUNAS, null, null, null,null,null);

        List<Frequencia> frequencias = new ArrayList<Frequencia>();
        while (cursor.moveToNext()){
            Frequencia frequenciaBean = criarFrequencia(cursor);
            frequencias.add(frequenciaBean);
        }

        cursor.close();
        return frequencias;
    }

    public List<Frequencia> listarDisciplina(){
        database = dbHelper.getWritableDatabase();
        String countQuery = "SELECT "+Frequencia.FREQUENCIA_DISCIPLINA_ID+","+Frequencia.FREQUENCIA_DISCIPLINA_NOME+" FROM "+ TBL_FREQUENCIA +" GROUP BY "+Frequencia.FREQUENCIA_DISCIPLINA_ID+","+Frequencia.FREQUENCIA_DISCIPLINA_NOME+";";
        Cursor cursor = database.rawQuery(countQuery, null);

        List<Frequencia> frequencias = new ArrayList<Frequencia>();
        while (cursor.moveToNext()){
            Frequencia frequenciaBean = new Frequencia();
            frequenciaBean.setDisciplina_id(cursor.getInt(0));
            frequenciaBean.setDisciplina_nome(cursor.getString(1));
            frequencias.add(frequenciaBean);
        }

        cursor.close();
        return frequencias;
    }

    public List<Frequencia> listarFrequenciaPorDisciplina(int disciplina_id){
        database = dbHelper.getWritableDatabase();
        String countQuery = "SELECT "+Frequencia.FREQUENCIA_DISCIPLINA_ID+","+Frequencia.FREQUENCIA_DTFALTA+","+Frequencia.FREQUENCIA_HRFALTA+" FROM "+ TBL_FREQUENCIA +" WHERE "+Frequencia.FREQUENCIA_DISCIPLINA_ID+" = "+disciplina_id+";";
        Cursor cursor = database.rawQuery(countQuery, null);

        List<Frequencia> frequencia = new ArrayList<Frequencia>();
        while (cursor.moveToNext()){
            Frequencia frequenciaBean = new Frequencia();
            frequenciaBean.setDisciplina_id(cursor.getInt(0));
            frequenciaBean.setDt_falta(cursor.getString(1));
            frequenciaBean.setHr_falta(cursor.getString(2));
            frequencia.add(frequenciaBean);
        }

        cursor.close();
        return frequencia;
    }

    public void deleteGeralFrequencias(){
        database = dbHelper.getWritableDatabase();
        String sql = "DELETE FROM "+ TBL_FREQUENCIA +";";
        database.execSQL(sql);
    }

    public void insert(Frequencia f) {
        database = dbHelper.getWritableDatabase();
        ContentValues frequencias = new ContentValues();
        frequencias.put(Frequencia.FREQUENCIA_CURSO_ALUNO_ID, f.getCurso_aluno_id());
        frequencias.put(Frequencia.FREQUENCIA_DISCIPLINA_ID, f.getDisciplina_id());
        frequencias.put(Frequencia.FREQUENCIA_DISCIPLINA_NOME, f.getDisciplina_nome());
        frequencias.put(Frequencia.FREQUENCIA_DTFALTA, f.getDt_falta());
        frequencias.put(Frequencia.FREQUENCIA_HRFALTA, f.getHr_falta());

        database.insert(TBL_FREQUENCIA, null, frequencias);
        database.close();
    }
}

