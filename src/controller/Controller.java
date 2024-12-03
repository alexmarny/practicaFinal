package controller;

import java.util.List;

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

	public Task getTaskById(int taskId) {
		
		return model.getTaskById(taskId);
	}

	public void updateTask(Task task, String atribute, String value) {

		model.updateTask(task, atribute, value);
		
	}

	public void deleteTask(int taskId) {

		model.deleteTask(taskId);
	}

	public List<Task> getAllTask() {
		return model.obtenerTasksInmutable();
	}


}
