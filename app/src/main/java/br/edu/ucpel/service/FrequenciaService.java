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

import br.edu.ucpel.bean.Frequencia;
import br.edu.ucpel.dao.FrequenciaDAO;
import br.edu.ucpel.db.Conexoes;

/**
 * Created by Miguel Aguiar Barbosa on 12/06/15.
 */
public class FrequenciaService extends AsyncTask<Integer, Void, Boolean> {

    private static final String BASE_URI = "http://"+ Conexoes.getIP()+":8080/UnimobileWS/webresources/frequencia/frequencia/listafrequencias";
    private Integer curso_aluno_id;
    private Context context;


    public FrequenciaService(Integer curso_aluno_id, Context context) {
        this.curso_aluno_id = curso_aluno_id;
        this.context = context;
    }

    @Override
    protected Boolean doInBackground(Integer... params) {
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(String.format("%s/%s",BASE_URI,curso_aluno_id.intValue())));
            HttpResponse response = httpclient.execute(request);
            InputStream content = response.getEntity().getContent();
            Reader reader = new InputStreamReader(content);

            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();

            List<Frequencia> frequenciaList = gson.fromJson(reader, new TypeToken<List<Frequencia>>() {}.getType());
            FrequenciaDAO frequenciaDAO = new FrequenciaDAO(context);
            frequenciaDAO.deleteGeralFrequencias();
            for (Frequencia f : frequenciaList) {
                Frequencia frequencia = new Frequencia();
                frequencia.set_id(f.get_id());
                frequencia.setCurso_aluno_id(f.getCurso_aluno_id());
                frequencia.setDisciplina_id(f.getDisciplina_id());
                frequencia.setDisciplina_nome(f.getDisciplina_nome());
                frequencia.setDt_falta(f.getDt_falta());
                frequencia.setHr_falta(f.getHr_falta());
                frequenciaDAO.insert(frequencia);
                Log.i("FREQUENCIA LIST", f.toString());
            }

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}

