package fr.epsi.individu;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;

@Named
@ApplicationScoped
public class IndividuRepository {

	@Resource(name = "baseTest")
	private DataSource dataSource;
	
	public String create(Individu individu) throws SQLException {
		
		Connection connection = dataSource.getConnection();
		try {
			
			String request = "insert into individus (prenom,nom,age) values (?, ?, ?)";
			
			PreparedStatement pstmt = connection.prepareStatement(request);
			try {

				pstmt.setString(1, individu.getPrenom());
				pstmt.setString(2, individu.getNom());
				pstmt.setInt(3, individu.getAge());

				pstmt.executeUpdate();
			}
			finally{
				if (pstmt != null) {
					pstmt.close();
				}
			}
		}
		finally{
			if (connection != null) {
				connection.close();
			}
		}
		return "individu?faces-redirect=true";
	}

	public List<Individu> getAll() {
		List<Individu> result = new ArrayList<>();
		return result;
	}

	public void delete(long id) {
	}

}
