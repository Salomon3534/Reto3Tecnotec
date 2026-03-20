package view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import db.DatabaseConnector;
import model.*;

import util.*;

public class View_SONIA_DELETE {
	
	// Atributos
	private GestionBiblioteca gestionBiblioteca;
	private Scanner scanner = new Scanner(System.in);

	// Constructor
	public View_SONIA_DELETE(GestionBiblioteca gestionBiblioteca) {
		this.gestionBiblioteca = gestionBiblioteca;
	}

	// Método para mostrar el menú y gestionar las opciones
	public void menu() throws SQLException {
		int opcion;

		do {
			System.out.println("\n--- MENÚ BIBLIOTECA ---");
			System.out.println("1. Mostrar todos los libros");
			System.out.println("2. Mostrar todos los miembros");
			System.out.println("3. Mostrar préstamos activos (sin devolver)");
			System.out.println("4. Mostrar préstamos de un miembro");
			System.out.println("5. Añadir nuevo libro");
			System.out.println("6. Cambiar género de un libro");
			System.out.println("7. Eliminar un libro por ID");
			System.out.println("8. Salir");
			System.out.print("Selecciona una opción: ");

			opcion = Integer.parseInt(scanner.nextLine());

			switch (opcion) {
				case 1:
					ArrayList listaLibros = gestionBiblioteca.mostrarTodosLosLibros();
					System.out.println("\n--- LISTA DE LIBROS ---");
					for (int i = 0; i<listaLibros.size();i++) {
						Libro libro = (Libro) listaLibros.get(i);
						System.out.println(
							"ID: " + libro.getId() +
							" | Título: " + libro.getTitulo() +
							" | Autor: " + libro.getAutor() +
							" | Año: " + libro.getAnioPublicacion() +
							" | Género: " + libro.getGenero()
						);
					}

					break;
				case 2:
					ArrayList listaMiembros = gestionBiblioteca.mostrarTodosLosMiembros();
					System.out.println("\n--- LISTA DE MIEMBROS ---");
					for (int i = 0; i<listaMiembros.size();i++) {
						Miembro miembro = (Miembro) listaMiembros.get(i);
						System.out.println(
							"ID: " + miembro.getId() +
							" | Nombre: " + miembro.getNombre() +
							" | fecha_registro: " + miembro.getFechaRegistro() +
							" | Email: " + miembro.getEmail()
						);
					}
					break;
				case 3:
					ArrayList<PrestamoActivo> listaPrestamosActivos = gestionBiblioteca.mostrarPrestamosActivos();
					if(listaPrestamosActivos.isEmpty()) {
						System.out.println("No hay prestamos activos");
					}else {
						System.out.println("\n--- PRÉSTAMOS ACTIVOS (SIN DEVOLVER) ---");
						// falta codigo
						for (int i = 0; i < listaPrestamosActivos.size(); i++) {
							PrestamoActivo p = listaPrestamosActivos.get(i);
							System.out.println(
								"ID Préstamo: " + p.getId() +
								" | Libro: " + p.getTitulo() +
								" | Miembro: " + p.getNombre() +
								" | Fecha préstamo: " + p.getFecha_prestamo()
							);
						}
					}
					
					break;
				case 4:
					System.out.print("Introduce el ID del miembro: ");
					int idMiembro = Integer.parseInt(scanner.nextLine());
					
					ArrayList<PrestamoActivo> listaPrestamoActivo = gestionBiblioteca.mostrarPrestamosDeUnMiembro(idMiembro);
					if(listaPrestamoActivo.isEmpty()) {
						System.out.println("Este miembro no ha realizado ningún prestamos");
					}else {
						System.out.println("\n--- PRÉSTAMOS DEL MIEMBRO ID " + idMiembro + " ---");
						for(int i=0; i<listaPrestamoActivo.size();i++) {
							PrestamoActivo prestamoActivo = listaPrestamoActivo.get(i);							
							System.out.println(
									"Préstamo ID: " + prestamoActivo.getId() +
									" | Libro: " + prestamoActivo.getTitulo() +
									" | Fecha préstamo: " + prestamoActivo.getFecha_prestamo() +
									" | Fecha devolución: " + prestamoActivo.getFecha_devolucion()
								);
						}
					}
					break;
				case 5:
					// Añadir nuevo libro
					System.out.println("\n--- AÑADIR NUEVO LIBRO ---");
					System.out.print("Título: ");
					String titulo = scanner.nextLine();
					System.out.print("Autor: ");
					String autor = scanner.nextLine();
					System.out.print("Año publicación (número): ");
					int anio = Integer.parseInt(scanner.nextLine());
					System.out.print("Género: ");
					String genero = scanner.nextLine();
					
					Libro nuevoLibro = new Libro();
					nuevoLibro.setTitulo(titulo);
					nuevoLibro.setAutor(autor);
					nuevoLibro.setAnioPublicacion(anio);
					nuevoLibro.setGenero(genero);
					
					if(gestionBiblioteca.insertarLibro(nuevoLibro)) {
						System.out.println("Libro añadido correctamente.");
					}else {
						System.out.println("Error al guardar el libro");
					}
					
					break;
				case 6:
					// Cambiar género de un libro
					System.out.print("Introduce el ID del libro cuyo género quieres cambiar: ");
					int idLibroCambiarGenero = Integer.parseInt(scanner.nextLine());
					System.out.print("Introduce el nuevo género: ");
					String nuevoGenero = scanner.nextLine();
					if (gestionBiblioteca.cambiarGeneroLibro(idLibroCambiarGenero, nuevoGenero)) {
						System.out.println("Género cambiado correctamente.");
					} else {
						System.out.println("No se pudo cambiar el género (ID no encontrado o error)." );
					}
					break;
				case 7:
					// Eliminar libro por ID
					System.out.print("Introduce el ID del libro a eliminar: ");
					int idLibroEliminar = Integer.parseInt(scanner.nextLine());
					if (gestionBiblioteca.eliminarLibro(idLibroEliminar)) {
						System.out.println("Libro eliminado correctamente.");
					} else {
						System.out.println("No se encontró ningún libro con ese ID o no pudo eliminarse.");
					}
					break;
				case 8:
					System.out.println("Saliendo del programa...");
					break;
				default:
					System.out.println("Opción inválida.");
			}

		} while (opcion != 8);

		scanner.close();
	}
}