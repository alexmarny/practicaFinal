
package model;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.reflect.TypeToken;

import java.io.File;
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

		String filePath = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + fileName + ".json";
		Gson gson = new Gson();
		Type taskListType = new TypeToken<ArrayList<Task>>() {}.getType();
		try (FileReader reader = new FileReader(filePath)) {
			List<Task> tasks = gson.fromJson(reader, taskListType);
			List<Task> importedTasks = new ArrayList<>();
			for (Task t : tasks) {
				importedTasks.add(t);
				
			} System.out.println(importedTasks.toArray());
		} catch (IOException e) {
			throw new ExporterException("Error importing tasks from JSON", e);
		}

	}

	

}
