import java.util.*;

public class ManOWar{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		String[] line1 = sc.nextLine().split(">");
		String[] line2 = sc.nextLine().split(">");
		
		int length1 = line1.length;
		int length2 = line2.length;
		
		int[] pirateshipStatus = new int[length1];
		int[] warshipStatus = new int[length2];
		
		for(int i = 0; i < pirateshipStatus.length; i++) pirateshipStatus[i] = Integer.parseInt(line1[i]);
		for(int i = 0; i < warshipStatus.length; i++) warshipStatus[i] = Integer.parseInt(line2[i]);
		
		int maxHealth = Integer.parseInt( sc.nextLine() );
		
		String next = sc.nextLine();
		
		while( !next.equals("Retire") ){
			
			String[] command = next.split(" ");
			
			switch(command[0]) {
				
				case "Fire":
							int index = Integer.parseInt(command[1]);
							int damage = Integer.parseInt(command[2]);
				
							if(index >= 0 && index < warshipStatus.length) {
								warshipStatus[index] -= damage;
				
								if( warshipStatus[index] <= 0 ){
									System.out.println("You won! The enemy ship has sunken.");
									return;
								}
							}
							break;
				case "Defend":
							int startIndex = Integer.parseInt(command[1]);
							int endIndex = Integer.parseInt(command[2]);
							int damages = Integer.parseInt(command[3]);
				
							if(endIndex < pirateshipStatus.length && startIndex >= 0){
								for(int i = startIndex; i <= endIndex; i++){
									pirateshipStatus[i] -= damages;
						  
									if( pirateshipStatus[i] <= 0 ){
										System.out.println("You lost! The pirate ship has sunken.");
										return;
									}	
								}
							}
							break;
				case "Repair":
							int ind  = Integer.parseInt( command[1] );
							int health = Integer.parseInt( command[2] );
				
							if(ind >= 0 && ind < pirateshipStatus.length){
								pirateshipStatus[ind] += health;
								if (pirateshipStatus[ind] > maxHealth) pirateshipStatus[ind] = maxHealth;
							}
							break;
				case "Status":
							int counter = 0;
				
							for(int i = 0; i < pirateshipStatus.length; i++){
								if(pirateshipStatus[i] < maxHealth*0.2) counter++;
							}
							System.out.printf("%d sections need repair.", counter);
							System.out.println();
							break;
			}
			
			next = sc.nextLine();
		}
		
		int pirateshipSum = 0;
		int warshipSum = 0;
		
		for(int pirateship: pirateshipStatus){
				pirateshipSum +=pirateship;
		}
		for(int warship: warshipStatus){
				warshipSum +=warship;
		}
		System.out.printf("Pirate ship status: %d", pirateshipSum); 
		System.out.println();
		System.out.printf("Warship status: %d", warshipSum);
	}
}