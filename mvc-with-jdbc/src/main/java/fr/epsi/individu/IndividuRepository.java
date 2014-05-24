package fr.epsi.individu;

import java.util.ArrayList;
import java.util.List;

public class IndividuRepository {

	public void create(Individu individu) throws ValidationException {
		individu.validate();
	}

	public void delete(long id) {
	}

	public List<Individu> getAll() {
		List<Individu> result = new ArrayList<>();
		return result;
	}
}
