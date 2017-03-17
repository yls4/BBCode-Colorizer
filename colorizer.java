package colorizer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.security.auth.Subject;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class colorizer {
    static String inputText;
    static JTextArea output = new JTextArea();
    static JCheckBox whisper = new JCheckBox();
    static JCheckBox underline = new JCheckBox();
    static JCheckBox italic = new JCheckBox();
    static JCheckBox bold = new JCheckBox();
    static JCheckBox strikethrough = new JCheckBox();
    static JCheckBox sup = new JCheckBox();
    static JButton clear = new JButton();
    static String key = "PlaceHolderString190429038";
	static int width = 650;
	static int height = 500;
	static ArrayList<String> list = new ArrayList<String>();
	static String regex = "(\\w+|[^\\w\\s])";
	static Pattern p = Pattern.compile(regex);
	static Matcher m;
	static boolean b;
	
	public static void colorize(String text) {
		inputText = text;
		int index = 0;
		String sub = "";
		String sub2 = "";
		String und = "";
		String und2 = "";
		String it = "";
		String it2 = "";
		String b = "";
		String b2 = "";
		String strike = "";
		String strike2 = "";
		String supe = "";
		String supe2 = "";
		
		if (whisper.isSelected()) {
			sub = "[sub]";
			sub2 = "[/sub]";
		}
		if (underline.isSelected()) {
			und = "[u]";
			und2 = "[/u]";
		}
		if (italic.isSelected()) {
			it = "[i]";
			it2 = "[/i]";
		}
		if (bold.isSelected()) {
			b = "[b]";
			b2 = "[/b]";
		}
		if (strikethrough.isSelected()) {
			strike = "[s]";
			strike2 = "[/s]";
		}
		if (sup.isSelected()) {
			supe = "[sup]";
			supe2 = "[/sup]";
		}
		//inputText = inputText.replace("!", key+"!"+key);
		//inputText = inputText.replace(".", key+"."+key);
		//inputText = inputText.replace("?", key+"?"+key);
		//inputText = inputText.replace(",", key+","+key);
		//inputText = inputText.replace(";", key+";"+key);
		//inputText = inputText.replace(":", key+":"+key);
		//inputText = inputText.replace("\"", key+"\""+key);
		//inputText = inputText.trim();
		
		//output.setText(text.replaceAll(regex, "<hello>$1<hello>"));
		String[] strArray = inputText.split("\\s+");
		m = p.matcher(text);
		ArrayList<String> matches = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "[color=" + list.get(index) + "]" + supe + sub + "$1" + sub2 + supe2 + "[/color]");
			if (index == list.size()-1) {
				index = 0;
			}
			else {
				index++;
			}
		}
		String out = "";
		//System.out.println(matches.toString());
		/*
		for (int i=0; i<strArray.length; i++) {
			String color = list.get(index);
			String[] strArray2 = strArray[i].split(key);
			String arrayVal = "";
				for (int i2=0; i2<strArray2.length; i2++) {
					color = list.get(index);
					//System.out.println(strArray2[i2]);
					arrayVal += strArray2[i2].replace(strArray2[i2], "[color=" + color + "]" + sub + supe + strArray2[i2] + sub2 + supe2 + "[/color]");
					if (index == list.size()-1) {
						index = 0;
					}
					else {
						index++;
					}
				}
				strArray[i] = arrayVal;
			}
		
		String out = "";
		*/
		if (underline.isSelected()) {
			out += und;
		}
		if (italic.isSelected()) {
			out += it;
		}
		if (bold.isSelected()) {
			out += b;
		}
		if (strikethrough.isSelected()) {
			out += strike;
		}

		out += sb.toString();
		/*
		for (int i=0; i<strArray.length; i++) {
			
			if (i < strArray.length-1 && (
					strArray[i+1].contains("!") || 
					strArray[i+1].contains("?") || 
					strArray[i+1].contains(".") || 
					strArray[i+1].contains(",") || 
					strArray[i+1].contains(";") || 
					strArray[i+1].contains(":") ||
					strArray[i+1].contains("\"")
					)) {
				out += strArray[i];
			}
			
			out += strArray[i] + " ";
			if (index == list.size()-1) {
				index = 0;
			}
			else {
				index++;
			}
		}
		
		
		*/
		
		if (strikethrough.isSelected()) {
			out += strike2;
		}
		if (bold.isSelected()) {
			out += b2;
		}
		if (italic.isSelected()) {
			out += it2;
		}
		if (underline.isSelected()) {
			out += und2;
		}
		output.setText(out);
	}
	
	public static void main(String[] args) throws IOException
	{
		File file = new File("colors.txt");
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       // process the line.
		       list.add(line);
		    }
		}
		createGui();
	}
	
	private static void createGui()
	{	
		JFrame frame = new JFrame("Colorizer");
		SpringLayout layout = new SpringLayout();
		JPanel jpanel = new JPanel(layout);
		
		frame.setSize(width, height);
		Border border = BorderFactory.createLineBorder(Color.BLACK);

		JLabel label1 = new JLabel("Input");
		jpanel.add(label1);
		layout.putConstraint(SpringLayout.WEST, label1, 130, SpringLayout.WEST, jpanel);
		
		JLabel labelResult = new JLabel("Output");
		layout.putConstraint(SpringLayout.WEST, labelResult, 450, SpringLayout.WEST, jpanel);
		jpanel.add(labelResult);
		
		JTextArea text1 = new JTextArea(15, 25);
		JScrollPane sp = new JScrollPane(text1);
		text1.setBorder(border);
		layout.putConstraint(SpringLayout.WEST, sp, 10, SpringLayout.WEST, jpanel);
		layout.putConstraint(SpringLayout.NORTH, sp, 30, SpringLayout.NORTH, jpanel);
		text1.setLineWrap(true);
		jpanel.add(sp);
		text1.getDocument().addDocumentListener(new DocumentListener() {
				@Override
				public void changedUpdate(DocumentEvent e) {
					colorize(text1.getText());
				}
				@Override
				public void insertUpdate(DocumentEvent e) {
					colorize(text1.getText());
				}
				@Override
				public void removeUpdate(DocumentEvent e) {
					colorize(text1.getText());
				}
			});
		
		output = new JTextArea(15, 25);
		JScrollPane sp2 = new JScrollPane(output);
		output.setBorder(border);
		layout.putConstraint(SpringLayout.WEST, output, 333, SpringLayout.WEST, jpanel);
		layout.putConstraint(SpringLayout.NORTH, output, 30, SpringLayout.NORTH, jpanel);
		output.setLineWrap(true);
		jpanel.add(output);
		jpanel.add(sp2);
		
		whisper = new JCheckBox("Whisper");
				whisper.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (sup.isSelected()) {
					whisper.setSelected(false);
				}
				colorize(text1.getText());
			}
		});
		jpanel.add(whisper);
		layout.putConstraint(SpringLayout.NORTH, whisper, 280, SpringLayout.NORTH, jpanel);


		underline = new JCheckBox("Underline");
		underline.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				colorize(text1.getText());
			}
		});
		jpanel.add(underline);
		layout.putConstraint(SpringLayout.NORTH, underline, 300, SpringLayout.NORTH, jpanel);

		italic = new JCheckBox("Italic");
		italic.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				colorize(text1.getText());
			}
		});
		jpanel.add(italic);
		layout.putConstraint(SpringLayout.NORTH, italic, 320, SpringLayout.NORTH, jpanel);
		
		bold = new JCheckBox("Bold");
		bold.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				colorize(text1.getText());
			}
		});
		jpanel.add(bold);
		layout.putConstraint(SpringLayout.NORTH, bold, 340, SpringLayout.NORTH, jpanel);
		
		strikethrough = new JCheckBox("Strikethrough");
		strikethrough.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				colorize(text1.getText());
			}
		});
		jpanel.add(strikethrough);
		layout.putConstraint(SpringLayout.NORTH, strikethrough, 360, SpringLayout.NORTH, jpanel);
		
		sup = new JCheckBox("Superscript");
		sup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (whisper.isSelected()) {
					sup.setSelected(false);
				}
				colorize(text1.getText());
			}
		});
		jpanel.add(sup);
		layout.putConstraint(SpringLayout.NORTH, sup, 380, SpringLayout.NORTH, jpanel);
		
		clear = new JButton("Clear");
		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				text1.setText("");
				output.setText("");
			}
		});
		jpanel.add(clear);
		layout.putConstraint(SpringLayout.NORTH, clear, 410, SpringLayout.NORTH, jpanel);
		
		frame.add(jpanel);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
