package pl.altkom.magazyn.model;

import java.util.Comparator;

public class ComparatorByNazwa implements Comparator<Towar> {

    @Override
    public int compare(Towar o1, Towar o2) {
        String naz1 = o1.getNazwa();
        String naz2 = o2.getNazwa();
        return naz1.compareToIgnoreCase(naz2);
    }

}
