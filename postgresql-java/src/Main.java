import database.DbFunctions;

import java.sql.Connection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DbFunctions db=new DbFunctions();
        Connection conn= db.connecToDb("java","postgres","110102ee");
        db.createTable(conn,"employee");
        db.insertRow(conn,"ernestogo","caucaia");
        db.searchByName(conn,"ernestogo");
    }


}
