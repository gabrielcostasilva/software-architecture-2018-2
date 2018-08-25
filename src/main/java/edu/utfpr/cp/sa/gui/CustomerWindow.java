package edu.utfpr.cp.sa.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import edu.utfpr.cp.sa.entity.Country;
import edu.utfpr.cp.sa.entity.Customer;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;

class CustomerTableModel extends AbstractTableModel {
	
	private ArrayList<Customer> customers;
	private String columnNames[] = {"Name", "Phone", "Credit Limit", "Age", "Country"};
	
	public CustomerTableModel(Set<Customer> customers) {
		this.customers = new ArrayList<>(customers);
	}
	
	@Override
	public int getRowCount() {
		return customers.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		switch (columnIndex) {
			case 0:
				return this.customers.get(rowIndex).getName();
				
			case 1:
				return this.customers.get(rowIndex).getPhone();
				
			case 2:
				return this.customers.get(rowIndex).getCreditLimit();

			case 3:
				return this.customers.get(rowIndex).getAge();

			case 4:
				return this.customers.get(rowIndex).getCountry().getName();
				
			default:
				break;
		}
		
		return null;
	}
	
}

public class CustomerWindow extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField phone;
	private JTextField age;
	private JComboBox<String> country;
	private JTable table;
	
	private Set<Customer> customers;
	private Set<Country> countries;
	
	private void create () {
		Customer c = new Customer();
		Country selected = countries
								.stream()
								.filter(
										e -> e.getName()
												.equalsIgnoreCase(
														(String) country.getSelectedItem()))
								.findFirst()
								.get();
		
		try {
			c.setCountry(selected);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			return;
			
		}
		
		c.setAge(new Integer (age.getText()));
		
		try {
			c.setName(name.getText());
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			return;
			
		}
		
		try {
			c.setPhone(phone.getText());

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			return;
			
		}
						
		if (this.customers.add(c)) {
			JOptionPane.showMessageDialog(this, "Customer successfully added!");
			this.table.setModel(new CustomerTableModel(customers));
			this.pack();
		
		} else
			JOptionPane.showMessageDialog(this, "Sorry, customer already exists");
		
	}
	
	public CustomerWindow(Set<Customer> customers, Set<Country> countries) {
		this.customers = customers;
		this.countries = countries;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane panelTable = new JScrollPane();
		contentPane.add(panelTable, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new CustomerTableModel(customers));
		panelTable.setViewportView(table);
		
		JPanel panelInclusion = new JPanel();
		contentPane.add(panelInclusion, BorderLayout.NORTH);
		panelInclusion.setLayout(new GridLayout(5, 2, 0, 0));
		
		JLabel lblName = new JLabel("Name");
		panelInclusion.add(lblName);
		
		name = new JTextField();
		panelInclusion.add(name);
		name.setColumns(10);
		
		JLabel lblPhone = new JLabel("Phone");
		panelInclusion.add(lblPhone);
		
		phone = new JTextField();
		panelInclusion.add(phone);
		phone.setColumns(10);

		JLabel lblAge = new JLabel("Age");
		panelInclusion.add(lblAge);
		
		age = new JTextField();
		panelInclusion.add(age);
		age.setColumns(10);
		
		JLabel lblCountry = new JLabel("Country");
		panelInclusion.add(lblCountry);
		
		country = new JComboBox<>(countries.stream().map(Country::getName).toArray(String[]::new));
		panelInclusion.add(country);
		
		JButton btnCreate = new JButton("Create");
		panelInclusion.add(btnCreate);
		btnCreate.addActionListener(e -> this.create());
		
		JButton btnClose = new JButton("Close");
		panelInclusion.add(btnClose);
		btnClose.addActionListener(e -> this.dispose());
		
		this.pack();
		this.setVisible(true);
	}

}
