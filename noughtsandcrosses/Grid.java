
public class Grid {
	
	private char[] grid = {' ',' ',' ',
		                   ' ',' ',' ',
			               ' ',' ',' '};
	private char human;
	private char computer;
	public Grid(char you, char opponent) 
	{
		this.human = you;
		this.computer = opponent;
	}
	public char giveHuman() 
	{
		return this.human;
	}
	public char giveComputer() 
	{
		return this.computer;
	}	
	public char[] giveGrid() 
	{
		return this.grid;
	}
	public int checkLength() 
	{
		return this.grid.length;
	}
	public void reset() 
	{
		for (int i=0; i<this.grid.length; i++) 
		{
			this.grid[i] = ' ';
		}
	}
	public void newCell(char cell, int location) 
	{
		this.grid[location] = cell;
	}
	public boolean locAvailable(int pos) 
	{
		return this.grid[pos] != this.human && 
				this.grid[pos] != this.computer;
	}	
	public boolean hasWon(char piece) 
	{
		for (int i=0;i<3;i++) 
		{
			if (this.grid[3*i] == piece && this.grid[3*i+1] == piece && 
					this.grid[3*i+2] == piece || this.grid[i] == piece && 
					this.grid[i+3] == piece && this.grid[i+6] == piece) 
			{
				return true;	
			}
		}
		if (this.grid[2] == piece && this.grid[4] == piece && 
				this.grid[6] == piece || this.grid[0] == piece && 
				this.grid[4] == piece && this.grid[8] == piece) 
		{
			return true;
		}
		return false;
	}
	public Grid copy() 
	{
		Grid newGrid = new Grid(this.human, this.computer);
		for (int i=0; i<this.grid.length; i++) 
		{
			newGrid.grid[i] = this.grid[i];
		}
		return newGrid;
	}
	
}

