package controller;

import model.Model;
import view.BaseView;

public class Controller {

	Model model;
	BaseView view;

	public Controller(Model model, BaseView view) {
		this.model = model;
		this.view = view;

		this.view.setControllerRef(this);
	}

	public void run() {
		view.init();
	}

}
