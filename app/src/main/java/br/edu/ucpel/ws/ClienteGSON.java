package br.edu.ucpel.ws;

import android.os.AsyncTask;

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

import br.edu.ucpel.bean.Login;
import br.edu.ucpel.bean.Matricula;
import br.edu.ucpel.bean.Usuario;
import br.edu.ucpel.dao.UsuarioDAO;

/**
 * Created by Miguel Aguiar Barbosa on 21/05/15.
 */
public class ClienteGSON extends AsyncTask<String, Void, Boolean> {

    private static final String BASE_URI = "http://10.10.100.9:8080/UnimobileWS/webresources/usuario/usuario/get";
    private String login;
    private String senha;

    public ClienteGSON(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(String.format("%s/%s/%s",BASE_URI,login.trim(), senha.trim())));
            HttpResponse response = httpclient.execute(request);
            InputStream content = response.getEntity().getContent();
            Reader reader = new InputStreamReader(content);

            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();

            Login login = gson.fromJson(reader, Login.class);

            return login.isLogado();

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
