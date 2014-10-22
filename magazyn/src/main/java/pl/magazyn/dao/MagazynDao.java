package pl.altkom.magazyn.dao;

import java.util.List;
import pl.altkom.magazyn.model.Towar;

public interface MagazynDao {

	public void addTowar(Towar t);

	public void updateTowar(Towar t);

	public Towar getTowar(long id);

	public void removeTowar(long id);

	public List<Towar> getAllTowar();
        
        public List<String> getKategorie();
}
