package net.tatablack.fswatcher;

import com.intellij.openapi.components.*;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import net.tatablack.fswatcher.fs.BulkFileListenerImpl;
import net.tatablack.fswatcher.logging.SeparateLogger;
import net.tatablack.fswatcher.state.Watcher;
import net.tatablack.fswatcher.state.WatcherState;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * @author: Angelo Tata
 */
@State(
		name = "WatcherManager",
		storages = {
				@Storage(id = "default", file = StoragePathMacros.PROJECT_FILE),
				@Storage(id = "dir", file = StoragePathMacros.PROJECT_CONFIG_DIR + "/FSWatcherConfiguration.xml", scheme = StorageScheme.DIRECTORY_BASED)
		}
)
public class WatcherManager implements ProjectComponent, PersistentStateComponent<WatcherState> {
	private static final Logger LOGGER = SeparateLogger.getInstance(WatcherManager.class.getName());

	private final BulkFileListenerImpl vfsListener;

	@NotNull private WatcherState state = new WatcherState();
	private final Project project;


	public WatcherManager(Project project) {
		this.project = project;
		this.vfsListener = new BulkFileListenerImpl(project);

		LOGGER.info("WatcherManager instantiated");
	}

	public static WatcherManager getInstance(Project project) {
		return project.getComponent(WatcherManager.class);
	}

	public void initComponent() {
		LOGGER.info("WatcherManager initComponent");
		this.vfsListener.start();
	}

	public void disposeComponent() {
	}

	@NonNls
	@NotNull
	public String getComponentName() {
		return "WatcherManager";
	}

	public void projectOpened() {
		LOGGER.info("WatcherManager projectOpened");
	}

	public void projectClosed() {
	}

	public void addWatcher(final Watcher watcher) {
		this.state.watchers.put(watcher.getName(), watcher);
	}

	public void removeWatcher(final Watcher watcher) {
		this.state.watchers.remove(watcher.getName());
	}

	public Collection<Watcher> getWatchers() {
		return this.state.watchers.values();
	}

	@NotNull
	@Override
	public WatcherState getState() {
		return this.state;
	}

	@Override
	public void loadState(WatcherState state) {
		this.state = state;
	}
}
