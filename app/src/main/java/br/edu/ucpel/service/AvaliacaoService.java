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

import br.edu.ucpel.bean.Avaliacao;
import br.edu.ucpel.bean.Horario;
import br.edu.ucpel.dao.AvaliacaoDAO;
import br.edu.ucpel.dao.HorarioDAO;
import br.edu.ucpel.db.Conexoes;

/**
 * Created by Miguel Aguiar Barbosa on 11/06/15.
 */
public class AvaliacaoService extends AsyncTask<Integer, Void, Boolean> {

    private static final String BASE_URI = "http://"+ Conexoes.getIP()+":8080/UnimobileWS/webresources/avaliacoes/avaliacoes/listaavaliacoes";
    private Integer curso_aluno_id;
    private Context context;


    public AvaliacaoService(Integer curso_aluno_id, Context context) {
        this.curso_aluno_id = curso_aluno_id;
        this.context = context;
    }

    @Override
    protected Boolean doInBackground(Integer... params) {
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            System.out.println(String.format("%s/%s",BASE_URI,curso_aluno_id.intValue()));
            request.setURI(new URI(String.format("%s/%s",BASE_URI,curso_aluno_id.intValue())));
            HttpResponse response = httpclient.execute(request);
            InputStream content = response.getEntity().getContent();
            Reader reader = new InputStreamReader(content);

            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();

            List<Avaliacao> avaliacaosList = gson.fromJson(reader, new TypeToken<List<Avaliacao>>() {}.getType());
            AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO(context);
            avaliacaoDAO.deleteGeralAvaliacoes();
            for (Avaliacao a : avaliacaosList) {
                Avaliacao avaliacao = new Avaliacao();
                avaliacao.set_id(a.get_id());
                avaliacao.setCurso_aluno_id(a.getCurso_aluno_id());
                avaliacao.setDisciplina_id(a.getDisciplina_id());
                avaliacao.setDisciplina_nome(a.getDisciplina_nome());
                avaliacao.setAvaliacao(a.getAvaliacao());
                avaliacao.setData(a.getData());
                avaliacao.setPeso(a.getPeso());
                avaliacao.setNota(a.getNota());
                avaliacao.setPeso_nota(a.getPeso_nota());
                avaliacaoDAO.insert(avaliacao);
                Log.i("HOTARIO LIST", a.toString());
            }

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}

