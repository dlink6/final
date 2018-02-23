package login.action;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Token {
	public static void main(String[] args) {
		 
		StringTokenizer token = new StringTokenizer("950211-235411", "-");
		List list = new ArrayList<>();
		while(token.hasMoreTokens()) {
			list.add(token.nextToken());
		}
			System.out.println(list.get(0));
			System.out.println(list.get(1));
			
			long currentTime = System.currentTimeMillis();
			System.out.println(currentTime);
			String time =Long.toString(currentTime);
			String times = time.substring(4,9);
			System.out.println(times);
			
			
	}

}
