
package model;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class JsonExporter implements IExporter {

	@Override
	public void export(ArrayList<Task> tasks, String fileName) throws ExporterException {
		String filePath = System.getProperty("user.home") + "/Desktop/" + fileName + ".json";
		Gson gson = new Gson();
		String json = gson.toJson(tasks);
		try (FileWriter writer = new FileWriter(filePath)) {
			writer.write(json);
		} catch (IOException e) {
			throw new ExporterException("Error exporting tasks to JSON", e);
		}
	}

	@Override
	public void importTasks(String fileName) throws ExporterException {

		String filePath = System.getProperty("user.home") + "/Desktop/" + fileName + ".json";
		Gson gson = new Gson();
		try (FileReader reader = new FileReader(filePath)) {
			Type taskListType = new TypeToken<ArrayList<Task>>() {}.getType();
			List<Task> tasks = gson.fromJson(reader, taskListType);
			for (Task t : tasks) {
				Model.addTask(t);
			}
		} catch (IOException e) {
			throw new ExporterException("Error importing tasks from JSON", e);
		}
	}

	

}
