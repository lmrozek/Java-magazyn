package pl.altkom.magazyn.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.altkom.magazyn.dao.MagazynDao;
import pl.altkom.magazyn.model.ComparatorByCena;
import pl.altkom.magazyn.model.ComparatorByKat;
import pl.altkom.magazyn.model.ComparatorByNazwa;
import pl.altkom.magazyn.model.Towar;

@Controller
public class MagazynController {

    private static List<Towar> towary;
    private List<String> kategorie;
    private static boolean ascOrderKat = true;
    private static boolean ascOrderNazwa = true;
    private static boolean ascOrderCena = true;
    private static String[] checkboxParam = {};
    private double cena1Param = 0;
    private double cena2Param = Double.MAX_VALUE;

    @Autowired
    private MagazynDao md;

    @RequestMapping(value = "/magazyn", method = RequestMethod.GET)
    public String magazyn(Model model, HttpServletRequest request) {
        cena1Param = 0;
        cena2Param = Double.MAX_VALUE;
        if (towary == null) {
            towary = md.getAllTowar();
        }
        kategorie = md.getKategorie();

        if (request.getParameter("id") != null
                && request.getParameter("action") != null) {
            if (request.getParameter("action").equals("delete")) {
                md.removeTowar(Integer.parseInt(request.getParameter("id")));
                towary = md.getAllTowar();
                kategorie = md.getKategorie();
                checkboxParam = new String[0];
            }
        }
        if (request.getParameter("sortBy") != null) {
            if (request.getParameter("sortBy").equals("kat")) {
                ascOrderNazwa = true;
                ascOrderCena = true;
                if (ascOrderKat) {
                    ascOrderKat = false;
                    Collections.sort(towary, new ComparatorByKat());
                } else {
                    ascOrderKat = true;
                    Collections.sort(towary, Collections.reverseOrder(new ComparatorByKat()));
                }
            } else if (request.getParameter("sortBy").equals("nazwa")) {
                ascOrderKat = true;
                ascOrderCena = true;
                if (ascOrderNazwa) {
                    ascOrderNazwa = false;
                    Collections.sort(towary, new ComparatorByNazwa());
                } else {
                    ascOrderNazwa = true;
                    Collections.sort(towary, Collections.reverseOrder(new ComparatorByNazwa()));
                }
            } else if (request.getParameter("sortBy").equals("cena")) {
                ascOrderKat = true;
                ascOrderNazwa = true;
                if (ascOrderCena) {
                    ascOrderCena = false;
                    Collections.sort(towary, new ComparatorByCena());
                } else {
                    ascOrderCena = true;
                    Collections.sort(towary, Collections.reverseOrder(new ComparatorByCena()));
                }
            }
        }
        model.addAttribute("checkeds", checkboxParam);
        model.addAttribute("cena1", cena1Param);
        model.addAttribute("cena2", cena2Param);
        model.addAttribute("magazyn", towary);
        model.addAttribute("kategorie", kategorie);
//        model.addAttribute(new Towar());
        return "magazyn";
    }

    @RequestMapping(value = "/magazyn", method = RequestMethod.POST)
    public String magazyn(@ModelAttribute Towar towar, Model model, HttpServletRequest request) {
        List<Towar> towaryFilteredByKat = md.getAllTowar();
        cena1Param = 0;
        cena2Param = Double.MAX_VALUE;

        if (request.getParameterValues("kategoria") != null) {
            checkboxParam = request.getParameterValues("kategoria");
            towary = filterKat(md.getAllTowar(), checkboxParam);
            towaryFilteredByKat = towary;
        } else {
            checkboxParam = new String[0];
            towary = md.getAllTowar();
        }
        if (!request.getParameter("cena1").equals("")) {
            try {
                cena1Param = Double.parseDouble(request.getParameter("cena1"));
            } catch (NumberFormatException nfe) {
                System.out.println("cena1: " + nfe);
                model.addAttribute("filtrByCenaValid", "Wymagana jest liczba, czêœæ u³amkow¹ oddziel kropk¹!");
            }
        }
        if (!request.getParameter("cena2").equals("")) {
            try {
                cena2Param = Double.parseDouble(request.getParameter("cena2"));
            } catch (NumberFormatException nfe) {
                System.out.println("cena2: " + nfe);
                model.addAttribute("filtrByCenaValid", "Wymagana jest liczba, czêœæ u³amkow¹ oddziel kropk¹!");
            }
        }

        towary = filterCena(towaryFilteredByKat, cena1Param, cena2Param);

        model.addAttribute("checkeds", checkboxParam);
        model.addAttribute("cena1", cena1Param);
        model.addAttribute("cena2", cena2Param);
        model.addAttribute("magazyn", towary);
        model.addAttribute("kategorie", md.getKategorie());
        return "/magazyn";
    }

    @RequestMapping(value = "magazynzmien", method = RequestMethod.GET)
    public String magazynZmien(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        model.addAttribute(md.getTowar(id));
        return "magazynzmien";
    }

    @RequestMapping(value = "magazynzmien", method = RequestMethod.POST)
    public String magazynPoZmianie(@Valid Towar towar, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "magazynzmien";
        }
        md.updateTowar(towar);
        towary = md.getAllTowar();
        model.addAttribute("magazyn", towary);
        model.addAttribute("kategorie", md.getKategorie());
        model.addAttribute(new Towar());
        return "/magazyn";
    }

    public List<Towar> filterKat(List<Towar> listaTowar, String... filtr) {
        List<Towar> filteredListaTowar = new ArrayList();
        for (Towar t : listaTowar) {
            for (String s : filtr) {
                if (t.getKategoria().equals(s)) {
                    filteredListaTowar.add(t);
                }
            }
        }
        return filteredListaTowar;
    }

    public List<Towar> filterCena(List<Towar> listaTowar, double cena1, double cena2) {
        List<Towar> filteredListaTowar = new ArrayList();
        double c1 = cena1;
        double c2 = cena2;

        if (cena1 > cena2) {
            c1 = cena2;
            c2 = cena1;
        }
        for (Towar t : listaTowar) {
            if (c1 <= t.getCena() && t.getCena() <= c2) {
                filteredListaTowar.add(t);
            }
        }
        return filteredListaTowar;
    }

    public static void setTowary(List<Towar> towary) {
        MagazynController.towary = towary;
    }

}
