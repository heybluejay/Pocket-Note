import java.awt.Font;
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
import javax.swing.JOptionPane;
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
		newFileButton.setToolTipText("Click here to clear the page.");
		JMenuItem openFileButton = new JMenuItem("Open...");
		openFileButton.setMnemonic('o');
		openFileButton.setAccelerator(KeyStroke.getKeyStroke("control O"));
		openFileButton.setToolTipText("Click here to clear the page and open a new file.");
		JMenuItem saveFileButton = new JMenuItem("Save");
		saveFileButton.setMnemonic('s');
		saveFileButton.setToolTipText("Click here to save the file at it's current location");
		JMenuItem saveAsFileButton = new JMenuItem("Save As...");
		saveAsFileButton.setMnemonic('a');
		saveAsFileButton.setAccelerator(KeyStroke.getKeyStroke("control shift S"));
		saveAsFileButton.setToolTipText("Click here to save the file at a new location.");
		JMenuItem exitFileButton = new JMenuItem("Exit");
		exitFileButton.setMnemonic('x');
		exitFileButton.setToolTipText("Click here to exit the application.");
		
		// Creating the Theme menu buttons.
		JRadioButton defaultTheme = new JRadioButton("Default Theme");
		JRadioButton solarTheme = new JRadioButton("Solarized Theme");
		JRadioButton darkTheme = new JRadioButton("Dark Theme");
		JRadioButton retroTheme = new JRadioButton("Retro Theme");
		
		// Creating the Font Button. Font Button changes the font face type.
		JMenuItem fontButton = new JMenuItem("Font Style...");
		// Creating the Color button. Color button changes font color.
	    JMenuItem fontColorButton = new JMenuItem("Font Color...");
		// Creating the Help menu buttons with hot keys.
		JMenuItem aboutButton = new JMenuItem("About");
		JMenuItem helpButton = new JMenuItem("Help");


		
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
		
		fontMenu.add(fontButton);
		
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
		
		FontStyleDialog fontStyleDialog = new FontStyleDialog(parent, parent.document);
	    
	    fontButton.addActionListener(new ActionListener()
	    {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				fontStyleDialog.setVisible(true);
			}
	    });
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
		
		newFileButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				parent.document.setText("");
				parent.currentFilePath = null;
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
					parent.currentFilePath = file.getPath();
					parent.openFile(parent.currentFilePath);
				}
			}	
		});
		
		saveFileButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
					if (parent.currentFilePath == null)
					{
						saveAsFileButton.doClick();
					}
					else
					{
						parent.saveFile(parent.currentFilePath);
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
					parent.currentFilePath = savePath;
				}
			}
			
		});
		
		exitFileButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				parent.setVisible(false);
				parent.dispose();
			}
			
		});
		
		aboutButton.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(parent, "This is a simple text editing application.");
			}
		});
		
		helpButton.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(parent, "File: \n"
						+ "Your typical file options. You can save and open files here. \n"
						+ "Font: \n"
						+ "You can change your font style and typeface here. \n"
						+ "Color:\n"
						+ "You can change your font color here.\n"
						+ "Themes: \n"
						+ "This allows you to change your document colors. they are preset.");
			}
		});
		
		defaultTheme.addActionListener(radioButtonListener);
		solarTheme.addActionListener(radioButtonListener);
		darkTheme.addActionListener(radioButtonListener);
		retroTheme.addActionListener(radioButtonListener);
		
	}
}
