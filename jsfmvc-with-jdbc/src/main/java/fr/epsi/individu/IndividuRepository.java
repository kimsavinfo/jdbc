package fr.epsi.individu;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class IndividuRepository {

	public String create(Individu individu) {
		return "individu?faces-redirect=true";
	}

	public List<Individu> getAll() {
		List<Individu> result = new ArrayList<>();
		return result;
	}

	public void delete(long id) {
	}

}
