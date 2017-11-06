import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.MetalTheme;
import javax.swing.plaf.metal.OceanTheme;


public class TextEditor extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	DocumentArea document = new DocumentArea();
	MainMenuBar menuBar = new MainMenuBar(this);
	UIDefaults originalDefaults;

	//Constructor
	public TextEditor()
	{		
		MetalLookAndFeel laf = (MetalLookAndFeel) UIManager.getLookAndFeel();
		originalDefaults = (UIDefaults) (laf.getDefaults()).clone();
				
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Setting up our frame.
		setTitle("Pocket Note");
		setSize(600, 600);
		setLocation(400,200);
		setLayout(new BorderLayout());
		
		add(menuBar, BorderLayout.NORTH);
				
		// Adding the write-able area.
		add(document, BorderLayout.CENTER);

		setTheme(OceanTheme.class);
	}
		
	public static void main(String[] args)
	{
		// Displays and runs the program.
		TextEditor mainWindow = new TextEditor();
		mainWindow.setVisible(true);
	}

	public void setTheme(Class<? extends MetalTheme> themeClass)
	{
		// Reset UIManager's style stuff to the LookAndFeel defaults so that themes don't have to explicitly replace each other's values.
	    UIManager.getDefaults().clear();

	    try
		{
			// Set the theme in memory.
			MetalLookAndFeel.setCurrentTheme(themeClass.newInstance());

	    	// Refresh the current Look and Feel using the new theme.
			UIManager.setLookAndFeel(new MetalLookAndFeel());
		}
	    catch (Exception e)
		{
			e.printStackTrace();
		}

	    // Refresh all the components with the new Look and Feel.
		SwingUtilities.updateComponentTreeUI(this);
	}
}
