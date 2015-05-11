package br.edu.ucpel.bean;

/**
 * Created by miguel on 11/05/15.
 */
public class Avaliacoes {
    private Integer _id;
    private String disciplina;
    private String avaliacao;
    private String data;

    public Avaliacoes() {    }

    public Avaliacoes(Integer id, String disciplina, String avaliacao, String data) {
        this._id = id;
        this.disciplina = disciplina;
        this.avaliacao = avaliacao;
        this.data = data;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
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

    @Override
    public String toString() {
        return "Avaliacoes{" +
                "_id=" + _id +
                ", disciplina='" + disciplina + '\'' +
                ", avaliacao='" + avaliacao + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
