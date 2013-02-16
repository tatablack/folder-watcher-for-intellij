package net.tatablack.fswatcher.ui;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.project.Project;
import net.tatablack.fswatcher.logging.SeparateLogger;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author: Angelo Tata
 */
public class WatcherConfigurable extends SearchableConfigurable.Parent.Abstract implements Configurable.NoScroll {
	private static final Logger logger = SeparateLogger.getInstance(WatcherConfigurable.class.getName());

	private WatchersPanel watchersPanel;

	public WatcherConfigurable(final Project project) {
		watchersPanel = new WatchersPanel(project);
	}

	@Nls
	@Override
	public String getDisplayName() {
		return "File watchers";
	}

	@Nullable
	@Override
	public String getHelpTopic() {
		return null;
	}

	@Nullable
	@Override
	public JComponent createComponent() {
		return watchersPanel.createComponent();
	}

	@Override
	public boolean isModified() {
		boolean result = false;

		if (watchersPanel != null) result = watchersPanel.isModified();

		return result;
	}

	@Override
	public void apply() throws ConfigurationException {
		if (watchersPanel != null) watchersPanel.apply();
	}

	@Override
	public void reset() {
		if (watchersPanel != null) watchersPanel.reset();
	}

	@Override
	public void disposeUIResources() {
		watchersPanel = null;
	}

	public boolean hasOwnContent() {
		return true;
	}

	public boolean isVisible() {
		return true;
	}

	@Override
	protected Configurable[] buildConfigurables() {
		return new Configurable[0];
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
}
