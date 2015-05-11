package br.edu.ucpel.bean;

import java.io.Serializable;

/**
 * Created by Miguel Aguiar Barbosa on 16/04/15.
 */

public class Horario {
    private Integer _id;
    private String disciplina;
    private String sala;
    private String horario;

    public Horario() {    }

    public Horario(Integer id, String disciplina, String sala, String horario) {
        this._id = id;
        this.disciplina = disciplina;
        this.sala = sala;
        this.horario = horario;
    }

    public long getId() {
        return _id;
    }

    public void setId(Integer id) {
        this._id = id;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
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
