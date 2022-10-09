import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class MazeSolver {
	
	/*static int[][] maze = {
			{2, 0, 1, 1},
			{1, 1, 0, 1},
			{0, 1, 1, 1}
	};*/
	//0 = Wall
	//1 = Path
	//2 = Destination
	
	//static LinkedList<Position> path = new LinkedList<Position>();

	public static void main(String[] args) throws FileNotFoundException {

		/*Position p = new Position(0, 3); //(y, x)Starting Point is y=1row, x=4col
		path.push(p); //Use 'push' instead of 'add', coz 'push' adds the element at the beginning of the list and 'add' at the end of the list
		//To look at the most recent position in our path we use peek()
		//After grabbing the x,y of the recent position, we check is there a path to move forward, i.e. 1
		//if not we mark this as 0 (visited, wall) so we don't go the path again, otherwise it will be an infinite loop
		int y = path.peek().y;
		int x = path.peek().x;
		maze[y][x] = 0; //Be Careful of y(row entries) and x(column entries) representation
		*/
		
		ArrayList<Maze> mazes = new ArrayList<Maze>();
		
		Maze m = new Maze();
		
		Scanner in = new Scanner(new File("src/mazes.txt"));
		int rows = Integer.parseInt(in.nextLine());
		m.maze = new int[rows][];
		
		for(int i = 0; i < rows; i++) {
			String line = in.nextLine();
			m.maze[i] = Arrays.stream(line.split(", ")).mapToInt(Integer::parseInt).toArray();
		}
		
		m.start = new Position(Integer.parseInt(in.nextLine()), Integer.parseInt(in.nextLine()));
		
		mazes.add(m);
		
		int i = 0;
		while(i < mazes.size()) {
			if(solveMaze(mazes.get(i))) {
				System.out.println("You won!");
			} else {
				System.out.println("No path");
			}
			i++;
		}
	}
	
	private static boolean solveMaze(Maze m) {

		Position p = m.start;
		m.path.push(p);
		
		
		while(true) {
			int y = m.path.peek().y;
			int x = m.path.peek().x;
			
			m.maze[y][x] = 0;

			//down
			if(isValid(y+1, x, m)) {
				if(m.maze[y+1][x] == 2) {
					System.out.println("Moved down");
					return true;
				} else if(m.maze[y+1][x] == 1) {
					System.out.println("Moved down");
					m.path.push(new Position(y+1, x));
					continue;
				}
			}

			//left
			if(isValid(y, x-1, m)) {
				if(m.maze[y][x-1] == 2) {
					System.out.println("Moved left");
					return true;
				} else if(m.maze[y][x-1] == 1) {
					System.out.println("Moved left");
					m.path.push(new Position(y, x-1));
					continue;
				}
			}
			
			//up
			if(isValid(y-1, x, m)) {
				if(m.maze[y-1][x] == 2) {
					System.out.println("Moved up");
					return true;
				} else if(m.maze[y-1][x] == 1) {
					System.out.println("Moved up");
					m.path.push(new Position(y-1, x));
					continue;
				}
			}

			//right
			if(isValid(y, x+1, m)) {
				if(m.maze[y][x+1] == 2) {
					System.out.println("Moved right");
					return true;
				} else if(m.maze[y][x+1] == 1) {
					System.out.println("Moved right");
					m.path.push(new Position(y, x+1));
					continue;
				}
			}
			
			m.path.pop();
			System.out.println("Moved back");
			if(m.path.size() <= 0) {
				return false;
			}
		}
	}

	public static boolean isValid(int y, int x, Maze m) {
		if(y < 0 || 
			y >= m.maze.length ||
			x < 0 ||
			x >= m.maze[y].length
		 ) {
			return false;
		}
		return true;
	}
		
		
		
		
	}

