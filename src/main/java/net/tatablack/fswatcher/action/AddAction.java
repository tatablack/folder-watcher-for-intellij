package net.tatablack.fswatcher.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.ui.InputValidator;
import com.intellij.openapi.ui.Messages;
import com.intellij.util.PlatformIcons;
import net.tatablack.fswatcher.WatcherManager;
import net.tatablack.fswatcher.state.Watcher;
import org.jetbrains.annotations.Nullable;

/**
 * @author: Angelo Tata
 */
public class AddAction extends AnAction {
	private final static String addText = "Add";
	private final static String addDescription = "Add a new watcher";
	private final WatcherManager watcherManager;

	public AddAction(WatcherManager watcherManager) {
		super(addText, addDescription, PlatformIcons.ADD_ICON);
		this.watcherManager = watcherManager;
	}


	@Override
	public void actionPerformed(AnActionEvent e) {
		final String name = askForProfileName("Create new Watcher");
		if (name == null) return;


		final Watcher watcher = new Watcher(name);
		//TODO implement
		//addProfileNode(lessProfile);
	}

	@Nullable
	private String askForProfileName(String title) {
		final String message = "Watcher name";
		return Messages.showInputDialog(message, title, Messages.getQuestionIcon(), "", new InputValidator() {
			public boolean checkInput(String s) {
				//TODO should watchers be a map, then?
				//return !watcherManager.getWatchers().containsKey(s) && s.length() > 0;
				return false;
			}

			public boolean canClose(String s) {
				return checkInput(s);
			}
		});
	}
}
