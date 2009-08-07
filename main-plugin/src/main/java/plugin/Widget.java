package plugin;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

public abstract class Widget extends Component {
	private static final long serialVersionUID = 7724662495410577075L;

	public Widget() {
		super();
		setPreferredSize(new Dimension(100, 100));
		setSize(new Dimension(100, 100));
	}

	@Override
	public void paint(Graphics g) {
		drawWidget(g);
	}

	public abstract void drawWidget(Graphics g);
}
