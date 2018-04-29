import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.sql.*;


public class Solution

{
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/test";

    static final String USER = "root";
    static final String PASS = "12345";

    public static void main(String args[]) throws Exception {

        // переход по куче ссылок, я не знаю как это сделать по-человечески
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
        elms = doc.getElementsByClass("descriptTenderTd");
        Element el = elms.first();
        String orderLink =el.getElementsByAttribute("href").first().attr("href");

        doc=Jsoup.connect("http://zakupki.gov.ru"+orderLink).get();

        el = doc.getElementsByClass("ktruNsiDescr").first();

        elms = el.getElementsByTag("td");
        // со страницы тендера забираем 'описание'
        String name = elms.first().text();
        String value = elms.last().text();

        //дальше база

        java.sql.Connection conn = null;
        Statement stmt = null;
        Class.forName("com.mysql.jdbc.Driver");

        //Open a connection
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL,USER,PASS);



        //Execute a query
        System.out.println("Creating statement...");
        stmt = conn.createStatement();
        String sql;
        sql = "insert into property values ('"+name+"','"+value+"')";
       stmt.execute(sql);
       ResultSet rs = stmt.executeQuery("select * from property");
        while(rs.next()){
            //Retrieve by column name
            String DBname = rs.getString("name");
            String DBvalue = rs.getString("value");

            //Display values
            System.out.println("DBname: " + name);
            System.out.println("DBvalue: " + value);
        }
        rs.close();
        stmt.close();
        conn.close();

    }
}
