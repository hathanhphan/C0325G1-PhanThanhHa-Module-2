package ss19_string_and_regex.bai_tap.crawl_news;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsCrawler {
    @SuppressWarnings({"CallToPrintStackTrace", "ResultOfMethodCallIgnored"})
    public static void main(String[] args) {
        try {
            URL url = new URL("https://dantri.com.vn/the-gioi.htm");
            // open the stream and put it into BufferedReader
            Scanner scanner = new Scanner(new InputStreamReader(url.openStream()));
            scanner.useDelimiter("\\Z");
            String content = scanner.next();
            // close scanner
            scanner.close();
            // remove all new line
            content = content.replaceAll("\\n+", "");
            // regex
            Pattern p = Pattern.compile("article-content\">(.*?)</div></div>");
            Matcher articleContents = p.matcher(content);
            Matcher detailContent;
            while (articleContents.find()) {
                String articleContent = articleContents.group(1).concat("</div>");
                articleContent = articleContent.replaceAll("<a[^>]*>(.*?)</a>", "$1");
                articleContent = articleContent.replaceAll("<span[^>]*>(.*?)</span>", "");
                p = Pattern.compile("article-title\">(.*?)</h3><div class=\"article-excerpt\">(.*?)</div>");
                detailContent = p.matcher(articleContent);
                if (detailContent.find()) {
                    System.out.println("Title: " + detailContent.group(1));
                    System.out.println("Excerpt: " + detailContent.group(2));
                    System.out.println();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
