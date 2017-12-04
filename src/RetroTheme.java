import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;

public class RetroTheme extends DefaultMetalTheme
{
	// Menu border colors = primary 1	
	// Button background colors = primary 2
	// Unknown primary 3
	// Unknown secondary 1
	// Menu bar bottom border =secondary 2
	// JPanel default background secondary 3
	public RetroTheme()
	{
		UIManager.put("RadioButton.background", new ColorUIResource(0xd92534));
		UIManager.put("RadioButton.selectionBackground", new ColorUIResource(0xfbedd2));
		UIManager.put("Button.background", new ColorUIResource(0x84bf9e));
		UIManager.put("TextArea.selectionBackground", new ColorUIResource(0x84bf9e));
		UIManager.put("ToolTip.background", new ColorUIResource(0x84bf9e));
	}
	
	public String getName()
	{
		return "Retro";	
	}
	
	protected ColorUIResource getPrimary1()
	{
		return new ColorUIResource(0x3e3e32);
	}

	protected ColorUIResource getPrimary2()
	{
		return new ColorUIResource(0x84bf9e);
	}

	protected ColorUIResource getPrimary3()
	{
		return new ColorUIResource(0xf3caa0);
	}

	protected ColorUIResource getSecondary1()
	{
		return new ColorUIResource(0xfde6cc);
	}

	protected ColorUIResource getSecondary2()
	{
		return new ColorUIResource(0x84bf9e);
	}

	protected ColorUIResource getSecondary3()
	{
		return new ColorUIResource(0x3e3e32);
	}
	
	public ColorUIResource getMenuBackground()
	{
		return new ColorUIResource(0xd92534);
	}
	
	
	//public ColorUIResource getPrimaryControl()
	//{
	//	return new ColorUIResource(0xff0000);
	//}

	public ColorUIResource getPrimaryControlShadow()
	{
		return new ColorUIResource(0x00ff00);
	}
	
	// Menu bar borders.
	@Override
	public ColorUIResource getPrimaryControlDarkShadow()
	{
		return new ColorUIResource(0x84bf9e);
	}
	
	@Override
	public ColorUIResource getAcceleratorForeground()
	{
		return new ColorUIResource(0xff0000);
	}
}