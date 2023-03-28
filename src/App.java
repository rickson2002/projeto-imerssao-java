import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        
        // fazer um conexão HTTP e buscar os top 250 filmes

       String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString()); 
        String body = response.body();
        


        // pegar somente os dados a qual nos interesssam, (Titulo, poster, classificação)

        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        
        // exibir e manipular dados

 
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("\u001b[1mtitulo:\u001b[m " + filme.get("title"));
            System.out.println("\u001b[3mURL de filmes:\u001b[m " + filme.get("image"));
            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            int numeroEstrelas = (int) classificacao;
            for (int e = 1;  e <= numeroEstrelas; e++) {
            System.out.print("⭐");

            
        }

        System.out.println("\n");

            
        }

    }
}
