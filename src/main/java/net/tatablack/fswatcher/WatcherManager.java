package net.tatablack.fswatcher;

import com.intellij.openapi.components.*;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Angelo Tata
 */
@State(
		name = "FSWatcherConfiguration",
		storages = {
				@Storage(id = "default", file = StoragePathMacros.PROJECT_FILE),
				@Storage(id = "dir", file = StoragePathMacros.PROJECT_CONFIG_DIR + "/FSWatcherConfiguration.xml", scheme = StorageScheme.DIRECTORY_BASED)
		}
)
public class WatcherManager implements ProjectComponent, PersistentStateComponent<WatcherManager.State> {
	State state = new State();
	Project project;

	public WatcherManager(Project project) {
		this.project = project;
	}



	public void initComponent() {
		// TODO: insert component initialization logic here
	}

	public void disposeComponent() {
		// TODO: insert component disposal logic here
	}

	@NotNull
	public String getComponentName() {
		return "WatcherManager";
	}

	public void projectOpened() {
		// called when project is opened
	}

	public void projectClosed() {
		// called when project is being closed
	}

	@Nullable
	@Override
	public State getState() {
		return this.state;
	}

	@Override
	public void loadState(State state) {
		this.state = state;
	}

	class State {
		List<Watcher> watchers = new ArrayList<Watcher>();
	}
}
