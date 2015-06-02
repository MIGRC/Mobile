package br.edu.ucpel.service;

import android.app.IntentService;
import android.content.Intent;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;

import br.edu.ucpel.bean.Login;

/**
 * Created by Miguel Aguiar Barbosa on 02/06/15.
 */
public class UsuarioService extends IntentService{

    private static final String SERVICE_NAME = "USUARIO_SERVICE";
    private static final String SERVER_URL = "http://10.10.100.9:8080/UnimobileWS/webresources/usuario/usuario/get";

    public UsuarioService() {
        super(SERVICE_NAME);
    }

    //@Override
    protected Login onHandleIntent(String login, String senha) throws Exception {
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(String.format("%s/%s/%s",SERVER_URL,login.trim(), senha.trim())));
            HttpResponse response = httpclient.execute(request);
            InputStream content = response.getEntity().getContent();
            Reader reader = new InputStreamReader(content);
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();

            return gson.fromJson(reader, Login.class);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}
