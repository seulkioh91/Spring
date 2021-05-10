package taco.tacocloud.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import taco.data.IngredientRepository;
import taco.tacocloud.Ingredient;
import taco.tacocloud.Ingredient.Type;
import taco.tacocloud.Order;
import taco.tacocloud.Taco;
import taco.data.TacoRepository;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

  private final IngredientRepository ingredientRepo;

  private TacoRepository designRepo;
  // private TacoRepository tacoRepo;
    
  @Autowired
  public DesignTacoController(IngredientRepository ingredientRepo, 
    TacoRepository designRepo) {
  this.ingredientRepo = ingredientRepo;
  this.designRepo = designRepo; // this.tacoRepo = tacoRepo;
}
@GetMapping
public String showDesignForm(Model model) { 
  List<Ingredient> ingredients = new ArrayList<>(); 
  ingredientRepo.findAll().forEach(i -> ingredients.add(i));

Type[] types = Ingredient.Type.values(); 
  for (Type type : types) {
  model.addAttribute(type.toString().toLowerCase(), 
  filterByType(ingredients, type));
} 
return "design"; 
}
@ModelAttribute(name = "order") 
public Order order() { 
  return new Order();
}
@ModelAttribute(name = "taco") 
public Taco taco() { 
  return new Taco();
}

@PostMapping
public String processOrder(@Valid Order order, Errors errors) {
    if (errors.hasErrors()) {
        return "orderForm";
    }

    log.info("Order submitted: " + order);
    return "redirect:/";
}

@PostMapping
public String processDesign( @Valid Taco design, Errors errors, @ModelAttribute Order order) {
if (errors.hasErrors()) { return "design";
}
Taco saved = designRepo.save(design); order.addDesign(saved);
return "redirect:/orders/current"; }


private List<Ingredient> filterByType(
    List<Ingredient> ingredients, Type type) {
  return ingredients
            .stream()
            .filter(x -> x.getType().equals(type))
            .collect(Collectors.toList());
  }

}
