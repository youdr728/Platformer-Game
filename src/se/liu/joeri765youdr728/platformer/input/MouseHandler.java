package se.liu.joeri765youdr728.platformer.input;

import se.liu.joeri765youdr728.platformer.AbstractPanel;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * @author      Johannes Eriksson <joeri765 @ student.liu.se>
 * @author      Yousef Drgham <youdr728 @ student.liu.se>
 * @version     1.0
 * @since       1.0
 */
public class MouseHandler extends MouseAdapter
{
    private AbstractPanel panel;

    public MouseHandler(AbstractPanel panel) {
	this.panel = panel;
    }

    private Logger logger = Logger.getLogger(MouseHandler.class.getName() );
    private SimpleFormatter formatter = new SimpleFormatter();
    private FileHandler fileHandler = null;

    {
	try {
	    fileHandler = new FileHandler("LogFile.log", 0, 1, true);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    @Override public void mouseClicked(final MouseEvent e) {
	if(e.getButton() == MouseEvent.BUTTON1){
	    try {
		logger.addHandler(fileHandler);
		fileHandler.setFormatter(formatter);
		panel.mouseClicked(e.getX(), e.getY());

	    } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
		logger.info(ex.getMessage());
		ex.printStackTrace();
	    }
	}
    }

    @Override public void mouseMoved(final MouseEvent e) {
	panel.mouseMoved(e.getX(), e.getY());
    }


}
