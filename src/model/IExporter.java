package model;

import java.util.ArrayList;

public interface IExporter {
	void export(ArrayList<Task> tasks) throws ExporterException;
	void importTasks(ArrayList<Task> tasks) throws ExporterException;
}

