package pl.altkom.magazyn.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pl.altkom.magazyn.model.Towar;

@Repository
public class MagazynDaoDerby implements MagazynDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addTowar(Towar t) {
        try {
            String sql = "INSERT INTO towary(nazwa,opis,cena,ilosc,kategoria) values(?,?,?,?,?)";
            jdbcTemplate.update(sql, new Object[]{t.getNazwa(), t.getOpis(),
                t.getCena(), t.getIlosc(), t.getKategoria()});
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateTowar(Towar t) {
        String sql = "update towary set nazwa = ?, opis = ?, cena = ?, ilosc = ?, kategoria = ? where id = ?";
        jdbcTemplate.update(sql, t.getNazwa(), t.getOpis(), t.getCena(),
                t.getIlosc(), t.getKategoria(), t.getId());
    }

    @Override
    public Towar getTowar(long id) {
        String sql = "select * from towary where id = ?";
        Towar towar = jdbcTemplate.queryForObject(sql, new Object[]{id},
                new TowarMapper());
        return towar;
    }

    @Override
    public void removeTowar(long id) {
        String sql = "delete from towary where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Towar> getAllTowar() {
        String sql = "select * FROM towary";
        List<Towar> towary = jdbcTemplate.query(sql, new TowarMapper());
        return towary;
    }
    @Override
    public List<String> getKategorie() {
        String sql = "select kategoria FROM towary group by kategoria";
        List<String> kategorie = jdbcTemplate.queryForList(sql, String.class);
        return kategorie;
    }

}
