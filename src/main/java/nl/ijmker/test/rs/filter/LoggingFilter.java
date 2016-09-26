package nl.ijmker.test.rs.filter;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingFilter implements ClientRequestFilter {
	
	private static final Logger LOG = LoggerFactory.getLogger(LoggingFilter.class);

	@Override
	public void filter(ClientRequestContext requestContext) throws IOException {
		
		LOG.info("Entity: " + requestContext.getEntity());
		
	}

}
