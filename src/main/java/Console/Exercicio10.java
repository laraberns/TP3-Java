package Console;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio10 {
    public static void main(String[] args) {
        try {
            String path = "https://apichallenges.eviltester.com/sim/entities/2";
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            conn.setConnectTimeout(10_000);

            int deleteStatus = conn.getResponseCode();
            System.out.println("Código de status HTTP (DELETE): " + deleteStatus);

            BufferedReader reader;
            if (deleteStatus >= 200 && deleteStatus < 300) {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            String line;
            StringBuilder deleteResponse = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                deleteResponse.append(line).append("\n");
            }
            reader.close();

            System.out.println("Resposta do servidor (DELETE):");
            System.out.println(deleteResponse);

        } catch (Exception e) {
            System.out.println("Erro durante a requisição:");
            e.printStackTrace();
        }
    }
}
