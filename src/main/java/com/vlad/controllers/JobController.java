package com.vlad.controllers;

import com.vlad.DAO.JobDAO;
import com.vlad.Entity.JobEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/jobs")
public class JobController {
    private final JobDAO jobDAO;

    public JobController(JobDAO jobDAO)
    {
        this.jobDAO = jobDAO;
    }

    @GetMapping()
    public String allCountries(Model model)
    {
        model.addAttribute("jobs", jobDAO.allJobs());
        return "jobs/allJobs";
    }

    @GetMapping("/new")
    public String newCar(@ModelAttribute("job")JobEntity jobEntity)
    {
        return "jobs/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("job") JobEntity jobEntity)
    {
        jobDAO.save(jobEntity);
        return "redirect:/jobs";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id)
    {
        model.addAttribute("job", jobDAO.takeJob(id));
        return "jobs/edit";
    }
    @GetMapping("/{id}/AllPeopleInJob")
    public String ShowAllPeopleInJob(Model model,@PathVariable("id")long id)
    {
        model.addAttribute("people",jobDAO.getAllPeopleInJob(id));
        model.addAttribute("job",jobDAO.takeJob(id));
        return "jobs/AllPeopleInJob";
    }
    @GetMapping("/{id}/AllCountriesWithJob")
    public String ShowAllCountriesWithJob(Model model,@PathVariable("id")long id)
    {
        model.addAttribute("countries",jobDAO.getAllCountiesWithThisJob(id));
        model.addAttribute("job",jobDAO.takeJob(id));
        return "jobs/allCountries";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("job") JobEntity jobEntity, @PathVariable("id") long id)
    {
        jobDAO.update(id,jobEntity);
        return "redirect:/jobs";
    }
    @GetMapping("/{id}/delete")
    public String confirm(Model model, @PathVariable("id") long id)
    {
        model.addAttribute("job", jobDAO.takeJob(id));
        return "jobs/delete";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id)
    {
        jobDAO.delete(id);
        return "redirect:/jobs";
    }
}
