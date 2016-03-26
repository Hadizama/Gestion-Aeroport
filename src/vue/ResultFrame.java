package vue;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ResultFrame extends JFrame {
	
	private String content;
	
	public ResultFrame(String contenu){
		
		JPanel content = new JPanel();
		JTextArea textArea = new JTextArea(contenu);
		textArea.setEditable(false);
		textArea.setFont(new Font("Arial", 20, 20));
		content.add(textArea);
		
		this.content = contenu;
		this.setTitle("Planning");
		this.add(content);
		this.setDefaultCloseOperation(ResultFrame.DISPOSE_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
