package edu.utfpr.cp.sa.gui;

import edu.utfpr.cp.sa.dao.CountryDAO;
import edu.utfpr.cp.sa.dao.CustomerDAO;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.utfpr.cp.sa.entity.Country;
import edu.utfpr.cp.sa.entity.Customer;

import java.awt.FlowLayout;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private CountryDAO countryDAO;
        private CustomerDAO customerDAO;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		this.countryDAO = new CountryDAO();
		this.customerDAO = new CustomerDAO();
		
		setTitle("Customer & Country Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnCustomerManagement = new JButton("Customer Management");
		contentPane.add(btnCustomerManagement);
		btnCustomerManagement.addActionListener(e -> new CustomerWindow(customerDAO, countryDAO));
		
		JButton btnCountryManagement = new JButton("Country Management");
		btnCountryManagement.addActionListener(e -> new CountryWindow(countryDAO));
		contentPane.add(btnCountryManagement);
		
		pack();
	}

}
