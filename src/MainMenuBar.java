import java.awt.event.*;
import java.io.File;
import java.nio.file.Paths;

import javax.swing.ButtonGroup;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
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
		
		
		// Creating the File menu buttons with hot keys.
		JMenuItem newFileButton = new JMenuItem("New");
		newFileButton.setMnemonic('n');
		JMenuItem openFileButton = new JMenuItem("Open...");
		openFileButton.setMnemonic('o');
		openFileButton.setAccelerator(KeyStroke.getKeyStroke("control O"));
		JMenuItem saveFileButton = new JMenuItem("Save");
		saveFileButton.setMnemonic('s');
		JMenuItem saveAsFileButton = new JMenuItem("Save As...");
		saveAsFileButton.setMnemonic('a');
		saveAsFileButton.setAccelerator(KeyStroke.getKeyStroke("control shift S"));
		JMenuItem exitFileButton = new JMenuItem("Exit");
		exitFileButton.setMnemonic('x');
		
		JMenuItem fontColorButton = new JMenuItem("Font Color...");
		
		// Creating the Theme menu buttons.
		JRadioButton defaultTheme = new JRadioButton("Default Theme");
		JRadioButton solarTheme = new JRadioButton("Solarized Theme");
		JRadioButton darkTheme = new JRadioButton("Dark Theme");
		JRadioButton retroTheme = new JRadioButton("Retro Theme");
		
		// Creating the Help menu buttons with hot keys.
		JMenuItem aboutButton = new JMenuItem("About");
		JMenuItem helpButton = new JMenuItem("Help");
		// ToDo: Add a shortcuts button or put it under help sub button
		
		
		// Adding the menu bar buttons.
		this.add(fileMenu);
		this.add(fontMenu);
		this.add(colorMenu);
		this.add(themeMenu);
		this.add(helpMenu);
		
		// Adding the File menu buttons.
		fileMenu.add(newFileButton);
		fileMenu.add(openFileButton);
		fileMenu.addSeparator();          // Create a divider
		fileMenu.add(saveFileButton);
		fileMenu.add(saveAsFileButton);
		fileMenu.addSeparator();          //Create a divider
		fileMenu.add(exitFileButton);
		
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
		
		// Adding the Help menu buttons.
		helpMenu.add(aboutButton);
		helpMenu.add(helpButton);
		
	    JColorChooser fontColorChooser = new JColorChooser();
	    fontColorChooser.setPreviewPanel(new JPanel());
	    
	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text File (*.txt)", "txt"));
	    fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Data File (*.dat)", "dat"));
		
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
		
		openFileButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				int input = fileChooser.showOpenDialog(parent);
				if(input == JFileChooser.APPROVE_OPTION)
				{
					File file = fileChooser.getSelectedFile();
					parent.openFile(file.getPath());
				}
			}
			
		});
		
		saveAsFileButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				int input = fileChooser.showSaveDialog(parent);
				if(input == JFileChooser.APPROVE_OPTION)
				{
					File file = fileChooser.getSelectedFile();

					String savePath = file.getPath();
					
					if (fileChooser.getFileFilter() instanceof FileNameExtensionFilter)
					{
						FileNameExtensionFilter currentFilter = (FileNameExtensionFilter)fileChooser.getFileFilter();
						
						if (Paths.get(savePath).getFileName().toString().lastIndexOf('.') == -1)
						{
							savePath += "." + currentFilter.getExtensions()[0];
						}
					}
					
					parent.saveFile(savePath);
				}
			}
			
		});
		
		
		defaultTheme.addActionListener(radioButtonListener);
		solarTheme.addActionListener(radioButtonListener);
		darkTheme.addActionListener(radioButtonListener);
		retroTheme.addActionListener(radioButtonListener);
		
	}
}
