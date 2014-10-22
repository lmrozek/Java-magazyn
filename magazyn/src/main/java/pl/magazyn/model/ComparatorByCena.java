package pl.altkom.magazyn.model;

import java.util.Comparator;

public class ComparatorByCena implements Comparator<Towar> {

    @Override
    public int compare(Towar o1, Towar o2) {
        Double cen1 = new Double(o1.getCena());
        Double cen2 = new Double(o2.getCena());

        return cen1.compareTo(cen2);
    }

}
