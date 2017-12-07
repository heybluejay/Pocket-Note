import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FontStyleDialog extends JDialog
{
	public FontStyleDialog(JFrame parent, DocumentArea documentArea)
	{
		super(parent, "Font Style", Dialog.ModalityType.DOCUMENT_MODAL);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel rowOne = new JPanel();
		rowOne.setLayout(new FlowLayout());
		
		String[] fontFamilyNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		JComboBox<String> fontComboBox = new JComboBox<String>(fontFamilyNames);
		
		Integer[] fontSizes = { 8, 9, 10, 11, 12, 14, 16, 18, 20, 22, 24, 26, 28, 36, 48, 72 };
		JComboBox<Integer> fontSizeComboBox = new JComboBox<Integer>(fontSizes);
		
		JButton submitButton = new JButton("OK");
		
		rowOne.add(fontComboBox);
		rowOne.add(fontSizeComboBox);
		rowOne.add(submitButton);
		
		JPanel rowTwo = new JPanel();
		rowTwo.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JToggleButton boldButton = new JToggleButton("B");
		boldButton.setFont(boldButton.getFont().deriveFont(Font.BOLD));
		JToggleButton italicButton = new JToggleButton("I");
		italicButton.setFont(italicButton.getFont().deriveFont(Font.ITALIC));
		
		rowTwo.add(boldButton);
		rowTwo.add(italicButton);
		
		add(rowOne);
		add(rowTwo);
		pack();
		
		submitButton.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String selectedFont = (String)fontComboBox.getSelectedItem();
				int selectedFontSize = (int)fontSizeComboBox.getSelectedItem();
				int fontStyle = Font.PLAIN;
				
				if(boldButton.isSelected())
				{
					fontStyle = fontStyle | Font.BOLD;
				}
				
				if(italicButton.isSelected())
				{
					fontStyle = fontStyle | Font.ITALIC;
				}

				Font temp = new Font(selectedFont, fontStyle, selectedFontSize);
				
				documentArea.setFontStyle(temp);
				
			    setVisible(false);
				
			}
		});
	}
}
