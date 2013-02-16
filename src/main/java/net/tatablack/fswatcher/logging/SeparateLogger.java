package net.tatablack.fswatcher.logging;

import com.intellij.openapi.diagnostic.Logger;
import org.apache.log4j.Level;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;

/**
 * @author: Angelo Tata
 */
public class SeparateLogger extends Logger {
	private org.slf4j.Logger wrappedLogger;

	private static Factory separateFactory = new Logger.Factory() {
		public Logger getLoggerInstance(String category) {
			return new SeparateLogger(category);
		}
	};

	public SeparateLogger(String category) {
		this.wrappedLogger = org.slf4j.LoggerFactory.getLogger(category);
	}

	public static Logger getInstance(String category) {
		return separateFactory.getLoggerInstance(category);
	}

	@Override
	public boolean isDebugEnabled() {
		return false;
	}

	@Override
	public void debug(@NonNls String message) {
		this.wrappedLogger.debug(message);
	}

	@Override
	public void debug(@Nullable Throwable t) {
		this.wrappedLogger.debug("", t);
	}

	@Override
	public void debug(@NonNls String message, @Nullable Throwable t) {
		this.wrappedLogger.debug(message, t);
	}

	@Override
	public void error(@NonNls String message, @Nullable Throwable t, @NonNls String... details) {
		this.wrappedLogger.error(message, t);

		System.err.println("ERROR: " + message);
		if (t != null) t.printStackTrace();
		if (details != null && details.length > 0) {
			System.out.println("details: ");
			for (String detail : details) {
				System.out.println(detail);
			}
		}

		throw new AssertionError(message);
	}

	@Override
	public void info(@NonNls String message) {
		this.wrappedLogger.info(message);
	}

	@Override
	public void info(@NonNls String message, @Nullable Throwable t) {
		this.wrappedLogger.info(message, t);
	}

	@Override
	public void warn(@NonNls String message, @Nullable Throwable t) {
		this.wrappedLogger.warn(message, t);
	}

	@Override
	public void setLevel(Level level) {
	}
}
