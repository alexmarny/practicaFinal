package model;

import java.util.ArrayList;

public interface IExporter {
	void export(ArrayList<Task> tasks, String fileName) throws ExporterException;
	void importTasks(String fileName) throws ExporterException;
}

