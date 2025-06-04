package Console;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio2 {
    public static void main(String[] args){
       for (int id = 1; id <= 8; id++) {
                try {
                    String path = "https://apichallenges.eviltester.com/sim/entities/" + id;
                    URL url = new URL(path);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setDoOutput(true);
                    conn.setConnectTimeout(10_000);

                    int status = conn.getResponseCode();
                    System.out.println("Código de status HTTP: " + status);

                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(conn.getInputStream()));
                    StringBuilder response = new StringBuilder();

                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    System.out.println("Resposta do servidor para id " + id + ":");
                    System.out.println(response);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    }
}

// Comparação das respostas do exercício 1 e do exercício 2
// A resposta do exercício 1 contém uma lista de 10 entidades com IDs fixos, porém a ordem é aleatória.
// As respostas do exercício 2 confirmam que as entidades são acessíveis individualmente, e os dados são consistentes com a lista geral.
