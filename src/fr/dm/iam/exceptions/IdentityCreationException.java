package fr.dm.iam.exceptions;


import fr.dm.iam.datamodel.Identity;



public class IdentityCreationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Identity faultyIdentity;

	/**
	 *
	 */
	public IdentityCreationException(Identity identity, Exception originalCause) {
		faultyIdentity = identity;
		initCause(originalCause);

	}

	@Override
	public String getMessage() {
		return "problem occured while creating that identity in the system " + faultyIdentity.toString();
	}

}
