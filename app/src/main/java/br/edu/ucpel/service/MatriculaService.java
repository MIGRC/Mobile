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

import br.edu.ucpel.bean.Matricula;
import br.edu.ucpel.dao.MatriculaDAO;
import br.edu.ucpel.db.Conexoes;

/**
 * Created by Miguel Aguiar Barbosa on 10/06/15.
 */
public class MatriculaService extends AsyncTask<Integer, Void, Boolean> {

    private static final String BASE_URI = "http://"+Conexoes.getIP()+":8080/EIMobileWS/webresources/matricula/matricula/listamatriculas";
    private Integer curso_aluno_id;
    private Context context;


    public MatriculaService(Integer curso_aluno_id, Context context) {
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

            List<Matricula> horarioList = gson.fromJson(reader, new TypeToken<List<Matricula>>() {}.getType());
            MatriculaDAO matriculaDAO = new MatriculaDAO(context);
            matriculaDAO.deleteGeralMatricula();
            for (Matricula m : horarioList) {
                Matricula matricula = new Matricula();
                matricula.set_id(m.get_id());
                matricula.setCurso_aluno_id(m.getCurso_aluno_id());
                matricula.setDisciplina_id(m.getDisciplina_id());
                matricula.setDisciplina_nome(m.getDisciplina_nome());
                matricula.setSituacao(m.getSituacao());
                matricula.setTurma(m.getTurma());
                matriculaDAO.insert(matricula);
                Log.i("Matricula LIST", m.getDisciplina_nome());
            }

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
