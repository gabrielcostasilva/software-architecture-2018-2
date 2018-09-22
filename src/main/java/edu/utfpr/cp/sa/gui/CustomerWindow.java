package edu.utfpr.cp.sa.gui;

import edu.utfpr.cp.sa.dao.CountryDAO;
import edu.utfpr.cp.sa.dao.CustomerDAO;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import edu.utfpr.cp.sa.entity.Country;
import edu.utfpr.cp.sa.entity.Customer;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;

class CustomerTableModel extends AbstractTableModel {

    private ArrayList<Customer> customers;
    private String columnNames[] = {"ID", "Name", "Phone", "Credit Limit", "Age", "Country"};

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
                return this.customers.get(rowIndex).getId();

            case 1:
                return this.customers.get(rowIndex).getName();

            case 2:
                return this.customers.get(rowIndex).getPhone();

            case 3:
                return this.customers.get(rowIndex).getCreditLimit();

            case 4:
                return this.customers.get(rowIndex).getAge();

            case 5:
                return this.customers.get(rowIndex).getCountry().getName();

            default:
                break;
        }

        return null;
    }

}

public class CustomerWindow extends JFrame {

    private JPanel contentPane;
    private JTextField id;
    private JTextField name;
    private JTextField phone;
    private JTextField age;
    private JComboBox<String> country;
    private JTable table;

    private CustomerDAO customerDAO;
    private CountryDAO countryDAO;

    private void cleanPanelData() {
        id.setText("");
        name.setText("");
        phone.setText("");
        age.setText("");
    }

    private void updatePanelData() {
        this.cleanPanelData();

        id.setText(String.valueOf(this.table.getModel().getValueAt(this.table.getSelectedRow(), 0)));
        name.setText(String.valueOf(this.table.getModel().getValueAt(this.table.getSelectedRow(), 1)));
        phone.setText(String.valueOf(this.table.getModel().getValueAt(this.table.getSelectedRow(), 2)));
        age.setText(String.valueOf(this.table.getModel().getValueAt(this.table.getSelectedRow(), 4)));
        country.setSelectedItem(String.valueOf(this.table.getModel().getValueAt(this.table.getSelectedRow(), 5)));
    }

    private void create() {
        Customer c = new Customer();
        Country selected = countryDAO.read().stream().filter(e -> e.getName().equalsIgnoreCase((String) country.getSelectedItem())).findFirst().get();

        try {
            c.setCountry(selected);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            return;

        }

        c.setAge(new Integer(age.getText()));

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

        try {
            customerDAO.create(c);
            JOptionPane.showMessageDialog(this, "Customer successfully added!");
            this.cleanPanelData();
            this.table.setModel(new CustomerTableModel(customerDAO.read()));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }

    private void update() {
        Customer c = customerDAO.read().stream().filter(e -> e.getId().equals(new Long(id.getText()))).findAny().get();
        Country selected = countryDAO.read().stream().filter(e -> e.getName().equalsIgnoreCase((String) country.getSelectedItem())).findFirst().get();

        try {
            c.setCountry(selected);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            return;

        }

        c.setAge(new Integer(age.getText()));

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

        try {
            customerDAO.update(c);
            JOptionPane.showMessageDialog(this, "Customer successfully updated!");
            this.cleanPanelData();
            this.table.setModel(new CustomerTableModel(customerDAO.read()));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }

    private void delete() {

        try {
            customerDAO.delete(new Long(id.getText()));
            
            JOptionPane.showMessageDialog(this, "Customer successfully deleted!");
            
            this.cleanPanelData();
            
            this.table.setModel(new CountryTableModel(countryDAO.read()));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public CustomerWindow(CustomerDAO customerDAO, CountryDAO countryDAO) {
        this.customerDAO = customerDAO;
        this.countryDAO = countryDAO;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JScrollPane panelTable = new JScrollPane();
        contentPane.add(panelTable, BorderLayout.CENTER);

        table = new JTable();
        table.setModel(new CustomerTableModel(customerDAO.read()));
        panelTable.setViewportView(table);
        table.getSelectionModel().addListSelectionListener(e -> this.updatePanelData());

        JPanel panelInclusion = new JPanel();
        contentPane.add(panelInclusion, BorderLayout.NORTH);
        panelInclusion.setLayout(new GridLayout(7, 2, 0, 0));

        JLabel lblId = new JLabel("Id");
        panelInclusion.add(lblId);

        id = new JTextField();
        id.setColumns(4);
        id.setEnabled(false);
        panelInclusion.add(id);

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

        country = new JComboBox<>(countryDAO.read().stream().map(Country::getName).toArray(String[]::new));
        panelInclusion.add(country);

        JButton btnCreate = new JButton("Create");
        panelInclusion.add(btnCreate);
        btnCreate.addActionListener(e -> this.create());

        JButton btnUpdate = new JButton("Update");
        panelInclusion.add(btnUpdate);
        btnUpdate.addActionListener(e -> this.update());

        JButton btnDelete = new JButton("Delete");
        panelInclusion.add(btnDelete);
        btnDelete.addActionListener(e -> this.delete());

        JButton btnClose = new JButton("Close");
        panelInclusion.add(btnClose);
        btnClose.addActionListener(e -> this.dispose());

        this.pack();
        this.setVisible(true);
    }

}
