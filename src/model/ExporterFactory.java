package model;

public class ExporterFactory {
	public static IExporter getExporter(String type) {
		if ("csv".equalsIgnoreCase(type)) {
			return new CsvExporter();
		} else if ("json".equalsIgnoreCase(type)) {
			return new JsonExporter();
		} else {
			throw new IllegalArgumentException("Unknown exporter type: " + type);
		}
	}
}
