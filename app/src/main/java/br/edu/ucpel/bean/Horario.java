package br.edu.ucpel.bean;

import java.io.Serializable;

/**
 * Created by Miguel Aguiar Barbosa on 16/04/15.
 */

public class Horario {

    private Integer _id;
    private Integer curso_aluno_id;
    private Integer disciplina_id;
    private String disciplina_nome;
    private String sala;
    private String horario;

    public static final String HORARIO_ID = "_id";
    public static final String HORARIO_CURSO_ALUNO_ID = "curso_aluno_id";
    public static final String HORARIO_DISCIPLINA_ID = "disciplina_id";
    public static final String HORARIO_DISCIPLINA_NOME = "disciplina_nome";
    public static final String HORARIO_SALA = "sala";
    public static final String HORARIO_HORARIO = "horario";

    public static final String[] COLUNAS = new String[]{
            HORARIO_ID,
            HORARIO_CURSO_ALUNO_ID,
            HORARIO_DISCIPLINA_ID,
            HORARIO_DISCIPLINA_NOME,
            HORARIO_SALA,
            HORARIO_HORARIO
    };

    public Horario() {
    }

    public Horario(Integer _id, Integer curso_aluno_id, Integer disciplina_id, String disciplina_nome, String sala, String horario) {
        this._id = _id;
        this.curso_aluno_id = curso_aluno_id;
        this.disciplina_id = disciplina_id;
        this.disciplina_nome = disciplina_nome;
        this.sala = sala;
        this.horario = horario;
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

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
