package com.krepsinis.controller;

import com.krepsinis.model.Krepsinis;
import com.krepsinis.model.User;
import com.krepsinis.validator.UserValidator;
import com.krepsinis.services.KrepsinisService;
import com.krepsinis.services.SecurityService;
import com.krepsinis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Objects;


@EnableAutoConfiguration
@Controller
public class KrepsinisController {
    @Autowired
    @Qualifier("KrepsinisService")
    public KrepsinisService krepsinisService;
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping({"/", "/pridejimas"})
    public String welcome(Model model) {
        model.addAttribute("komandos",new Krepsinis());
        return "pridejimas";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/pridėti")
    public String teams(@Valid @ModelAttribute("komandos") Krepsinis krepsinis, BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            return "pridejimas";
        } else {
            String teamname = krepsinis.getTeamname();
            String namesurname = krepsinis.getNamesurname();
            String sponsors = krepsinis.getSponsors();
            String league=krepsinis.getLeague();
            switch (league) {
                case "A":
                    break;
                case "B":
                    break;
                case "C":
                    break;
                case "Įio":
                    break;
                case "+30":
                    break;
                default:
                    league="+30";
            }
            modelMap.put("teamname", teamname);
            modelMap.put("namesurname", namesurname);
            modelMap.put("league", league);
            modelMap.put("sponsors", sponsors);
            krepsinisService.save(new Krepsinis(teamname, namesurname, league, sponsors));
        }
        return "prideti";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/komandos")
    public String getAllteams(Model model) {
        model.addAttribute("komandos", krepsinisService.getAll());
        return "komandos";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/rodyti{id}")
    public String getById(@RequestParam("id") int id, Model model) {
        model.addAttribute("komanda", krepsinisService.getById(id));
        return "komanda";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/trinti{id}")
    public String delete(@RequestParam("id") int id, Model model) {
        krepsinisService.delete(id);
        model.addAttribute("komandos", krepsinisService.getAll());
        return "komandos";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/atnaujinti{id}")
    public String update(@RequestParam("id") int id, Krepsinis krepsinis, Model model) {
        model.addAttribute("komanda", krepsinisService.getById(id));
        return "atnaujinti";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/atnaujintikomanda")
    public String UpdateNumber(@Valid @ModelAttribute("komandos") Krepsinis krepsinis) {
            krepsinisService.update(krepsinis);
            return "redirect:/rodyti?id=" + krepsinis.getId();
        }
    @RequestMapping(method = RequestMethod.GET, value = "/ieskoti{teamname}")
    public String search (@RequestParam(value = "teamname") String teamname,  Model model) {
        model.addAttribute("komandos",krepsinisService.search(teamname));
        return "paieska";
    }

    @GetMapping("/registruoti")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userForm", new User());

        return "registruoti";
    }

    @PostMapping("/registruoti")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registruoti";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/pridejimas";
    }

    @GetMapping("/prisijungti")
    public String showLoginForm(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Įvestas prisijungimo vardas arba slaptazodis neteisingi.");

        if (logout != null)
            model.addAttribute("message", "Sekmingai atsijungete");

        return "prisijungti";
    }
    @GetMapping("/slaptazodis")
    public String update(Model model) {
        model.addAttribute("userForm", new User());
        return "slaptazodis";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/slaptazodis")
    public String changePassword(@ModelAttribute("userForm") User userForm,BindingResult bindingResult ) {


      try {
          userValidator.validate(userForm, bindingResult);

          if (bindingResult.hasErrors()) {
                return "slaptazodis";
            }
            userService.update(userForm);

        } catch (NullPointerException e) {
        }
       return "redirect:/pridejimas";
    }

    }











