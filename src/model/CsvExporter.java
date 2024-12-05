package model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.ArrayList;

public class CsvExporter implements IExporter {

	@Override
	public void export(ArrayList<Task> tasks, String fileName) throws ExporterException {
		String filePath = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + fileName + ".csv";
		StringBuilder csv = new StringBuilder();
		csv.append("Identifier;Title;Date;Content;Priority;Estimated Duration;Completed\n");
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

    @Override
	public void importTasks(ArrayList<Task> tasks, String fileName) throws ExporterException {

		String filePath = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + fileName + ".csv";
		List<String> lines;
		try {
			lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new ExporterException("Error importing tasks from CSV", e);

			}
			lines.remove(0); // Remove header line
			for (String line : lines) { tasks.add(Task.getTaskFromDelimitedString(line, ";")); }

    }

}
