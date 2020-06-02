import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TreasureHunt{

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		String[] input = sc.nextLine().split("\\|");
		List<String> chest = new ArrayList<>( Arrays.asList(input) );
		
		String command = sc.nextLine();
		
		while(!command.equals("Yohoho!")){
			String[] commandParts = command.split(" ");
			
			switch(commandParts[0]){
					case "Loot": 
					for(int i = 1; i < commandParts.length; i++){
							if( !chest.contains(commandParts[i]) ){
								chest.add(0, commandParts[i]);
							}
					}
					break;
					
					case "Drop": 
					int index = Integer.parseInt(commandParts[1]);
					if( index >= chest.size() || index < 0){
						break;
					}
					String element = chest.remove(index);
					chest.add(element);
					break;
					
					case "Steal": 
					int count = Integer.parseInt(commandParts[1]);
					if( count >= chest.size() ){
						System.out.println(String.join(", ", chest));
                        chest.clear();	
                        break;						
					}
					List<String> stolen = new ArrayList<>();
					for(int i = 0; i < count; i++){
							stolen.add(chest.remove(chest.size() - 1));
					}
					Collections.reverse(stolen);
					System.out.println(String.join(", ", stolen));
					break;
				
			}
			command = sc.nextLine();	
		}
		
		if(chest.size() > 0){
			double lengthSum = 0;
			for(String s: chest){
				lengthSum += s.length();
			}
			System.out.printf( "Average treasure gain: %.2f pirate credits.", lengthSum/chest.size() );
		}
		else{
			System.out.println("Failed treasure hunt." );
		}
	}
}