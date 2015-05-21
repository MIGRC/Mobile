package br.edu.ucpel.bean;

import java.io.Serializable;

/**
 * Created by Miguel Aguiar Barbosa on 11/05/15.
 */
public class Matricula {

    private Integer curso_aluno_id;
    private Integer disciplina_id;
    private String disciplina_nome;
    private String situacao;
    private String turma;

    public static final String MATRICULA_CURSO_ALUNO_ID = "curso_aluno_id";
    public static final String MATRICULA_DISCIPLINA_ID = "disciplina_id";
    public static final String MATRICULA_DISCIPLINA_NOME = "disciplina_nome";
    public static final String MATRICULA_SITUACAO = "situacao";
    public static final String MATRICULA_TURMA = "turma";

    public static final String[] COLUNAS = new String[]{
            MATRICULA_CURSO_ALUNO_ID, MATRICULA_DISCIPLINA_ID, MATRICULA_DISCIPLINA_NOME, MATRICULA_SITUACAO, MATRICULA_TURMA
    };

    public Matricula() {
    }

    public Matricula(Integer curso_aluno_id, Integer disciplina_id, String disciplina_nome, String situacao, String turma) {
        this.curso_aluno_id = curso_aluno_id;
        this.disciplina_id = disciplina_id;
        this.disciplina_nome = disciplina_nome;
        this.situacao = situacao;
        this.turma = turma;
    }

    public Integer getCursoAlunoId() {
        return curso_aluno_id;
    }

    public void setCursoAlunoId(Integer curso_aluno_id) {
        this.curso_aluno_id = curso_aluno_id;
    }

    public Integer getDisciplinaId() {
        return disciplina_id;
    }

    public void setDisciplinaId(Integer disciplina_id) {
        this.disciplina_id = disciplina_id;
    }

    public String getDisciplinaNome() {
        return disciplina_nome;
    }

    public void setDisciplinaNome(String disciplina_nome) {
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

    @Override
    public String toString() {
        return disciplina_nome;
    }
}
