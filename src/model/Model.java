package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public class Model {

	private static IRepository repository;

	private static ArrayList<Task> tasks;

	File ficheroEstadoSerializado;

	private static HashMap<UUID, Task> taskMap;
	{
		taskMap = new HashMap<>();
	}


	public Model(IRepository repository) {
		Model.repository = repository;
		ficheroEstadoSerializado = Paths.get(System.getProperty("user.home"), "Desktop", "tasks.bin").toFile();
		tasks = new ArrayList<Task>();
	}

	

	public static boolean addTask(Task task) {

		try {
			if (taskMap.containsKey(task.getIdentifier())) {
				throw new IllegalArgumentException("Task ID: already exists");
			}
			taskMap.put(task.getIdentifier(), task);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return false;
		}

		try {
			repository.addTask(task);
			tasks.add(task);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Task getTaskById(UUID taskId) {
		try {
			if (taskMap.containsKey(taskId)) {
				return taskMap.get(taskId);
			} else {
				throw new IllegalArgumentException("Task ID not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
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
	}



	public void deleteTask(UUID taskId) {

		Task task = taskMap.get(taskId);
		if (task != null) {
			taskMap.remove(taskId);
			tasks.remove(task);
			try {
				repository.removeTask(task);
			} catch (RepositoryException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Task ID not found");
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
	
	@SuppressWarnings("unchecked")
	public boolean cargarTareas() {

		if (repository instanceof NotionRepository) {
			try {
				NotionRepository notionRepository = (NotionRepository) repository;
				List<Task> notionTasks = notionRepository.getAllTasks();
				for (Task task : notionTasks) {
					taskMap.put(task.getIdentifier(), task);
					tasks.add(task);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}


		if (ficheroEstadoSerializado.exists() && ficheroEstadoSerializado.isFile()) {
			ObjectInputStream ois = null;
			try {
				ois = new ObjectInputStream(new FileInputStream(ficheroEstadoSerializado));
				Model.tasks = (ArrayList<Task>) ois.readObject();
				Model.taskMap = (HashMap<UUID, Task>) ois.readObject();
			} catch (IOException | ClassNotFoundException ex) {
				// Dejamos el error para la depuración, por el canal err.
				System.err.println("Error durante la deserialización: " + ex.getMessage());
				return false;
			} finally {
				if (ois != null) {
					try {
						ois.close();
					} catch (IOException ex) {
						// Dejamos el error para la depuración, por el canal err.
						System.err.println("Error durante la deserialización: " + ex.getMessage());
						return false;
					}
				}
			}
			return true;
		} else {
			return false;
		}

		
	}

	public boolean guardarTareas() {

		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(ficheroEstadoSerializado));
			oos.writeObject(tasks);
			oos.writeObject(taskMap);
			return true;
		} catch (IOException ex) {
			// Dejamos el error para la depuración, por el canal err.
			System.err.println("Error durante la serialización: " + ex.getMessage());
			return false;
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException ex) {
					// Dejamos el error para la depuración, por el canal err.
					System.err.println("Error al cerrar el flujo: " + ex.getMessage());
					return false;
				}
			}
		}

	}

}
