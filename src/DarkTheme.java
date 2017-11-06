import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;

public class DarkTheme extends DefaultMetalTheme
{
	
	public DarkTheme()
	{
		UIManager.put("RadioButton.background", new ColorUIResource(0x2d5559));
		UIManager.put("RadioButton.foreground", new ColorUIResource(0x7ca687));
		UIManager.put("RadioButton.selectionBackground", new ColorUIResource(0xfbedd2));
		UIManager.put("Button.background", new ColorUIResource(0x688c7f));
		UIManager.put("TextArea.selectionBackground", new ColorUIResource(0x688c7f));
		UIManager.put("Menu.foreground", new ColorUIResource(0x7ca687));
		UIManager.put("MenuItem.foreground", new ColorUIResource(0x7ca687));

	}
	
	public String getName()
	{
		return "Dark";	
	}
	
	protected ColorUIResource getPrimary1()
	{
		return new ColorUIResource(0x766a58);
	}

	protected ColorUIResource getPrimary2()
	{
		return new ColorUIResource(0x688c7f);
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
		return new ColorUIResource(0x7ca687);
	}

	protected ColorUIResource getSecondary3()
	{
		return new ColorUIResource(0x141426);
	}
	
	public ColorUIResource getMenuBackground()
	{
		return new ColorUIResource(0x2d5559);
	}

	public ColorUIResource getPrimaryControl()
	{
		return new ColorUIResource(0xff0000);
	}

	public ColorUIResource getPrimaryControlShadow()
	{
		return new ColorUIResource(0x00ff00);
	}
	
	// Menu bar borders.
	@Override
	public ColorUIResource getPrimaryControlDarkShadow()
	{
		return new ColorUIResource(0x7ca687);
	}
	
	@Override
	public ColorUIResource getAcceleratorForeground()
	{
		return new ColorUIResource(0xff0000);
	}
}
