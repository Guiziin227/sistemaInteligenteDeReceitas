package com.github.guiziin227.sistemadereceitas.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_generated_recipes")
public class GeneratedRecipe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "generated_recipe_id")
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @CreationTimestamp
    private LocalDateTime createdAt;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "tb_generated_recipe_ingredients",
            joinColumns = @JoinColumn(name = "generated_recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Set<Ingredients> ingredientsUsed = new HashSet<>();

    public void addIngredient(Ingredients ingredient) {
        this.ingredientsUsed.add(ingredient);
        ingredient.getRecipes().add(this);
    }

    public GeneratedRecipe(Long id, String content, LocalDateTime createdAt, User user, Set<Ingredients> ingredientsUsed) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.user = user;
        this.ingredientsUsed = ingredientsUsed;
    }

    public GeneratedRecipe() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Set<Ingredients> getIngredients() {
        return ingredientsUsed;
    }

    public void setIngredients(Set<Ingredients> ingredients) {
        this.ingredientsUsed = ingredients;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GeneratedRecipe that = (GeneratedRecipe) o;
        return Objects.equals(id, that.id) && Objects.equals(content, that.content) && Objects.equals(createdAt, that.createdAt) && Objects.equals(user, that.user) && Objects.equals(ingredientsUsed, that.ingredientsUsed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, createdAt, user, ingredientsUsed);
    }
}
