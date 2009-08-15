package plugin;

import java.util.List;

import net.todd.osgi.platform.IServiceEventHandler;
import net.todd.osgi.platform.util.ServiceRegistryUtil;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {
	private WidgetDisplayer displayer;

	public void start(final BundleContext context) throws Exception {
		displayer = new WidgetDisplayer();
		displayer.addCloseListener(new Runnable() {
			public void run() {
				try {
					stop(context);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		});
		displayer.show();

		List<Widget> implementations = ServiceRegistryUtil.getServiceImplementations(context,
				Widget.class);
		for (Widget widget : implementations) {
			displayer.addWidget(widget);
		}

		ServiceRegistryUtil.addServiceListener(context, Widget.class, new IServiceEventHandler() {
			public void handleServiceEvent(BundleContext context,
					ServiceReference serviceReference, int eventType) {
				Widget widget = (Widget) context.getService(serviceReference);
				if (eventType == ServiceEvent.REGISTERED) {
					displayer.addWidget(widget);
				} else if (eventType == ServiceEvent.UNREGISTERING) {
					displayer.removeWidget(widget);
				}
			}
		});
	}

	public void stop(BundleContext context) throws Exception {
		displayer.hide();
	}
}
