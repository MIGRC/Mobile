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

import br.edu.ucpel.bean.Horario;
import br.edu.ucpel.dao.HorarioDAO;
import br.edu.ucpel.db.Conexoes;

/**
 * Created by Miguel Aguiar Barbosa on 04/06/15.
 */
public class HorarioService extends AsyncTask<Integer, Void, Boolean> {

    private static final String BASE_URI = "http://"+Conexoes.getIP()+":8080/EIMobileWS/webresources/horario/horario/listahorarios";
    private Integer curso_aluno_id;
    private Context context;


    public HorarioService(Integer curso_aluno_id, Context context) {
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

            List<Horario> horarioList = gson.fromJson(reader, new TypeToken<List<Horario>>() {}.getType());
            HorarioDAO horarioDAO = new HorarioDAO(context);
            horarioDAO.deleteGeral();
            for (Horario h : horarioList) {
                Horario horario = new Horario();
                horario.set_id(h.get_id());
                horario.setCurso_aluno_id(h.getCurso_aluno_id());
                horario.setDisciplina_id(h.getDisciplina_id());
                horario.setDisciplina_nome(h.getDisciplina_nome());
                horario.setSala(h.getSala());
                horario.setHorario(h.getHorario());
                horarioDAO.insert(horario);
                Log.i("HOTARIO LIST", h.getDisciplina_nome());
            }

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
