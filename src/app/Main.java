package app;

import java.sql.SQLException;
import db.DatabaseConnector;
import util.TotalManagerEuskalEncounter;
import view.ViewEuskalEncounter;

public class Main {

    public static void main(String[] args) {
        try {

            DatabaseConnector.getConexion(); 
            

            TotalManagerEuskalEncounter managerTotal = new TotalManagerEuskalEncounter();
            

            ViewEuskalEncounter view = new ViewEuskalEncounter(managerTotal);
            
            //loop principal del proyecto
            view.mainMenu();
            
            //fin y cierre
            DatabaseConnector.cerrarConexion();
            
        } catch (SQLException e) {

            System.err.println("error al iniciar la aplicacion: " + e.getMessage());
        } catch (Exception e) {

            System.err.println("error inesperado: " + e.getMessage());
        }
    }
}