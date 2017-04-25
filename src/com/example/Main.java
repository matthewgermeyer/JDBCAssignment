package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;

public class Main {
    public static void main(String[] args) throws SQLException {
        //Trevor
        LocalDate localDate = LocalDate.of(2017, 4, 19);
        long epochMilliseconds = localDate.atStartOfDay(ZoneId.systemDefault()).toEpochSecond() * 1000;

        try (Connection conn = DatabaseUtils.getInstance().getConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("UPDATE person set contacted = ? where id = ?");

            pStmt.setDate(1, new java.sql.Date(epochMilliseconds));
            pStmt.setInt(2, 3);
            pStmt.executeUpdate();

            //Frank
            localDate = LocalDate.of(2017, 1, 23);
            epochMilliseconds = localDate.atStartOfDay(ZoneId.systemDefault()).toEpochSecond() * 1000;
            pStmt.setDate(1, new java.sql.Date(epochMilliseconds));
            pStmt.setInt(2, 5);
            pStmt.executeUpdate();

            //Cybil
            //birth date
            localDate = LocalDate.of(1950, 02, 15);
            Long birthDateMilliseconds = localDate.atStartOfDay(ZoneId.systemDefault()).toEpochSecond() * 1000;

            //contacted date
            localDate = LocalDate.of(1986, 01, 29);
            epochMilliseconds = localDate.atStartOfDay(ZoneId.systemDefault()).toEpochSecond() * 1000;

            pStmt = conn.prepareStatement(" INSERT INTO person (name, dob, contacted) VALUES " +
                    "" +
                    "(?, ?, ?)");
            pStmt.setString(1, "Cybil Six");
            pStmt.setDate(2, new java.sql.Date(birthDateMilliseconds));
            pStmt.setDate(3, new java.sql.Date(epochMilliseconds));
            //pStmt.executeUpdate();

            //Give Cybil an email
            pStmt = conn.prepareStatement("INSERT INTO email (email, person_id) VALUES (?, ?)");
            pStmt.setString(1, "CybilSix@gmail.com");
            pStmt.setInt(2, 7);
            //pStmt.executeUpdate();

            //Delete Cybil's newly created email.
            pStmt = conn.prepareStatement("DELETE FROM email where person_id = ?");
            pStmt.setInt(1, 7);
            pStmt.executeUpdate();

            //Give Cybil 2 new emails
            //First email
            pStmt = conn.prepareStatement("INSERT INTO email (email, person_id) VALUES (?, ?)");
            pStmt.setString(1, "CybilSix@gmail.com");
            pStmt.setInt(2, 7);
            pStmt.executeUpdate();

            //2nd email
            pStmt = conn.prepareStatement("INSERT INTO email (email, person_id) VALUES (?, ?)");
            pStmt.setString(1, "CybilSix@yahoooo.com");
            pStmt.setInt(2, 7);
            pStmt.executeUpdate();

            //Give her an address
            pStmt = conn.prepareStatement("INSERT INTO address (street1, city, stateAbbr, zip, person_id) VALUES (?, ?, ?, ?, ?)");
            pStmt.setString(1, "2515 Marquart dr");
            pStmt.setString(2, "Austin");
            pStmt.setString(3, "TX");
            pStmt.setString(4, "78744");
            pStmt.setInt(5, 7);
            pStmt.executeUpdate();

        }
    }
}
