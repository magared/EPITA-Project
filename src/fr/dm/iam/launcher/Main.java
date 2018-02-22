package fr.dm.iam.launcher;

import java.util.Scanner;

import fr.dm.iam.datamodel.Identity;
import fr.dm.iam.exceptions.IdentityCreationException;
import fr.dm.iam.exceptions.IdentityDeleteException;
import fr.dm.iam.exceptions.IdentityUpdateException;
import fr.dm.iam.service.IdentityDAO;
import fr.dm.iam.service.IdentityJDBCDAO;



public class Main {


	public static void main(String[] args) {
		int choice=0;
		Scanner scanner=new Scanner(System.in);
		IdentityDAO dao=new IdentityJDBCDAO();
		Identity identity=new Identity();
		
		do {
			System.out.println("1. Create");
			System.out.println("2. Search");
			System.out.println("3. Delete");
			System.out.println("4. Update");
			System.out.println("5. Exit");
			
			System.out.println("Enter your choice.");
			choice=scanner.nextInt();
			
			
			switch(choice) {
				
			case 1:
				//load the identity
				
				try {
					identity= readIdentity(scanner);
					dao.create(identity);
				} catch (IdentityCreationException e) {
					e.printStackTrace();
				}
				break;
				
			case 2:
				identity= readIdentity(scanner);
				dao.search(identity);
				break;
				
			case 3:
				try {
					identity=readIdentityForDelete(scanner);
					dao.delete(identity);
				} catch (IdentityDeleteException e) {
					e.printStackTrace();
				}
				break;

			case 4:
				try {
					identity= readIdentity(scanner);
					dao.update(identity);
				} catch (IdentityUpdateException e) {
					e.printStackTrace();
				}
				break;
		
			case 5:
				System.exit(0);
				break;
				
			}
			
		}while(choice!=5);
		
	}
	
	public static Identity readIdentity(Scanner scanner) {
		
		System.out.println("Enter your details below: ");
		
		Identity identity = new Identity();
		System.out.println("Enter your display name");
		String temp=scanner.next();
		identity.setDisplayName(temp);
		System.out.println("Enter your email address");
		temp=scanner.next();
		identity.setEmail(temp);
		System.out.println("Enter your uid");
		temp=scanner.next();
		identity.setUid(temp);
		
		return identity;
		
	}

public static Identity readIdentityForDelete(Scanner scanner) {
		
		System.out.println("Enter your details below: ");
		
		Identity identity = new Identity();
		System.out.println("Enter your uid");
		String temp=scanner.next();
		identity.setUid(temp);
		
		return identity;
		
	}
}
