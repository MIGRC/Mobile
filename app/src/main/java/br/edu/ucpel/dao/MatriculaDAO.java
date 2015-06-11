package br.edu.ucpel.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.ucpel.bean.Horario;
import br.edu.ucpel.bean.Matricula;
import br.edu.ucpel.db.DatabaseHelper;

/**
 * Created by Miguel Aguiar Barbosa on 11/05/15.
 */
public class MatriculaDAO {

    public static final String TBL_MATRICULA = "matriculas";
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public MatriculaDAO(Context context) {
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

    private Matricula criarMatricula(Cursor cursor){
        Matricula MatriculaBean = new Matricula (
                cursor.getInt(cursor.getColumnIndex(Matricula.MATRICULA_ID)),
                cursor.getInt(cursor.getColumnIndex(Matricula.MATRICULA_CURSO_ALUNO_ID)),
                cursor.getInt(cursor.getColumnIndex(Matricula.MATRICULA_DISCIPLINA_ID)),
                cursor.getString(cursor.getColumnIndex(Matricula.MATRICULA_DISCIPLINA_NOME)),
                cursor.getString(cursor.getColumnIndex(Matricula.MATRICULA_SITUACAO)),
                cursor.getString(cursor.getColumnIndex(Matricula.MATRICULA_TURMA))
        );

        return MatriculaBean;
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

    public List<Matricula> listarMatriculas(){
        Cursor cursor = getDatabase().query(TBL_MATRICULA,
                Matricula.COLUNAS, null, null, null, null, null);

        List<Matricula> matriculas = new ArrayList<Matricula>();
        while(cursor.moveToNext()){
            Matricula matriculaBean = criarMatricula(cursor);
            matriculas.add(matriculaBean);
        }
        cursor.close();
        return matriculas;
    }

    public void deleteGeralMatricula(){
        database = dbHelper.getWritableDatabase();
        String sql = "DELETE FROM "+ TBL_MATRICULA +";";
        System.out.println(sql);
        database.execSQL(sql);
    }

    public void insert(Matricula h) {
        database = dbHelper.getWritableDatabase();
        ContentValues MATRICULAs = new ContentValues();
        MATRICULAs.put(Matricula.MATRICULA_CURSO_ALUNO_ID, h.getCurso_aluno_id());
        MATRICULAs.put(Matricula.MATRICULA_DISCIPLINA_ID, h.getDisciplina_id());
        MATRICULAs.put(Matricula.MATRICULA_DISCIPLINA_NOME, h.getDisciplina_nome());
        MATRICULAs.put(Matricula.MATRICULA_SITUACAO, h.getSituacao());
        MATRICULAs.put(Matricula.MATRICULA_TURMA, h.getTurma());

        database.insert(TBL_MATRICULA, null, MATRICULAs);
        database.close();
    }
}