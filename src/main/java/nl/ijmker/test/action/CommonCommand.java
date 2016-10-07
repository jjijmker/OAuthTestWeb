package nl.ijmker.test.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jan IJmker
 * 
 *         This command is common to all servers and actions.
 *
 */
public abstract class CommonCommand implements Command {

	@Override
	public abstract void execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
