package fr.dm.iam.service;

import java.util.List;

import fr.dm.iam.datamodel.Identity;
import fr.dm.iam.exceptions.IdentityCreationException;
import fr.dm.iam.exceptions.IdentityDeleteException;
import fr.dm.iam.exceptions.IdentityUpdateException;

public interface IdentityDAO {

	public boolean create(Identity identity) throws IdentityCreationException;

	public boolean update(Identity identity) throws IdentityUpdateException;

	public boolean delete(Identity identity) throws IdentityDeleteException;

	public List<Identity> search(Identity criteria);

}
