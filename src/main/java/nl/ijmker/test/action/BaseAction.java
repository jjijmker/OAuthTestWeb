package nl.ijmker.test.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseAction implements Action {

	@Override
	public abstract void execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
