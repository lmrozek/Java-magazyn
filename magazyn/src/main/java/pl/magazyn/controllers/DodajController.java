package pl.altkom.magazyn.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.altkom.magazyn.dao.MagazynDao;
import pl.altkom.magazyn.model.Towar;

@Controller
@RequestMapping("/dodaj")
public class DodajController {

    @Autowired
    private MagazynDao md;

    @RequestMapping(method = RequestMethod.GET)
    public String magazyn(Model model, HttpServletRequest request) {
        model.addAttribute(new Towar());
        return "dodaj";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String magazynDodaj(@Valid Towar towar, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "dodaj";
        }
        md.addTowar(towar);
        MagazynController.setTowary(md.getAllTowar());
        model.addAttribute("magazyn", md.getAllTowar());
        model.addAttribute("kategorie", md.getKategorie());
        model.addAttribute(new Towar());
        return "/magazyn";
    }

}
