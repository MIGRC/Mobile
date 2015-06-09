package br.edu.ucpel.bean;

/**
 * Created by Miguel Aguiar Barbosa on 09/06/15.
 */
public class Aluno {

    private Integer _id;
    private Integer curso_aluno_id;
    private String chave;
    private String cpf;
    private String flg_ativo;

    public static final String ALUNO_ID = "_id";
    public static final String ALUNO_CURSO_ALUNO_ID = "curso_aluno_id";
    public static final String ALUNO_CHAVE = "chave";
    public static final String ALUNO_CPF = "cpf";
    public static final String ALUNO_FLG_ATIVO = "flg_ativo";

    public static final String[] COLUNAS = new String[]{
            ALUNO_ID,
            ALUNO_CURSO_ALUNO_ID,
            ALUNO_CHAVE,
            ALUNO_CPF,
            ALUNO_FLG_ATIVO
    };

    public Aluno() {
    }

    public Aluno(Integer _id, Integer curso_aluno_id, String chave, String cpf, String flg_ativo) {
        this._id = _id;
        this.curso_aluno_id = curso_aluno_id;
        this.chave = chave;
        this.cpf = cpf;
        this.flg_ativo = flg_ativo;
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

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getFlg_ativo() {
        return flg_ativo;
    }

    public void setFlg_ativo(String flg_ativo) {
        this.flg_ativo = flg_ativo;
    }
}
