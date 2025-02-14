package org.example.tickerssystem.controller;

import org.example.tickerssystem.repository.ticket.TicketRepository;
import org.example.tickerssystem.repository.user.UserRepository;
import org.example.tickerssystem.service.UserDetailsServiceImpl;
import org.example.tickerssystem.service.ticket.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/buy")
    public ModelAndView buy(Long ticketId) {
        ticketService.sellTicket(ticketService.findById(ticketId), UserDetailsServiceImpl.getCurrentUser());
        return new ModelAndView("redirect:/tickets/my");
    }

    @GetMapping("/my")
    public String my(Model model) {
        var tickets = userRepository.findByEmailWithTickets(UserDetailsServiceImpl.getCurrentUser().getEmail()).getTickets();
        model.addAttribute("tickets", tickets);
        return "myTickets";
    }
}
