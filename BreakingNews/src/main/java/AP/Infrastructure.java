package AP;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

import com.google.gson.*;

import AP.News;

public class Infrastructure {

    private final String URL;
    private final String APIKEY;
    private final String JSONRESULT;
    private ArrayList<News> newsList; // TODO: Create the News class
    private ArrayList<String> rawList;

    public Infrastructure(String query, int days, String APIKEY) {
        this.APIKEY = "0f52485a9f7a404693c7c82d221a2239";
        this.URL = "https://newsapi.org/v2/everything?q=" + query + "&from=" + LocalDate.now().minusDays(Math.min(10, days)) + "&sortBy=publishedAt&language=en&apiKey=";
        this.JSONRESULT = getInformation();
    }

    public ArrayList<News> getNewsList() {
        return newsList;
    }

    private String getInformation() {
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL + APIKEY))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return response.body();
            } else {
                throw new IOException("HTTP error code: " + response.statusCode());
            }
        } catch (Exception e) {
            System.out.println("!!Exception : " + e.getMessage());
        }
        return null;
    }

    private void parseInformation() {
    }

    public void displayNewsList() {
        // TODO: Display titles of the news you got from api
        //  and print them in a way that user can choose one
        //  to see the full information of the news
    }

    public String GETS(){
        return this.JSONRESULT;
    }

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        Gson gson = new Gson();

        int days, resCount;
        String query;

        System.out.print("-- News Aggregator --\n\n");
        System.out.print("Enter your search query: ");

        query = cin.nextLine();

        System.out.print("How many days to go back? (max 10): ");
        while(true){
            try{
                days = cin.nextInt();
                break;
            }
            catch(InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                cin.next();
            }
        }

        System.out.print("How many headlines to show? (max 30): ");
        while(true){
            try{
                resCount = cin.nextInt();
                break;
            }
            catch(InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                cin.next();
            }
        }

        Infrastructure infrastructure = new Infrastructure(query, days, "");
        String raw = infrastructure.GETS();
        News news = gson.fromJson(raw, News.class);

        System.out.println("Status: " + news.status);
        System.out.println("Total Results: " + news.totalResults);
    }
}
