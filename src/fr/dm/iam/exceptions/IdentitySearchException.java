package fr.dm.iam.exceptions;


import fr.dm.iam.datamodel.Identity;



public class IdentitySearchException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Identity faultyIdentity;

	/**
	 *
	 */
	public IdentitySearchException(Identity identity, Exception originalCause) {
		faultyIdentity = identity;
		initCause(originalCause);

	}

	@Override
	public String getMessage() {
		return "problem occured while searching that identity in the system " + faultyIdentity.toString();
	}

}
