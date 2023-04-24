package gad.maze;

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
		boolean[][] blocked = maze;
		boolean left = true;
		boolean right = true;
		boolean down = true;
		boolean up = true;

		result.addLocation(x, y);

		for(int i = 0; i < maze.length * maze[0].length; i++) {
			if (x == maze.length - 1 && y == maze[0].length - 2)
				return true;

			if(x < maze.length - 1)
				right = blocked[x + 1][y];
			if(y < maze[0].length - 1)
				down = blocked[x][y + 1];
			if(x > 0)
				left = blocked[x - 1][y];
			if(y > 0)
				up = blocked[x][y - 1];


			//If wall is right, left and in front go back
			if (down && right && left) {
				blocked[x][y] = true;
				//y--;
			}
			//If wall is left, in front and above go back
			else if (up && down && left) {
				blocked[x][y] = true;
				//x++;
			}
			//If wall is right, in front and above go back
			 else if (down && right && up) {
				blocked[x][y] = true;
				//x--;
			}
			//If wall is right, left and above go back
			else if (up && right && left) {
				blocked[x][y] = true;
				//y++;
			}

			if(!down)
				y++;
			else if (!right)
				x++;
			else if (!left)
				x--;
			else if (!up)
				y--;

			result.addLocation(x, y);
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
