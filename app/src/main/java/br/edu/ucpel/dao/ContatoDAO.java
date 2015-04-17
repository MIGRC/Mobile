package br.edu.ucpel.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ContatoDAO {
/*
	private SQLiteDatabase database;
	private BaseDAO dbHelper;

	// Campos da tabela Agenda
	private String[] colunas = { BaseDAO.AGENDA_ID, BaseDAO.AGENDA_NOME,
			BaseDAO.AGENDA_ENDERECO, BaseDAO.AGENDA_TELEFONE };

	public ContatoDAO(Context context) {
		dbHelper = new BaseDAO(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public long Inserir(Contato pValue) {
		ContentValues values = new ContentValues();

		// Carregar os valores nos campos do Contato que será incluído
		values.put(BaseDAO.AGENDA_NOME, pValue.getNome());
		values.put(BaseDAO.AGENDA_ENDERECO, pValue.getEndereco());
		values.put(BaseDAO.AGENDA_TELEFONE, pValue.getTelefone());

		return database.insert(BaseDAO.TBL_AGENDA, null, values);
	}

	public int Alterar(Contato pValue) {
		long id = pValue.getId();
		ContentValues values = new ContentValues();

		// Carregar os novos valores nos campos que serão alterados
		values.put(BaseDAO.AGENDA_NOME, pValue.getNome());
		values.put(BaseDAO.AGENDA_ENDERECO, pValue.getEndereco());
		values.put(BaseDAO.AGENDA_TELEFONE, pValue.getTelefone());

		// Alterar o registro com base no ID
		return database.update(BaseDAO.TBL_AGENDA, values, BaseDAO.AGENDA_ID
				+ " = " + id, null);
	}

	public void Excluir(Contato pValue) {
		long id = pValue.getId();

		// Exclui o registro com base no ID
		database.delete(BaseDAO.TBL_AGENDA, BaseDAO.AGENDA_ID + " = " + id,
				null);
	}

	public List<Contato> Consultar() {
		List<Contato> lstAgenda = new ArrayList<Contato>();

		// Consulta para trazer todos os dados da tabela Agenda ordenados pela
		// coluna Nome
		Cursor cursor = database.query(BaseDAO.TBL_AGENDA, colunas, null, null,
				null, null, BaseDAO.AGENDA_NOME);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Contato lContatoVO = cursorToContato(cursor);
			lstAgenda.add(lContatoVO);
			cursor.moveToNext();
		}

		// Tenha certeza que você fechou o cursor
		cursor.close();
		return lstAgenda;
	}

	// Converter o Cursor de dados no objeto POJO ContatoVO
	private Contato cursorToContato(Cursor cursor) {
		Contato lContatoVO = new Contato();
		lContatoVO.setId(cursor.getLong(0));
		lContatoVO.setNome(cursor.getString(1));
		lContatoVO.setEndereco(cursor.getString(2));
		lContatoVO.setTelefone(cursor.getString(3));
		return lContatoVO;
	}*/
}