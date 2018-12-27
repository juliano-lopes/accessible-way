package com.julopes.accessibleway.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.julopes.accessibleway.domain.Telephone;
import com.julopes.accessibleway.domain.TelephoneType;
import com.julopes.accessibleway.domain.User;
import com.julopes.accessibleway.repository.TelephoneRepository;
import com.julopes.accessibleway.repository.UserRepository;

@Controller
public class UserController {
	private UserRepository uRepo;
	private TelephoneRepository tRepo;

	@Autowired
	public UserController(UserRepository uRepo, TelephoneRepository tRepo) {
		this.uRepo = uRepo;
		this.tRepo = tRepo;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView signupUser() {
		return new ModelAndView("/signup/signupUser").addObject("user", new User());
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveUser(@Valid User user, BindingResult bResult) {
		ModelAndView mv = new ModelAndView("/signup/signupUser");
		List<String> msgErrors = new ArrayList<String>();
		if (bResult.hasErrors()) {
			for (ObjectError error : bResult.getAllErrors()) {
				msgErrors.add(error.getDefaultMessage());
			}
		} else {
			uRepo.save(user);
		}
		mv.addObject("user", user);
		mv.addObject("msgErrors", msgErrors);
		return mv;
	}

	@RequestMapping(value = "listUser", method = RequestMethod.GET)
	public ModelAndView listUser() {
		ModelAndView mv = new ModelAndView("listUser");
		mv.addObject("users", uRepo.findAll());
		return mv;
	}

	@RequestMapping(value = "/edite/{id}", method = RequestMethod.GET)
	public ModelAndView edite(@PathVariable("id") long id) {
		Optional<User> opUser = uRepo.findById(id);
		return new ModelAndView("/signup/signupUser").addObject("user", opUser.get());
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") long id) {
		try {
			uRepo.delete(uRepo.findById(id).get());
			return "redirect:/listUser";
		} catch (Exception e) {
			return "redirect:/home";
		}
	}

	@PostMapping("/search")
	@Query("select u from user u where u.name like %?1%")
	public ModelAndView searchUserByName(@RequestParam("nameUser") String nameUser) {
		return new ModelAndView("listUser").addObject("users", uRepo.findByName(nameUser));
	}

	@GetMapping("/detail/{id}")
	public ModelAndView detail(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("userDetails");
		mv.addObject("user", uRepo.findById(id).get());
		mv.addObject("telephones", tRepo.findTelephoneByUserId(id));
		mv.addObject("types", TelephoneType.values());
		mv.addObject("tel", new Telephone());
		return mv;
	}

	@PostMapping("/saveTelephone/{userId}")
	public ModelAndView saveNewTelephoneNumber(@PathVariable("userId") long userId, @Valid Telephone tel,
			BindingResult bResult) {
		ModelAndView mv = new ModelAndView("userDetails");
		List<String> msgErrors = new ArrayList<String>();
		if (bResult.hasErrors()) {
			for (ObjectError error : bResult.getAllErrors()) {
				msgErrors.add(error.getDefaultMessage());
			}
		} else {
			tel.setUser(uRepo.findById(userId).get());
			tRepo.save(tel);
		}
		mv.addObject("user", uRepo.findById(userId).get());
		mv.addObject("telephones", tRepo.findTelephoneByUserId(userId));
		mv.addObject("types", TelephoneType.values());
		mv.addObject("tel", new Telephone());
		mv.addObject("msgErrors", msgErrors);
		return mv;
	}

	@GetMapping("/deleteTelephone/{telephoneId}")
	public String deleteTelephone(@PathVariable("telephoneId") long telephoneId) {
		try {
			Telephone tel = tRepo.findById(telephoneId).get();
			long userId = tel.getUser().getId();
			tRepo.delete(tel);
			return "redirect:/detail/" + userId;
		} catch (Exception e) {
			return "redirect:/home";

		}

	}

}
