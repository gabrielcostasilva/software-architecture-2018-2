package edu.utfpr.cp.sa.dao;

import edu.utfpr.cp.sa.entity.Country;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class CountryDAO {

    private final String URL = "jdbc:derby:database";

    public boolean create(Country country) throws Exception {

        if (this.read().stream().map(Country::getName).anyMatch(e -> e.equals(country.getName()))) {
            throw new IllegalArgumentException("There already is a country with this name!");

        } else {

            try (Connection conn = DriverManager.getConnection(URL)) {

                PreparedStatement statement = conn.prepareStatement("INSERT INTO Country (name, acronym, phoneDigits) VALUES (?, ?, ?)");
                statement.setString(1, country.getName());
                statement.setString(2, country.getAcronym());
                statement.setInt(3, country.getPhoneDigits());

                if (statement.executeUpdate() > 0) {
                    return true;
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return false;
    }

    public Set<Country> read() {
        HashSet<Country> countries = new HashSet<>();

        try (Connection conn = DriverManager.getConnection(URL)) {

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM Country");

            Country currentCountry = null;

            int count = 0;

            while (result.next()) {
                currentCountry = new Country();
                currentCountry.setId(result.getLong("id"));
                currentCountry.setName(result.getString("name"));
                currentCountry.setAcronym(result.getString("acronym"));
                currentCountry.setPhoneDigits(result.getInt("phoneDigits"));

                countries.add(currentCountry);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

        return countries;
    }

    public boolean update(Country country) {
        
        if (this.read().stream().map(Country::getName).anyMatch(e -> e.equals(country.getName()))) {
            throw new IllegalArgumentException("There already is a country with this name!");

        } else {
            
            try (Connection conn = DriverManager.getConnection(URL)) {

                PreparedStatement statement = conn.prepareStatement("UPDATE Country SET name=?, acronym=?, phoneDigits=? WHERE id=?");
                statement.setString(1, country.getName());
                statement.setString(2, country.getAcronym());
                statement.setInt(3, country.getPhoneDigits());
                statement.setLong(4, country.getId());
                
                if (statement.executeUpdate() > 0) {
                    return true;
                }

            } catch (SQLException ex) {
                ex.printStackTrace();

            }
        }

        return false;
    }

    public boolean delete(Long id) {

        try (Connection conn = DriverManager.getConnection(URL)) {

            PreparedStatement statement = conn.prepareStatement("DELETE FROM Country WHERE id=?");
            statement.setLong(1, id);

            if (statement.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        
        return false;
    }

}
