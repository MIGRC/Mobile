package br.edu.ucpel.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.ucpel.bean.Matricula;
import br.edu.ucpel.db.DatabaseHelper;

/**
 * Created by Miguel Aguiar Barbosa on 11/05/15.
 */
public class MatriculaDAO {

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
        Matricula matriculaBean = new Matricula(
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Matriculas.MATRICULA_ID)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Matriculas.MATRICULA_DISCIPLINA)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Matriculas.MATRICULA_SITUACAO)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Matriculas.MATRICULA_TURMA))
        );

        return matriculaBean;
    }

    public List<Matricula> listarMatriculas(){
        Cursor cursor = getDatabase().query(DatabaseHelper.Matriculas.TBL_MATRICULA,
                DatabaseHelper.Matriculas.COLUNAS, null, null, null, null, null);

        List<Matricula> matriculas = new ArrayList<Matricula>();
        while(cursor.moveToNext()){
            Matricula matriculaBean = criarMatricula(cursor);
            matriculas.add(matriculaBean);
        }
        cursor.close();
        return matriculas;
    }

    private Matricula cursorToMatricula(Cursor cursor) {
        Matricula lMatriculaVO = new Matricula();
        lMatriculaVO.setId(cursor.getInt(0));
        lMatriculaVO.setDisciplina(cursor.getString(1));
        lMatriculaVO.setSituacao(cursor.getString(2));
        lMatriculaVO.setTurma(cursor.getString(3));
        return lMatriculaVO;
    }
}
