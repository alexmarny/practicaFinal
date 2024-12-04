package model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.ArrayList;

public class CsvExporter implements IExporter {

	@Override
	public void export(ArrayList<Task> tasks) throws ExporterException {
		String filePath = "tasks.csv";
		StringBuilder csv = new StringBuilder();
		for (Task t : tasks) {
			csv.append(t.getIdentifier());
			csv.append(";");
			csv.append(t.getTitle());
			csv.append(";");
			csv.append(t.getDate());
			csv.append(";");
			csv.append(t.getContent());
			csv.append(";");
			csv.append(t.getPriority());
			csv.append(";");
			csv.append(t.getEstimatedDuration());
			csv.append(";");
			csv.append(t.isCompleted());
			csv.append(";");
			csv.append("\n");
		}
		try {
			Files.write(Paths.get(filePath), csv.toString().getBytes(StandardCharsets.UTF_8));
		} catch (IOException e) {
			throw new ExporterException("Error exporting tasks to CSV", e);
		}
	}

	Path ruta = Paths.get(System.getProperty("user.home") + File.separator + "tasks.csv");
    String delimitador = "\t";

    @Override
	public void importTasks(ArrayList<Task> tasks) throws ExporterException {

        try {
            List<String> lineas = Files.readAllLines(ruta);
            for (String linea : lineas) {
                Task p = Task.getPersonFromDelimitedString(linea, delimitador);
                if (p != null) {
                    tasks.add(p);
                }
            }

        } catch (IOException e) {
			throw new ExporterException("Error importing tasks from CSV", e);
        }

    }

}
