import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Solution
{
    public static void main(String args[]) throws Exception {

        Document doc = Jsoup.connect("https://jsoup.org/download").get();
        Elements elms = doc.getElementsByTag("h2");
        for ( Element text : elms)
        {
            System.out.println(text.text());
        }
    }
}