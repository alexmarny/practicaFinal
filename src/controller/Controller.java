package controller;

import java.util.List;
import java.util.ArrayList;

import model.Model;
import model.Task;
import view.BaseView;
import model.IExporter;
import model.ExporterFactory;

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

	public Task getTaskById(Double taskId) {
		
		return model.getTaskById(taskId);
	}

	public void updateTask(Task task, String atribute, String value) {

		model.updateTask(task, atribute, value);
		
	}

	public void deleteTask(Double taskId) {

		model.deleteTask(taskId);
	}

	public List<Task> getAllTasks() {
		return model.obtenerTasksInmutable();
	}

	public void importarTareas(String nombreFichero, int tipoArchivo) {
		IExporter exporter = ExporterFactory.getExporter(String.valueOf(tipoArchivo));
		try {
			exporter.importTasks(new ArrayList<>(model.obtenerTasksInmutable()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void exportarTareas(String nombreFichero, int tipoArchivo) {
		IExporter exporter = ExporterFactory.getExporter(String.valueOf(tipoArchivo));
		try {
			exporter.export(new ArrayList<>(model.obtenerTasksInmutable()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
