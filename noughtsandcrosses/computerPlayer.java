 

public class computerPlayer {
	public int getMove(Grid gBoard) 
	{
		Grid gCopy = gBoard.copy();

		if (gCopy.locAvailable(4)) 
		{
			return 4;

		} else if (gCopy.giveGrid()[4] == gBoard.giveHuman() && 
				gCopy.locAvailable(2)) 
		{
			return 2;
		}

		int computerIndex = findWinLoc(gCopy, gBoard.giveComputer());
		if (computerIndex != -1) 
		{
			return computerIndex;
		}

		int playerIndex = findWinLoc(gCopy, gBoard.giveHuman());
		if (playerIndex != -1) 
		{
			return playerIndex;
		}
		computerIndex = lookFork(gCopy, gBoard.giveComputer(), 2);
		if (computerIndex != -1) 
		{
			return computerIndex;
		}
		playerIndex = lookFork(gCopy, gBoard.giveHuman(), 2);
		if (playerIndex != -1) 
		{
			if (gCopy.giveGrid()[4] == gCopy.giveHuman()) 
			{

				return lookFork(gCopy, gBoard.giveComputer(), 1);
			} 
			else 
			{
				return playerIndex;
			}
		}
		for (int i=0; i<gCopy.checkLength(); i+= 2) 
		{
			if (gCopy.giveGrid()[i] == gBoard.giveHuman() && 
					i != 4 && gCopy.locAvailable(8 - i)) 
			{
				return 8 - i;
			}
		}
		for (int i=0; i<gCopy.checkLength(); i+= 2) 
		{
			if (i != 4 && gCopy.locAvailable(i)) 
			{
				return i;
			}
		}
		for (int i=0; i<gCopy.checkLength(); i++) 
		{
			if (gCopy.locAvailable(i)) 
			{
				return i;
			}
		}
		return -1;
	}
	public int findWinLoc(Grid board, char piece) 
	{
		Grid boardCopy = board.copy();
		Grid newBoardCopy;
		for (int i=0; i<boardCopy.checkLength(); i++) 
		{
			newBoardCopy = boardCopy.copy();
			if (newBoardCopy.locAvailable(i)) {
				newBoardCopy.newCell(piece, i);
				if (newBoardCopy.hasWon(piece) == true) 
				{
					return i;
				}
			}
		}
		return -1;
	}
	public int lookFork(Grid gBoard, char piece, int winnings) 
	{
		Grid grCopy = gBoard.copy();
		Grid newGrCopy;
		Grid newGrCopy2;

		int totalWinnings;

		for (int i=0; i<grCopy.checkLength(); i++) 
		{
			totalWinnings = 0;
			newGrCopy = grCopy.copy();
			if (newGrCopy.locAvailable(i)) 
			{
				newGrCopy.newCell(piece, i);
			}
			for (int j=0; j<grCopy.checkLength(); j++) 
			{
				newGrCopy2 = newGrCopy.copy();
				if (newGrCopy2.locAvailable(j)) 
				{
					newGrCopy2.newCell(piece, j);
					if (newGrCopy2.hasWon(piece)) 
					{
						totalWinnings++;
					}
					if (totalWinnings >= winnings) 
					{
						return i;
					}
				}
			}
		}
		return -1;
	}
}

