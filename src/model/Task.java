package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.io.Serializable;

public class Task implements Serializable {

	private static double idCounter = 0;

	private double identifier;
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
		this.identifier = ++idCounter;
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

	public double getIdentifier() {
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

	public void setCompleted() {
		this.completed = !this.completed;
	}
}
