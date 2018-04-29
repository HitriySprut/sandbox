import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Solution
{
    public static void main(String args[]) throws Exception {

        Document doc = Jsoup.connect("http://zakupki.gov.ru/epz/main/public/home.html").get();
        Elements elms = doc.getElementsByAttribute("href");
        String catLink = null;
        for ( Element el : elms)
        {
            if (el.text().contains("Каталог")){
                catLink=el.attr("href");

                System.out.println(catLink);

            }
        }
        doc = Jsoup.connect("http://zakupki.gov.ru"+catLink).get();
        //System.out.println(doc);
        elms = doc.getElementsByClass("descriptTenderTd");
        Element el = elms.first();
        String orderLink =el.getElementsByAttribute("href").first().attr("href");

        doc=Jsoup.connect("http://zakupki.gov.ru"+orderLink).get();
        //System.out.println(doc);
        el = doc.getElementsByClass("ktruNsiDescr").first();
        //System.out.println("\n"+el);
        elms = el.getElementsByTag("td");
        String name = elms.first().text();
        String value = elms.last().text();

        System.out.println(name+":\n"+value);

    }
}
