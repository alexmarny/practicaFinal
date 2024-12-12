package view;

import java.util.List;
import java.util.UUID;

import com.coti.tools.Esdia;

import model.ExporterException;
import model.RepositoryException;
import model.Task;

public class InteractiveView extends BaseView {


	@Override
	public void init(String welcomeMsg) {
		System.out.println(welcomeMsg);
		showMenu();
	}

	@Override
	public void showMessage(String message) {
		System.out.println(message);
	}

	@Override
	public void showErrorMessage(String errorMsg) {
		System.out.println("ERROR: " + errorMsg);
	}

	@Override
	public void end(String goodbyeMsg) {
		System.out.println(goodbyeMsg);
		
	}

	public void showMenu(){

		int option;
		do {
			try {
				Thread.sleep(500);
				System.out.print("\033[H\033[2J");
				System.out.flush();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				showErrorMessage("Error: " + e.getMessage());
			}
			                                                                                                                                         
			
		  System.out.println("\n      ███╗   ███╗███████╗███╗   ██╗██╗   ██╗    ██████╗ ██████╗ ██╗███╗   ██╗ ██████╗██╗██████╗  █████╗ ██╗     ");
			System.out.println("      ████╗ ████║██╔════╝████╗  ██║██║   ██║    ██╔══██╗██╔══██╗██║████╗  ██║██╔════╝██║██╔══██╗██╔══██╗██║     ");
			System.out.println("      ██╔████╔██║█████╗  ██╔██╗ ██║██║   ██║    ██████╔╝██████╔╝██║██╔██╗ ██║██║     ██║██████╔╝███████║██║     ");
			System.out.println("      ██║╚██╔╝██║██╔══╝  ██║╚██╗██║██║   ██║    ██╔═══╝ ██╔══██╗██║██║╚██╗██║██║     ██║██╔═══╝ ██╔══██║██║     ");
			System.out.println("      ██║ ╚═╝ ██║███████╗██║ ╚████║╚██████╔╝    ██║     ██║  ██║██║██║ ╚████║╚██████╗██║██║     ██║  ██║███████╗");
			System.out.println("      ╚═╝     ╚═╝╚══════╝╚═╝  ╚═══╝ ╚═════╝     ╚═╝     ╚═╝  ╚═╝╚═╝╚═╝  ╚═══╝ ╚═════╝╚═╝╚═╝     ╚═╝  ╚═╝╚══════╝\n");
        
			System.out.println("┌───────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
			System.out.println("│ __                                                                                                                │");																																							
			System.out.println("│/  )   ____ _    ___ ____    ___ ____ ____ ____ ____                                                               │");
			System.out.println("│ )(    |__| |     |  |__|     |  |__| |__/ |___ |__|                                                               │");
			System.out.println("│(__)() |  | |___  |  |  |     |  |  | |  \\ |___ |  |                                                               │");
			System.out.println("│ ___                                                                                                               │");
			System.out.println("│(__ \\   _  _ ____ ____    _    _ ____ ___ ____ ___  ____ ____    ___  ____    ___ ____ ____ ____ ____ ____         │");
			System.out.println("│ / _/   |  | |___ |__/    |    | [__   |  |__| |  \\ |  | [__     |  \\ |___     |  |__| |__/ |___ |__| [__          │");
			System.out.println("│(____)() \\/  |___ |  \\    |___ | ___]  |  |  | |__/ |__| ___]    |__/ |___     |  |  | |  \\ |___ |  | ___]         │"); 
			System.out.println("│ ___                                                                                                               │");									
			System.out.println("│(__ )   ___  ____ ___ ____ _    _    ____    ___  ____    _  _ _  _ ____    ___ ____ ____ ____ ____                │");
			System.out.println("│ (_ \\   |  \\ |___  |  |__| |    |    |___    |  \\ |___    |  | |\\ | |__|     |  |__| |__/ |___ |__|                │");
			System.out.println("│(___/() |__/ |___  |  |  | |___ |___ |___    |__/ |___    |__| | \\| |  |     |  |  | |  \\ |___ |  |                │");
			System.out.println("│  ___                                                                                                              │");
			System.out.println("│ /. |    _ _  _ ___  ____ ____ ___ ____ ____      /    ____ _  _ ___  ____ ____ ___ ____ ____                      │");   
			System.out.println("│(_  _)   | |\\/| |__] |  | |__/  |  |__| |__/     /     |___  \\/  |__] |  | |__/  |  |__| |__/                      │");
			System.out.println("│  (_)()  | |  | |    |__| |  \\  |  |  | |  \\    /      |___ _/\\_ |    |__| |  \\  |  |  | |  \\                      │");    
			System.out.println("│ ___                                                                                                               │");
			System.out.println("│| __)   ____ ____ _    _ ____                                                                                      │");     
			System.out.println("│|__ \\   [__  |__| |    | |__/ 	                                                                                    │");     
			System.out.println("│(___/() ___] |  | |___ | |  \\	                                                                                    │");
			System.out.println("└───────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘");

			option = Esdia.readInt("Seleccione una opción: ", 1, 5);

			switch (option) {
				case 1:
					
					addTask();
					break;
				case 2:
					subMenuListado();
					break;
				case 3:
					subMenuDetalle();
					break;
				case 4:
					menuImportarExportar();
					break;
				case 5:
					System.out.println("Saliendo del programa...");
					controller.end();
					break;
				default:
					System.out.println("Opción no válida");
			}
		} while (option != 5);
		
	}

	/*

	 * Este menu se encarga de mostrar las tareas que se han añadido
	 * divididas en completadas e incompletas
	 * 
	 */

	private void subMenuListado() {
		
		System.out.println("Listado de tareas:");
		System.out.println("1. Tareas incompletas");
		System.out.println("2. Tareas completadas");
		System.out.println("3. Volver al menú principal");

		int option = Esdia.readInt("Seleccione una opción: ", 1, 3);

		switch (option) {
			case 1:
				
			System.out.println("Tareas incompletas:");

			List<Task> incompletedTasks = controller.getAllTasks();
			incompletedTasks.sort((task1, task2) -> Integer.compare(task2.getPriority(), task1.getPriority()));


			System.out.println(Task.getHeaderTableStringForTask());
			for (Task task : incompletedTasks) {
				if (!task.isCompleted()) {
					System.out.println(task.getAsRowString());
				}
			}

			Esdia.readString("Presione Enter para continuar...");
				
				break;
				
			case 2:

				System.out.println("Todas las tareas (completas o no):");

				List<Task> tasks = controller.getAllTasks();

				tasks.sort((task1, task2) -> { // Ordenamos por completitud, fecha, prioridad
					if (task1.isCompleted() != task2.isCompleted()) {
						return Boolean.compare(task1.isCompleted(), task2.isCompleted());
					} else if (!task1.getDate().equals(task2.getDate())) {
						return task1.getDate().compareTo(task2.getDate());
					} else {
						return Integer.compare(task1.getPriority(), task2.getPriority());
					}
				});

				List<Task> completedTasks = controller.getAllTasks();
				
				System.out.println(Task.getHeaderTableStringForTask());
				for (Task task : completedTasks) {
					
						System.out.println(task.getAsRowString());
					
				}

			Esdia.readString("Presione Enter para continuar...");
				
				break;
			case 3:
				System.out.println("Volviendo al menú principal...");
				break;
			default:
				System.out.println("Opción no válida");
		}


		
	}


	/*
	 *
	 * Este menu se encarga de mostrar en detalle una tarea
	 * y de permitir modificarla o eliminarla
	 * 
	 */

	private void subMenuDetalle() {

		String taskIdString = Esdia.readString_ne("Introduzca el ID de la tarea: ");
		Task task = null;
		UUID taskId = null;
		try {
			taskId = UUID.fromString(taskIdString);
			task = controller.getTaskById(taskId);
		} catch (IllegalArgumentException e) {
			showErrorMessage("ID de tarea no válido.");
			return;
		} catch (Exception e) {
			showErrorMessage("Error al obtener la tarea: " + e.getMessage());
			return;
		}

		if (task == null) {
			showErrorMessage("Tarea no encontrada.");
			return;
		}

		int option;
		do {
			System.out.println("Detalle de la tarea:");
			System.out.println("1. Marcar como " + (task.isCompleted()? "incompleta" : "completa"));
			System.out.println("2. Modificar información de la tarea");
			System.out.println("3. Eliminar la tarea");
			System.out.println("4. Volver al menú principal");
			option = Esdia.readInt("Seleccione una opción: ", 1, 4);

			switch (option) {
				case 1:
					controller.updateTask(task, "completed", String.valueOf(!task.isCompleted()));
					clearTerminal();
					showMessage("El estado de la tarea ha sido actualizado.");
					break;
				case 2:
					modifyTaskMenu(task);
					clearTerminal();
					break;
				case 3:
					controller.deleteTask(taskId);
					clearTerminal();
					showMessage("La tarea ha sido eliminada.");

					break;
				case 4:
					System.out.println("Volviendo al menú principal...");
					break;
				default:
					System.out.println("Opción no válida");
			}
		} while (option != 4 && option != 3);
	}

	/*
	 *
	 * Este método se encarga de modificar los atributos de una tarea
	 * 
	 */

	private void modifyTaskMenu(Task task) {
		System.out.println("Que desea modificar?");
		System.out.println("1. Nombre");
		System.out.println("2. Descripción");
		System.out.println("3. Prioridad");
		System.out.println("4. Duración estimada");
		System.out.println("5. Cancelar");

		int opcion = Esdia.readInt("Seleccione una opción: ", 1, 5);

		switch (opcion) {
			case 1:
				String newName = Esdia.readString_ne("Introduzca el nuevo nombre de la tarea: ");
				controller.updateTask(task, "title", newName);
				
				break;
			case 2:
				String newDescription = Esdia.readString_ne("Introduzca la nueva descripción de la tarea: ");
				controller.updateTask(task, "content", newDescription);
				
				break;
			case 3:
				int newPriority = Esdia.readInt("Introduzca la nueva prioridad de la tarea: ", 1, 5);
				controller.updateTask(task, "priority", String.valueOf(newPriority));
			
				break;
			case 4:
				int newEstimatedDuration = Esdia.readInt("Introduzca la nueva duración estimada de la tarea (en minutos): ");
				controller.updateTask(task, "estimatedDuration", String.valueOf(newEstimatedDuration));
				
				break;
			case 5:
				System.out.println("Cancelando modificación...");
				break;
			default:
				System.out.println("Opción no válida");
		}

		
	}
	


	private void addTask() {
		String taskName = Esdia.readString_ne("Introduzca el nombre de la tarea: ");
		String taskDescription = Esdia.readString_ne("Introduzca la descripción de la tarea: ");
		int taskPriority = Esdia.readInt("Introduzca la prioridad de la tarea: ", 1, 5);
		int estimatedDuration = Esdia.readInt("Introduzca la duración estimada de la tarea (en minutos): ");
		
		try {
			controller.addTask(new Task(taskName, new java.util.Date(), taskDescription, taskPriority, estimatedDuration, false));
			showMessage("TAREA AÑADIDA CORRECTAMENTE");
		} catch (Exception e) {
			showErrorMessage("Error al añadir la tarea: " + e.getMessage());
		}

		Esdia.readString("Presione Enter para continuar...");
	}
	
	private void menuImportarExportar() {
		showMessage("1. Importar tareas");
		showMessage("2. Exportar tareas");
		showMessage("3. Volver al menú principal");

		int option = Esdia.readInt("Seleccione una opción: ", 1, 3);

		switch (option) {
			case 1:
				importarTareas();
				break;
			case 2:
				exportarTareas();
				break;
			case 3:
				System.out.println("Volviendo al menú principal...");
				break;
			default:
				System.out.println("Opción no válida");
		}
	}

	private void importarTareas() {

		System.out.println("1. Importar desde fichero CSV");
		System.out.println("2. Importar desde fichero JSON");
		int opcionTipoArchivo = Esdia.readInt("Introduzca el tipo de archivo a importar:", 1, 3);
		String tipoArchivo = null;

		do{
		
			switch(opcionTipoArchivo){
				case 1:
					tipoArchivo = "csv";
					break;
				case 2:
					tipoArchivo = "json";
					break;

				case 3:
					System.out.println("Volviendo al menú principal...");
					return;
				default:
					System.out.println("Opción no válida");
			}

		} while (opcionTipoArchivo <1 || opcionTipoArchivo > 3);
		
		String nombreFichero = Esdia.readString_ne("Introduzca el nombre del fichero a importar desde home: ");

		try {
			controller.importarTareas(nombreFichero, tipoArchivo);

		} catch(ExporterException e){
			showErrorMessage("Error al importar tareas: " + e.getMessage());
		} catch (RepositoryException e) {
			showErrorMessage("Error al importar tareas: " + e.getMessage());
		}
		Esdia.readString("Presione Enter para continuar...");
	}

	private void exportarTareas() {
		System.out.println("1. Exportar a fichero CSV");
		System.out.println("2. Exportar a fichero JSON");
		System.out.println("3. Volver al menú principal");

		int opcionTipoArchivo = Esdia.readInt("Introduzca el tipo de archivo a exportar (1 para CSV, 2 para JSON): ", 1, 3);
		String tipoArchivo = null;

		do{
		
			switch(opcionTipoArchivo){
				case 1:
					tipoArchivo = "csv";
					break;
				case 2:
					tipoArchivo = "json";
					break;

				case 3:
					System.out.println("Volviendo al menú principal...");
					return;
				default:
					System.out.println("Opción no válida");
			}

		} while (opcionTipoArchivo <1 || opcionTipoArchivo > 3);

		String nombreFichero = Esdia.readString_ne("Introduzca el nombre del fichero a exportar en home: ");

		try {
			controller.exportarTareas(nombreFichero, tipoArchivo);
			showMessage("Tareas exportadas correctamente");
		} catch (Exception e) {
			showErrorMessage("Error al exportar tareas: " + e.getMessage());
		}

		Esdia.readString("Presione Enter para continuar...");
	}


	void clearTerminal(){
		System.out.print("\033[H\033[2J");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			showErrorMessage("Error: " + e.getMessage());}
		System.out.flush();
	}
}
