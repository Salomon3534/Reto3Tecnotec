package app;

import java.sql.SQLException;

import db.DatabaseConnector;
import util.TotalManagerEuskalEncounter;
import view.View;
import view.ViewEuskalEncounter;

public class Main {

    public static void main(String[] args) throws SQLException  {
	       
        ViewEuskalEncounter vee = new ViewEuskalEncounter(new TotalManagerEuskalEncounter());
        db.DatabaseConnector.conectar();
     
        
            try {
				vee.();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
     
        
            db.DatabaseConnector.cerrarConexion();
          
    }

}