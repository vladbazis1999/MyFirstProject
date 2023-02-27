package com.vlad.controllers;

import com.vlad.DAO.CountryDAO;
import com.vlad.Entity.CountryEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/countries")
public class CountryController {
    private final CountryDAO countryDAO;

    public CountryController(CountryDAO countryDAO)
    {
        this.countryDAO = countryDAO;
    }

    @GetMapping()
    public String allCountries(Model model)
    {
        model.addAttribute("countries", countryDAO.allCountries());
        return "countries/allCountries";
    }
    @GetMapping("/{id}/AllPeopleInCountry")
    public String ShowAllPeopleInCountry(Model model,@PathVariable("id")long id)
    {
        model.addAttribute("people",countryDAO.getAllPeopleInCountry(id));
        model.addAttribute("country",countryDAO.takeCountry(id));
        return "countries/AllPeopleInCountry";
    }
    @GetMapping("/{id}/AllJobsInCountry")
    public String ShowJobsInCountry(Model model,@PathVariable("id")long id)
    {
        model.addAttribute("jobs",countryDAO.getAllJobInThisCountry(id));
        model.addAttribute("country",countryDAO.takeCountry(id));
        return "countries/allJobs";
    }

    @GetMapping("/new")
    public String newCar(@ModelAttribute("country") CountryEntity countryEntity)
    {
        return "countries/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("country") CountryEntity countryEntity)
    {
        countryDAO.save(countryEntity);
        return "redirect:/countries";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id)
    {
        model.addAttribute("country", countryDAO.takeCountry(id));
        return "countries/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("country") CountryEntity countryEntity, @PathVariable("id") long id)
    {
        countryDAO.update(id,countryEntity);
        return "redirect:/countries";
    }
    @GetMapping("/{id}/delete")
    public String confirm(Model model, @PathVariable("id") long id)
    {
        model.addAttribute("country", countryDAO.takeCountry(id));
        return "countries/delete";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id)
    {
        countryDAO.delete(id);
        return "redirect:/countries";
    }
}
