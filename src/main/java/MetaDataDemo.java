import java.sql.*;

public class MetaDataDemo {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        Connection con = ConnectDemo.getConnection();
        ConnectDemo.prepareBase(con);

        PreparedStatement stat = con.prepareStatement("SELECT * FROM PERSON");

        ResultSetMetaData meta = stat.getMetaData();
        System.out.println("Kolumny");

        for(int i=1; i < meta.getColumnCount();i++){
            System.out.print(meta.getColumnName(i)); //nazwy kolumn w bazie
            System.out.print(meta.getColumnTypeName(i) + " ");
            System.out.print(meta.getColumnType(i) + " ");
            System.out.println(meta.getColumnClassName(i));
        }

        System.out.println("Skrolorwanie Resultsetu");
        System.out.println(stat.getResultSetType());


    }


}
