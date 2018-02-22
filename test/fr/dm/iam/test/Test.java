package fr.dm.iam.test;

import java.util.List;

import fr.dm.iam.datamodel.Identity;
import fr.dm.iam.exceptions.IdentityCreationException;
import fr.dm.iam.exceptions.IdentityDeleteException;
import fr.dm.iam.exceptions.IdentityUpdateException;
import fr.dm.iam.service.IdentityDAO;
import fr.dm.iam.service.IdentityJDBCDAO;

public class Test {
	
	private static final String UID= "2123212";
	
	static final IdentityDAO dao=new IdentityJDBCDAO();
	
	public static void insertOperation() {
		
		Identity identity=new Identity();
		
		identity.setDisplayName("Dennis Magare");
		identity.setEmail("dennis.magare@gmail.com");
		identity.setUid(UID);
		
		boolean updated;
		try {
			updated = dao.create(identity);
			
			if(updated) {
				System.out.println("update success");
			}else {
				System.out.println("some error");
			}
			
		} catch (IdentityCreationException e) {
			e.printStackTrace();
		}
	}
	
	public void updateBothEmailAndName() {
		
		Identity identity=new Identity();
		
		identity.setDisplayName("Dennis Magare");
		identity.setEmail("dennis.magare@gmail.com");
		identity.setUid(UID);
		
		boolean updated;
		try {
			updated = dao.update(identity);
			
			if(updated) {
				System.out.println("update success");
			}else {
				System.out.println("some error");
			}
		} catch (IdentityUpdateException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void updateOperationOnlyEmail() {
		
		Identity identity=new Identity();
		
		identity.setEmail("dennis.magare@gmail.com");
		identity.setUid(UID);
		
		try {
			boolean updated=dao.update(identity);
			
			if(updated) {
				System.out.println("update success");
			}else {
				System.out.println("some error");
			}
			
		} catch (IdentityUpdateException e) {
			e.printStackTrace();
		}
		
	}

	public void updateOperationOnlyDisplayName() {
		
		Identity identity=new Identity();
		
		identity.setDisplayName("MAGARE Dennis");
		identity.setUid(UID);
		
		try {
			boolean updated=dao.update(identity);
			
			if(updated) {
				System.out.println("update success");
			}else {
				System.out.println("some error");
			}
		} catch (IdentityUpdateException e) {
			e.printStackTrace();
		}
	}
	
	public void searchOperation() {
		Identity identity =new Identity();
		identity.setUid(UID);
		
		
		List<Identity> listOfidentities=dao.search(identity);
		
	}
	
	public static void deleteOperation() {
		
		Identity identity =new Identity();
		identity.setUid(UID);
		
		try {
			boolean updated= dao.delete(identity);
			
			if(updated) {
				System.out.println("update success");
			}else {
				System.out.println("some error");
			}
		} catch (IdentityDeleteException e) {
			e.printStackTrace();
		}
		
	}
}
