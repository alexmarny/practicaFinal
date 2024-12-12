package view;

import controller.Controller;

/**
 *
 * Clase abstracta que marcará los métodos mínimos que se deben implementar
 * para interactuar con el usuario.
 * 
 * - Mostrar la inicialización del programa.
 * - Mostrar un menú.
 * - Mostrar una finalización del programa.
 * 
 * 
 */
public abstract class BaseView {

	public void setControllerRef(Controller controllerRef) {
		this.controller = controllerRef;
	}
    
    protected Controller controller;

    public abstract void init(String welcomeMsg); // Inicialización de la logica de la vista con el mensaje de bienvenida
    
    public abstract void showMessage(String message); // Mostrar un mensaje

	public abstract void showErrorMessage(String errorMsg); // Mostrar un mensaje de error
    
    public abstract void end(String goodbyeMsg); // Finalización de la logica de la vista con el mensaje de despedida

   

}
