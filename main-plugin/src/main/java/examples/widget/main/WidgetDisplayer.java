package examples.widget.main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class WidgetDisplayer {
	private final JFrame frame;
	private final JPanel panel;

	public WidgetDisplayer() {
		frame = new JFrame("Widgets!");

		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(panel, BorderLayout.CENTER);
	}

	public void show() {
		frame.setVisible(true);
	}

	public void hide() {
		frame.setVisible(false);
	}

	public void addWidget(Widget widget) {
		panel.add(widget);
		panel.repaint();
		frame.pack();
	}

	public void removeWidget(Widget widget) {
		panel.remove(widget);
		panel.repaint();
		frame.pack();
	}

	public void addCloseListener(final Runnable runnable) {
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				runnable.run();
			}
		});
	}
}
