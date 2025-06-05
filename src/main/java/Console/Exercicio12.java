package Console;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio12 {
    public static void main(String[] args) {
        try {
            // 1. GET - todos os itens
            String pathItems = "https://apichallenges.eviltester.com/simpleapi/items";
            URL urlItems = new URL(pathItems);
            HttpURLConnection connItems = (HttpURLConnection) urlItems.openConnection();
            connItems.setRequestMethod("GET");
            connItems.setConnectTimeout(10_000);

            int statusCodeItems = connItems.getResponseCode();
            System.out.println("Código de status HTTP (GET - todos os itens): " + statusCodeItems);

            BufferedReader readerItems;
            if (statusCodeItems >= 200 && statusCodeItems < 300) {
                readerItems = new BufferedReader(new InputStreamReader(connItems.getInputStream()));
            } else {
                readerItems = new BufferedReader(new InputStreamReader(connItems.getErrorStream()));
            }

            String lineItems;
            StringBuilder responseItems = new StringBuilder();
            while ((lineItems = readerItems.readLine()) != null) {
                responseItems.append(lineItems).append("\n");
            }
            readerItems.close();

            System.out.println("Resposta do servidor (GET - todos os itens):");
            System.out.println(responseItems);

            // 2. GET - gerar ISBN aleatório
            String pathISBN = "https://apichallenges.eviltester.com/simpleapi/randomisbn";
            URL urlISBN = new URL(pathISBN);
            HttpURLConnection connISBN = (HttpURLConnection) urlISBN.openConnection();
            connISBN.setRequestMethod("GET");
            connISBN.setConnectTimeout(10_000);

            int statusCodeISBN = connISBN.getResponseCode();
            System.out.println("Código de status HTTP (GET - gerar ISBN aleatório): " + statusCodeISBN);

            BufferedReader readerISBN;
            if (statusCodeISBN >= 200 && statusCodeISBN < 300) {
                readerISBN = new BufferedReader(new InputStreamReader(connISBN.getInputStream()));
            } else {
                readerISBN = new BufferedReader(new InputStreamReader(connISBN.getErrorStream()));
            }

            String lineISBN;
            StringBuilder responseISBN = new StringBuilder();
            while ((lineISBN = readerISBN.readLine()) != null) {
                responseISBN.append(lineISBN);
            }
            readerISBN.close();

            String isbnGerado = responseISBN.toString().trim();
            System.out.println("ISBN gerado: " + isbnGerado);

            // 3. POST - criar item com ISBN gerado
            String postUrl = "https://apichallenges.eviltester.com/simpleapi/items";
            URL urlPost = new URL(postUrl);
            HttpURLConnection connPost = (HttpURLConnection) urlPost.openConnection();
            connPost.setRequestMethod("POST");
            connPost.setRequestProperty("Content-Type", "application/json");
            connPost.setDoOutput(true);
            connPost.setConnectTimeout(10_000);

            JSONObject jsonPost = new JSONObject();
            jsonPost.put("type", "book");
            jsonPost.put("isbn13", isbnGerado);
            jsonPost.put("price", 5.99);
            jsonPost.put("numberinstock", 5);

            try (DataOutputStream outPost = new DataOutputStream(connPost.getOutputStream())) {
                outPost.writeBytes(jsonPost.toString());
                outPost.flush();
            }

            int statusPost = connPost.getResponseCode();
            System.out.println("Código de status HTTP (POST - criar item): " + statusPost);

            BufferedReader readerPost;
            if (statusPost >= 200 && statusPost < 300) {
                readerPost = new BufferedReader(new InputStreamReader(connPost.getInputStream()));
            } else {
                readerPost = new BufferedReader(new InputStreamReader(connPost.getErrorStream()));
            }

            String linePost;
            StringBuilder responsePost = new StringBuilder();
            while ((linePost = readerPost.readLine()) != null) {
                responsePost.append(linePost).append("\n");
            }
            readerPost.close();

            System.out.println("Resposta do servidor (POST - criar item):");
            System.out.println(responsePost);

            // 4. PUT - atualizar o item criado
            String putUrl = "https://apichallenges.eviltester.com/simpleapi/items";
            URL urlPut = new URL(putUrl);
            HttpURLConnection connPut = (HttpURLConnection) urlPut.openConnection();
            connPut.setRequestMethod("PUT");
            connPut.setRequestProperty("Content-Type", "application/json");
            connPut.setDoOutput(true);
            connPut.setConnectTimeout(10_000);

            JSONObject jsonUpdate = new JSONObject();
            jsonUpdate.put("isbn13", isbnGerado);  // usar o mesmo ISBN criado
            jsonUpdate.put("price", 9.99);         // atualizando preço
            jsonUpdate.put("numberinstock", 10);  // atualizando estoque

            try (DataOutputStream outPut = new DataOutputStream(connPut.getOutputStream())) {
                outPut.writeBytes(jsonUpdate.toString());
                outPut.flush();
            }

            int statusPut = connPut.getResponseCode();
            System.out.println("Código de status HTTP (PUT - atualizar item): " + statusPut);

            BufferedReader readerPut;
            if (statusPut >= 200 && statusPut < 300) {
                readerPut = new BufferedReader(new InputStreamReader(connPut.getInputStream()));
            } else {
                readerPut = new BufferedReader(new InputStreamReader(connPut.getErrorStream()));
            }

            String linePut;
            StringBuilder responsePut = new StringBuilder();
            while ((linePut = readerPut.readLine()) != null) {
                responsePut.append(linePut).append("\n");
            }
            readerPut.close();

            System.out.println("Resposta do servidor (PUT - atualizar item):");
            System.out.println(responsePut);

            // 5. DELETE - remover item pelo ISBN
            String deleteUrl = "https://apichallenges.eviltester.com/simpleapi/items/" + isbnGerado;
            URL urlDelete = new URL(deleteUrl);
            HttpURLConnection connDelete = (HttpURLConnection) urlDelete.openConnection();
            connDelete.setRequestMethod("DELETE");
            connDelete.setConnectTimeout(10_000);

            int statusDelete = connDelete.getResponseCode();
            System.out.println("Código de status HTTP (DELETE - remover item): " + statusDelete);

            BufferedReader readerDelete;
            if (statusDelete >= 200 && statusDelete < 300) {
                readerDelete = new BufferedReader(new InputStreamReader(connDelete.getInputStream()));
            } else {
                readerDelete = new BufferedReader(new InputStreamReader(connDelete.getErrorStream()));
            }

            String lineDelete;
            StringBuilder responseDelete = new StringBuilder();
            while ((lineDelete = readerDelete.readLine()) != null) {
                responseDelete.append(lineDelete).append("\n");
            }
            readerDelete.close();

            System.out.println("Resposta do servidor (DELETE - remover item):");
            System.out.println(responseDelete);

        } catch (Exception e) {
            System.out.println("Erro durante a requisição:");
            e.printStackTrace();
        }
    }
}
