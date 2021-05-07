package taco.tacocloud.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import taco.tacocloud.Ingredient;
import taco.tacocloud.Ingredient.Type;
import taco.tacocloud.Taco;
import taco.tacocloud.Order;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {
    

@ModelAttribute
public void addIngredientsToModel(Model model) {
    List<Ingredient> ingredients = Arrays.asList(
        new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
        new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
        new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
        new Ingredient("CARN", "Carnitas", Type.PROTEIN),
        new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
        new Ingredient("LETC", "Lettuce", Type.VEGGIES),
        new Ingredient("CHED", "Cheddar", Type.CHEESE),
        new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
        new Ingredient("SLSA", "Salsa", Type.SAUCE),
        new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
      );
    
    Type[] types = Ingredient.Type.values();
    for(Type type : types) {
        model.addAttribute(type.toString().toLowerCase(),filterByType(ingredients, type));
    }
}
@GetMapping
public String showDesignForm(Model model) {
  model.addAttribute("design", new Taco());
  return "design";
}

@PostMapping
public String processOrder(@Valid Order order, Errors errors) {
    if (errors.hasErrors()) {
        return "orderForm";
    }

    log.info("Order submitted: " + order);
    return "redirect:/";
}
/*
@PostMapping
public String processDesign(@Valid @ModelAttribute("design") Taco design, Errors errors, Model model) {
  if (errors.hasErrors()) {
    return "design";
  }

  log.info("Processing design: " + design);

  return "redirect:/orders/current";
}
*/

private List<Ingredient> filterByType(
    List<Ingredient> ingredients, Type type) {
  return ingredients
            .stream()
            .filter(x -> x.getType().equals(type))
            .collect(Collectors.toList());
}

}
