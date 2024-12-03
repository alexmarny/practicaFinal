package model;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Model {

	private IRepository repository;

	private ArrayList<Task> tasks;

	File ficheroEstadoSerializado;

	private HashMap<Double, Task> taskMap;
	{
		taskMap = new HashMap<>();
	}

	

	public Model(IRepository repository) {
		this.repository = repository;
		ficheroEstadoSerializado = Paths.get(System.getProperty("user.home"), "Desktop", "tasks.bin").toFile();
		tasks = new ArrayList<Task>();
	}

	

	public boolean addTask(Task task) {

		try {
			if (taskMap.containsKey(task.getIdentifier())) {
				throw new IllegalArgumentException("Task ID already exists");
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

	public Task getTaskById(Double taskId) {
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



	public void deleteTask(Double taskId) {

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

	

	

}
