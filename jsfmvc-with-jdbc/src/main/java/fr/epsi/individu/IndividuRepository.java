package fr.epsi.individu;

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
	
	public String create(Individu individu) throws SQLException 
	{
		try( java.sql.Connection connection = dataSource.getConnection() )
		{
			String request = "CALL AJOUTERINDIVIDU(?, ?, ?)";
			
			try( java.sql.PreparedStatement pstmt = connection.prepareStatement(request) )
			{
				pstmt.setString(1, individu.getPrenom());
				pstmt.setString(2, individu.getNom());
				pstmt.setInt(3, individu.getAge());
				
				pstmt.executeUpdate();
			}
		}
		
		return "individu?faces-redirect=true";	
	}

	public List<Individu> getAll() throws SQLException 
	{
		List<Individu> listIndividus = new ArrayList<>();
		
		try( java.sql.Connection connection = dataSource.getConnection() )
		{
			String request = "SELECT id_individu, nom_individu, prenom_individu, age_individu  FROM INDIVIDUS";
						
			try( java.sql.PreparedStatement pstmt = connection.prepareStatement(request) )
			{	
				try( java.sql.ResultSet resultSet = pstmt.executeQuery())
				{
					while( resultSet.next() )
					{
						Individu individu = new Individu();
						individu.setId(resultSet.getLong(1));
						individu.setNom(resultSet.getString(2));
						individu.setPrenom(resultSet.getString(3));
						individu.setAge(resultSet.getInt(4));
						
						listIndividus.add(individu);
					}
				}
			}
		}
		
		return listIndividus;
	}

	public void delete(long id) throws SQLException 
	{	
		try( java.sql.Connection connection = dataSource.getConnection() )
		{
			String request = "DELETE FROM INDIVIDUS WHERE id_individu=?";
			
			try( java.sql.PreparedStatement pstmt = connection.prepareStatement(request) )
			{
				pstmt.setLong(1, id);

				pstmt.executeUpdate();
			}
		}
	}

}
