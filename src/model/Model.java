package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public class Model {

	private static IRepository repository;

	protected static ArrayList<Task> tasks;

	File ficheroEstadoSerializado;

	protected static HashMap<UUID, Task> taskMap;
	{
		taskMap = new HashMap<>();
	}


	public Model(IRepository repository) {
		Model.repository = repository;
		ficheroEstadoSerializado = Paths.get(System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "tasks.bin").toFile();
		tasks = new ArrayList<Task>();
	}

	

	public static void addTask(Task task) throws IllegalArgumentException {	

		if (!(taskMap.containsKey(task.getIdentifier()))) {
			taskMap.put(task.getIdentifier(), task);
			try {
				repository.addTask(task);
				tasks.add(task); 
			} catch (RepositoryException e) {
				e.printStackTrace();
			}
		}
	}

	public Task getTaskById(UUID taskId) {
		if (taskMap.containsKey(taskId)) {
			return taskMap.get(taskId);
		} else {
			return null;
		}
	}



	public void updateTask(Task task, String atribute, String value) {

		switch (atribute) {
		case "title":
			task.setTitle(value);
			break;
		case "content":
			task.setContent(value);
			break;
		case "priority":
			task.setPriority(Integer.parseInt(value));
			break;
		case "estimatedDuration":
			task.setEstimatedDuration(Integer.parseInt(value));
			break;
		case "completed":
			task.setCompleted(Boolean.parseBoolean(value));
			break;
		default:
			break;
		}
		
		task.setDate(new java.util.Date());

		try {
			repository.modifyTask(task);
			
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
	}



	public void deleteTask(UUID taskId) throws IllegalArgumentException {

		Task task = taskMap.get(taskId);
		if (task == null) {
			throw new IllegalArgumentException("Task ID not found");
		}
		try {
			repository.removeTask(task);
			tasks.remove(task);
			taskMap.remove(taskId);
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
	}

	 public List<Task> obtenerTasksInmutable() {

		// Creamos una copia de la lista original
		List<Task> listaCopia = new ArrayList<>(tasks.size());

		for (Task task : tasks) {
			listaCopia.add(new Task(task));
		}

		return listaCopia;

	}
	
	public boolean cargarTareas() throws RepositoryException{
		try {
			repository.getAllTask().forEach(task -> {
				tasks.add(task);
				taskMap.put(task.getIdentifier(), task);
			});
			return true;
		} catch (RepositoryException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean guardarTareas() throws RepositoryException {
		if ((repository instanceof BinaryRepository)) {
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ficheroEstadoSerializado))) {
				oos.writeObject(tasks);
				oos.writeObject(taskMap);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

}
