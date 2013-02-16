package net.tatablack.fswatcher.logging;

import com.intellij.openapi.diagnostic.Logger;

/**
 * @author: Angelo Tata
 */
public class SeparateLoggerManager {
	public static void initialize() {
		Logger.setFactory(new Logger.Factory() {
			public Logger getLoggerInstance(String category) {
				return new SeparateLogger(category);
			}
		});
	}
}
