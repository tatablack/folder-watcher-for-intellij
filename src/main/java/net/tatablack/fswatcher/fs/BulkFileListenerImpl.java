package net.tatablack.fswatcher.fs;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.openapi.vfs.newvfs.BulkFileListener;
import com.intellij.openapi.vfs.newvfs.events.*;
import com.intellij.util.messages.MessageBusConnection;
import com.intellij.vcsUtil.VcsUtil;
import net.tatablack.fswatcher.WatcherManager;
import net.tatablack.fswatcher.logging.SeparateLogger;
import net.tatablack.fswatcher.state.Watcher;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

/**
 * @author: Angelo Tata
 */
public class BulkFileListenerImpl extends BulkFileListener.Adapter {
	private static final Logger LOGGER = SeparateLogger.getInstance(BulkFileListenerImpl.class.getName());
	private final MessageBusConnection messageBusConnection;
	private final Project project;

	public BulkFileListenerImpl(final Project project) {
		this.project = project;
		this.messageBusConnection = ApplicationManager.getApplication().getMessageBus().connect(this.project);
	}

	public void start() {
		LOGGER.info("MessageBus connected");
		messageBusConnection.subscribe(VirtualFileManager.VFS_CHANGES, this);
	}

	@Override
	public void after(@NotNull List<? extends VFileEvent> events) {
		for (VFileEvent event : events) {
			if (!mustProcess(event)) continue;

			final VirtualFile file = getFileForEvent(event);
			if (mustSkip(file)) continue;

			WatcherManager manager = WatcherManager.getInstance(project);
			Collection<Watcher> watchers = manager.getWatchers();

			for (Watcher watcher : watchers) {
				manager.process(watcher, event);
			}
		}
	}

	/**
	 * We only care about files if:
	 * - they are local
	 * - they are in the current project
	 *
	 * @param file
	 * @return
	 */
	private boolean mustSkip(VirtualFile file) {
		return file == null ||
				!file.isInLocalFileSystem() ||
				(file.getPresentableUrl() != null &&
					project.getPresentableUrl() != null &&
					!file.getPresentableUrl().startsWith(project.getPresentableUrl())
				);
	}

	private boolean mustProcess(VFileEvent event) {
		return event instanceof VFileContentChangeEvent ||
				event instanceof VFileCopyEvent ||
				event instanceof VFileCreateEvent ||
				event instanceof VFileMoveEvent ||
				(event instanceof VFilePropertyChangeEvent &&
				((VFilePropertyChangeEvent) event).getPropertyName().equals(VirtualFile.PROP_NAME));
	}

	@Nullable
	private static VirtualFile getFileForEvent(VFileEvent event) {
		return VcsUtil.getVirtualFile(event.getPath());
	}
}
