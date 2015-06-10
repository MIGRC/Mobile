package br.edu.ucpel.bean;

import java.io.Serializable;

/**
 * Created by Miguel Aguiar Barbosa on 11/05/15.
 */
public class Matricula {

    private Integer _id;
    private Integer curso_aluno_id;
    private Integer disciplina_id;
    private String disciplina_nome;
    private String situacao;
    private String turma;

    public static final String MATRICULA_ID = "_id";
    public static final String MATRICULA_CURSO_ALUNO_ID = "curso_aluno_id";
    public static final String MATRICULA_DISCIPLINA_ID = "disciplina_id";
    public static final String MATRICULA_DISCIPLINA_NOME = "disciplina_nome";
    public static final String MATRICULA_SITUACAO = "situacao";
    public static final String MATRICULA_TURMA = "turma";

    public static final String[] COLUNAS = new String[]{
            MATRICULA_ID,
            MATRICULA_CURSO_ALUNO_ID,
            MATRICULA_DISCIPLINA_ID,
            MATRICULA_DISCIPLINA_NOME,
            MATRICULA_SITUACAO,
            MATRICULA_TURMA
    };

    public Matricula() {
    }

    public Matricula(Integer _id, Integer curso_aluno_id,  Integer disciplina_id, String disciplina_nome, String situacao, String turma) {
        this.curso_aluno_id = curso_aluno_id;
        this._id = _id;
        this.disciplina_id = disciplina_id;
        this.disciplina_nome = disciplina_nome;
        this.situacao = situacao;
        this.turma = turma;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public Integer getCurso_aluno_id() {
        return curso_aluno_id;
    }

    public void setCurso_aluno_id(Integer curso_aluno_id) {
        this.curso_aluno_id = curso_aluno_id;
    }

    public Integer getDisciplina_id() {
        return disciplina_id;
    }

    public void setDisciplina_id(Integer disciplina_id) {
        this.disciplina_id = disciplina_id;
    }

    public String getDisciplina_nome() {
        return disciplina_nome;
    }

    public void setDisciplina_nome(String disciplina_nome) {
        this.disciplina_nome = disciplina_nome;
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
}
