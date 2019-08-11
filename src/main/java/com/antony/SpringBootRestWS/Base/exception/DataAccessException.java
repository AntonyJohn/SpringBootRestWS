/**
 * 
 */
package com.antony.SpringBootRestWS.Base.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Antony John
 *
 */
public class DataAccessException extends DataStoreSystemException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -199557901655420032L;
	private static final Log LOG = LogFactory.getLog(DataAccessException.class);

	/**
	 * @param message
	 */
	public DataAccessException(String message) {
		super(message);
		LOG.error(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
		LOG.error(message, cause);
	}
}
