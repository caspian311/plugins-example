package examples.widget.subplugin2;

import java.awt.Color;
import java.awt.Graphics;

import examples.widget.main.Widget;


public class SubWidget2 extends Widget {
	private static final long serialVersionUID = -8594350029847846033L;

	public void drawWidget(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getSize().width - 1, getSize().height - 1);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getSize().width - 1, getSize().height - 1);
		g.setColor(Color.RED);
		g.drawString(":)", 50, 50);
	}
}
