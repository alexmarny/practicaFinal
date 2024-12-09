package controller;

import java.util.List;
import java.util.UUID;
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
		if(model.cargarTareas()){
            view.init("Cargado estado anterior con exito");
        }else{
            view.init("No se encontró fichero para carga del programa");
        }
	}

	public boolean addTask(Task task) {

		return model.addTask(task);}

	public Task getTaskById(UUID taskId) {
		
		return model.getTaskById(taskId);
	}

	public void updateTask(Task task, String atribute, String value) {

		model.updateTask(task, atribute, value);
		
	}

	public void deleteTask(UUID taskId) {

		model.deleteTask(taskId);
	}

	public List<Task> getAllTasks() {
		return model.obtenerTasksInmutable();
	}

	public void importarTareas(String nombreFichero, String tipoArchivo) {
		IExporter exporter = ExporterFactory.getExporter(String.valueOf(tipoArchivo));
		
		try {
			exporter.importTasks(nombreFichero);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void exportarTareas(String nombreFichero, String tipoArchivo) {
		IExporter exporter = ExporterFactory.getExporter(String.valueOf(tipoArchivo));
		try {
			exporter.export(new ArrayList<>(model.obtenerTasksInmutable()), nombreFichero);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void end() {

        // Guardado final del programa
        if(model.guardarTareas()){
            view.end("Guardado el estado de la aplicación.\nSaliendo...");
        }else{
            view.end("No se pudo guardar el estado de la aplicación.\nSaliendo...");
        }

	}

}
