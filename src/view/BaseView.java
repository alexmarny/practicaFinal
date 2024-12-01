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
    
    protected Controller controller;

    public abstract void init(String welcomeMsg);
    
    public abstract void showMessage(String message);

	public abstract void showErrorMessage(String errorMsg);
    
    public abstract void end(String goodbyeMsg);

    public void setControllerRef(Controller controllerRef) {
		this.controller = controllerRef;
	}

}
