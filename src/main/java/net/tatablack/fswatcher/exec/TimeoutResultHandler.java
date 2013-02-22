package net.tatablack.fswatcher.exec;

import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;

/**
 * @author: Angelo Tata
 */
public class TimeoutResultHandler extends DefaultExecuteResultHandler {
    private ExecuteWatchdog watchdog;

	public TimeoutResultHandler(ExecuteWatchdog watchdog) {
		this.watchdog = watchdog;
	}

	public TimeoutResultHandler(int exitValue) {
		super.onProcessComplete(exitValue);
	}

	public void onProcessComplete(int exitValue) {
		super.onProcessComplete(exitValue);
		System.out.println("[resultHandler] The document was successfully printed ...");
	}

	public void onProcessFailed(ExecuteException e){
		super.onProcessFailed(e);

		if (watchdog != null && watchdog.killedProcess()) {
		    System.err.println("[resultHandler] The print process timed out");
		} else {
		    System.err.println("[resultHandler] The print process failed to do : " + e.getMessage());
		}
	}
}
