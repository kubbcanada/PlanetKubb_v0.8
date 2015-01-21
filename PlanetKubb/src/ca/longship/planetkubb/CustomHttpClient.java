package ca.longship.planetkubb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;

import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.DefaultHttpClient;

class CustomHttpClient {

    public String getInternetData(String loginName, String loginPassword) {
        BufferedReader in = null;
        String data = null;
        try {
            HttpClient client = new DefaultHttpClient();
            URI website = new URI(
                    "http://wiki.planetkubb.com/w/api.php?action=login&lgname="
                            + loginName + "&lgpassword=" + loginPassword);
            HttpGet request = new HttpGet();
            request.setURI(website);
            HttpResponse response = client.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity()
                    .getContent()));
            StringBuilder sb = new StringBuilder("");
            String l;
            String nl = System.getProperty("line.separator");

            while ((l = in.readLine()) != null) {
                sb.append(l).append(nl);
            }
            in.close();
            data = sb.toString();
            return data;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return data;

    }
}
