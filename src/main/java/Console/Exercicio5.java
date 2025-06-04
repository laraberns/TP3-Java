package Console;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class Exercicio5 {
    public static void main(String[] args) {
        try {
            String path = "https://apichallenges.eviltester.com/sim/entities";
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            conn.setConnectTimeout(10_000);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", "aluno");

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

            System.out.println("Resposta do servidor:");
            System.out.println(response);

        } catch (Exception e) {
            System.out.println("Erro durante a requisição:");
            e.printStackTrace();
        }
    }
}
