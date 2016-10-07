package nl.ijmker.test.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jan IJmker
 * 
 *         This command is specific to a certain server and action.
 *
 */
public abstract class SpecificCommand implements Command {

	@Override
	public abstract void execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
