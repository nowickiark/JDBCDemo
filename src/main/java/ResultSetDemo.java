import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.sql.*;

public class ResultSetDemo {


    public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        Connection con = ConnectDemo.getConnection();
        ConnectDemo.prepareBase(con);

        PreparedStatement stat = con.prepareStatement(
                "SELECT * FROM PERSON",
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        );

        ResultSet set = stat.executeQuery();
        StatemetDemo.results(set);

        set.beforeFirst(); //Zwracane "licznika" listy na początek
        StatemetDemo.results(set);

        set.beforeFirst();
        System.out.println("Modyfikacja wiersza");
        while (set.next()){
            String name = set.getString("first_name");
            if("JAK".equals(name)){
                set.updateString("email","nowyemailJakiego@gmail.com");
                set.updateRow(); // konieczne żeby rekord został zaktualizoany w bazie
            }
        }

        set.beforeFirst();
        System.out.println("Baza po Updacie");
        StatemetDemo.results(set);



        //Usuwanie
        System.out.println("Usuwanie wiersza");

        set.beforeFirst();

        while (set.next()){
            String name = set.getString("first_name");

            if ("JAK".equals(name)){
                set.deleteRow();  //uwaga działa od razu na tabele na w bazie danych i nie widać w aktualnej liście
            }
        }

        System.out.println();

        System.out.println("############### Baza po usunięciu JAKIEGO");

        set = stat.executeQuery(); // konieczne odświeżene zapytania z bazy żeby widzieć nowe lub usunięte osoby

        set.beforeFirst();
        StatemetDemo.results(set);


        set.beforeFirst();
        System.out.println("Dodawanie nowej osoby"); //Rzeby wstawić dane do bazy kursor musi wskazywać na jakieś miejsce w bazie, dlatego konieczne jest przeiterowanie np. na ostatnie miejsce
        while (set.next()){
            if(set.isLast()){
                set.moveToInsertRow(); // przechodzi kursorem na puste miejsce
                set.updateString("first_name","Mikołaj");
                set.updateString("last_name", "Swiety");
                set.updateString("email", "paczki@laponia.com");
                set.updateInt("id",5);
                set.insertRow(); // wstawia i aktualizuje do bazy już wypełnione miejsce
            }
        }

        set = stat.executeQuery(); // konieczne odświeżene zapytania z bazy żeby widzieć nowe lub usunięte osoby

        System.out.println();

        System.out.println("############## Baza po dodaniu Mikołaja");
        set.beforeFirst();
        StatemetDemo.results(set);



    }
}
