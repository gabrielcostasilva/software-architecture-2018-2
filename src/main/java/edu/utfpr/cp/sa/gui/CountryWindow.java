package edu.utfpr.cp.sa.gui;

import edu.utfpr.cp.sa.business.CountryBusiness;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import edu.utfpr.cp.sa.entity.Country;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

class CountryTableModel extends AbstractTableModel {

    private ArrayList<Country> countries;
    private String columnNames[] = {"ID", "Name", "Acronym", "Phone Digits"};

    public CountryTableModel(Set<Country> countries) {
        this.countries = new ArrayList<>(countries);
    }

    @Override
    public int getRowCount() {
        return countries.size();
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
                return this.countries.get(rowIndex).getId();
                
            case 1:
                return this.countries.get(rowIndex).getName();

            case 2:
                return this.countries.get(rowIndex).getAcronym();

            case 3:
                return this.countries.get(rowIndex).getPhoneDigits();

            default:
                break;
        }

        return null;
    }

}

public class CountryWindow extends JFrame {

    private JPanel contentPane;
    private JTextField id;
    private JTextField name;
    private JTextField acronym;
    private JTextField phoneDigits;
    private JTable table;
    private CountryBusiness countryBusiness;
    
    
    private void cleanPanelData() {
        id.setText("");
        name.setText("");
        acronym.setText("");
        phoneDigits.setText("");
    }
    
    private void updatePanelData() {
        this.cleanPanelData();
        
        id.setText(String.valueOf(this.table.getModel().getValueAt(this.table.getSelectedRow(), 0)));
        name.setText(String.valueOf(this.table.getModel().getValueAt(this.table.getSelectedRow(), 1)));
        acronym.setText(String.valueOf(this.table.getModel().getValueAt(this.table.getSelectedRow(), 2)));
        phoneDigits.setText(String.valueOf(this.table.getModel().getValueAt(this.table.getSelectedRow(), 3)));
    }

    private void create() {
        Country c = new Country();
        c.setName(name.getText());
        c.setAcronym(acronym.getText());
        c.setPhoneDigits(new Integer(phoneDigits.getText()));

        try {
            countryBusiness.create(c);
            JOptionPane.showMessageDialog(this, "Country successfully added!");
            this.table.setModel(new CountryTableModel(countryBusiness.read()));
            
            this.cleanPanelData();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    private void update() {
        
        try {
            
            Country c = countryBusiness.read().stream().filter(e -> e.getId().equals(new Long(id.getText()))).findAny().get();
            
            c.setName(name.getText());
            c.setAcronym(acronym.getText());
            c.setPhoneDigits(new Integer(phoneDigits.getText()));

            countryBusiness.update(c);
            JOptionPane.showMessageDialog(this, "Country successfully updated!");
            this.cleanPanelData();
            this.table.setModel(new CountryTableModel(countryBusiness.read()));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    private void delete() {
     
        try {
            countryBusiness.delete(new Long (id.getText()));
            JOptionPane.showMessageDialog(this, "Country successfully deleted!");
            this.cleanPanelData();
            this.table.setModel(new CountryTableModel(countryBusiness.read()));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public CountryWindow(CountryBusiness countryBusiness) {
        this.countryBusiness = countryBusiness;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JScrollPane panelTable = new JScrollPane();
        contentPane.add(panelTable, BorderLayout.CENTER);

        table = new JTable();
        table.setModel(new CountryTableModel(countryBusiness.read()));
        panelTable.setViewportView(table);
        table.getSelectionModel().addListSelectionListener(e -> this.updatePanelData());

        JPanel panelInclusion = new JPanel();
        contentPane.add(panelInclusion, BorderLayout.NORTH);
        panelInclusion.setLayout(new GridLayout(6, 2, 0, 0));

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

        JLabel lblAcronym = new JLabel("Acronym");
        panelInclusion.add(lblAcronym);

        acronym = new JTextField();
        panelInclusion.add(acronym);
        acronym.setColumns(10);

        JLabel lblPhoneDigits = new JLabel("Phone Digits");
        panelInclusion.add(lblPhoneDigits);

        phoneDigits = new JTextField();
        panelInclusion.add(phoneDigits);
        phoneDigits.setColumns(10);

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
