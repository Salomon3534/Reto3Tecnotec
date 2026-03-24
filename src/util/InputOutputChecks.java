package util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputOutputChecks {

	static Scanner sc = new Scanner(System.in);

	public int getInt(int minInt, int maxInt) {
		int numero = 0;
		while (true) {
			try {
				numero = sc.nextInt();
				if (numero < minInt || numero > maxInt) {
					throw new IllegalArgumentException(
							"El numero no es valido, recuerda que debe de ser entre " + minInt + " y " + maxInt);
				}
				sc.nextLine();
				return numero;
			} catch (IllegalArgumentException e) {
				System.out.println("Error: " + e.getMessage());
				System.out.println("Por favor, escríbelo otra vez:");
			} catch (Exception e) {
				System.out.println("Error: Por favor inserte un numero válido.");
				System.out.println("Por favor, escríbelo otra vez:");
				sc.nextLine();
			}
		}
	}

	public String getString(String message, int maxLength) {
		String texto = "";
		while (true) {
			try {
				System.out.print(message);
				texto = sc.nextLine();

				if (texto.trim().isEmpty()) {
					throw new Exception("La entrada no puede estar vacía.");
				}
				if (!texto.matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ 0-9\\- .:]+,;")) {
					throw new Exception("Lleva caracteres especiales no permitidos.");
				}

				if (texto.length() > maxLength) {
					throw new Exception("Se ha excedido la longitud máxima de: " + maxLength);
				}

				return texto;
			} catch (Exception e) {
				System.out.println("Error: Entrada no válida - " + e.getMessage());
				System.out.println("Por favor, escríbelo otra vez:");
			}
		}
	}

	public LocalDate getDate(String message, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		LocalDate cleanDate = null;
		boolean valida = false;

		do {
			try {
				System.out.print(message + " (" + format + "): ");
				String fechaTexto = sc.nextLine();

				cleanDate = LocalDate.parse(fechaTexto, formatter);
				valida = true;

			} catch (DateTimeParseException e) {
				System.out.println("Error: La fecha no es válida o no existe. Use el formato " + format);
				System.out.println("Por favor, escríbelo otra vez:");
			}
		} while (!valida);
		return cleanDate;
	}

	public LocalTime getHour(String mensaje, String formatoPatron) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatoPatron);
		LocalTime hora = null;
		boolean valida = false;

		do {
			try {
				System.out.print(mensaje + " (" + formatoPatron + "): ");
				String horaTexto = sc.nextLine();

				hora = LocalTime.parse(horaTexto, formatter);
				valida = true;
			} catch (DateTimeParseException e) {
				System.out.println("Error: La hora no es válida o no existe. Use el formato " + formatoPatron);
				System.out.println("Por favor, escríbelo otra vez:");
			}
		} while (!valida);
		return hora;
	}

	public String getEmail(String mensaje, int longitudMax) {
		String texto = "";
		while (true) {
			try {
				System.out.print(mensaje + " (formato: usuario@dominio.com): ");
				texto = sc.nextLine();

				if (!texto.matches("^[\\w.-]+@([\\w-]+\\.)+[a-zA-Z]{2,}$")) {
					throw new Exception("Formato de email no válido (ej: usuario@dominio.com)");
				}

				if (texto.length() > longitudMax) {
					throw new Exception("Se ha excedido la longitud máxima de: " + longitudMax);
				}

				return texto;
			} catch (Exception e) {
				System.out.println("Error: Entrada no válida - " + e.getMessage());
				System.out.println("Por favor, escríbelo otra vez:");
			}
		}
	}

	public String getDni() {
		String dni = "";
		while (true) {
			try {
				System.out.println("Escribe el DNI:");
				dni = sc.nextLine();

				if (!dni.matches("^[0-9]{8}[A-Za-z]$")) {
					throw new Exception("El DNI no cumple el formato.");
				}
				return dni;
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
				System.out.println("Por favor, escríbelo otra vez:");
			}
		}
	}
	
	public String getDni() {
		String dni = "";
		while(true){
			try {
				System.out.println("Escribe el DNI");
				dni = sc.nextLine();
				
				if(dni.matches("^[0-9]{8}[A-Za-z]$")) {
					throw new Exception("El DNI no cumple el formato.");
				}
				return dni;
			}catch(Exception e) {
				System.out.println("El DNI introducido no es correcto.");
			}
		}
		
	}
}