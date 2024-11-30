package view;

import controller.Controller;

public class BaseView {

	Controller controller;

	public void setControllerRef(Controller controllerRef) {
		this.controller = controllerRef;
	}

	public void init() {

		//TODO: inicia la vista y desencadena la lógica de la aplicación
		
	}

	public void showMessage(String message) {
		System.out.println(message);
	}

	public void showErrorMessage(String message) {
		System.err.println(message);
	}

	public void end() {
		//TODO: finaliza la vista ordenadamente
	}

}
