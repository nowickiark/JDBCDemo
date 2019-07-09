import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

//Menu dodaj, pokaz, znajdz
//Na podstawie danych z klawiatury zrealizuj zadanie

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static java.sql.Connection.*;

public class ConnectDemo {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

        Connection con = getConnection();

        Statement stat = prepareBase(con);




        if(stat.execute("SELECT * FROM PERSON")){
           System.out.println("SELECT SUCCESS");
       }


        con.setTransactionIsolation(TRANSACTION_REPEATABLE_READ);

       int isolation = con.getTransactionIsolation();

       switch (isolation){
           case TRANSACTION_NONE:
               System.out.println("BRAK");
               break;
           case TRANSACTION_READ_UNCOMMITTED:
               System.out.println("Read uncommited");
               break;
           case TRANSACTION_READ_COMMITTED:
               System.out.println("Read Commited");
               break;
           case TRANSACTION_REPEATABLE_READ:
               System.out.println("Repetable read");
               break;
           case TRANSACTION_SERIALIZABLE:
               System.out.println("Serializable");
               break;
       }



        con.close();


    }

    public static Statement prepareBase(Connection con) throws SQLException {
        Statement stat = con.createStatement();

        String createTable = "CREATE TABLE PERSON(id int primary key, " +
                                                " first_name varchar(20), " +
                                                " last_name varchar(20) not null, " +
                                                " email varchar(40))";


        stat.executeUpdate(createTable);

        int count = stat.executeUpdate("INSERT INTO PERSON VALUES (1,'AREK','NOWAK','nowyemail@gma.com')");
        stat.executeUpdate("INSERT INTO PERSON VALUES (2,NULL,'KOWALSKI','jego@gma.com')");
        stat.executeUpdate("INSERT INTO PERSON VALUES (3,'MARK','ZUK','mail@gma.com')");
        stat.executeUpdate("INSERT INTO PERSON VALUES (4,'JHN','WIK','wik@gma.com')");

        if (count == 1) {
            System.out.println("Update Success");
        }else {
            System.out.println("Update Failed");
        }
        return stat;
    }

    public static Connection getConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver").newInstance();
        return DriverManager.getConnection("jdbc:h2:mem:testdb","sa","");
    }

}
