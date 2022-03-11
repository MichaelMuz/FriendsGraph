package friends;

import java.util.ArrayList;

import structures.Queue;
import structures.Stack;

public class Friends {

	
	public static ArrayList<String> shortestChain(Graph g, String p1, String p2) {

		
		ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
		doSomethng1(g, p1, p2, new ArrayList<String>(), results);

		ArrayList<String> answer = new ArrayList<String>();
		if(results.size() > 0)
		{
			ArrayList<String> current = results.get(0);
			for(int i = 1; i < results.size(); i ++)
			{
				if(results.get(i).size() < current.size())
				{
					current = results.get(i);
				}
			}
			answer = current;
		}

			return answer;
		}

	private static void doSomethng1(Graph g, String p1, String p2, ArrayList<String> running,
			ArrayList<ArrayList<String>> results) {
		if (running.contains(p1)) {
			return;
		}

		ArrayList<String> localRunning = new ArrayList<String>();
		localRunning.addAll(running);
		localRunning.add(p1);
		Person person = g.members[g.map.get(p1)];
		if (person.name.equals(p2)) {
			results.add(localRunning);
			return;
		}

		if (person.first == null) {
			return;
		}

		Person nextPerson = g.members[person.first.fnum];
		if (nextPerson.name.equals(p2)) {
			localRunning.add(nextPerson.name);
			results.add(localRunning);
			return;
		} else {
			Friend friend = person.first.next;
			while (friend != null) {
				Person lastPerson = g.members[friend.fnum];
				doSomethng1(g, lastPerson.name, p2, localRunning, results);
				friend = friend.next;
			}

			doSomethng1(g, nextPerson.name, p2, localRunning, results);
		}
	}
		
		
		

	public static ArrayList<ArrayList<String>> cliques(Graph g, String school) {
		
		boolean [] visited = new boolean [g.members.length];
		ArrayList<ArrayList<String>> clis = new ArrayList<ArrayList<String>>();
		
		int cliNum = 0;
		
		for(int i = 0; i < g.members.length; i++) {
			clis.add(new ArrayList<String>());
		}
		
		boolean empty = true;
		
		for(int i = 0; i < visited.length; i++) {
			if(!visited[i]) {
				if(g.members[i].student && g.members[i].school.equals(school)) {
					aClick(g, i, school, visited, clis.get(cliNum));
					cliNum = cliNum + 1;
					empty = false;
				}
				
			}
		}
		
		if(empty) {
			return null;
		}
		
		
		for(int i = 0; i < clis.size(); i++) {
			if(clis.get(i).isEmpty()) {
				clis.remove(i);
				i--;
			}
		}
		
		
		
		
		return clis;
		
	}
	
	private static void aClick(Graph g, int start, String school, boolean [] visited, ArrayList<String> click) {
		
		
		
		Queue<Integer> q = new Queue<Integer>();
		q.enqueue(start);
		visited[start] = true;
		
		while(!q.isEmpty()) {
			int thisNum = q.dequeue();
			Person thisPerson = g.members[thisNum];
			click.add(thisPerson.name);
			Friend f = thisPerson.first;
			while(f != null) {
				if(!visited[f.fnum]) {
					if(g.members[f.fnum].student && g.members[f.fnum].school.equals(school)) {
						q.enqueue(f.fnum);
						visited[f.fnum] = true;
					}
				}
				f = f.next;
			}
		}
	}
	
	
	public static ArrayList<String> connectors(Graph g) {
		
		int [] dfs = new int[g.members.length];
		int [] back = new int[g.members.length];
		ArrayList<String> conns = new ArrayList<String>();
		depth(g, 0, dfs, back, 1, 0, conns);
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY
		// CHANGE AS REQUIRED FOR YOUR IMPLEMENTATION
		ArrayList<String> connectors = new ArrayList<String>();
		for(String s : conns) {
			if(!connectors.contains(s)) {
				connectors.add(s);
			}
		}
		return connectors;
		
	}
	
	private static void depth(Graph g, int thisNum, int[] dfs, int [] back, int currInt, int orig, ArrayList<String> conns) {
		
		Person thisPerson = g.members[thisNum];
		if(dfs[thisNum] > 0) {
			return;
		}
		dfs[thisNum] = currInt;
		
		back[thisNum] = currInt;
		currInt = currInt + 1;
		Friend fir = thisPerson.first;
		
		while(fir != null) {
			if(dfs[fir.fnum] > 0) {
				back[thisNum] = Math.min(back[thisNum], dfs[fir.fnum]);
				fir = fir.next;
				continue;
			}
			
			depth(g, fir.fnum, dfs, back, currInt, orig, conns);
			
			if(dfs[thisNum] > back[fir.fnum]) {
				back[thisNum] = Math.min(back[thisNum], back[fir.fnum]);
			}
			if(dfs[thisNum] <= back[fir.fnum]) {
				if(thisNum != orig) {
					conns.add(thisPerson.name);
				}
				else if(dfs[thisNum] < back[fir.fnum]){
					conns.add(thisPerson.name);
				}
				
			}
			fir = fir.next;	
		}
		
		
	}
}

