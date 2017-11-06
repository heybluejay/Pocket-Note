import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;

public class LucasTheme extends DefaultMetalTheme
{
	
	public LucasTheme()
	{
		UIManager.put("RadioButton.background", new ColorUIResource(0xe7b590));
		//UIManager.put("RadioButton.focus", new ColorUIResource(0xff0000)); //new ColorUIResource(0xff0000)
		UIManager.put("RadioButton.selectionBackground", new ColorUIResource(0xfbedd2));
		UIManager.put("Button.background", new ColorUIResource(0xfde6cc));
		
	}
	
	public String getName()
	{
		return "Lucas";	
	}
	
	protected ColorUIResource getPrimary1()
	{
		return new ColorUIResource(0x766a58);
	}

	protected ColorUIResource getPrimary2()
	{
		return new ColorUIResource(0xfbedd2);
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
		return new ColorUIResource(0xfde6cc);
	}

	protected ColorUIResource getSecondary3()
	{
		return new ColorUIResource(0x766a58);
	}
	
	public ColorUIResource getMenuBackground()
	{
		return new ColorUIResource(0xe7b590);
	}
	
	public ColorUIResource getWhite()
	{
		return new ColorUIResource(0xfbedd2);
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
		return new ColorUIResource(0x766a58);
	}
	
	@Override
	public ColorUIResource getAcceleratorForeground()
	{
		return new ColorUIResource(0xff0000);
	}
}
