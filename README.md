## Hello! This is the source code for my TFT rolling simulator based on set 7!
The goal of this is to find out the odds of finding a specific unit.
This is mostly for figuring out rolling on 7 or 8 and seeing the best outcome in case you're contested.
Levels 6-9 will have the code for them but not 1-5 as there are no scenarios where they're needed.
The calculations were done using a hypergeometric distribution.
  
## Things that this can account for:  ##
* Level  
* Buying a unit and replacement rolls  
* Units out of the pool  
  
## Things that this does not account for: ##
* Gold you have, I attempted the math and I couldn't get it. The formula is (gold // 2) * 5.
* Other people's boards and shops.

# For some general knowledge, the champion pool is as stands: #
The total number of copies of a unit:
* 1 Cost: 29  
* 2 Cost: 22  
* 3 Cost: 18  
* 4 Cost: 12  
* 5 Cost: 10  
  
The total amount of units per cost:  
* 1 Cost: 13  
* 2 Cost: 13  
* 3 Cost: 13  
* 4 Cost: 11  
* 5 Cost: 8  


![Alt text](src/RollingOddsPerLevel.jpg?raw=true "Odds")

For those who do not play TFT, each shop has 5 units in them. The champion pool means that
there are only a specific amount of that one champion per its cost. The two main factors
in determining what is inside your shop. Your level, which increases the odds of higher cost
champions appearing and the other champions in and out of the pool.
