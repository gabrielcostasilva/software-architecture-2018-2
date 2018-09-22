package edu.utfpr.cp.sa.gui;

import edu.utfpr.cp.sa.dao.CountryDAO;
import edu.utfpr.cp.sa.dao.CustomerDAO;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;

public class MainWindow extends JFrame {

    private JPanel contentPane;
    private CountryDAO countryDAO;
    private CustomerDAO customerDAO;

    public static void main(String[] args) {
        // Create tables in the database
        try (Connection conn = DriverManager.getConnection("jdbc:derby:database;create=true")) {
            conn.createStatement().executeUpdate(
                    "CREATE TABLE COUNTRY "
                    + "(ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT COUNTRY_PK PRIMARY KEY,"
                    + "NAME VARCHAR(50) NOT NULL,"
                    + "ACRONYM VARCHAR(10), "
                    + "PHONEDIGITS INT)");

            conn.createStatement().executeUpdate(
                    "CREATE TABLE CUSTOMER"
                    + "(ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT CUSTOMER_PK PRIMARY KEY,"
                    + "NAME VARCHAR(50) NOT NULL,"
                    + "PHONE VARCHAR(30),"
                    + "AGE INT, "
                    + "CREDITLIMIT REAL, "
                    + "COUNTRYID BIGINT)");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        new MainWindow().setVisible(true);
    }

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
