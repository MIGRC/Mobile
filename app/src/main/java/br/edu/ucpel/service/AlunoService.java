package br.edu.ucpel.service;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.util.List;

import br.edu.ucpel.bean.Aluno;
import br.edu.ucpel.dao.AlunoDAO;
import br.edu.ucpel.util.Conexoes;

/**
 * Created by Miguel Aguiar Barbosa on 09/06/15.
 */
public class AlunoService extends AsyncTask<String, Void, Boolean> {

    private static final String BASE_URI = "http://"+ Conexoes.getIP()+":8080/UnimobileWS/webresources/aluno/aluno/listaalunos";
    private String cpf;
    private Context context;


    public AlunoService(String cpf, Context context) {
        this.cpf = cpf;
        this.context = context;
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            System.out.println(String.format("%s/%s",BASE_URI,cpf.toString()));
            request.setURI(new URI(String.format("%s/%s",BASE_URI,cpf.toString())));
            HttpResponse response = httpclient.execute(request);
            InputStream content = response.getEntity().getContent();
            Reader reader = new InputStreamReader(content);

            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();

            List<Aluno> alunoList = gson.fromJson(reader, new TypeToken<List<Aluno>>() {}.getType());
            AlunoDAO alunoDAO = new AlunoDAO(context);
            alunoDAO.deleteGeralAluno();
            for (Aluno a : alunoList) {
                Aluno aluno = new Aluno();
                aluno.set_id(a.get_id());
                aluno.setCurso_aluno_id(a.getCurso_aluno_id());
                aluno.setChave(a.getChave());
                aluno.setCpf(a.getCpf());
                aluno.setFlg_ativo("N");
                alunoDAO.insertAluno(aluno);
                Log.i("ALUNO LIST", a.getChave());
            }

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
