package br.edu.ucpel.service;

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

import br.edu.ucpel.HorariosActivity;
import br.edu.ucpel.bean.Horario;
import br.edu.ucpel.bean.Login;
import br.edu.ucpel.dao.HorarioDAO;

/**
 * Created by miguel on 04/06/15.
 */
public class HorarioService extends AsyncTask<Integer, Void, List<Horario>> {

    private static final String BASE_URI = "http://192.168.1.30:8080/UnimobileWS/webresources/horario/horario/listahorarios";
    private Integer curso_aluno_id;


    public HorarioService(Integer curso_aluno_id) {
        this.curso_aluno_id = curso_aluno_id;
    }

    @Override
    protected List<Horario> doInBackground(Integer... params) {
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(String.format("%s/%s",BASE_URI,curso_aluno_id.intValue())));
            HttpResponse response = httpclient.execute(request);
            InputStream content = response.getEntity().getContent();
            Reader reader = new InputStreamReader(content);

            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();

           // return gson.fromJson(reader, new TypeToken<List<Horario>>() {}.getType());

            List<Horario> horarioList = gson.fromJson(reader, new TypeToken<List<Horario>>() {}.getType());
            HorarioDAO horarioDAO = new HorarioDAO();
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

            return null;

        } catch (Exception ex) {
            ex.printStackTrace();
            //return (List<Horario>) null;
            return null;
        }
    }

}
