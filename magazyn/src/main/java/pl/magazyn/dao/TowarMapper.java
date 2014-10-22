package pl.altkom.magazyn.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pl.altkom.magazyn.model.Towar;

public class TowarMapper implements RowMapper<Towar> {

    public Towar mapRow(ResultSet rs, int rowNum) throws SQLException {
        Towar towar = new Towar();
        towar.setId(rs.getLong("ID"));
        towar.setNazwa(rs.getString("nazwa"));
        towar.setOpis(rs.getString("opis"));
        towar.setCena(rs.getDouble("cena"));
        towar.setIlosc(rs.getDouble("ilosc"));
        towar.setKategoria(rs.getString("kategoria"));
        return towar;
    }
}
