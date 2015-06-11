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
    private double peso;
    private double nota;
    private double peso_nota;
    private double media;

    public static final String AVALIACAO_ID = "_id";
    public static final String AVALIACAO_CURSO_ALUNO_ID = "curso_aluno_id";
    public static final String AVALIACAO_DISCIPLINA_ID = "disciplina_id";
    public static final String AVALIACAO_DISCIPLINA_NOME = "disciplina_nome";
    public static final String AVALIACAO_AVALIACAO = "avaliacao";
    public static final String AVALIACAO_DATA = "data";
    public static final String AVALIACAO_PESO = "peso";
    public static final String AVALIACAO_NOTA = "nota";
    public static final String AVALIACAO_PESO_NOTA = "peso_nota";

    public static final String[] COLUNAS = new String[]{
            AVALIACAO_ID,
            AVALIACAO_CURSO_ALUNO_ID,
            AVALIACAO_DISCIPLINA_ID,
            AVALIACAO_DISCIPLINA_NOME,
            AVALIACAO_AVALIACAO,
            AVALIACAO_DATA,
            String.valueOf(AVALIACAO_PESO),
            String.valueOf(AVALIACAO_NOTA),
            String.valueOf(AVALIACAO_PESO_NOTA)
    };

    public Avaliacao() {
    }

    public Avaliacao(Integer disciplina_id, String avaliacao) {
        this.disciplina_id = disciplina_id;
        this.avaliacao = avaliacao;
    }

    public Avaliacao(Integer _id, Integer curso_aluno_id, Integer disciplina_id, String disciplina_nome, String avaliacao, String data, String peso, String nota, String peso_nota) {
        this._id = _id;
        this.curso_aluno_id = curso_aluno_id;
        this.disciplina_id = disciplina_id;
        this.disciplina_nome = disciplina_nome;
        this.avaliacao = avaliacao;
        this.data = data;
        this.peso = Double.parseDouble(peso);
        this.nota = Double.parseDouble(nota);
        this.peso_nota = Double.parseDouble(peso_nota);
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

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public double getPeso_nota() {
        return peso_nota;
    }

    public void setPeso_nota(double peso_nota) {
        this.peso_nota = peso_nota;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    @Override
    public String toString() {
        return "Avaliacao{" +
                "_id=" + _id +
                ", curso_aluno_id=" + curso_aluno_id +
                ", disciplina_id=" + disciplina_id +
                ", disciplina_nome='" + disciplina_nome + '\'' +
                ", avaliacao='" + avaliacao + '\'' +
                ", data='" + data + '\'' +
                ", peso=" + peso +
                ", nota=" + nota +
                ", peso_nota=" + peso_nota +
                '}';
    }
}
