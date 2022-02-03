# Assumptions:

1. Character moves at a set speed, 1 square a second.
2. Battles are done with the character attacking, then the enemies. Repeat until the character or all enemies are dead.
3. Cards gold and equipment drops are random, depending on the type of enemy, exp is not random
4. Vampires have a support radius of 3 and battle radius of 2, slugs 1 and zombies 2
5. Only a single enemy can occupy a square
6. Keyboard Shortcut consumes a health potion
7. All Gold experience and items from overflow of items and cards is predetermined based on item or card.
8. Weapons increase player damage by x amount, depending on type of weapon
9. Armor increases player 
10. Shop menu will have a random assortment of items at a set price
11. The goal is set when the game starts
12. The goal is clearly displayed to the player
13. Enemies move from their spawn location along the path, choosing direction randomly
14. There is a Maximum distance enemies can move from their spawn location, 2 squares.
15. Village heals 10% of maximum hp
16. Towers do a set amount of damage, attacking during the player’s turn, after the player
17. Allied soldiers do a set amount of damage, attacking during the player’s turn, after the Towers
18. Traps one shot any enemy, all things the enemy would drop is lost
19. Campfires only affect the player character and Allied Soldiers, not towers.

## Iteration 2

20. When a player is holding too many cards, the oldest card is destroyed and turned into 20 gold.
21. Slugs will always drop experience then a 10% chance of a sword, a 40% chance of a zombie pit card and a 50% chance of 5 gold
22. Zombies will always drop experience then a 20% chance of a staff, a 10% chance of a vampire castle card, a 20% chance of a zombie pit card, 5% chance of a stake, and a 45% chance of 20 gold
23. Vampire will always drop experience than a 30% chance of a vampire castle card, a 5% chance of a stake, a 20% chance of a staff and a 45% chance of 100 gold.
24. If there are multiple places to spawn an enemy, a building will choose a random one of these options.
25. The Staff has "Magic" damage, which makes attacks cause a random amount of damage (extra damage has uniform probability from -2 to 8)
26. Slugs attack for 1, have 3 hp, Don't Move, have Battle and Support Radaius of 1 and no crit chance.
27. Zombies attack for 3, have 2 health move 1/4 of the time, have Battle radius of 2 and Support Radius of 3 and 5% crit chance.
28. Vampires attack for 6, have 10 health move 1/2 of the time, have Battle radius of 3 and Support Radius of 4 and 10% crit chance, on crit, their next 3 attacks do 3 bonus damage.
29. Campfires have a radius of 4
30. Towers have a radius of 4
31. Players must achieve a gold, then xp then loop victory condition
32. Trap Buildings kill all enemies on a single square for a one time use.