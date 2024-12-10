package model;

import notion.api.v1.NotionClient;
import notion.api.v1.http.OkHttp5Client;
import notion.api.v1.logging.Slf4jLogger;
import notion.api.v1.model.databases.QueryResults;
import notion.api.v1.model.pages.Page;
import notion.api.v1.model.pages.PageParent;
import notion.api.v1.model.pages.PageProperty;
import notion.api.v1.model.pages.PageProperty.RichText;
import notion.api.v1.model.pages.PageProperty.RichText.Text;
import notion.api.v1.request.databases.QueryDatabaseRequest;
import notion.api.v1.request.pages.CreatePageRequest;
import notion.api.v1.request.pages.UpdatePageRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Date;


public class NotionRepository implements IRepository {

	private final NotionClient client;
    private final String databaseId;
    private final String titleColumnName = "Identifier"; // Este es el nombre de la columna que se utilizará 
														// como clave primaria única de type Title en Notion
	public NotionRepository(String apiToken, String databaseId) {
	// Crear cliente de Notion
	this.client = new NotionClient(apiToken);

	// Configurar cliente HTTP adecuado y tiempos de espera
	client.setHttpClient(new OkHttp5Client(60000,60000,60000));

	// Configurar loggers
	client.setLogger(new Slf4jLogger());

	// Silenciar/Activar los registros de log de Notion API
	// Ver en nivel debug los mensajes de depuración
	System.setProperty("notion.api.v1.logging.StdoutLogger", "debug");

	// Nivel más alto de log para NO ver mensajes de depuración
	//System.setProperty("notion.api.v1.logging.StdoutLogger", "off");

	this.databaseId = databaseId;
	}													 

	@Override
	public Task addTask(Task task) throws RepositoryException {
		
		System.out.println("Creando una nueva tarea...");
		// Crear una nueva página en la base de datos de Notion
        Map<String, PageProperty> properties = Map.of(
                "Identifier", createTitleProperty(task.getIdentifier().toString()),
                "Tarea", createRichTextProperty(task.getTitle()),
				"Fecha", createDateProperty(task.getDate().toString()),
				"Contenido", createRichTextProperty(task.getContent()),
				"Prioridad", createNumberProperty(task.getPriority()),
				"Duración estimada", createNumberProperty(task.getEstimatedDuration()),
				"Completada", createCheckboxProperty(task.isCompleted())
           );

		PageParent parent = PageParent.database(databaseId);
		CreatePageRequest request = new CreatePageRequest(parent, properties);
		
		Page page = client.createPage(request);

		System.out.println("Tarea creada con éxito con ID: " + page.getId());
		return task;
	}

	@Override
	public void removeTask(Task t) throws RepositoryException {
		// TODO Auto-generated method stub

	}

	@Override
	public void modifyTask(Task t) throws RepositoryException {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Task> getAllTask() throws RepositoryException {

		ArrayList<Task> tasks = new ArrayList<>();
		try {
			// Crear la solicitud para consultar la base de datos
			QueryDatabaseRequest queryRequest = new QueryDatabaseRequest(databaseId);

			// Ejecutar la consulta
			QueryResults queryResults = client.queryDatabase(queryRequest);

			// Procesar los resultados
			for (Page page : queryResults.getResults()) {
				Map<String, PageProperty> properties = page.getProperties();
				Task Task = mapPageToTask(page.getId(), properties);
				if (Task != null) {
					tasks.add(Task);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tasks;
	}

	// Métodos auxiliares para crear propiedades de página
    private PageProperty createTitleProperty(String title) {
        RichText idText = new RichText();
        idText.setText(new Text(title));
        PageProperty idProperty = new PageProperty();
        idProperty.setTitle(Collections.singletonList(idText));
        return idProperty;
    }

    // Metodos auxiliares para crear propiedades de página
    private PageProperty createRichTextProperty(String text) {
        RichText richText = new RichText();
        richText.setText(new Text(text));
        PageProperty property = new PageProperty();
        property.setRichText(Collections.singletonList(richText));
        return property;
    }

    private PageProperty createNumberProperty(Integer number) {
        PageProperty property = new PageProperty();
        property.setNumber(number);
        return property;
    }

    private PageProperty createDateProperty(String date) {
        PageProperty property = new PageProperty();
        PageProperty.Date dateProperty = new PageProperty.Date();
        dateProperty.setStart(date);
        property.setDate(dateProperty);
        return property;
    }

    private PageProperty createCheckboxProperty(boolean checked) {
        PageProperty property = new PageProperty();
        property.setCheckbox(checked);
        return property;
    }

    private Task mapPageToTask(String pageId, Map<String, PageProperty> properties) {
        try {
            Task task = new Task();

				PageProperty identifierProperty = properties.get("Identifier");
				if (identifierProperty != null && !identifierProperty.getTitle().isEmpty()) {
					task.setIdentifier(UUID.fromString(identifierProperty.getTitle().get(0).getText().getContent()));
				} else {
					task.setIdentifier(UUID.randomUUID());
				}
            
                if (properties.get("Tarea") != null && !properties.get("Tarea").getRichText().isEmpty()) {
                    task.setTitle(properties.get("Tarea").getRichText().get(0).getText().getContent());
                }
                
                if (properties.get("Fecha") != null && properties.get("Fecha").getDate() != null) {
                    task.setDate(Date.from(java.time.OffsetDateTime.parse(properties.get("Fecha").getDate().getStart()).toInstant()));
                }
                
                if (properties.get("Descripcion") != null && !properties.get("Descripcion").getRichText().isEmpty()) {
                    task.setContent(properties.get("Descripcion").getRichText().get(0).getText().getContent());
                }
                
                if (properties.get("Prioridad") != null) {
                    task.setPriority(properties.get("Prioridad").getNumber().intValue());
                }
                
                if (properties.get("Tiempo estimado") != null) {
                    task.setEstimatedDuration(properties.get("Tiempo estimado").getNumber().intValue());
                }
                
                if (properties.get("Completado") != null) {
                    task.setCompleted(properties.get("Completado").getCheckbox());
                }

            return task;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

	public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        try {
            // Crear la solicitud para consultar la base de datos
            QueryDatabaseRequest queryRequest = new QueryDatabaseRequest(databaseId);

            // Ejecutar la consulta
            QueryResults queryResults = client.queryDatabase(queryRequest);

            // Procesar los resultados
            for (Page page : queryResults.getResults()) {
                Map<String, PageProperty> properties = page.getProperties();
                Task Task = mapPageToTask(page.getId(), properties);
                if (Task != null) {
                    tasks.add(Task);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasks;
    }


}
