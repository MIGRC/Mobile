package br.edu.ucpel.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.ucpel.bean.Avaliacoes;
import br.edu.ucpel.db.DatabaseHelper;

/**
 * Created by miguel on 11/05/15.
 */
public class AvaliacaoDAO {

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

    private Avaliacoes criarAvaliacao(Cursor cursor){
        Avaliacoes avaliacaoBean = new Avaliacoes(
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Avaliacoes.AVALIACOES_ID)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Avaliacoes.AVALIACOES_DISCIPLINA)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Avaliacoes.AVALIACOES_AVALIACAO)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Avaliacoes.AVALIACOES_DATA))
        );

        return  avaliacaoBean;
    }

    public List<Avaliacoes> listarAvaliacoes(){
        Cursor cursor = getDatabase().query(DatabaseHelper.Avaliacoes.TBL_AVALIACOES,
                DatabaseHelper.Avaliacoes.COLUNAS, null, null, null,null,null);

        List<Avaliacoes> avaliacoes = new ArrayList<Avaliacoes>();
        while (cursor.moveToNext()){
            Avaliacoes avaliacaoBean = criarAvaliacao(cursor);
            avaliacoes.add(avaliacaoBean);
        }

        cursor.close();
        return avaliacoes;
    }
}
