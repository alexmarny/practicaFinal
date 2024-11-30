package view;

import com.coti.tools.Esdia;

public class InteractiveView {

	void showMenu(){

		int option;
		do {
			System.out.println("Menú CRUD:");
			System.out.println("1. Alta de tarea");
			System.out.println("2. Listado de tareas sin completar (ordenadas por prioridad)");
			System.out.println("3. Listado del historial completo de tareas");
			System.out.println("4. Detalle de una tarea");
			System.out.println("5. Salir");
			option = Esdia.readInt("Seleccione una opción: ", 1, 5);

			switch (option) {
				case 1:
					
					break;
				case 2:
					// Lógica para listado de tareas sin completar
					break;
				case 3:
					// Lógica para listado del historial completo de tareas
					break;
				case 4:
					// Lógica para detalle de una tarea
					break;
				case 5:
					System.out.println("Saliendo del menú...");
					break;
				default:
					System.out.println("Opción no válida");
			}
		} while (option != 5);
		
	}


}
