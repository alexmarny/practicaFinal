package model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.UUID;

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
	public void importTasks(String fileName) throws ExporterException {
		
		String filePath = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + fileName + ".csv";
		List<String> lines;
		try {
			lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new ExporterException("Error reading tasks from CSV", e);
		}

		for (String line : lines.subList(1, lines.size())) { // Skip header line
			String[] fields = line.split(";");
			if (fields.length == 7) {
				Task task = new Task();
				try {
					Date date = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(fields[2]);
					task.setDate(date);
				} catch (ParseException e) {
					throw new ExporterException("Error parsing date from CSV", e);
				}
				task.setIdentifier(UUID.fromString(fields[0]));
				task.setTitle(fields[1]);
				task.setContent(fields[3]);
				task.setPriority(Integer.parseInt(fields[4]));
				task.setEstimatedDuration(Integer.parseInt(fields[5]));
				task.setCompleted(Boolean.parseBoolean(fields[6]));
				
				Model.addTask(task);

			} else {
				throw new ExporterException("Invalid CSV format");
				

			}
		}
	}

	

}
