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

   /* private Matricula criarMatricula(Cursor cursor){
        Matricula matriculaBean = new Matricula(
                cursor.getInt(cursor.getColumnIndex(matriculaBean.MATRICULA_CURSO_ALUNO_ID)),
                cursor.getInt(cursor.getColumnIndex(MATRICULA_DISCIPLINA_ID)),
                cursor.getString(cursor.getColumnIndex(MATRICULA_DISCIPLINA_NOME)),
                cursor.getString(cursor.getColumnIndex(MATRICULA_SITUACAO)),
                cursor.getString(cursor.getColumnIndex(MATRICULA_DISCIPLINA_ID))
        );

        return matriculaBean;
    }*/

    public List<Matricula> listarMatriculas(String curso_aluno_id){
        Cursor cursor = getDatabase().query(TBL_MATRICULA, Matricula.COLUNAS, null, null, null, null, null);

        List<Matricula> listMatricula = new ArrayList<Matricula>();
        while(cursor.moveToFirst()) {
            int idxCursoAlunoId = cursor.getColumnIndex(Matricula.MATRICULA_CURSO_ALUNO_ID);
            int idxDisciplinaId = cursor.getColumnIndex(Matricula.MATRICULA_DISCIPLINA_ID);
            int idxDisciplinaNome = cursor.getColumnIndex(Matricula.MATRICULA_DISCIPLINA_NOME);
            int idxSituacao = cursor.getColumnIndex(Matricula.MATRICULA_SITUACAO);
            int idxTurma = cursor.getColumnIndex(Matricula.MATRICULA_DISCIPLINA_ID);

            do {
                Matricula matricula = new Matricula();
                listMatricula.add(matricula);
                matricula.setCursoAlunoId(cursor.getInt(idxCursoAlunoId));
                matricula.setDisciplinaId(cursor.getInt(idxDisciplinaId));
                matricula.setDisciplinaNome(cursor.getString(idxDisciplinaNome));
                matricula.setSituacao(cursor.getString(idxSituacao));
                matricula.setTurma(cursor.getString(idxTurma));
            } while (cursor.moveToNext());
        }
        //Collections.sort(listMatricula);
        return listMatricula;

    }

    private Matricula cursorToMatricula(Cursor cursor) {
        Matricula matriculaBean = new Matricula();
        matriculaBean.setCursoAlunoId(cursor.getInt(0));
        matriculaBean.setDisciplinaId(cursor.getInt(1));
        matriculaBean.setDisciplinaNome(cursor.getString(2));
        matriculaBean.setSituacao(cursor.getString(3));
        matriculaBean.setTurma(cursor.getString(4));
        return matriculaBean;
    }

    public void onInsert(Matricula m) {
        ContentValues v = new ContentValues();
        v.put(Matricula.MATRICULA_CURSO_ALUNO_ID, m.getCursoAlunoId());
        v.put(Matricula.MATRICULA_DISCIPLINA_ID, m.getDisciplinaId());
        v.put(Matricula.MATRICULA_DISCIPLINA_NOME, m.getDisciplinaNome());
        v.put(Matricula.MATRICULA_SITUACAO, m.getSituacao());
        v.put(Matricula.MATRICULA_TURMA, m.getTurma());
        insert(values);
    }

    public void insert(ContentValues valores) {
        dbHelper.inserir(TBL_MATRICULA, "", valores);
        Log.i(TBL_MATRICULA, "Inseriu o registro");
    }

    public int onDelete(Integer curso_aluno_id) {
        String where = Matricula.MATRICULA_CURSO_ALUNO_ID + "=?";
        Integer whereArg = curso_aluno_id;
        int count = delete(where, whereArg);
        return count;
    }

    public int delete(String where, Integer whereArg) {
        int count = dbHelper.delete(TBL_MATRICULA, where, whereArg);
        Log.i(TBL_MATRICULA, "Deletou (" + count + ") registros;");
    }
}
