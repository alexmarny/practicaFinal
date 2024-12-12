package controller;

import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

import model.Model;
import model.RepositoryException;
import model.Task;
import view.BaseView;
import model.IExporter;
import model.ExporterException;
import model.ExporterFactory;

public class Controller {

	Model model;
	BaseView view;
	public Controller(Model model, BaseView view) {
		this.model = model;
		this.view = view;

		this.view.setControllerRef(this);
	}

	public void init() throws RepositoryException {
		if(model.cargarTareas()){
            view.init("Cargado estado anterior con exito");
        }else{
            view.init("No se encontró fichero para carga del programa");
        }
	}

	public void addTask(Task task) throws RepositoryException {
		
		Model.addTask(task);
		
	}

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

	public void importarTareas(String nombreFichero, String tipoArchivo) throws ExporterException, RepositoryException {
		IExporter exporter = ExporterFactory.getExporter(String.valueOf(tipoArchivo));
		
		exporter.importTasks(nombreFichero);
		
	}

	public void exportarTareas(String nombreFichero, String tipoArchivo) throws ExporterException {
		IExporter exporter = ExporterFactory.getExporter(String.valueOf(tipoArchivo));
	
		exporter.export(new ArrayList<>(model.obtenerTasksInmutable()), nombreFichero);
		
	}

	public void end() {

        // Guardado final del programa
		try {
			if(model.guardarTareas()){
				view.end("Guardado el estado de la aplicación.\nSaliendo...");
			}else{
				view.end("No se pudo guardar el estado de la aplicación.\nSaliendo...");
			}
		} catch (RepositoryException e) {
			e.printStackTrace();
			view.end("Error al guardar el estado de la aplicación.\nSaliendo...");
		}

	}

}
