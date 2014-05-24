package fr.epsi.individu;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Named
@RequestScoped
public class Individu {

	private Long id;

	@Size(min = 1, max = 30, message = "Le nom est obligatoire et doit contenir au plus 30 caractères !")
	private String nom;

	@Size(min = 1, max = 30, message = "Le prénom est obligatoire et doit contenir au plus 30 caractères !")
	private String prenom;

	@NotNull(message = "L'âge est obligatoire")
	@Min(value = 0, message = "L'âge ne peut pas être négatif")
	@Max(value = 150, message = "L'âge est incorrect")
	private Integer age;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
