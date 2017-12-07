//Pocket Note
//By Sidney Harwood
//11/2017

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.MetalTheme;
import javax.swing.plaf.metal.OceanTheme;


public class TextEditor extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	DocumentArea document = new DocumentArea();
	MainMenuBar menuBar = new MainMenuBar(this);
	UIDefaults originalDefaults;
	String currentFilePath;

	//Constructor
	public TextEditor()
	{		
		MetalLookAndFeel laf = (MetalLookAndFeel) UIManager.getLookAndFeel();
		originalDefaults = (UIDefaults) (laf.getDefaults()).clone();
				
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		this.addWindowListener(new ExitWindowListener());
		
		// Setting up our frame.
		setTitle("Pocket Note");
		setSize(600, 600);
		setLocation(400,200);
		setLayout(new BorderLayout());
		
		// Adding the top menu bar.
		add(menuBar, BorderLayout.NORTH);
				
		// Adding the write-able area.
		add(document, BorderLayout.CENTER);

		setTheme(OceanTheme.class);
		//openFile("E:/Projects/Pocket-Note/test.txt");
	}
	
	// Handles opening files.
	public void openFile(String path)
	{
		try
		{
			FileInputStream fileInput = new FileInputStream(path);
			byte[] fileData = new byte[fileInput.available()];
			
			fileInput.read(fileData);
			document.setText(new String(fileData));
			
			fileInput.close();
			document.setModified(false);
		}
		catch(IOException exception)
		{
			System.out.println("File not found!");
		}
	}
	
	// Handles saving files.
	public void saveFile(String path)
	{
		try
		{
			FileOutputStream fileOutput = new FileOutputStream(path);
			byte[] fileData = document.getText().getBytes();
			
			fileOutput.write(fileData);
			document.setText(new String(fileData));
			
			fileOutput.close();
			document.setModified(false);	
		}
		catch(IOException exception)
		{
			System.out.println("File not found!");
		}
	}
	
	// This method is used to change the theme.
	public void setTheme(Class<? extends MetalTheme> themeClass)
	{
		// Reset UIManager's style stuff to the LookAndFeel defaults so that themes don't have to explicitly replace each other's values.
	    UIManager.getDefaults().clear();
	    Font defaultFont = new Font("SansSerif", Font.PLAIN, 13);
	    UIManager.put("Menu.font", defaultFont);
	    UIManager.put("MenuItem.font", defaultFont);
	    UIManager.put("RadioButton.font", defaultFont);

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
	
	// Displays an unsaved changes dialog prompting the user to save, or exit without saving.
	public boolean showUnsavedChangesDialog()
	{
		int dialogResult = JOptionPane.showConfirmDialog(this, "You have unsaved changes! Would you like to save your changes?", "Warning", JOptionPane.YES_NO_CANCEL_OPTION);
		if (dialogResult == JOptionPane.YES_OPTION)
		{
			if (currentFilePath == null)
			{
				menuBar.saveAsFileButton.doClick();
			}
			else
			{
				saveFile(currentFilePath);
			}
			return true;
		}
		else if (dialogResult == JOptionPane.NO_OPTION)
		{
			return true;
		}
		
		return false;
	}
		
	public static void main(String[] args)
	{
		// Displays and runs the program.
		TextEditor mainWindow = new TextEditor();
		mainWindow.setVisible(true);
	}
}
