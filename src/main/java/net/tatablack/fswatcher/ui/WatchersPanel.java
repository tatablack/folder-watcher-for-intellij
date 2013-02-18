package net.tatablack.fswatcher.ui;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MasterDetailsComponent;
import com.intellij.openapi.util.Conditions;
import net.tatablack.fswatcher.WatcherManager;
import net.tatablack.fswatcher.action.AddAction;
import net.tatablack.fswatcher.logging.SeparateLogger;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author: Angelo Tata
 */
public class WatchersPanel extends MasterDetailsComponent implements SearchableConfigurable {
	private static final Logger LOGGER = SeparateLogger.getInstance(WatchersPanel.class.getName());

	@NotNull private final Project project;
	@NotNull private final WatcherManager watcherManager;


	public WatchersPanel(@NotNull final Project project) {
		this.project = project;
		this.watcherManager = WatcherManager.getInstance(project);

		super.initTree();
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

	@Override
	public void reset() {
		LOGGER.debug("RESET was called");
		super.reset();
	}

	@Nullable
	@Override
	protected ArrayList<AnAction> createActions(boolean fromPopup) {
		final ArrayList<AnAction> actions = new ArrayList<AnAction>();

		actions.add(new AddAction(watcherManager));
		actions.add(new MyDeleteAction(forAll(Conditions.alwaysTrue())));

		return actions;
	}

	private void reloadTree() {
		myRoot.removeAllChildren();

		/*
		lessProfileConfigurableForms.clear();
		Collection<LessProfile> collection = lessManager.getProfiles();
		for (LessProfile profile : collection) {
			LessProfile clone = new LessProfile(profile);
			final LessProfileConfigurableForm lessProfileConfigurableForm = new LessProfileConfigurableForm(project, clone, this, TREE_UPDATER);
			lessProfileConfigurableForms.add(lessProfileConfigurableForm);
			addNode(new MyNode(lessProfileConfigurableForm), myRoot);
		}
		isInitialized.set(true);
		*/
	}

}
