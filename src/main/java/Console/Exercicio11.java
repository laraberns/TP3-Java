package Console;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio11 {
    public static void main(String[] args) {
        try {
            String path = "https://apichallenges.eviltester.com/sim/entities";
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("OPTIONS");
            conn.setConnectTimeout(10_000);
            int statusCode = conn.getResponseCode();
            System.out.println("Código de status HTTP (OPTIONS): " + statusCode);

            // Captura o cabeçalho Allow, que contém os métodos permitidos
            String allowHeader = conn.getHeaderField("Allow");
            if (allowHeader != null) {
                System.out.println("Métodos permitidos (Allow): " + allowHeader);
            } else {
                System.out.println("Cabeçalho 'Allow' não encontrado.");
            }

        } catch (Exception e) {
            System.out.println("Erro durante a requisição:");
            e.printStackTrace();
        }
    }
}
