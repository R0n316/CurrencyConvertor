import java.sql.*;

public class Converter {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println(convert(100,"RUB","USD"));
    }
    public static String convert(int value, String from, String to) throws SQLException, ClassNotFoundException {
        if(value==0){
            return String.valueOf(0);
        }
        float toRate = 0;
        float fromRate = 0;
        from = from.toUpperCase();
        to = to.toUpperCase();
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb","postgres","1");
        Statement statement = connection.createStatement();
        ResultSet fromResult = statement.executeQuery("SELECT rate FROM currency_info WHERE currency_name = '"+from+"'");
        while (fromResult.next()){
            fromRate = fromResult.getFloat("rate");
        }
        if(fromRate == 0){
            return "Incorrect data";
        }
        ResultSet toResult = statement.executeQuery("SELECT rate FROM currency_info WHERE currency_name = '"+to+"'");
        while(toResult.next()){
            toRate = toResult.getFloat("rate");
        }
        if(toRate == 0){
            return "Incorrect data";
        }
        return String.valueOf(value*fromRate/toRate);
    }
}
