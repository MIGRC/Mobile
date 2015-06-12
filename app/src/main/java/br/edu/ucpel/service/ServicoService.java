package br.edu.ucpel.service;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;

import br.edu.ucpel.bean.Servico;
import br.edu.ucpel.db.Conexoes;

/**
 * Created by Miguel Aguiar Barbosa on 12/06/15.
 */
public class ServicoService extends AsyncTask<String, Void, Boolean> {

    private static final String BASE_URI = "http://"+ Conexoes.getIP()+":8080/UnimobileWS/webresources/servico";

    public ServicoService() {
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(String.format("%s",BASE_URI)));
            HttpResponse response = httpclient.execute(request);
            InputStream content = response.getEntity().getContent();
            Reader reader = new InputStreamReader(content);

            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();

            Servico servico = gson.fromJson(reader, Servico.class);

            return servico.isTeste();

        } catch (IOException ex) {
            return false;
        } catch (URISyntaxException e) {
            return false;
        }
    }

}
