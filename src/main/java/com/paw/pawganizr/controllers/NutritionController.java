package com.paw.pawganizr.controllers;

import com.paw.pawganizr.models.Nutrition;
import com.paw.pawganizr.services.nutrientservice;
import com.paw.pawganizr.wrappers.nutrients;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class NutritionController {

    private final nutrientservice nutrientservice;

    public NutritionController(nutrientservice nutrientservice) {
        this.nutrientservice = nutrientservice;
    }

    @GetMapping("/{userId}/pets/{petId}/nutrients/{nutritionId}")
    public Nutrition findById(@PathVariable(name = "userId") final UUID userId,
                              @PathVariable(name = "petId") final UUID petId,
                              @PathVariable(name = "nutritionId") final UUID nutritionId) {
        return nutrientservice.findExistingNutritionById(nutritionId, petId, userId);
    }

    @GetMapping("/{userId}/pets/{petId}/nutrients")
    public nutrients findAll(@PathVariable(name = "userId") final UUID userId,
                              @PathVariable(name = "petId") final UUID petId) {
        return nutrientservice.findAllnutrientsByPetId(petId, userId);
    }

    @PostMapping("/{userId}/pets/{petId}/nutrients")
    @ResponseStatus(HttpStatus.CREATED)
    public Nutrition create(@PathVariable(name = "userId") final UUID userId,
                            @PathVariable(name = "petId") final UUID petId,
                            @RequestBody @Valid final Nutrition nutrition) {
        return nutrientservice.addNutritionToPet(userId, petId, nutrition);
    }

    @PutMapping("/{userId}/pets/{petId}/nutrients/{nutritionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Nutrition update(@PathVariable(name = "userId") final UUID userId,
                            @PathVariable(name = "petId") final UUID petId,
                            @PathVariable(name = "nutritionId") final UUID nutritionId,
                            @RequestBody @Valid final Nutrition nutrition) {
        return nutrientservice.updateNutrition(userId, petId, nutritionId, nutrition);
    }


    @DeleteMapping("/{userId}/pets/{petId}/nutrients/{nutritionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable(name = "userId") final UUID userId,
                           @PathVariable(name = "petId") final UUID petId,
                           @PathVariable(name = "nutritionId") final UUID nutritionId) {
        nutrientservice.deleteById(nutritionId);
    }

    @DeleteMapping("/{userId}/pets/{petId}/nutrients")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll(@PathVariable(name = "userId") final UUID userId,
                          @PathVariable(name = "petId") final UUID petId) {
        nutrientservice.deleteAllnutrients(petId, userId);
    }
}
