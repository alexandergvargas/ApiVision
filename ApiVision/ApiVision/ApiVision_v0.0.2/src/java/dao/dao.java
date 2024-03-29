package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import model.model;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class dao {

    public model consultarapiVision(model dl) throws JSONException, IOException {
        HttpClient httpClient = new DefaultHttpClient();

        try {
            HttpPost request = new HttpPost("https://vision.googleapis.com/v1/images:annotate?key=AIzaSyA8dMaAKaInJOhW3V6tLHDT6SBel_8-cFQ");
            StringEntity params = new StringEntity("{\n"
                    + "  \"requests\":[\n"
                    + "    {\n"
                    + "      \"image\":{\n"
                    + "        \"source\":{\n"
                    + "          \"imageUri\":\n"
                    + "            \"gs://vasquez/" + dl.getNameFile() + "\"\n"
                    + "        }\n"
                    + "      },\n"
                    + "      \"features\":[\n"
                    + "        {\n"
                    + "          \"type\":\"DOCUMENT_TEXT_DETECTION\",\n"
                    + "          \"maxResults\":1\n"
                    + "        }\n"
                    + "      ]\n"
                    + "    }\n"
                    + "  ]\n"
                    + "}"
            );
            request.addHeader("Content-Type", "application/json");
            request.addHeader("Authorization", "Bearer " + dl.getToken_acces());
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
//            System.out.println(EntityUtils.toString(entity));
//            JSONObject root = new JSONObject(EntityUtils.toString(entity));
//            JSONArray responses = root.getJSONArray("responses");
//            JSONObject primerElemento = responses.getJSONObject(0);
//            JSONObject Fulltext = primerElemento.getJSONObject("fullTextAnnotation");
//            String texto = Fulltext.getString("text");
//            System.out.println(texto);
            JSONObject json = new JSONObject(EntityUtils.toString(entity));
//            System.out.println(json.getJSONArray("responses").getJSONObject(0).getJSONObject("fullTextAnnotation").getString("text"));

            dl.setResponses(json.getJSONArray("responses").getJSONObject(0).getJSONObject("fullTextAnnotation").getString("text"));
        } catch (IOException ex) {
            throw ex;
        }
        return dl;
    }

    public void envioStorage() {
        try {
            //Leo fichero
            String filename = "C:/Users/PC/Desktop/acta.jpg";

            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String lineaTotal = "";
            String linea = reader.readLine();
            while (linea != null) {
                lineaTotal = lineaTotal + linea;
                linea = reader.readLine();
            }

            String envio = lineaTotal;
            System.out.println("TAMAÑO KILOBYTES: " + (lineaTotal.length() / 1024));
            reader.close();
            StringEntity params = new StringEntity(envio);

            HttpClient httpClient = new DefaultHttpClient();
            model dl = new model();

            HttpPost request = new HttpPost("https://www.googleapis.com/upload/storage/v1/b/sleyva18/o?uploadType=media&name=acta");
            request.addHeader("Content-Type", "image/jpeg");
            request.addHeader("Authorization", "Bearer ya29.c.El_GBSmWiowoXl2KoJh0y9PCpmlYJNbAOsZf7Bsc13KXMRww8n8MSOYFGRlq71IZggBZA9vSVBbp8VH-daR6UDE5iqwWhR4j6J8pqnajH-oTMCo_6eph5ChMo261FjqvuQ");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            System.out.println(EntityUtils.toString(entity));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
