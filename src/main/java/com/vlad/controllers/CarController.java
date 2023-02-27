package com.vlad.controllers;

import com.vlad.DAO.CarDAO;
import com.vlad.Entity.CarEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/cars")
public class CarController {
    private final CarDAO carDAO;

    public CarController(CarDAO carDAO)
    {
        this.carDAO = carDAO;
    }

    @GetMapping()
    public String allCars(Model model)
    {
        model.addAttribute("cars", carDAO.allCars());
        return "cars/allCars";
    }

    @GetMapping("/new")
    public String newCar(@ModelAttribute("car") CarEntity carEntity)
    {
        return "cars/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("car") CarEntity carEntity)
    {
        carDAO.save(carEntity);
        return "redirect:/cars";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id)
    {
        model.addAttribute("car", carDAO.takeCar(id));
        return "cars/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("car") CarEntity carEntity, @PathVariable("id") long id)
    {
        carDAO.update(id,carEntity);
        return "redirect:/cars";
    }
    @GetMapping("/{id}/delete")
    public String confirm(Model model, @PathVariable("id") long id)
    {
        model.addAttribute("car", carDAO.takeCar(id));
        return "cars/delete";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id)
    {
        carDAO.delete(id);
        return "redirect:/cars";
    }
}