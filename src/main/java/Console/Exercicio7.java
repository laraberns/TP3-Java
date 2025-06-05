package Console;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class Exercicio7 {
    public static void main(String[] args) {
        try {
            String path = "https://apichallenges.eviltester.com/sim/entities/10";
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            conn.setConnectTimeout(10_000);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", "atualizado");

            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes(jsonObject.toString());
            out.flush();
            out.close();

            int statusCode = conn.getResponseCode();
            System.out.println("Código de status HTTP: " + statusCode);

            BufferedReader reader;
            if (statusCode >= 200 && statusCode < 300) {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line).append("\n");
            }
            reader.close();

            System.out.println("Resposta do servidor (POST):");
            System.out.println(response);

            // GET
            URL getUrl = new URL(path);
            HttpURLConnection getConn = (HttpURLConnection) getUrl.openConnection();
            getConn.setRequestMethod("GET");
            getConn.setRequestProperty("Content-Type", "application/json");
            getConn.setConnectTimeout(10_000);

            int getStatus = getConn.getResponseCode();
            System.out.println("Código de status HTTP (GET): " + getStatus);

            BufferedReader getReader;
            if (getStatus >= 200 && getStatus < 300) {
                getReader = new BufferedReader(new InputStreamReader(getConn.getInputStream()));
            } else {
                getReader = new BufferedReader(new InputStreamReader(getConn.getErrorStream()));
            }

            StringBuilder getResponse = new StringBuilder();
            while ((line = getReader.readLine()) != null) {
                getResponse.append(line).append("\n");
            }
            getReader.close();

            System.out.println("Resposta do servidor (GET):");
            System.out.println(getResponse);

        } catch (Exception e) {
            System.out.println("Erro durante a requisição:");
            e.printStackTrace();
        }
    }
}
