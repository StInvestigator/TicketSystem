package org.example.tickerssystem.controller;

import org.example.tickerssystem.service.event.EventCreationDTO;
import org.example.tickerssystem.service.event.EventService;
import org.example.tickerssystem.service.place.PlaceService;
import org.example.tickerssystem.service.ticket.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private PlaceService placeService;

    @Autowired
    private EventService eventService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("events", eventService.findClosestEventsIn2Weeks());
        return "events";
    }

    @GetMapping("/add")
    public String CreateEvent(Model model) {
        model.addAttribute("places", placeService.findAll());
        return "eventCreate";
    }

    @PostMapping("/add")
    public ModelAndView Add(@ModelAttribute EventCreationDTO event) {
        event.setPlace(placeService.findById(event.getPlace().getId()));
        if(eventService.save(event)) {
            return new ModelAndView("redirect:/events/add?success=true");
        }
        return new ModelAndView("redirect:/events/add?errorMessage=" + event.getPlace().getName() + " is already taken by another event at the " + event.getDate().getDate() + '.' + (event.getDate().getMonth()+1) + '.' + (event.getDate().getYear()+1900));
    }
}
