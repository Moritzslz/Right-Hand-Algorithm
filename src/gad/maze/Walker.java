package gad.maze;

public class Walker {

	private boolean[][] maze;
	private Result result;

	public Walker(boolean[][] maze, Result result) {
		this.maze = maze;
		this.result = result;
	}

	public boolean walk() {
		//Edge case maze is empty
		if(maze.length == 0)
			return false;

		//0 = down; 1 = right; 2 = up; 3 = left
		int walkingDirection = 0;
		int x = 1;
		int y = 0;
		boolean xToLittle = false;
		boolean xToLarge = false;
		boolean yToLittle = false;
		boolean yToLarge = false;

		//Log the starting point
		result.addLocation(x,y);

		//The maximum length of a path would be to visit every field
		//which is not possible due to walls however if loops occur the
		//algorithm should be stopped at some point.
		for(int i = 0; i < maze.length * maze[0].length; i++) {
			if(x == maze.length - 1 && y == maze[0].length - 2) {
				return true;
			}
			if(i != 0 && x == 1 && y == 0)
				return false;

			//Preventing index out of bounds and decreasing runtime by
			//validating once every loop instead of validating in every if statement.
			xToLarge = !(x < maze.length - 1);
			xToLittle = !(x > 0);
			yToLarge = !(y < maze[0].length - 1);
			yToLittle = !(y > 0);

			//Using a switch statement to effectively switch the algorithms behaviour for every walking direction.
			//The algorithm looks at the field right to the player and the next one in the walking direction.
			//If there is no wall on the right of the player the direction is changed until there is a wall on the right.
			//If the next field in walking direction is a wall the direction is changed until the path is not blocked anymore.
			//If a step was executed it is checked weather the new field to the right is free.
			//If so the walking direction is changed and the step into the free field is taken.
			switch (walkingDirection) {
				//going down
				case 0:
					if(!xToLittle && !yToLarge && maze[x-1][y] && !maze[x][y+1]) {
						y++;
						result.addLocation(x,y);
						if(!maze[x-1][y]) {
							x--;
							result.addLocation(x,y);
							walkingDirection = 3;
						}
						break;
					} else {
						walkingDirection = 1;
					}


				//going right
				case 1:
					if(!xToLarge && !yToLarge && maze[x][y+1] && !maze[x+1][y]) {
						x++;
						result.addLocation(x,y);
						if(!maze[x][y+1]) {
							y++;
							result.addLocation(x,y);
							walkingDirection = 0;
						}
						break;
					} else {
						walkingDirection = 2;
					}

				//going up
				case 2:
					if(!xToLarge && !yToLittle && maze[x+1][y] && !maze[x][y-1]) {
						y--;
						result.addLocation(x,y);
						if(!maze[x+1][y]) {
							x++;
							result.addLocation(x,y);
							walkingDirection = 1;
						}
						break;
					} else {
						walkingDirection = 3;
					}

				//going left
				case 3:
					if(!xToLittle && !yToLittle && maze[x][y-1] && !maze[x-1][y]) {
						x--;
						result.addLocation(x,y);
						if(!maze[x][y-1]) {
							y--;
							result.addLocation(x,y);
							walkingDirection = 2;
						}
						break;
					} else {
						walkingDirection = 0;
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
