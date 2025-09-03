package com.github.guiziin227.sistemadereceitas.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.text.Normalizer;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_ingredients")
public class Ingredients implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private Long id;

    @Column(nullable = false)
    private String displayName;

    @Column(nullable = false, unique = true)
    private String normalizedName;

    @ManyToMany(mappedBy = "ingredientsUsed")
    private Set<GeneratedRecipe> recipes = new HashSet<>();

    public Ingredients(Long id, String displayName, Set<GeneratedRecipe> recipes) {
        this.id = id;
        this.setDisplayName(displayName);
        this.recipes = recipes;
    }

    public Ingredients() {}

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
        this.normalizedName = normalize(displayName);
    }

    private String normalize(String displayName) {
        if (displayName == null || displayName.isBlank()) return "";
        String normalizedName = displayName.toLowerCase().trim();
        normalizedName = Normalizer.normalize(normalizedName, Normalizer.Form.NFD);
        normalizedName = normalizedName.replaceAll("\\p{InCOMBINING_DIACRITICAL_MARKS}", "");
        return normalizedName;
    }

    public String getNormalizedName() {
        return normalizedName;
    }

   

    public Set<GeneratedRecipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<GeneratedRecipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ingredients that = (Ingredients) o;
        return Objects.equals(id, that.id) && Objects.equals(displayName, that.displayName) && Objects.equals(normalizedName, that.normalizedName) && Objects.equals(recipes, that.recipes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, displayName, normalizedName, recipes);
    }
}
