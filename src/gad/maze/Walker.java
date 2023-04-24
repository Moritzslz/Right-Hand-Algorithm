package gad.maze;

import java.util.ArrayList;

public class Walker {

	private boolean[][] maze;
	private Result result;

	public Walker(boolean[][] maze, Result result) {
		this.maze = maze;
		this.result = result;
	}

	public boolean walk() {
		int x = 1;
		int y = 0;
		result.addLocation(x,y);
		for(int i = 0; i < maze.length * maze[0].length; i++) {
			if(x == maze.length - 1 && y == maze[0].length - 2)
				return true;
			if(maze[x-1][y] && !maze[x][y+1]) {
				y++;
				result.addLocation(x,y);
				if (!maze[x-1][y]) {
					x--;
					result.addLocation(x, y);
				}
			}
			else if(maze[x][y+1] && !maze[x+1][y]) {
				x++;
				result.addLocation(x,y);
				if (!maze[x][y+1]) {
					y++;
					result.addLocation(x,y);
				}
			}
			else if(maze[x+1][y] && !maze[x][y-1]) {
				y--;
				result.addLocation(x,y);
				if (!maze[x+1][y]) {
					x++;
					result.addLocation(x,y);
				}
			}
			else if(maze[x][y-1] && !maze[x-1][y]) {
				x--;
				result.addLocation(x,y);
				if (!maze[x][y-1]) {
					y--;
					result.addLocation(x, y);
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		boolean[][] maze = Maze.generateStandardMaze(10, 10);
		StudentResult result = new StudentResult();
		Walker walker = new Walker(maze, result);
		System.out.println(walker.walk());
		Maze.draw(maze, result);
	}
}
