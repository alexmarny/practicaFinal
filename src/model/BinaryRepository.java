package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.File;


public class BinaryRepository implements IRepository{
	
	private ArrayList<Task> tasks;

	private final String path = System.getProperty("user.home") + File.separator +"tasks.bin";

	public BinaryRepository() {
		tasks = new ArrayList<>();
	}
	
	@Override
	public Task addTask(Task t) throws RepositoryException {
		tasks.add(t);
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
			oos.writeObject(tasks);
		} catch (IOException e) {
			throw new RepositoryException("Error writing tasks to binary file", e);
		}
		return t;
	}

	@Override
	public void removeTask(Task t) throws RepositoryException {
		if (!tasks.contains(t)) {
			throw new RepositoryException("Task does not exist");
		}
		tasks.remove(t);
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
			oos.writeObject(tasks);
		} catch (IOException e) {
			throw new RepositoryException("Error deleting task from binary file", e);
		}
	}

	@Override
	public void modifyTask(Task t) throws RepositoryException {
		if (!tasks.contains(t)) {
			throw new RepositoryException("Task does not exist");
		}
		tasks.remove(t);
		tasks.add(t);
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
			oos.writeObject(tasks);
		} catch (IOException e) {
			throw new RepositoryException("Error modifying task in binary file", e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<Task> getAllTask() throws RepositoryException {

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
			tasks = (ArrayList<Task>) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			throw new RepositoryException("Error reading tasks from binary file", e);
		}
		return tasks;
	}

	


	
	
	


}