package util;

import java.sql.*;
import model.Encounter;
import db.DatabaseConnector;

public class ManagerEncounters {

	public String createEncounter(String location, Date dateStart, Date dateEnd) throws SQLException {
		String sql = "INSERT INTO encuentro (FECHA_INICIO, FECHA_FIN, UBICACION) VALUES (?, ?, ?)";
		try (Connection conn = DatabaseConnector.getConexion();

				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setDate(1, dateStart);
			pstmt.setDate(2, dateEnd);
			pstmt.setString(3, location);
			pstmt.executeUpdate();
			return "Encuentro creado correctamente.";
		}
	}

	public String listEncounters() {
		StringBuilder sb = new StringBuilder();
		String sql = "SELECT CODIGO, FECHA_INICIO, FECHA_FIN, UBICACION FROM encuentro";

		try (Connection conn = DatabaseConnector.getConexion();

				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {

				int codigo = rs.getInt("CODIGO");
				Date inicio = rs.getDate("FECHA_INICIO");
				Date fin = rs.getDate("FECHA_FIN");
				String ubicacion = rs.getString("UBICACION");

				Encounter e = new Encounter(codigo, ubicacion, inicio, fin);

				sb.append(e.toString()).append("\n");
			}
		} catch (SQLException e) {
			return "Error al listar encuentros: " + e.getMessage();
		}

		return sb.isEmpty() ? "No hay encuentros registrados." : sb.toString();
	}

	public String updateEncounter(Encounter e) throws SQLException {
		String sql = "UPDATE encuentro SET FECHA_INICIO = ?, FECHA_FIN = ?, UBICACION = ? WHERE CODIGO = ?";
		try (Connection conn = DatabaseConnector.getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setDate(1, e.getDateStart());
			pstmt.setDate(2, e.getDateEnd());
			pstmt.setString(3, e.getLocation());
			pstmt.setInt(4, e.getCode());

			int rows = pstmt.executeUpdate();
			return rows > 0 ? "Encuentro actualizado." : "No se encontró el encuentro.";
		}
	}

	public String deleteEncounter(int code) throws SQLException {
		String sql = "DELETE FROM encuentro WHERE CODIGO = ?";
		try (Connection conn = DatabaseConnector.getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, code);
			int rows = pstmt.executeUpdate();
			return rows > 0 ? "Encuentro eliminado." : "No se encontró el código.";
		}
	}
}