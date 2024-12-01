package controller;

import java.util.Date;

import model.Model;
import model.Task;
import view.BaseView;

public class Controller {

	Model model;
	BaseView view;

	public Controller(Model model, BaseView view) {
		this.model = model;
		this.view = view;

		this.view.setControllerRef(this);
	}

	public void init() {
		view.init("Bienvenido a la aplicación de tareas");
	}

	public boolean addTask(Task task) {

		return model.addTask(task);}

	public Task getTaskById(double taskId) {
		
		return model.getTaskById(taskId);
	}

	public void updateTask(Task task, String atribute, String value) {

		model.updateTask(task, atribute, value);
		
	}

	public void deleteTask(Double taskId) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'deleteTask'");
	}

	public boolean isCompleted(double id) {
		
		return model.isCompleted(id);
	}


}
