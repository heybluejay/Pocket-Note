import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class DocumentArea extends JPanel
{
	private static final long serialVersionUID = 1L;
	JTextArea textArea;
	
	public DocumentArea()
	{
		setAlignmentY(CENTER_ALIGNMENT);
		
		// Creates a page with some default settings.
		textArea = new JTextArea();
		textArea.setPreferredSize(new Dimension(350, 500));
		textArea.setLineWrap(true);
		textArea.setTabSize(2);
				
		// Adds a 1px border around the page.
		LineBorder grayBorder = new LineBorder(Color.LIGHT_GRAY, 1);
		// Creates text padding and then adds it below.
		CompoundBorder border = BorderFactory.createCompoundBorder(grayBorder, new EmptyBorder(15, 15, 15, 15));
		
		textArea.setBorder(border);
		
		add(textArea);
	} 
	
	public void setText(String text)
	{
		textArea.setText(text);
	}
	
	public String getText()
	{
		return textArea.getText();
	}
	
	public void setTextColor(Color c)
	{
		textArea.setForeground(c);
	}
	
	public void setFontStyle(Font font)
	{
		textArea.setFont(font);
	}
}