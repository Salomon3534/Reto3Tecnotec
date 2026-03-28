package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

	private static final String FILE_PATH = "log.txt";
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

<<<<<<< Updated upstream
	public void writeLog(String action) {

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {

			String timestamp = LocalDateTime.now().format(formatter);
			writer.write("[" + timestamp + "] ACCIÓN: " + action);
			writer.newLine();

		} catch (IOException e) {

			System.err.println("Error al escribir en el log: " + e.getMessage());
=======
	public void writeLog(String action) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true));
			String timestamp = LocalDateTime.now().format(formatter);
			writer.write("[" + timestamp + "] ACCIÓN: " + action);
			writer.newLine();
>>>>>>> Stashed changes
		}

<<<<<<< Updated upstream
	public String readLog() {

		// string builder para simular un bloc de notas
=======
	public String readLog() throws IOException {
>>>>>>> Stashed changes
		StringBuilder sb = new StringBuilder();

		sb.append("\n--- CONTENIDO DEL FICHERO LOG ---\n");
<<<<<<< Updated upstream

		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {

			String line;
			while ((line = reader.readLine()) != null) {

=======
		BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH)); {
		String line;
		while ((line = reader.readLine()) != null) {
>>>>>>> Stashed changes
				sb.append(line).append("\n");
		}
	}
		return sb.toString();
	}
}