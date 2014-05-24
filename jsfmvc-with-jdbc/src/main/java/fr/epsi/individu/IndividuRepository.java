package fr.epsi.individu;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

	@Resource(name = "jdbc")
	private DataSource dataSource;
	
	public String create(Individu individu) throws SQLException {
		
		Connection connection = dataSource.getConnection();
		try {
			
			String request = "CALL AJOUTERINDIVIDU(?, ?, ?)";
			
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

	public List<Individu> getAll() throws SQLException {
		List<Individu> result = new ArrayList<>();
		
		Connection connection = dataSource.getConnection();
		try {
			ResultSet resultSet = null;
			String request = "SELECT id_individu, nom_individu, prenom_individu, age_individu  FROM INDIVIDUS";
			
			PreparedStatement pstmt = connection.prepareStatement(request);
			pstmt.execute();
			resultSet = pstmt.getResultSet();
			
			while(resultSet.next())
			{
				Individu individu = new Individu();
				individu.setId(resultSet.getLong(1));
				individu.setNom(resultSet.getString(2));
				individu.setPrenom(resultSet.getString(3));
				individu.setAge(resultSet.getInt(4));
				
				result.add(individu);
			}
		}
		finally{
			if (connection != null) {
				connection.close();
			}
		}
		
		return result;
	}

	public void delete(long id) {
		
	}

}
