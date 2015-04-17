package br.edu.ucpel.bean;

import java.io.Serializable;

/**
 * Created by sti on 16/04/15.
 */

//Classe responsável pelo transporte dos dados entre a
//interface(tela) e Banco de Dados
public class Horario implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private String disciplina;
    private String sala;
    private String horario;

    public Horario() {    }

    public Horario(String disciplina, String sala, String horario) {
        this.disciplina = disciplina;
        this.sala = sala;
        this.horario = horario;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String value) {
        this.disciplina = value;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String value) {
        this.sala = value;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String value) {
        this.horario = value;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return disciplina;
    }
}
