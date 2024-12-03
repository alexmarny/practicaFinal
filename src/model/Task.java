package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.io.Serializable;

public class Task implements Serializable {

	private static int idCounter = 0;

	private int identifier;
	private String title;
	private Date date;
	private String content;
	private int priority;
	private int estimatedDuration;
	private boolean completed;

	public Task(String title, Date date, String content, int priority, int estimatedDuration, boolean completed) {
		this.identifier = ++idCounter;
		this.title = title;
		this.date = date;
		this.content = content;
		setPriority(priority);
		this.estimatedDuration = estimatedDuration;
		this.completed = completed;
	}

	public Task(Task other) {
		this.identifier = other.identifier;
		this.title = other.title;
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
		this.identifier = ++idCounter;
	}

	public int getIdentifier() {
		return identifier;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
        return String.format("|%-5.2s|%-40s|%-28s|%-100s|%-8s|%-14s|%-9s|", "ID", "Title", "Date", "Content", "Priority", "Duration (est)", "Completed");
    }

    public String getAsRowString() {
		return String.format("|%-5.0f|%-40s|%-28s|%-100s|%-8d|%-14d|%-9s|", identifier, title, date, content, priority, estimatedDuration, completed ? "yes" : "no");
    }

    public String getInstanceAsDelimitedString(String delimiter) { 
        return String.format(Locale.ENGLISH, "%s" + delimiter + "%s" + delimiter + "%s" + delimiter + "%s" + delimiter + "%s" + delimiter + "%s" + delimiter + "%s", identifier, title, date, content, priority, estimatedDuration, completed? "yes" : "no");
    }

	public void setIdentifier(int int1) {

		if (int1 < 0) {
			throw new IllegalArgumentException("Task ID must be positive");
		}

		this.identifier = int1;
			
	}
}
