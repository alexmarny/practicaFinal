package view;

import java.io.IOException;
import java.util.List;

import com.coti.tools.Esdia;

import io.github.jonelo.jAdapterForNativeTTS.engines.SpeechEngine;
import io.github.jonelo.jAdapterForNativeTTS.engines.SpeechEngineNative;
import io.github.jonelo.jAdapterForNativeTTS.engines.Voice;
import io.github.jonelo.jAdapterForNativeTTS.engines.VoicePreferences;
import io.github.jonelo.jAdapterForNativeTTS.engines.exceptions.SpeechEngineCreationException;
import model.Task;
import java.util.UUID;

public class ttsView extends BaseView {

	SpeechEngine speechEngine;

	//METODO CONSTUCTOR PARA TTS

	public ttsView() {
		String text = "Bienvenido a la aplicación de tareas por voz.";
		try {
			this.speechEngine = SpeechEngineNative.getInstance();
			List<Voice> voices = speechEngine.getAvailableVoices();

			// We want to find a voice according our preferences
			VoicePreferences voicePreferences = new VoicePreferences();
			voicePreferences.setLanguage("es"); //  ISO-639-1
			voicePreferences.setCountry("ES"); // ISO 3166-1 Alpha-2 code
			voicePreferences.setGender(VoicePreferences.Gender.MALE);
			Voice voice = speechEngine.findVoiceByPreferences(voicePreferences);

			// simple fallback just in case our preferences didn't match any voice
			if (voice == null) {
				System.out.printf("Warning: Voice has not been found by the voice preferences %s%n", voicePreferences);
				voice = voices.get(0); // it is guaranteed that the speechEngine supports at least one voice
				System.out.printf("Using \"%s\" instead.%n", voice);
			}

			speechEngine.setVoice(voice.getName());
			speechEngine.say(text);
			Thread.sleep(2000); // tiempo entre mensaje de bienvenida y menu

		} catch (SpeechEngineCreationException | IOException | InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}

	public void decir(String texto) {
		try {
			this.speechEngine.say(texto);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	@Override
	public void init(String welcomeMsg) {

		System.out.println(welcomeMsg);
		decir(welcomeMsg);

		showMenu();
		
	}

	@Override
	public void showMessage(String message) {
		System.out.println(message);
	}

	@Override
	public void showErrorMessage(String errorMsg) {
		System.out.println(errorMsg);
	}

	@Override
	public void end(String goodbyeMsg) {
		System.out.println(goodbyeMsg);
	}
	
	public void showMenu() {

		int opcion;

		String menu = "1. Alta de tarea\n" + "2. Listado de tareas\n" + "3. Detalle de tarea\n" + "4. Importar o exportar tareas\n"
				+ "5. Salir\n" + "Seleccione una opción: ";
		System.out.println(menu);

		decir(menu);

		do{

		opcion = Esdia.readInt(null);

		decir("Ha seleccionado la opción " + opcion);

		switch (opcion) {
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

			showMessage("Saliendo de la aplicación...");
			decir("Saliendo de la aplicación...");
			System.exit(0);
			break;

		default:

			showMessage("Opción no válida.");
			decir("Opción no válida.");
			break;
		}	

		}while(opcion != 5);
		
	}

	private void addTask() {

		decir("Ha seleccionado la opción de alta de tarea, introduzca el nombre de la tarea.");
		String taskName = Esdia.readString_ne("Introduzca el nombre de la tarea: ");

		decir("Introduzca la descripción de la tarea.");
		String taskDescription = Esdia.readString_ne("Introduzca la descripción de la tarea: ");
		decir("Introduzca la prioridad de la tarea.");
		int taskPriority = Esdia.readInt("Introduzca la prioridad de la tarea: ", 1, 5);
		decir("Introduzca la duración estimada de la tarea.");
		int estimatedDuration = Esdia.readInt("Introduzca la duración estimada de la tarea (en minutos): ");
		
		try {
			controller.addTask(new Task(taskName, new java.util.Date(), taskDescription, taskPriority, estimatedDuration, false));
			showMessage("TAREA AÑADIDA CORRECTAMENTE");
			decir("TAREA AÑADIDA CORRECTAMENTE");
		} catch (Exception e) {
			showErrorMessage("Error al añadir la tarea: " + e.getMessage());
		}

		decir("Presione Enter para continuar...");
		Esdia.readString("Presione Enter para continuar...");
	
	}

	private void subMenuListado() {
		int opcion;
		String menu = "1. Listar todas las tareas\n" + "2. Listar tareas pendientes\n"
				+ "4. Volver al menú principal\n" + "Seleccione una opción: ";
		System.out.println(menu);
		decir(menu);
		opcion = Esdia.readInt(null);
		decir("Ha seleccionado la opción " + opcion);
		switch (opcion) {
		case 1:
			showAllTasks();
			break;
		case 2:
			showPendingTasks();
			break;
		case 4:
			showMenu();
			break;
		}

	}

	private void showAllTasks() {
		List<Task> tasks = controller.getAllTasks();
		if (tasks.isEmpty()) {
			showMessage("No hay tareas registradas.");
		} else {
			showMessage("Listado de tareas:");
			for (Task task : tasks) {
				showMessage(task.toString());
				decir(task.toString());
			}
		}
		decir("Presione Enter para continuar...");
		Esdia.readString("Presione Enter para continuar...");
		showMenu();
	}

	private void showPendingTasks() {
		List<Task> tasks = controller.getAllTasks();
		if (tasks.isEmpty()) {
			showMessage("No hay tareas pendientes.");
		} else {
			showMessage("Listado de tareas pendientes:");
			for (Task task : tasks) {
				if(!task.isCompleted()){
					showMessage(task.toString());
					decir(task.toString());
				}
			}
		}
		decir("Presione Enter para continuar...");
		Esdia.readString("Presione Enter para continuar...");
		showMenu();
	}

	public void subMenuDetalle(){
		UUID taskId = UUID.fromString(Esdia.readString_ne("Introduzca el ID de la tarea: "));
		Task task = controller.getTaskById(taskId);
		if (task == null) {
			showErrorMessage("No se encontró ninguna tarea con el ID proporcionado.");
			decir("No se encontró ninguna tarea con el ID proporcionado.");
		} else {
			showMessage("Tarea encontrada: " + task.toString());
			decir("Tarea encontrada: " + task.toString());
			modifyTaskMenu(task);
		}

	}

	private void modifyTaskMenu(Task task) {
		int opcion;
		String menu = "1. Marcar tarea como" + (task.isCompleted()? "incompleta" : "completa")+ "\n" + "2. Modificar tarea\n" + "3. Eliminar tarea\n"
				+ "4. Volver al menú principal\n" + "Seleccione una opción: ";
		System.out.println(menu);
		decir(menu);
		opcion = Esdia.readInt(null);
		decir("Ha seleccionado la opción " + opcion);
		switch (opcion) {
		case 1:
			controller.updateTask(task, "completed", String.valueOf(!task.isCompleted()));
			showMessage("El estado de la tarea ha sido actualizado.");
			decir("El estado de la tarea ha sido actualizado.");
			break;
		case 2:
			modifyTask(task);
			break;
		case 3:
		controller.deleteTask(task.getIdentifier());
		showMessage("La tarea ha sido eliminada.");
		decir("La tarea ha sido eliminada.");
			break;
		case 4:
			showMenu();
			decir("Volviendo al menú principal...");
			break;
		}
	}
	

	private void modifyTask(Task task) {
		int opcion = Esdia.readInt("Seleccione una opción: ", 1, 5);

		switch (opcion) {
			case 1:
				String newName = Esdia.readString_ne("Introduzca el nuevo nombre de la tarea: ");
				decir("Introduzca el nuevo nombre de la tarea.");
				controller.updateTask(task, "title", newName);
				
				break;
			case 2:
				String newDescription = Esdia.readString_ne("Introduzca la nueva descripción de la tarea: ");
				decir("Introduzca la nueva descripción de la tarea.");
				controller.updateTask(task, "content", newDescription);
				
				break;
			case 3:
				int newPriority = Esdia.readInt("Introduzca la nueva prioridad de la tarea: ", 1, 5);
				decir("Introduzca la nueva prioridad de la tarea.");
				controller.updateTask(task, "priority", String.valueOf(newPriority));
			
				break;
			case 4:
				int newEstimatedDuration = Esdia.readInt("Introduzca la nueva duración estimada de la tarea (en minutos): ");
				decir("Introduzca la nueva duración estimada de la tarea.");
				controller.updateTask(task, "estimatedDuration", String.valueOf(newEstimatedDuration));
				
				break;
			case 5:
				System.out.println("Cancelando modificación...");
				decir("Cancelando modificación...");
				break;
			default:
				System.out.println("Opción no válida");
				decir("Opción no válida");
		}

		
	}

	private void menuImportarExportar() {
		int opcion;
		String menu = "1. Importar tareas\n" + "2. Exportar tareas\n" + "3. Volver al menú principal\n"
				+ "Seleccione una opción: ";
		System.out.println(menu);
		decir(menu);
		opcion = Esdia.readInt(null);
		decir("Ha seleccionado la opción " + opcion);
		switch (opcion) {
		case 1:
			importTasks();
			break;
		case 2:
			exportTasks();
			break;
		case 3:
			showMenu();
			break;
		}
	}

	private void importTasks() {

		decir("Seleccione el tipo de archivo a importar.");

		System.out.println("1. Importar desde fichero CSV");
		decir("1. Importar desde fichero CSV");
		
		System.out.println("2. Importar desde fichero JSON");
		decir("2. Importar desde fichero JSON");

		System.out.println("3. Volver al menú principal");
		decir("3. Volver al menú principal");

		int opcionTipoArchivo = 0;

		String tipoArchivo = null;

		do{

			opcionTipoArchivo = Esdia.readInt("Introduzca el tipo de archivo a importar:", 1, 2);

			switch (opcionTipoArchivo) {
				case 1:
					tipoArchivo = "csv";
					break;
				case 2:
					tipoArchivo = "json";
					break;
				case 3:
					showMenu();
					break;
				
				default:
					showMessage("Opción no válida");
					decir("Opción no válida");
			} 
		} while (opcionTipoArchivo <1 || opcionTipoArchivo > 3);

		String nombreFichero = Esdia.readString_ne("Introduzca el nombre del fichero a importar desde home: ");

		decir("Introduzca el nombre del fichero a importar desde home.");

		try {
			controller.importarTareas(nombreFichero, tipoArchivo);			
			showMessage("Tareas importadas correctamente");
			decir("Tareas importadas correctamente");
		} catch (Exception e) {
			showErrorMessage("Error al importar tareas: " + e.getMessage());
			decir("Error al importar tareas: " + e.getMessage());
		}

		Esdia.readString("Presione Enter para continuar...");
		decir("Presione Enter para continuar...");
	}

	private void exportTasks() {

		decir("Seleccione el tipo de archivo a exportar.");
		System.out.println("1. Exportar a fichero CSV");
		decir("1. Exportar a fichero CSV");
		System.out.println("2. Exportar a fichero JSON");
		decir("2. Exportar a fichero JSON");
		System.out.println("3. Volver al menú principal");
		decir("3. Volver al menú principal");

		int opcionTipoArchivo;
		String tipoArchivo = null;

		do{
			opcionTipoArchivo = Esdia.readInt("Introduzca el tipo de archivo a exportar:", 1, 2);

			switch (opcionTipoArchivo) {
				case 1:
					tipoArchivo = "csv";
					break;
				case 2:
					tipoArchivo = "json";
					break;
				case 3:
					showMenu();
					break;
				
				default: 
					showMessage("Opción no válida");
					decir("Opción no válida");
			}
				
		} while (opcionTipoArchivo <1 || opcionTipoArchivo > 3);
		
		String nombreFichero = Esdia.readString_ne("Introduzca el nombre del fichero a exportar en home: ");
		decir("Introduzca el nombre del fichero a exportar en home.");

		try {
			controller.exportarTareas(nombreFichero, tipoArchivo);
			showMessage("Tareas exportadas correctamente");
			decir("Tareas exportadas correctamente");
		} catch (Exception e) {
			showErrorMessage("Error al exportar tareas: " + e.getMessage());
			decir("Error al exportar tareas: " + e.getMessage());
		}

		Esdia.readString("Presione Enter para continuar...");
		decir("Presione Enter para continuar...");
		
	}
	
}

