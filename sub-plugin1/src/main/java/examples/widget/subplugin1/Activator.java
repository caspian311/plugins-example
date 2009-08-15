package examples.widget.subplugin1;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import examples.widget.main.Widget;


public class Activator implements BundleActivator {
	public void start(BundleContext context) throws Exception {
		context.registerService(Widget.class.getName(), new SubWidget(), new Hashtable());
	}

	public void stop(BundleContext context) throws Exception {
	}
}
