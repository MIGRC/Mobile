package br.edu.ucpel.bean;

import java.io.Serializable;

/**
 * Created by Miguel Aguiar Barbosa on 11/05/15.
 */
public class Matricula {
    private Integer _id;
    private String disciplina;
    private String situacao;
    private String turma;

    public Matricula() {  }

    public Matricula(Integer id, String disciplina, String situacao, String turma) {
        this._id = id;
        this.disciplina = disciplina;
        this.situacao = situacao;
        this.turma = turma;
    }

    public Integer get_id() {
        return _id;
    }

    public void setId(Integer _id) {
        this._id = _id;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    @Override
    public String toString() {
        return disciplina;
    }
}
