package view;

import com.coti.tools.Esdia;

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
				Thread.sleep(1500);
				System.out.print("\033[H\033[2J");
				System.out.flush();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				showErrorMessage("Error: " + e.getMessage());
			}
			                                                                                                                                         
			
			System.out.println("\n	███╗   ███╗███████╗███╗   ██╗██╗   ██╗    ██████╗ ██████╗ ██╗███╗   ██╗ ██████╗██╗██████╗  █████╗ ██╗     ");
			System.out.println("	████╗ ████║██╔════╝████╗  ██║██║   ██║    ██╔══██╗██╔══██╗██║████╗  ██║██╔════╝██║██╔══██╗██╔══██╗██║     ");
			System.out.println("	██╔████╔██║█████╗  ██╔██╗ ██║██║   ██║    ██████╔╝██████╔╝██║██╔██╗ ██║██║     ██║██████╔╝███████║██║     ");
			System.out.println("	██║╚██╔╝██║██╔══╝  ██║╚██╗██║██║   ██║    ██╔═══╝ ██╔══██╗██║██║╚██╗██║██║     ██║██╔═══╝ ██╔══██║██║     ");
			System.out.println("	██║ ╚═╝ ██║███████╗██║ ╚████║╚██████╔╝    ██║     ██║  ██║██║██║ ╚████║╚██████╗██║██║     ██║  ██║███████╗");
			System.out.println("	╚═╝     ╚═╝╚══════╝╚═╝  ╚═══╝ ╚═════╝     ╚═╝     ╚═╝  ╚═╝╚═╝╚═╝  ╚═══╝ ╚═════╝╚═╝╚═╝     ╚═╝  ╚═╝╚══════╝\n");
        
			System.out.println(" __");																																							
			System.out.println("/  )   ____ _    ___ ____    ___ ____ ____ ____ ____ ");
			System.out.println(" )(    |__| |     |  |__|     |  |__| |__/ |___ |__| ");
			System.out.println("(__)() |  | |___  |  |  |     |  |  | |  \\ |___ |  | ");
														
			System.out.println(" ___");
			System.out.println("(__ \\   _  _ ____ ____    _    _ ____ ___ ____ ___  ____ ____    ___  ____    ___ ____ ____ ____ ____ ____ ");
			System.out.println(" / _/   |  | |___ |__/    |    | [__   |  |__| |  \\ |  | [__     |  \\ |___     |  |__| |__/ |___ |__| [__  ");
			System.out.println("(____)() \\/  |___ |  \\    |___ | ___]  |  |  | |__/ |__| ___]    |__/ |___     |  |  | |  \\ |___ |  | ___] "); 

			
			System.out.println(" ___ ");
			System.out.println("(__ )   ___  ____ ___ ____ _    _    ____    ___  ____    _  _ _  _ ____    ___ ____ ____ ____ ____ ");
			System.out.println(" (_ \\   |  \\ |___  |  |__| |    |    |___    |  \\ |___    |  | |\\ | |__|     |  |__| |__/ |___ |__| ");
			System.out.println("(___/() |__/ |___  |  |  | |___ |___ |___    |__/ |___    |__| | \\| |  |     |  |  | |  \\ |___ |  |");

			System.out.println("  __");
			System.out.println(" /. |   ____ ____ _    _ ____ ");
			System.out.println("(_  _)  [__  |__| |    | |__/ ");
			System.out.println("  (_)() ___] |  | |___ | |  \\ \n\n");          

		    
			 

			option = Esdia.readInt("Seleccione una opción: ", 1, 4);

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
					System.out.println("Saliendo del programa...");
					break;
				default:
					System.out.println("Opción no válida");
			}
		} while (option != 4);
		
	}

	private void subMenuListado() {
		// Implement the logic for subMenuListado here
		System.out.println("Listado de tareas:");
		// Example logic
	}

	private void subMenuDetalle() {
		
		Double taskId = Esdia.readDouble("Introduzca el ID de la tarea a visualizar: ");
		Task task = controller.getTaskById(taskId);

		if (task == null) {
			showErrorMessage("No se encontró la tarea con ID: " + taskId);
			return;
		}

		int option;
		do {
			System.out.println("Detalle de la tarea:");
			System.out.println("1. Marcar como " + (controller.isCompleted(taskId) ? "incompleta" : "completa"));
			System.out.println("2. Modificar información de la tarea");
			System.out.println("3. Eliminar la tarea");
			System.out.println("4. Volver al menú principal");
			option = Esdia.readInt("Seleccione una opción: ", 1, 4);

			switch (option) {
				case 1:
					controller.updateTask(task, "completed", String.valueOf(!controller.isCompleted(taskId)));
					showMessage("El estado de la tarea ha sido actualizado.");
					break;
				case 2:
					modifyTaskMenu(task);
					break;
				case 3:
					controller.deleteTask(taskId);
					showMessage("La tarea ha sido eliminada.");
					return;
				case 4:
					System.out.println("Volviendo al menú principal...");
					break;
				default:
					System.out.println("Opción no válida");
			}
		} while (option != 4);
	}

	private void modifyTaskMenu(Task task) {
		System.out.println("Que desea modificar?");
		System.out.println("1. Nombre");
		System.out.println("2. Descripción");
		System.out.println("3. Prioridad");
		System.out.println("4. Duración estimada");
		System.out.println("5. Cancelar");

		int opcion = Esdia.readInt("Seleccione una opción: ", 1, 5);
		boolean continuar = true;

		do{
		switch (opcion) {
			case 1:
				String newName = Esdia.readString_ne("Introduzca el nuevo nombre de la tarea: ");
				controller.updateTask(task, "title", newName);
				continuar = Esdia.yesOrNo("Desea modificar algo más?");
				break;
			case 2:
				String newDescription = Esdia.readString_ne("Introduzca la nueva descripción de la tarea: ");
				controller.updateTask(task, "content", newDescription);
				continuar = Esdia.yesOrNo("Desea modificar algo más?");
				break;
			case 3:
				int newPriority = Esdia.readInt("Introduzca la nueva prioridad de la tarea: ", 1, 5);
				controller.updateTask(task, "priority", String.valueOf(newPriority));
				continuar = Esdia.yesOrNo("Desea modificar algo más?");
				break;
			case 4:
				int newEstimatedDuration = Esdia.readInt("Introduzca la nueva duración estimada de la tarea (en minutos): ");
				controller.updateTask(task, "estimatedDuration", String.valueOf(newEstimatedDuration));
				continuar = Esdia.yesOrNo("Desea modificar algo más?");
				break;
			case 5:
				System.out.println("Cancelando modificación...");
				break;
			default:
				System.out.println("Opción no válida");
		}}while(opcion != 5 || continuar == false);

		
	}
	


	private void addTask() {
		String taskName = Esdia.readString_ne("Introduzca el nombre de la tarea: ");
		String taskDescription = Esdia.readString_ne("Introduzca la descripción de la tarea: ");
		int taskPriority = Esdia.readInt("Introduzca la prioridad de la tarea: ", 1, 5);
		int estimatedDuration = Esdia.readInt("Introduzca la duración estimada de la tarea (en horas): ");
		
		try {
			controller.addTask(new Task(taskName, new java.util.Date(), taskDescription, taskPriority, estimatedDuration, false));
			showMessage("TAREA AÑADIDA CORRECTAMENTE");
		} catch (Exception e) {
			showErrorMessage("Error al añadir la tarea: " + e.getMessage());
		}
	}




}
