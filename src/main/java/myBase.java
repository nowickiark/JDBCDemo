import javax.swing.plaf.nimbus.State;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.Scanner;

public class myBase {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {


        Class.forName("org.h2.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:h2:mem:testdb","sa","");

        Statement statement = con.createStatement();

        populateTable(statement);

        boolean koniec = false;

        while (koniec){
            int action = showMenu();

            switch (action){
                case 1://Dodaj
                    break;
                case 2: //Pokaz
                    Statement stat = con.createStatement();
                    ResultSet resultSet = stat.executeQuery("SELECT * FROM SHOPINGCART");

                    while (resultSet.next()){
                        String id = resultSet.getString("id");
                        String name = resultSet.getString("name");
                        int quantity = resultSet.getInt("quantity");
                        double price = resultSet.getDouble("price");

                        System.out.println("Nazwa" + name + "quantity :" + quantity + "with price" + price);
                    }


                    break;
                case 3: //Znajdz
                    break;
                case 4: //Koniec
                    koniec = true;
                    break;
            }

        }




    }

    public static void populateTable(Statement statement) throws SQLException {
        String tableCreation = "CREATE TABLE SHOPINGCART(id int primary key, " +
                                                        "product_name varchar(30), " +
                                                        "prduct_quantity int ," +
                                                        "price double)";

        statement.executeUpdate(tableCreation);

        statement.executeUpdate("INSERT INTO SHOPINGCART VALUES (1,'Apples',3, 0.55)");
        statement.executeUpdate("INSERT INTO SHOPINGCART VALUES (2,'Keyboard',1, 50)");
        statement.executeUpdate("INSERT INTO SHOPINGCART VALUES (3,'Truck',2,100000)");
        statement.executeUpdate("INSERT INTO SHOPINGCART VALUES (4,'Computer',3,1500)");
    }

    public static int showMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Menu");
        System.out.println("Dodaj wartość do bazy            : 1");
        System.out.println("Pokaz wszystkie wpisy w bazie    : 2");
        System.out.println("Znajdz konkretny wpis po imieniu : 3");
        System.out.println("Koniec                           : 4");
        System.out.println("Wybierz zadanie - ");

        int akcja = scanner.nextInt();

        return akcja;
    }

}
