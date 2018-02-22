package fr.dm.iam.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.dm.iam.datamodel.Identity;
import fr.dm.iam.exceptions.IdentityCreationException;
import fr.dm.iam.exceptions.IdentityDeleteException;
import fr.dm.iam.exceptions.IdentityUpdateException;

public class IdentityJDBCDAO implements IdentityDAO {
	

	@Override
	public boolean create(Identity identity) throws IdentityCreationException {
		Connection connection = null;
		int executed=0;
		boolean updated=false;
		
		try {
			connection = DBConnection.getConnection();
			final PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO IDENTITIES(UID, DISPLAY_NAME, EMAIL) values (?,?,?) ");
			preparedStatement.setString(1, identity.getUid());
			preparedStatement.setString(2, identity.getDisplayName());
			preparedStatement.setString(3, identity.getEmail());
			executed=preparedStatement.executeUpdate();
			
			if(executed>0)
				updated=true;

		} catch (SQLException e) {
			final IdentityCreationException businessException = new IdentityCreationException(identity, e);

			throw businessException;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (final SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		return updated;
	}

	@Override
	public List<Identity> search(Identity criteria) {
		final List<Identity> identities = new ArrayList<>();
		Connection connection = null;
		try {
			connection = DBConnection.getConnection();
			final PreparedStatement preparedStatement = connection
					.prepareStatement("select UID, DISPLAY_NAME, EMAIL FROM IDENTITIES WHERE DISPLAY_NAME = ? OR EMAIL = ? OR UID = ? ");
			preparedStatement.setString(3, criteria.getUid());
			preparedStatement.setString(1, criteria.getDisplayName());
			preparedStatement.setString(2, criteria.getEmail());

			final ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				final Identity identity = new Identity();
				identity.setDisplayName(resultSet.getString(2));
				identity.setEmail(resultSet.getString(3));
				identity.setUid(resultSet.getString(1));
				identities.add(identity);
			}
		} catch (SQLException e) {
			System.out.println("error while searching");
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (final SQLException e) {
				System.out.println("unresolved error");
			}
		}

		return identities;
	}


	public boolean update(Identity identity) throws IdentityUpdateException{
		
		Connection connection = null;
		int executed=0;
		boolean updated=false;
		
		try {
			connection = DBConnection.getConnection();
			final PreparedStatement preparedStatement = connection
					.prepareStatement("UPDATE IDENTITIES SET DISPLAY_NAME=?, EMAIL=? WHERE UID=?");
			
			preparedStatement.setString(1, identity.getDisplayName());
			preparedStatement.setString(2, identity.getEmail());
			preparedStatement.setString(3, identity.getUid());
			executed=preparedStatement.executeUpdate();
			if(executed>0)
				updated=true;
				

		} catch (SQLException e) {
			final IdentityUpdateException businessException = new IdentityUpdateException(identity, e);
			throw businessException;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (final SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		return updated;
	}

	public boolean delete(Identity identity) throws IdentityDeleteException {
		
		int executed=0;
		boolean updated=false;
		Connection connection = null;
		
		try {
			connection = DBConnection.getConnection();
			final PreparedStatement preparedStatement = connection
					.prepareStatement("DELETE FROM IDENTITIES WHERE UID=?");
			
			preparedStatement.setString(1, identity.getUid());
			executed=preparedStatement.executeUpdate();
			
			if(executed>0)
				updated=true;

		} catch (SQLException e) {
			final IdentityDeleteException businessException = new IdentityDeleteException(identity, e);
			throw businessException;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (final SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		return updated;
	}
}