package model;

import java.util.ArrayList;
import java.util.concurrent.Future;

public interface IRepository {
	Task addTask(Task t) throws RepositoryException;
	void removeTask(Task t) throws RepositoryException;
	void modifyTask(Task t) throws RepositoryException;
	ArrayList<Task> getAllTask() throws RepositoryException;

}

