package net.codejava;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

	@Autowired
	private UserService service; 
	
	//Get AllData
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<User> listProducts = service.listAll();
		model.addAttribute("listP", listProducts);
		
		return "index";
	}
	
	@RequestMapping("/new")
	public String showNewProductPage(Model model) {
		User product = new User();
		model.addAttribute("pro", product);
		
		return "new_user";
	}
	
	//Create Method
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") User product) {
		
		//System.out.println(product.getMobileNo());
		service.save(product);
		
		return "redirect:/";
	}
	
	//Edit Data
	@RequestMapping("/edit/{number}")
	public ModelAndView showEditProductPage(@PathVariable(name = "number") int id) {
		ModelAndView mav = new ModelAndView("edit_user");
		User product = service.get(id);
		mav.addObject("product", product);
		
		return mav;
	}
	
	//Delete Data
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id) {
		service.delete(id);
		return "redirect:/";		
	}
}
