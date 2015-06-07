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

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    private Context context;

    public HorarioDAO() {
    }

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
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Horarios.HORARIO_ID)),
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Horarios.HORARIO_CURSO_ALUNO_ID)),
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Horarios.HORARIO_DISCIPLINA_ID)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Horarios.HORARIO_DISCIPLINA_NOME)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Horarios.HORARIO_SALA)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Horarios.HORARIO_HORARIO))
        );

        return horarioBean;
    }

    public List<Horario> listarHorarios(){
        Cursor cursor = getDatabase().query(DatabaseHelper.Horarios.TBL_HORARIO,
                DatabaseHelper.Horarios.COLUNAS, null, null, null, null, null);

        List<Horario> horarios = new ArrayList<Horario>();
        while(cursor.moveToNext()){
            Horario horarioBean = criarHorario(cursor);
            horarios.add(horarioBean);
        }
        cursor.close();
        return horarios;
    }

    public void deleteGeral(){
      //  dbHelper = new DatabaseHelper(this.context);
     //   database = dbHelper.getWritableDatabase();
        String sql = "DELETE FROM " + DatabaseHelper.Horarios.TBL_HORARIO;
        database.execSQL(sql);
    }

    public void insert(Horario h) {
        //dbHelper = new DatabaseHelper(this.context);
       // database = dbHelper.getWritableDatabase();
        ContentValues horarios = new ContentValues();
        horarios.put(DatabaseHelper.Horarios.HORARIO_CURSO_ALUNO_ID, h.getCurso_aluno_id());
        horarios.put(DatabaseHelper.Horarios.HORARIO_DISCIPLINA_ID, h.getDisciplina_id());
        horarios.put(DatabaseHelper.Horarios.HORARIO_DISCIPLINA_NOME, h.getDisciplina_nome());
        horarios.put(DatabaseHelper.Horarios.HORARIO_SALA, h.getSala());
        horarios.put(DatabaseHelper.Horarios.HORARIO_HORARIO, h.getHorario());

        database.insert(DatabaseHelper.Horarios.TBL_HORARIO, null, horarios);
        database.close();
    }

    /*public long Inserir(Contato contato) {
        ContentValues cv = new ContentValues();
        cv.put(dbHelper.AGENDA_NOME, contato.getNome());
        cv.put(dbHelper.AGENDA_ENDERECO, contato.getEndereco());
        cv.put(dbHelper.AGENDA_TELEFONE, contato.getTelefone());

        return database.insert(dbHelper.TBL_AGENDA, null, cv);
    }

    public int Alterar(Contato contato) {
        long id = contato.getId();
        ContentValues values = new ContentValues();

        values.put(dbHelper.AGENDA_NOME, contato.getNome());
        values.put(dbHelper.AGENDA_ENDERECO, contato.getEndereco());
        values.put(dbHelper.AGENDA_TELEFONE, contato.getTelefone());

        return database.update(dbHelper.TBL_AGENDA, values, dbHelper.AGENDA_ID + " = " + id, null);
    }

    public void Excluir(Contato pValue) {
        long id = pValue.getId();

        database.delete(dbHelper.TBL_AGENDA, dbHelper.AGENDA_ID + " = " + id, null);
    }*/

   /* public List<Horario> Consultar() {
        List<Horario> lstHorarios = new ArrayList<Horario>();

        Cursor cursor = database.query(dbHelper.TBL_HORARIO, colunas, null, null,
                null, null, dbHelper.HORARIO_DISCIPLINA);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Horario lHorarioVO = cursorToHorario(cursor);
            lstHorarios.add(lHorarioVO);
            cursor.moveToNext();
        }

        cursor.close();
        return lstHorarios;
    }*/

   /* private Horario cursorToHorario(Cursor cursor) {
        Horario lHorarioVO = new Horario();
        lHorarioVO.setId(cursor.getInt(0));
        lHorarioVO.setDisciplina(cursor.getString(1));
        lHorarioVO.setSala(cursor.getString(2));
        lHorarioVO.setHorario(cursor.getString(3));
        return lHorarioVO;
    }*/
}