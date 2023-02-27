package com.vlad.controllers;


import com.vlad.DAO.CountryDAO;
import com.vlad.DAO.JobDAO;
import com.vlad.DAO.PeopleDAO;
import com.vlad.Entity.PersonEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleDAO peopleDAO;
    private final JobDAO jobDAO;
    private final CountryDAO countryDAO;
    public PeopleController(PeopleDAO peopleDAO,JobDAO jobDAO,CountryDAO countryDAO)
    {
        this.peopleDAO = peopleDAO;
        this.countryDAO = countryDAO;
        this.jobDAO = jobDAO;
    }

    @GetMapping()
    public String allPeople(Model model)
    {
        model.addAttribute("people", peopleDAO.allPeople());
        return "people/AllPeople";
    }
    @GetMapping("/{id}/show")
    public String showPerson(Model model, @PathVariable("id") long id)
    {
        model.addAttribute("person",peopleDAO.takePerson(id));
        model.addAttribute("country",peopleDAO.takePerson(id).getCountryEntity());
        model.addAttribute("job",peopleDAO.takePerson(id).getJobEntity());
      return "people/person";
    }

    @GetMapping("/new")
    public String newPeople(Model model,@ModelAttribute("person")PersonEntity personEntity)
    {
        model.addAttribute("countries", countryDAO.allCountries());
        model.addAttribute("jobs", jobDAO.allJobs());
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") PersonEntity personEntity)
    {
        peopleDAO.save(personEntity);
        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id)
    {
        model.addAttribute("person", peopleDAO.takePerson(id));
        model.addAttribute("countries", countryDAO.allCountries());
        model.addAttribute("jobs", jobDAO.allJobs());
        return "people/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") PersonEntity personEntity,
                         @PathVariable("id") long id)
    {
        peopleDAO.update(id,personEntity);
        return "redirect:/people";
    }
    @GetMapping("/{id}/delete")
    public String confirm(Model model, @PathVariable("id") long id)
    {
        model.addAttribute("person", peopleDAO.takePerson(id));
        return "people/delete";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id)
    {
        peopleDAO.delete(id);
        return "redirect:/people";
    }
}