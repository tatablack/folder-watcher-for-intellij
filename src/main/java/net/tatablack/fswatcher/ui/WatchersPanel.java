package net.tatablack.fswatcher.ui;

import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MasterDetailsComponent;
import net.tatablack.fswatcher.WatcherManager;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author: Angelo Tata
 */
public class WatchersPanel extends MasterDetailsComponent implements SearchableConfigurable {
	@NotNull private final Project project;
	@NotNull private final WatcherManager watcherManager;

	public WatchersPanel(@NotNull final Project project) {
		this.project = project;
		this.watcherManager = WatcherManager.getInstance(project);
	}

	@Override
	protected void processRemovedItems() {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@SuppressWarnings("SuspiciousMethodCalls")
	@Override
	protected boolean wasObjectStored(Object watcher) {
		return watcherManager.getWatchers().contains(watcher);
	}

	@NotNull
	@Override
	public String getId() {
		return "net.tatablack.fswatcher";
	}

	@Nullable
	@Override
	public Runnable enableSearch(String option) {
		return null;
	}

	@Nls
	@Override
	public String getDisplayName() {
		return "Watchers";
	}
}
