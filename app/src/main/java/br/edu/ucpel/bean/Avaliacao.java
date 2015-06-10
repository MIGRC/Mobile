package br.edu.ucpel.bean;

/**
 * Created by miguel on 11/05/15.
 */
public class Avaliacao {
    private Integer _id;
    private Integer curso_aluno_id;
    private Integer disciplina_id;
    private String disciplina_nome;
    private String avaliacao;
    private String data;

    public static final String AVALIACAO_ID = "_id";
    public static final String AVALIACAO_CURSO_ALUNO_ID = "curso_aluno_id";
    public static final String AVALIACAO_DISCIPLINA_ID = "disciplina_id";
    public static final String AVALIACAO_DISCIPLINA_NOME = "disciplina_nome";
    public static final String AVALIACAO_AVALIACAO = "avaliacao";
    public static final String AVALIACAO_DATA = "data";

    public static final String[] COLUNAS = new String[]{
            AVALIACAO_ID,
            AVALIACAO_CURSO_ALUNO_ID,
            AVALIACAO_DISCIPLINA_ID,
            AVALIACAO_DISCIPLINA_NOME,
            AVALIACAO_AVALIACAO,
            AVALIACAO_DATA
    };

    public Avaliacao() {
    }

    public Avaliacao(Integer disciplina_id, String avaliacao) {
        this.disciplina_id = disciplina_id;
        this.avaliacao = avaliacao;
    }

    public Avaliacao(Integer _id, Integer disciplina_id, Integer curso_aluno_id, String disciplina_nome, String avaliacao, String data) {
        this._id = _id;
        this.disciplina_id = disciplina_id;
        this.curso_aluno_id = curso_aluno_id;
        this.disciplina_nome = disciplina_nome;
        this.avaliacao = avaliacao;
        this.data = data;
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

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
