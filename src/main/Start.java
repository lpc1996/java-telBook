package main;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import iframe.MainIframe;

public class Start extends JFrame{
	private static final JDesktopPane DESKTOP_PANE = new JDesktopPane();
	public static void main(String[] arrgv) {
		
		MainIframe frame = new MainIframe();
//		frame.createJFrame();
	}
	
	public static void addIFrame(JInternalFrame iframe) {
		DESKTOP_PANE.add(iframe);
	}
}
