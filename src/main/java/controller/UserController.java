package controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Telephone;
import domain.TelephoneType;
import domain.User;
import repository.TelephoneRepository;
import repository.UserRepository;

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
	public String saveUser(User user) {
		try {
			uRepo.save(user);
			return "redirect:/listUser";
		} catch (Exception e) {
			return "erro ao salvar";
		}
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
		mv.addObject("types", TelephoneType.values());
		return mv;
	}

	@PostMapping("/saveTelephone/{userId}")
	public String saveNewTelephoneNumber(@PathVariable("userId") long userId, Telephone tel) {
		try {
			tel.setUser(uRepo.findById(userId).get());
			tRepo.save(tel);
			return "redirect:/listUser";
		} catch (Exception e) {
			return "redirect:/home";

		}
	}
}
