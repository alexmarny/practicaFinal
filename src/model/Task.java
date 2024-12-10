package model;

import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

public class Task implements Serializable {

	private UUID identifier;
	private String taskTitle;
	private Date date;
	private String content;
	private int priority;
	private int estimatedDuration;
	private boolean completed;

	public Task(String taskTitle, Date date, String content, int priority, int estimatedDuration, boolean completed) {
	
		this.identifier = UUID.randomUUID();
		this.taskTitle = taskTitle;
		this.date = date;
		this.content = content;
		setPriority(priority);
		this.estimatedDuration = estimatedDuration;
		this.completed = completed;
	}

	public Task(Task other) {
		this.identifier = other.identifier;
		this.taskTitle = other.taskTitle;
		this.date = new Date(other.date.getTime());
		this.content = other.content;
		this.priority = other.priority;
		this.estimatedDuration = other.estimatedDuration;
		this.completed = other.completed;
	}

	@Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.identifier);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Task other = (Task) obj;
        return Objects.equals(this.identifier, other.identifier);
    }

	public Task() {
		this.identifier = UUID.randomUUID();
	}

	public UUID getIdentifier() {
		return identifier;
	}

	public void setIdentifier(UUID identifier) {
		this.identifier = identifier;
	}

	public String getTitle() {
		return taskTitle;
	}

	public void setTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getEstimatedDuration() {
		return estimatedDuration;
	}

	public void setEstimatedDuration(int estimatedDuration) {
		this.estimatedDuration = estimatedDuration;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	  public static String getHeaderTableStringForTask() {
        return String.format("|%-23s|%-40s|%-28s|%-100s|%-8s|%-14s|%-9s|", "ID", "Title", "Date", "Content", "Priority", "Duration (est)", "Completed");
    }

    public String getAsRowString() {
		return String.format("|%-23s|%-40s|%-28s|%-100s|%-8d|%-14d|%-9s|", identifier, taskTitle, date, content, priority, estimatedDuration, completed? "yes" : "no");
    }

    public String getInstanceAsDelimitedString(String delimiter) { 
        return String.format(Locale.ENGLISH, "%s" + delimiter + "%s" + delimiter + "%s" + delimiter + "%s" + delimiter + "%s" + delimiter + "%s" + delimiter + "%s", identifier, taskTitle, date, content, priority, estimatedDuration, completed? "yes" : "no");
    }

	public static Task getTaskFromDelimitedString(String delimitedString, String delimiter) {

        // Con split es posible separar por el delimitador
        String[] chunks = delimitedString.split(delimiter);

		if (chunks.length != 7) {
			throw new IllegalArgumentException("Invalid delimited string format");
		}

		try {
            // Creamos la tarea
		Task p = new Task();
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
		p.setIdentifier(UUID.fromString(chunks[0]));
		p.setTitle(chunks[1]);
		p.setDate(dateFormat.parse(chunks[2]));
		p.setContent(chunks[3]);
		p.setPriority(Integer.parseInt(chunks[4]));
		p.setEstimatedDuration(Integer.parseInt(chunks[5]));
		p.setCompleted(chunks[6].equalsIgnoreCase("yes"));
            return p;
        } catch (Exception e) {
            // Tomamos linea como inválida
            return null;
        }
    }

}
