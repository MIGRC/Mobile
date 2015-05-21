package br.edu.ucpel.ws;

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

/**
 * Created by Miguel Aguiar Barbosa on 21/05/15.
 */
public class ClienteGSON {

    private static final String BASE_URI = "http://10.20.5.14:8080/TCI-war/meuservico/testeservico";


    public Matricula matriculaGet(Integer curso_aluno_id) throws Exception {
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(String.format("%s/%s/%s",BASE_URI,curso_aluno_id.intValue())));
            HttpResponse response = httpclient.execute(request);
            InputStream content = response.getEntity().getContent();
            Reader reader = new InputStreamReader(content);
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
            return gson.fromJson(reader, new TypeToken<List<Matricula>>(){}.getType());

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}
