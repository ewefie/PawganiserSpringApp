package com.paw.pawganizr.controllers;

import com.paw.pawganizr.models.Nutrition;
import com.paw.pawganizr.services.NutritionService;
import com.paw.pawganizr.wrappers.Nutritions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class NutritionController {

    private final NutritionService nutritionService;

    public NutritionController(NutritionService nutritionService) {
        this.nutritionService = nutritionService;
    }

    @GetMapping("/{userId}/pets/{petId}/nutritions/{nutritionId}")
    public Nutrition findById(@PathVariable(name = "userId") final UUID userId,
                              @PathVariable(name = "petId") final UUID petId,
                              @PathVariable(name = "nutritionId") final UUID nutritionId) {
        return nutritionService.findExistingNutritionById(nutritionId, petId, userId);
    }

    @GetMapping("/{userId}/pets/{petId}/nutritions")
    public Nutritions findAll(@PathVariable(name = "userId") final UUID userId,
                              @PathVariable(name = "petId") final UUID petId) {
        return nutritionService.findAllNutritionsByPetId(petId, userId);
    }

    @PostMapping("/{userId}/pets/{petId}/nutritions")
    @ResponseStatus(HttpStatus.CREATED)
    public Nutrition create(@PathVariable(name = "userId") final UUID userId,
                            @PathVariable(name = "petId") final UUID petId,
                            @RequestBody @Valid final Nutrition nutrition) {
        return nutritionService.addNutritionToPet(userId, petId, nutrition);
    }

    @PutMapping("/{userId}/pets/{petId}/nutritions/{nutritionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Nutrition update(@PathVariable(name = "userId") final UUID userId,
                            @PathVariable(name = "petId") final UUID petId,
                            @PathVariable(name = "nutritionId") final UUID nutritionId,
                            @RequestBody @Valid final Nutrition nutrition) {
        return nutritionService.updateNutrition(userId, petId, nutritionId, nutrition);
    }


    @DeleteMapping("/{userId}/pets/{petId}/nutritions/{nutritionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable(name = "userId") final UUID userId,
                           @PathVariable(name = "petId") final UUID petId,
                           @PathVariable(name = "nutritionId") final UUID nutritionId) {
        nutritionService.deleteById(nutritionId);
    }

    @DeleteMapping("/{userId}/pets/{petId}/nutritions")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll(@PathVariable(name = "userId") final UUID userId,
                          @PathVariable(name = "petId") final UUID petId) {
        nutritionService.deleteAllNutritions(petId, userId);
    }
}
