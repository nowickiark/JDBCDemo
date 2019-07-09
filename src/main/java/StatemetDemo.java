import java.sql.*;
import java.util.Scanner;

public class StatemetDemo {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        Connection con = ConnectDemo.getConnection();
        ConnectDemo.prepareBase(con);

        Scanner scanner = new Scanner(System.in);


        Statement stat = con.createStatement();

        System.out.println("Pokaż wszystkie osoby");

        ResultSet all = stat.executeQuery("SELECT * FROM PERSON");
        results(all);

        System.out.println("Wpisz id osoby");
        String id = scanner.next();


        ResultSet set = stat.executeQuery("SELECT * FROM PERSON WHERE id =" + id);

        //next przeskakuje do następnej pozycji, jeżeli nie da się przeskoczyć to wyżuci fałsz
        results(set);


        System.out.println("Podaj imie do sprawdzenia");

        String name = scanner.next();

        set = stat.executeQuery("SELECT * FROM PERSON WHERE first_name LIKE '" + name + "'");

        results(set);

        //Przekazywanie parametrów na pomoca preparedStatement

        System.out.println("Przekazywanie parametrów zapytań w preparedStatement");

        PreparedStatement findByFirstName = con.prepareStatement("SELECT * FROM PERSON WHERE first_name = ?");
        findByFirstName.setString(1,name);
         set = findByFirstName.executeQuery();

         results(set);

        stat.close();
        con.close();
        scanner.close();

        //ResultSet - zbiór danych który możemy iterować
        //


    }

    public static void results(ResultSet set) throws SQLException {
        while (set.next()){
            String firstName = set.getString("first_name");
            String lastName = set.getString("last_name");
            String emial = set.getString("email");

            System.out.println("First name : " + firstName + ", last name : " + lastName + ", email :" + emial);
        }
    }

}
