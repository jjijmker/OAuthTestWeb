package nl.ijmker.test.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.ijmker.test.constant.ConfigConstants;
import nl.ijmker.test.util.ConfigUtil;

public class DottedProperties extends Properties {

	private static final long serialVersionUID = -7163604376274821849L;

	private static final Logger LOG = LoggerFactory.getLogger(ConfigUtil.class);

	private static Properties props = null;

	/**
	 * Returns the value of the key components[0].components[1].components[2]
	 * etc.
	 * 
	 * comp0.comp1.comp2=val0<br/>
	 * comp0.comp1.comp3=val1<br/>
	 * comp0.comp2.comp3=val2<br/>
	 * 
	 * get(comp0, comp1, comp2) returns val0<br/>
	 * get(comp0, comp1, comp3) returns val1<br/>
	 * get(comp0, comp1) returns null<br/>
	 * 
	 * Can be used to read property values from a property file with dotted
	 * properties.
	 * 
	 * @param components
	 * @return
	 */
	public static String get(String... components) {

		String key = StringUtils.join(components, ".");

		LOG.info("Key: " + key);

		String value = getConfigProperties().getProperty(key);

		LOG.info("Value: " + value);

		return value;
	}

	/**
	 * Returns the components occurring as the next component following
	 * components[0].components[1].components[2] etc.
	 * 
	 * comp0.comp1.comp2=val0<br/>
	 * comp0.comp1.comp3=val1<br/>
	 * comp0.comp2.comp3=val2<br/>
	 * 
	 * list(comp0, comp1) returns [comp2, comp3]<br/>
	 * list(comp0) returns [comp1, comp2]<br/>
	 * 
	 * Can be used to generate lists of options from a property file with dotted
	 * properties.
	 * 
	 * @param components
	 * @return
	 */
	public static Set<String> list(String... components) {

		String filterKey = StringUtils.join(components, ".");

		LOG.info("Filter Key: " + filterKey);

		Set<String> keySet = new HashSet<String>();

		for (Object keyObject : getConfigProperties().keySet()) {

			String key = keyObject.toString();

			if (key.startsWith(filterKey)) {

				String[] keyComponents = key.split("\\.");

				if (keyComponents.length > components.length) {

					String keyComponent = keyComponents[components.length];

					if (!keySet.contains(keyComponent)) {

						LOG.info("Match: " + keyComponent);

						keySet.add(keyComponent);
					}
				}
			}
		}

		LOG.info("Result: " + keySet.toString());

		return keySet;
	}

	/**
	 * @param servletContext
	 * @return
	 */
	private static Properties getConfigProperties() {

		if (props == null) {
			try {
				LOG.info("Loading config properties from " + ConfigConstants.FILE_PATH_CONFIG_PROPS);

				props = new Properties();
				InputStream propsStream = ConfigUtil.class.getResourceAsStream(ConfigConstants.FILE_PATH_CONFIG_PROPS);
				props.load(propsStream);

				LOG.info("Loaded {} properties from " + ConfigConstants.FILE_PATH_CONFIG_PROPS, props.size());

			} catch (IOException e) {
				LOG.error("Could not open config properties from " + ConfigConstants.FILE_PATH_CONFIG_PROPS, e);
				throw new RuntimeException("Could not open config properties", e);
			}
		}

		return props;
	}
}
