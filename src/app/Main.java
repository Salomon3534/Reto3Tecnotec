package app;

import java.sql.SQLException;

import util.TotalManagerEuskalEncounter;
import view.ViewEuskalEncounter;

public class Main {

    public static void main(String[] args) throws SQLException  {
	       
        ViewEuskalEncounter vee = new ViewEuskalEncounter(new TotalManagerEuskalEncounter());
        db.DatabaseConnector.conectar();
     
        vee.mainMenu();
        
        db.DatabaseConnector.cerrarConexion();
          
    }

}