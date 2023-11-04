import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.*;

public class DBManagement {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        update();
    }
    public static void update() throws ClassNotFoundException, SQLException, IOException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb","postgres","1");
        Statement statement = connection.createStatement();
        Document doc = Jsoup.connect("https://www.cbr.ru/currency_base/daily/").get();
        Elements rows = doc.select("tr");
        statement.executeUpdate("TRUNCATE TABLE currency_info");
        statement.executeUpdate("INSERT INTO currency_info(currency_name,rate) VALUES ('RUB',1)");
        for(Element row: rows){
            Elements cells = row.select("td");
            if(cells.size()==5){
                String str = cells.get(4).text();
                str = str.replace(",",".");
                int val = statement.executeUpdate("INSERT INTO currency_info(currency_name,rate) \n" +
                        "VALUES ('"+cells.get(1).text()+"',"+str+")");
                System.out.println("Added "+val+" rows");
            }
        }
        statement.close();
    }
}
