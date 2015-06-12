package br.edu.ucpel.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.ucpel.bean.Horario;
import br.edu.ucpel.db.DatabaseHelper;

/**
 * Created by sti on 16/04/15.
 */
public class HorarioDAO {

    public String TBL_HORARIO = "horarios";
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    private Context context;

    public HorarioDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    private SQLiteDatabase getDatabase() {
        if (database == null) {
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

    private Horario criarHorario(Cursor cursor){
        Horario horarioBean = new Horario(
                cursor.getInt(cursor.getColumnIndex(Horario.HORARIO_ID)),
                cursor.getInt(cursor.getColumnIndex(Horario.HORARIO_CURSO_ALUNO_ID)),
                cursor.getInt(cursor.getColumnIndex(Horario.HORARIO_DISCIPLINA_ID)),
                cursor.getString(cursor.getColumnIndex(Horario.HORARIO_DISCIPLINA_NOME)),
                cursor.getString(cursor.getColumnIndex(Horario.HORARIO_SALA)),
                cursor.getString(cursor.getColumnIndex(Horario.HORARIO_HORARIO))
        );

        return horarioBean;
    }

    public List<Horario> listarHorarios(){
        Cursor cursor = getDatabase().query(TBL_HORARIO,
               Horario.COLUNAS, null, null, null, null, null);

        List<Horario> horarios = new ArrayList<Horario>();
        while(cursor.moveToNext()){
            Horario horarioBean = criarHorario(cursor);
            horarios.add(horarioBean);
        }
        cursor.close();
        return horarios;
    }

    public void deleteGeral(){
        database = dbHelper.getWritableDatabase();
        String sql = "DELETE FROM "+ TBL_HORARIO +";";
        System.out.println(sql);
        database.execSQL(sql);
    }

    public void insert(Horario h) {
        database = dbHelper.getWritableDatabase();
        ContentValues horarios = new ContentValues();
        horarios.put(Horario.HORARIO_CURSO_ALUNO_ID, h.getCurso_aluno_id());
        horarios.put(Horario.HORARIO_DISCIPLINA_ID, h.getDisciplina_id());
        horarios.put(Horario.HORARIO_DISCIPLINA_NOME, h.getDisciplina_nome());
        horarios.put(Horario.HORARIO_SALA, h.getSala());
        horarios.put(Horario.HORARIO_HORARIO, h.getHorario());

        database.insert(TBL_HORARIO, null, horarios);
        database.close();
    }
}