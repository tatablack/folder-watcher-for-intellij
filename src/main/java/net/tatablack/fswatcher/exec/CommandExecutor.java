package net.tatablack.fswatcher.exec;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.vfs.newvfs.events.VFileEvent;
import net.tatablack.fswatcher.logging.SeparateLogger;
import net.tatablack.fswatcher.state.Watcher;
import org.apache.commons.exec.*;

import java.io.IOException;

/**
 * @author: Angelo Tata
 */
public class CommandExecutor {
	private static final Logger LOGGER = SeparateLogger.getInstance(CommandExecutor.class.getName());

	@SuppressWarnings("ConstantConditions")
	public static void process(Watcher watcher, VFileEvent event) {
		if (!(event != null && event.getFile() !=null)) return;

		LOGGER.debug("Processing" + event.getClass().getName() + " for " + watcher.getName());
		//ApplicationManager.getApplication().executeOnPooledThread();

		Executor executor = new DefaultExecutor();
		ExecuteWatchdog watchdog = new ExecuteWatchdog(120000);
		TimeoutResultHandler resultHandler = new TimeoutResultHandler(watchdog);

		executor.setWatchdog(watchdog);

		CommandLine commandLine = CommandExecutor.getCommandLine(
			watcher.getExecutable(),
			event.getFile().getPresentableUrl()
		);

		try {
			executor.execute(commandLine, resultHandler);
			resultHandler.waitFor();
			LOGGER.info(Integer.toString(resultHandler.getExitValue()));
		} catch (IOException ioEx) {
			ioEx.printStackTrace();
		} catch (InterruptedException iEx) {
			iEx.printStackTrace();
		}
	}

	private static CommandLine getCommandLine(String executable, String path) {
		//Map map = new HashMap();
		//map.put("file", new File("invoice.pdf"));
		//cmdLine.addArgument("${file}");
		//cmdLine.setSubstitutionMap(map);

		CommandLine commandLine = new CommandLine("cmd");
		commandLine.addArgument("/c");
		commandLine.addArgument(executable);
		commandLine.addArgument(path);

		return commandLine;
	}
}
