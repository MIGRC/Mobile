package br.edu.ucpel.bean;

/**
 * Created by Miguel Aguiar Barbosa on 12/06/15.
 */
public class Frequencia {
    private Integer _id;
    private Integer curso_aluno_id;
    private Integer disciplina_id;
    private String disciplina_nome;
    private String dt_falta;
    private String hr_falta;

    public static final String FREQUENCIA_ID = "_id";
    public static final String FREQUENCIA_CURSO_ALUNO_ID = "curso_aluno_id";
    public static final String FREQUENCIA_DISCIPLINA_ID = "disciplina_id";
    public static final String FREQUENCIA_DISCIPLINA_NOME = "disciplina_nome";
    public static final String FREQUENCIA_DTFALTA = "dt_falta";
    public static final String FREQUENCIA_HRFALTA = "hr_falta";

    public static final String[] COLUNAS = new String[]{
            FREQUENCIA_ID,
            FREQUENCIA_CURSO_ALUNO_ID,
            FREQUENCIA_DISCIPLINA_ID,
            FREQUENCIA_DISCIPLINA_NOME,
            FREQUENCIA_DTFALTA,
            FREQUENCIA_HRFALTA
    };

    public Frequencia() {
    }

    public Frequencia(Integer _id, Integer curso_aluno_id, Integer disciplina_id, String disciplina_nome, String dt_falta, String hr_falta) {
        this._id = _id;
        this.curso_aluno_id = curso_aluno_id;
        this.disciplina_id = disciplina_id;
        this.disciplina_nome = disciplina_nome;
        this.dt_falta = dt_falta;
        this.hr_falta = hr_falta;
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

    public String getDt_falta() {
        return dt_falta;
    }

    public void setDt_falta(String dt_falta) {
        this.dt_falta = dt_falta;
    }

    public String getHr_falta() {
        return hr_falta;
    }

    public void setHr_falta(String hr_falta) {
        this.hr_falta = hr_falta;
    }
}
