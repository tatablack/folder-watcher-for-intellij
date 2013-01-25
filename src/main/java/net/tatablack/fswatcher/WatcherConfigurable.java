package net.tatablack.fswatcher;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * @author: Angelo Tata
 */
public class WatcherConfigurable implements Configurable {
	private TextFieldWithBrowseButton folderToWatchChooser;
	private JPanel watcherOptionsPanel;
	private TextFieldWithBrowseButton textFieldWithBrowseButton1;
	private WatcherManager manager;

	public WatcherConfigurable() {
		folderToWatchChooser.getButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileChooserDescriptor folderDescriptor = FileChooserDescriptorFactory.createSingleFolderDescriptor();
				VirtualFile chosenFolder = FileChooser.chooseFile(folderDescriptor, folderToWatchChooser, null, null);
				if (chosenFolder != null) {
					folderToWatchChooser.setText(chosenFolder.getPath().replace('/', File.separatorChar));
				}
			}
		});
	}

	@Nls
	@Override
	public String getDisplayName() {
		return "File watchers";
	}

	@Nullable
	@Override
	public String getHelpTopic() {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Nullable
	@Override
	public JComponent createComponent() {

		return watcherOptionsPanel;
	}

	@Override
	public boolean isModified() {
		return false;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void apply() throws ConfigurationException {

	}

	@Override
	public void reset() {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void disposeUIResources() {
		//To change body of implemented methods use File | Settings | File Templates.
	}
}
