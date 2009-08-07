package subplugin2;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import plugin.Widget;

public class Activator implements BundleActivator {
	public void start(BundleContext context) throws Exception {
		context.registerService(Widget.class.getName(), new SubWidget2(), new Hashtable());
	}

	public void stop(BundleContext context) throws Exception {
	}
}
