package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;


public class BinaryRepository implements IRepository{
	
	private ArrayList<Task> tasks;
	
	public BinaryRepository() {
		tasks = new ArrayList<Task>();
	}
	
	@Override
	public Task addTask(Task t) throws RepositoryException {
		if (tasks.contains(t)) {
			throw new RepositoryException("Task already exists");
		}
		tasks.add(t);
		return t;
	}

	@Override
	public void removeTask(Task t) throws RepositoryException {
		if (!tasks.contains(t)) {
			throw new RepositoryException("Task does not exist");
		}
		tasks.remove(t);
	}

	@Override
	public void modifyTask(Task t) throws RepositoryException {
		if (!tasks.contains(t)) {
			throw new RepositoryException("Task does not exist");
		}
		tasks.remove(t);
		tasks.add(t);
	}

	@Override
	public ArrayList<Task> getAllTask() throws RepositoryException {
		return tasks;
	}
	
	public void saveToFile(String path) throws IOException {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
			oos.writeObject(tasks);
		}
	}
	
	public void loadFromFile(String path) throws IOException, ClassNotFoundException {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
			tasks = (ArrayList<Task>) ois.readObject();
		}
	}
	
	public CompletableFuture<Void> saveToFileAsync(String path) {
		return CompletableFuture.runAsync(() -> {
			try {
				saveToFile(path);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
	}
	
	public CompletableFuture<Void> loadFromFileAsync(String path) {
		return CompletableFuture.runAsync(() -> {
			try {
				loadFromFile(path);
			} catch (IOException | ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		});
	}

}