
package model;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileNotFoundException;
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
	public void importTasks(String fileName) throws ExporterException{

		String filePath = System.getProperty("user.home") + "/Desktop/" + fileName + ".json";
		Gson gson = new Gson();
		Type listType = new TypeToken<ArrayList<Task>>(){}.getType();
		try (FileReader reader = new FileReader(filePath)) {
			List<Task> tasks = gson.fromJson(reader, listType);
			for (Task t : tasks) {
				Model.addTask(t);
			}
		} catch (FileNotFoundException e) {
			throw new ExporterException("Fichero no encontrado", e);
		} catch (IOException e) {
			throw new ExporterException("Error de importación", e);
		}
		
	}

	

}
