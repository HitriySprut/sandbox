import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.hibernate.annotations.Table;

import java.io.IOException;
import java.sql.*;


public class Solution

{
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/test";

    static final String USER = "root";
    static final String PASS = "12345";

    public static final String HOME_HTML = "http://zakupki.gov.ru/epz/main/public/home.html";
    public static final String HREF = "href";

    private static Elements getElementsFromHomepage() throws IOException {
        Document doc = Jsoup.connect(HOME_HTML).get();
        Elements elms = doc.getElementsByAttribute(HREF);
        return elms;

    }

    private static String getOrderLinkFromCatalogue(String catLink) throws IOException {
        Document doc = Jsoup.connect("http://zakupki.gov.ru" + catLink).get();
        Elements elms = doc.getElementsByClass("descriptTenderTd");
        Element el = elms.first();
        return el.getElementsByAttribute(HREF).first().attr(HREF);

    }

    private static void saveToDB(String name, String value) throws SQLException, ClassNotFoundException {
        java.sql.Connection conn = null;
        Statement stmt = null;
        Class.forName("com.mysql.jdbc.Driver");

        //Open a connection
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);

        //Execute a query
        System.out.println("Creating statement...");
        stmt = conn.createStatement();
        String sql;
        sql = "insert into property values ('" + name + "','" + value + "')";
        stmt.execute(sql);

        ResultSet rs = stmt.executeQuery("select * from property");
        while (rs.next()) {
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

        return;
    }

    private static void loadFromDB(String name, String value) throws SQLException, ClassNotFoundException {
        java.sql.Connection conn = null;
        Statement stmt = null;
        Class.forName("com.mysql.jdbc.Driver");


        //Open a connection
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from property");
        while (rs.next()) {
            //Retrieve by column name
            String DBname = rs.getString("name");
            String DBvalue = rs.getString("value");

            //Display values
            System.out.println("DBname: " + DBname);
            System.out.println("DBvalue: " + DBvalue);
        }
        rs.close();
        stmt.close();
        conn.close();


    }

    private static Elements getOrderFromCatalogue(String orderLink) throws IOException {
        Document doc = Jsoup.connect("http://zakupki.gov.ru" + orderLink).get();

        Element el = doc.getElementsByClass("ktruNsiDescr").first();

        Elements elms = el.getElementsByTag("td");
        return elms;

    }

    private static String getOrderTopic(Elements elms) {
        return elms.first().text();
    }

    private static String getOrderDesc(Elements elms) {
        return elms.last().text();
    }

    private static String getCatLink(Elements elms) {
        String catLink = null;
        for (Element el : elms) {
            if (el.text().contains("Каталог")) {
                catLink = el.attr(HREF);

                System.out.println(catLink);

            }
        }
        return catLink;
    }


    public static void parseGosuslugi() throws Exception {

        // переход по куче ссылок, я не знаю как это сделать по-человечески

        String catLink = getCatLink(getElementsFromHomepage());

        String orderLink = getOrderLinkFromCatalogue(catLink);
        Elements elms = getOrderFromCatalogue(orderLink);

        String orderTopic = getOrderTopic(elms);
        String orderDesc = getOrderDesc(elms);

        saveToDB(orderTopic, orderDesc);
        loadFromDB(orderTopic, orderDesc);


    }

}
