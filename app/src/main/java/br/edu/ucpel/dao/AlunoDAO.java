package br.edu.ucpel.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.ucpel.bean.Aluno;
import br.edu.ucpel.bean.Horario;
import br.edu.ucpel.db.DatabaseHelper;

/**
 * Created by Miguel Aguiar Barbosa on 09/06/15.
 */
public class AlunoDAO {

    public String TBL_ALUNO = "alunos";
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    private Context context;

    public AlunoDAO(Context context) {
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

    private Aluno criarAluno(Cursor cursor){
        Aluno alunoBean = new Aluno(
                cursor.getInt(cursor.getColumnIndex(Aluno.ALUNO_ID)),
                cursor.getInt(cursor.getColumnIndex(Aluno.ALUNO_CURSO_ALUNO_ID)),
                cursor.getString(cursor.getColumnIndex(Aluno.ALUNO_CHAVE)),
                cursor.getString(cursor.getColumnIndex(Aluno.ALUNO_CPF)),
                cursor.getString(cursor.getColumnIndex(Aluno.ALUNO_FLG_ATIVO))
        );

        return alunoBean;
    }

    public List<Aluno> listarAlunos(){
        Cursor cursor = getDatabase().query(TBL_ALUNO,
                Horario.COLUNAS, null, null, null, null, null);

        List<Aluno> alunos = new ArrayList<Aluno>();
        while(cursor.moveToNext()){
            Aluno alunoBean = criarAluno(cursor);
            alunos.add(alunoBean);
        }
        cursor.close();
        return alunos;
    }

    public void deleteGeralAluno(){
        database = dbHelper.getWritableDatabase();
        String sql = "DELETE FROM "+ TBL_ALUNO +";";
        database.execSQL(sql);
    }

    public void insertAluno(Aluno a) {
        database = dbHelper.getWritableDatabase();
        ContentValues Alunos = new ContentValues();
        Alunos.put(Aluno.ALUNO_CURSO_ALUNO_ID, a.getCurso_aluno_id());
        Alunos.put(Aluno.ALUNO_CHAVE, a.getChave());
        Alunos.put(Aluno.ALUNO_CPF, a.getCpf());
        Alunos.put(Aluno.ALUNO_FLG_ATIVO, a.getFlg_ativo());

        database.insert(TBL_ALUNO, null, Alunos);
        database.close();
    }

    public void updateAluno(Aluno a) {
        database = dbHelper.getWritableDatabase();
        ContentValues Alunos = new ContentValues();
        Alunos.put(Aluno.ALUNO_FLG_ATIVO, "S");

        database.update(TBL_ALUNO, Alunos, "_id = ?", new String[]{ a.get_id().toString() });
        database.close();
    }

}