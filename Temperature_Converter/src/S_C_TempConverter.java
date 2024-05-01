/**
 * Program Name:	S_C_TempConverter.java
 * Purpose: 		This program will create a GUI application that will allow the user to enter a temperature and then calculate the equivalent temperatures.
 * @author 			Sara Chupa
 * Date:			Apr 18, 2024
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.event.*;

public class S_C_TempConverter extends JFrame
{
	// class wide scope
	JPanel topPnl, centerPnl, rightPnl;
	JTextField fahrTextFld, celsiusTextFld, kelvinTextFld;
	JLabel topLbl, fahrLbl, celsLbl, kelvinLbl;
	JButton clearBtn, calcBtn;
	
	//constructor
	public S_C_TempConverter() {
		
	// Call to the super class
	super("Sara Chupa's Temperature Converter");
	
	// Boiler plate code
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setLayout(new BorderLayout());
	this.setSize(500, 200);
	this.setLocationRelativeTo(null);

	
	// top panel
	topPnl = new JPanel();
	topPnl.setBackground(Color.BLACK);
	topPnl.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
	topLbl = new JLabel("Enter a temperature in any field and then press the Calculate button");
	topLbl.setForeground(Color.WHITE);
	topPnl.add(topLbl);
	
	// center panel components
	centerPnl = new JPanel();
	centerPnl.setLayout(new GridLayout(3, 2, 10, 20));
	
	// labels
	fahrLbl = new JLabel("Fahrenheit Temp:");
	celsLbl = new JLabel("Celsius Temp:");
	kelvinLbl = new JLabel("Kelvin Temp:");
	
	// text fields
	fahrTextFld = new JTextField("");
	celsiusTextFld = new JTextField("");
	kelvinTextFld = new JTextField("");
	
	// add components to panel
	centerPnl.add(fahrLbl);
	centerPnl.add(fahrTextFld);
	centerPnl.add(celsLbl);
	centerPnl.add(celsiusTextFld);
	centerPnl.add(kelvinLbl);
	centerPnl.add(kelvinTextFld);
	
	// right panel components
	rightPnl = new JPanel();
	rightPnl.setLayout(new GridLayout(2, 1, 0, 30));
	rightPnl.setBackground(Color.CYAN);
	
	// buttons
	clearBtn = new JButton("Clear Fields");
	calcBtn = new JButton("Calculate");
	
	// register action listeners
	clearBtn.addActionListener(new ButtonListener());
	calcBtn.addActionListener(new ButtonListener());
	
	rightPnl.add(clearBtn);
	rightPnl.add(calcBtn);
	
	
	// add panels
	this.add(topPnl, BorderLayout.NORTH);
	this.add(centerPnl, BorderLayout.CENTER);
	this.add(rightPnl, BorderLayout.EAST);
		
	
		
		// last line
		this.setVisible(true);
	} // end constructor
	
	// inner class
	public class ButtonListener implements ActionListener, S_C_TempConvertable {
		double celsius, fahr, kelvin;
		String belowZeroWarningMessage = "Warning: This value is below absolute zero... please re-enter.";
		
		@Override
		public void actionPerformed(ActionEvent ev)
		{
			// source is clear button
			if (ev.getSource().equals(clearBtn)) {
				
				fahrTextFld.setText("");
				celsiusTextFld.setText("");
				kelvinTextFld.setText("");
			}
			
			// source is calculate button
			if (ev.getSource().equals(calcBtn)) {
			
				if (!(fahrTextFld.getText().isEmpty())) {
					
					double fahren = Double.parseDouble(fahrTextFld.getText());
					
					//validate input is greater than absolute zero
					if (fahren < ABS_ZERO_F) {
						JOptionPane.showMessageDialog(rootPane, belowZeroWarningMessage, "Temperature Converter", JOptionPane.WARNING_MESSAGE);
						fahrTextFld.setText("");
						fahrTextFld.requestFocus();
					}
					else {
						// calculate kelvin && celsius
						celsius = convertFtoC(fahren);
						kelvin = convertFtoK(fahren);
						
						// set texts fields
						celsiusTextFld.setText("" + celsius);
						kelvinTextFld.setText("" + kelvin);
					}
				} // end outer if 
				
				else if (!(celsiusTextFld.getText().isEmpty())) {
					
					// parse to double
					double cels = Double.parseDouble(celsiusTextFld.getText());
					
					// validate input is greater than absolute zero
					if (cels < ABS_ZERO_C) {
						JOptionPane.showMessageDialog(rootPane, belowZeroWarningMessage, "Temperature Converter", JOptionPane.WARNING_MESSAGE);
						celsiusTextFld.setText("");
						celsiusTextFld.requestFocus();
					}
					else {
						// calculate fahr && kelv
						kelvin = convertCtoK(cels);
						fahr = convertCtoF(cels);
						
						//set text fields
						fahrTextFld.setText("" + fahr);
						kelvinTextFld.setText("" + kelvin);
					}
				} // end outer else if 
				
				else if (!(kelvinTextFld.getText().isEmpty())) {
					// parse to double
					double kelv = Double.parseDouble(kelvinTextFld.getText());
					
					// validate input is greater than absolute zero
					if (kelv < ABS_ZERO_K) {
						JOptionPane.showMessageDialog(rootPane, belowZeroWarningMessage, "Temperature Converter", JOptionPane.WARNING_MESSAGE);
						kelvinTextFld.setText("");
						kelvinTextFld.requestFocus();
					}
					else {
						//calculate fahr & celsius
						celsius = convertKtoC(kelv);
						fahr = convertKtoF(kelv);
						
						//set text
						fahrTextFld.setText("" + fahr);
						celsiusTextFld.setText("" + celsius);
					}
				} // end outer else if 
				
				else {
					// display warning message for empty text fields
					String emptyFieldWarningMessage = "Warning: Text fields cannot be left empty.";
					JOptionPane.showMessageDialog(rootPane, emptyFieldWarningMessage, "Temperature Converter", JOptionPane.WARNING_MESSAGE);
					fahrTextFld.requestFocus();   // request focus to the top text field
				} // end else
				
			} // end outer if 
			
		} // End action performed method


		@Override
		public double convertCtoK(double cTemp)
		{
			double kelv = cTemp + 273.15;
			return kelv;
		}

		@Override
		public double convertKtoC(double kTemp)
		{
			return kTemp - 273.15;
		}

		@Override
		public double convertFtoK(double fTemp)
		{
			double cels = 5.0/9.0 * (fTemp - 32.0);
			double kelv = cels + 273.15;
			return kelv;
		}

		@Override
		public double convertKtoF(double kTemp)
		{
			double cels = kTemp - 273.15;
			double fahr = (9.0/5.0) * (cels + 32.0);
			return fahr;
			
		}

		@Override
		public double convertFtoC(double fTemp)
		{
			double celsius = (5.0/9.0) * (fTemp - 32.0);
			return celsius;
			
		}

		@Override
		public double convertCtoF(double cTemp)
		{
			return (9.0/5.0) * (cTemp + 32.0);
		}
		
	} // end inner class

	public static void main(String[] args)
	{
		// create anonymous object
		new S_C_TempConverter();
		
	} //End of main method
} // End of class