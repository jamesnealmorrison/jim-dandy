package com.jimmie.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.springframework.stereotype.Component;

@Component
public class ConsolePanel extends JPanel implements PropertyChangeListener, ActionListener {
	private static final long serialVersionUID = 1234567890L;
	JTextArea textArea = null;
	JTextField textField = null;
	String textFieldValue = null;

	public ConsolePanel()
	{
		this.setLayout(new BorderLayout(5,5));
// This used to not be here at all:
		this.setPreferredSize(new Dimension(100,300));
		textArea = new JTextArea(10, 20);
		textArea.setEditable(false);
		JScrollPane scrollPaneForConsole = new JScrollPane(textArea);

		textField = new JTextField();
		textField.addActionListener(this);

		add(scrollPaneForConsole,BorderLayout.CENTER);
		add(textField,BorderLayout.SOUTH);
	}

	public void propertyChange(PropertyChangeEvent evt)
	{
		String text = (String) evt.getNewValue();
		if ("newText".equalsIgnoreCase(evt.getPropertyName()))
		{
			textArea.append(text + "\n");
			textArea.setCaretPosition(textArea.getDocument().getEndPosition().getOffset()-1);
			repaint();
			validate();
		}
	}

	public void actionPerformed(ActionEvent evt)
	{
		textFieldValue = evt.getActionCommand();
		/* After hitting enter, we need to empty the text box. */
		textField.setText(null);
	}

	public String getTextFieldValue()
	{
		String tempText = textFieldValue;
		/* After a call to getTextFieldValue, we need to empty it. */
		textFieldValue = null;

		return tempText;
	}

	public void setTextFieldValue(String textFieldValue)
	{
		this.textFieldValue = textFieldValue;
	}
}
