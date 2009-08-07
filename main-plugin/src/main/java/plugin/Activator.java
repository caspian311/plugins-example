package plugin;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.Filter;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {
	private WidgetDisplayer displayer;

	public void start(final BundleContext context) throws Exception {
		displayer = new WidgetDisplayer();
		displayer.show();

		Filter widgetFilter = context.createFilter("(" + Constants.OBJECTCLASS + "="
				+ Widget.class.getName() + ")");

		ServiceReference[] allServiceReferences = context.getAllServiceReferences(null,
				widgetFilter.toString());
		if (allServiceReferences != null) {
			for (ServiceReference serviceReference : allServiceReferences) {
				handleServiceEvent(context, serviceReference, ServiceEvent.REGISTERED);
			}
		}
		context.addServiceListener(new ServiceListener() {
			public void serviceChanged(ServiceEvent serviceEvent) {
				handleServiceEvent(context, serviceEvent.getServiceReference(), serviceEvent
						.getType());
			}
		}, widgetFilter.toString());
		displayer.addCloseListener(new Runnable() {
			public void run() {
				try {
					stop(context);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		});
	}

	public void stop(BundleContext context) throws Exception {
		displayer.hide();
	}

	private void handleServiceEvent(final BundleContext context, ServiceReference serviceReference,
			int eventType) {
		Widget widget = (Widget) context.getService(serviceReference);
		switch (eventType) {
			case ServiceEvent.REGISTERED:
				displayer.addWidget(widget);
				break;
			case ServiceEvent.UNREGISTERING:
				displayer.removeWidget(widget);
				break;
		}
	}
}
