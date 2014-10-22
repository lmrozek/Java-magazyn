package pl.altkom.magazyn.model;

import java.util.Comparator;

public class ComparatorByKat implements Comparator<Towar> {

    @Override
    public int compare(Towar o1, Towar o2) {
        String kat1 = o1.getKategoria();
        String kat2 = o2.getKategoria();
        return kat1.compareToIgnoreCase(kat2);
    }

}
