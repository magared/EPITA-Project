package fr.dm.iam.exceptions;


import fr.dm.iam.datamodel.Identity;


public class IdentityUpdateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Identity faultyIdentity;

	/**
	 *
	 */
	public IdentityUpdateException(Identity identity, Exception originalCause) {
		faultyIdentity = identity;
		initCause(originalCause);

	}

	@Override
	public String getMessage() {
		return "problem occured while deleting that identity in the system " + faultyIdentity.toString();
	}

}
