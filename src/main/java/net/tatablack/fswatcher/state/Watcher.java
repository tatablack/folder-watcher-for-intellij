package net.tatablack.fswatcher.state;

/**
 * @author: Angelo Tata
 */
public class Watcher {
	private String name;
	private String path;
	private String executable;
	private boolean triggerOnFileAdded;
	private boolean triggerOnFileDeleted;
	private boolean triggerOnFileChanged;
	private boolean disabled;

	public Watcher(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getExecutable() {
		return executable;
	}

	public void setExecutable(String executable) {
		this.executable = executable;
	}

	public boolean hasTriggerOnFileAdded() {
		return triggerOnFileAdded;
	}

	public void setTriggerOnFileAdded(boolean triggerOnFileAdded) {
		this.triggerOnFileAdded = triggerOnFileAdded;
	}

	public boolean hasTriggerOnFileDeleted() {
		return triggerOnFileDeleted;
	}

	public void setTriggerOnFileDeleted(boolean triggerOnFileDeleted) {
		this.triggerOnFileDeleted = triggerOnFileDeleted;
	}

	public boolean hasTriggerOnFileChanged() {
		return triggerOnFileChanged;
	}

	public void setTriggerOnFileChanged(boolean triggerOnFileChanged) {
		this.triggerOnFileChanged = triggerOnFileChanged;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
}
