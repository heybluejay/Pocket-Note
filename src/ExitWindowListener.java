import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ExitWindowListener implements WindowListener
{

	@Override
	public void windowActivated(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		TextEditor mainWindow = (TextEditor)e.getWindow();
		if (mainWindow.document.getModified() && mainWindow.showUnsavedChangesDialog() == false)
		{
			return;
		}

		e.getWindow().dispose();
	}

	@Override
	public void windowDeactivated(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

}
