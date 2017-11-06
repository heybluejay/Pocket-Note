import java.awt.event.*;

import javax.swing.ButtonGroup;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.plaf.metal.OceanTheme;

public class MainMenuBar extends JMenuBar
{
	private static final long serialVersionUID = 1L;
	
	TextEditor parent;
	JDialog fontColorPopup;

	public MainMenuBar(TextEditor parent)
	{
		this.parent = parent;
		
		// Creating the menu bar buttons.
		JMenu fileMenu = new JMenu("File");
		JMenu fontMenu = new JMenu("Font");
		JMenu colorMenu = new JMenu("Color");
		JMenu themeMenu = new JMenu("Theme");
		JMenu helpMenu = new JMenu("Help");
		
		// Adding the menu bar buttons.
		this.add(fileMenu);
		this.add(fontMenu);
		this.add(colorMenu);
		this.add(themeMenu);
		this.add(helpMenu);
		
		// Creating the File menu buttons with hot keys.
		JMenuItem newFileButton = new JMenuItem("New");
		newFileButton.setMnemonic('n');
		JMenuItem openFileButton = new JMenuItem("Open...");
		openFileButton.setMnemonic('o');
		JMenuItem saveFileButton = new JMenuItem("Save");
		saveFileButton.setMnemonic('s');
		JMenuItem saveAsFileButton = new JMenuItem("Save As...");
		saveAsFileButton.setMnemonic('a');
		JMenuItem exitFileButton = new JMenuItem("Exit");
		exitFileButton.setMnemonic('x');
		
		JMenuItem fontColorButton = new JMenuItem("Font Color...");
		
		// Creating the Theme menu buttons.
		JRadioButton defaultTheme = new JRadioButton("Default Theme");
		JRadioButton solarTheme = new JRadioButton("Solarized Theme");
		JRadioButton darkTheme = new JRadioButton("Dark Theme");
		JRadioButton retroTheme = new JRadioButton("Retro Theme");
		
		// Radio Button Listeners
		ActionListener radioButtonListener = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource() == defaultTheme)
				{
					parent.setTheme(OceanTheme.class);
				}
				if (e.getSource() == solarTheme)
				{
					parent.setTheme(LucasTheme.class);  // Solarized theme
				}
				if (e.getSource() == darkTheme)
				{
					parent.setTheme(DarkTheme.class);
				}
				if (e.getSource() == retroTheme)
				{
					parent.setTheme(RetroTheme.class);
				}
			}
			
		};
		
		// Creating the Help menu buttons with hot keys.
		JMenuItem aboutButton = new JMenuItem("About");
		JMenuItem helpButton = new JMenuItem("Help");
		// ToDo: Add a shortcuts button or put it under help sub button
		
		// Adding the File menu buttons.
		fileMenu.add(newFileButton);
		fileMenu.add(openFileButton);
		fileMenu.addSeparator();          // Create a divider
		fileMenu.add(saveFileButton);
		fileMenu.add(saveAsFileButton);
		fileMenu.addSeparator();          //Create a divider
		fileMenu.add(exitFileButton);
		
	    JColorChooser fontColorChooser = new JColorChooser();
	    fontColorChooser.setPreviewPanel(new JPanel());
		
	    ActionListener colorSubmit = new ActionListener()
	    {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		parent.document.setTextColor(fontColorChooser.getColor());
	    	}
	    };
	    
		fontColorButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				fontColorPopup = JColorChooser.createDialog(parent, "Font Color", true, fontColorChooser, colorSubmit, null);
				fontColorPopup.setVisible(true);
			}
		});
		
		colorMenu.add(fontColorButton);
		
		// Adding the Theme menu buttons as groups.
		ButtonGroup radioButtonGroup = new ButtonGroup();
		radioButtonGroup.add(defaultTheme);
		radioButtonGroup.add(solarTheme);
		radioButtonGroup.add(darkTheme);
		radioButtonGroup.add(retroTheme);
		
		// Adding theme Radio Buttons
		themeMenu.add(defaultTheme);
		themeMenu.add(solarTheme);
		themeMenu.add(darkTheme);
		themeMenu.add(retroTheme);
		
		defaultTheme.addActionListener(radioButtonListener);
		solarTheme.addActionListener(radioButtonListener);
		darkTheme.addActionListener(radioButtonListener);
		retroTheme.addActionListener(radioButtonListener);
		
		// Adding the Help menu buttons.
		helpMenu.add(aboutButton);
		helpMenu.add(helpButton);
	}
}
