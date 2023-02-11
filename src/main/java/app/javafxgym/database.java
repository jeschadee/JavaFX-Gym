package app.javafxgym;

import java.sql.Connection;
import java.sql.DriverManager;

public class database {

    public static Connection connectdb() {

        try {

            Class.forName("com.mysql.jdbc.Driver");

            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/bonati_db", "root", "");

            return connect;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
