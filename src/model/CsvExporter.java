package model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class CsvExporter implements IExporter {

	@Override
	public void export(ArrayList<Task> tasks) throws ExporterException {
		String filePath = "tasks.csv";
		StringBuilder csv = new StringBuilder();
		for (Task t : tasks) {
			csv.append(t.getIdentifier());
			csv.append(",");
			csv.append(t.getContent());
			csv.append(",");
			csv.append(t.getPriority());
			csv.append(",");
			csv.append(t.getDate());
			csv.append("\n");
		}
		try {
			Files.write(Paths.get(filePath), csv.toString().getBytes(StandardCharsets.UTF_8));
		} catch (IOException e) {
			throw new ExporterException("Error exporting tasks to CSV", e);
		}
	}

	@Override
	public void importTasks(ArrayList<Task> tasks) throws ExporterException {
		String filePath = "tasks.csv";
		try {
			List<String> lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
			for (String line : lines) {
				String[] parts = line.split(",");
				Task t = new Task();
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date date = sdf.parse(parts[3]);
					t.setDate(date);
				} catch (ParseException e) {
					throw new ExporterException("Error parsing date from CSV", e);
				}
				
				t.setIdentifier(Integer.parseInt(parts[0]));
				t.setContent(parts[1]);
				t.setPriority(Integer.parseInt(parts[2]));
		
				tasks.add(t);
			}
		} catch (IOException e) {
			throw new ExporterException("Error importing tasks from CSV", e);
		}
	}

}
