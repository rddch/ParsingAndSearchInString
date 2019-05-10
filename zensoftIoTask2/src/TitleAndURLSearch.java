import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Scanner;

public class TitleAndURLSearch {
    public static void main(String[] args) throws Exception {

        System.out.println("Найти: ");
        // Блок ввода
        Scanner in = new Scanner(System.in);
        String search = in.nextLine();
        in.close();

        String google = "https://www.google.com/search?q=";
        String charset = "UTF-8";

        // Идентификатор который сообщается посещаймому сайту
        String userAgent = "ExampleBot 1.0 (+http://example.com/bot)";

        // Выборка всех элементв из блока
        Elements links = Jsoup.connect(google + URLEncoder.encode(search, charset)).userAgent(userAgent).get().select(".g>.r>a");

        // Цикл для пробора всех элементов
        for (Element link : links) {
            // Получаем заголовок страницы
            String title = link.text();
            // Получаем URL в виде http://www.google.com/url?q=<url>&sa=U&ei=<someKey>
            String url = link.absUrl("href");
            url = URLDecoder.decode(url.substring(url.indexOf('=') + 1, url.indexOf('&')), "UTF-8");

            // Проверка, начинается ли строка с указанного префикса
            if (!url.startsWith("http")) {
                continue;
            }

            // Вывод в консоль
            System.out.println("Title: " + title);
            System.out.println("URL: " + url);
            break;
        }
    }
}
