# Space-API-Example
A basic example on the functionality of the Space API. 

## Getting started
In order to intialise the Space API you'll need to create an instance of the SpaceAPI class
```java
SpaceAPI spaceAPI = new SpaceAPI();
```
From there, the SpaceAPI class has a few powerful methods that are capable of creating machines that can work with any other plugin using the Space API (Or space itself), getting a list of items that Space uses, or getting the Space world manager so that you can edit attributes of the planets space has.

Methods included:
## Items
```java
spaceAPI.getItems();
```
Provides a class containing all of the items space uses
## World Management
```java
WorldManager worldManager = spaceAPI.getWorldManager();
```
Provides a class with arrays of both all of the planets space manages, and the planets that can be displayed in a rocket. You can also get a planet by it's display name, location, test whether a world is a space world or test whether or not a player is in a space world
```java
worldManager.getPlanets();
worldManager.getPlanetsToDisplay();
worldManager.getPlanetByName(String name);
worldManager.getPlanetByLocation(Location location);
worldManager.isInSpaceWorld(Player player);
worldManager.isPlanet(World world);
```

## Energy networks and machines
```java
// Must be called in order to register an ItemStack as a placable machine. Stores an nbtIdentifier within the item to test for later.
spaceAPI.registerAsMachine(ItemStack stack, String nbtIdentifier);
// Attempt to place a machine. Should only be called after testing if isMachine() is true for the held ItemStack
spaceAPI.attemptPlaceMachine(ItemStack stack, BlockPlaceEvent event, String nbtIdentifier);
// Tests whether or not a given ItemStack is a machine with a stored nbtIdentifier
boolean isMachine = spaceAPI.isMachine(ItemStack stack, String nbtIdentifier);
```
Provides a general interface for creating machines. Please see the coded example for more understanding. These methods are quite simple and user friendly.


Things to note:
* The API automatically manages breaking, clicking and all inventory-related matters for machines. The only thing required of you to do is to manage placing machines as shown in the example code here.
* Any ItemStack provided to the registerAsMachine() method will have specialised NBT added to it. As a result it is important you test for your machine using the isMachine() method and so remembering your nbt identifier you give the item is important (Supplied as a parameter to the registerAsMachine() method)
* You should not attempt to "tick" machines yourself after placing them. Energy and oxygen inputs/outputs will be automatically managed based on the settings you provide when creating the machine, the functionality of said machine, and what is occuring in the surrounding network (if one exists)

Any recommended plugins using the Space API will be listed here as time goes on.
