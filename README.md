# Space-API-Example
A basic example on the functionality of the Space API. 
* [Getting Started](https://github.com/Z4OLLIEZ4/Space-API-Example/blob/main/README.md#getting-started)
* [Items](https://github.com/Z4OLLIEZ4/Space-API-Example/blob/main/README.md#items)
* [Categories](https://github.com/Z4OLLIEZ4/Space-API-Example/blob/main/README.md#categories)
* [World Management](https://github.com/Z4OLLIEZ4/Space-API-Example/blob/main/README.md#world-management)
* [Energy Networks And Machines](https://github.com/Z4OLLIEZ4/Space-API-Example/blob/main/README.md#energy-networks-and-machines)
* [Vehicles](https://github.com/Z4OLLIEZ4/Space-API-Example/blob/main/README.md#vehicles)
* [Things to note](https://github.com/Z4OLLIEZ4/Space-API-Example/blob/main/README.md#things-to-note)

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
## Categories
### Space Guide + Space Cheat Guide
```java
// Create a category in both the space guide, and space cheat guide
Category customCategory = new Category(ItemStack icon);
// Add items to the category
customCategory.add(ItemStack item);
// Register category with the space guide
CategoryManager.registerCategory(customCategory);
```
### Space Cheat Guide Only
```java
// Create a category in the space cheat guide
CreativeCategory customCategory = new CreativeCategory(ItemStack icon);
// Add items to the category
customCategory.add(ItemStack item);
// Register category with the space guide
CategoryManager.registerCategory(customCategory);
```
The Category API is to display custom items within the space guide easily, under automatically paged and sorted GUI's
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

It is also possible to get the last rocket takeoff location of a player from a planet.
```java
spaceAPI.getLastLocation(Player player, Planet planet);
```
* If the player has not visited the planet, it will return the world's spawn location
* If the world relevant to the planet is not present, it will return null

## Energy networks and machines
```java
// Must be called in order to register an ItemStack as a placable machine. Stores an nbtIdentifier within the item to test for later.
spaceAPI.registerAsMachine(ItemStack stack, String nbtIdentifier);
// Attempt to place a machine. Should only be called after testing if isMachine() is true for the held ItemStack
spaceAPI.attemptPlaceMachine(ItemStack stack, BlockPlaceEvent event, String nbtIdentifier);
// Tests whether or not a given ItemStack is a machine with a stored nbtIdentifier
boolean isMachine = spaceAPI.isMachine(ItemStack stack, String nbtIdentifier);
// Forcefully removes and terminates all energy networks in the given planet. This action is irreversible.
spaceAPI.discardEnergyNetworks(Planet planet);
// Gets the machine in the location a block is, or returns null if one is not present
Machine machine = spaceAPI.getMachineByBlock(Block b);
// Gets the energy network in the location a block is, or returns null if one is not present
EnergyNetwork network = spaceAPI.getNetworkByBlock(Block b);
// Returns an integer of the current energy network tick speed loaded into space. Will auto-throttle for performance
int speed = spaceAPI.getNetworkSpeed();
// Returns an integer of the current energy density loaded into space. Will auto-throttle for performance
int density = spaceAPI.getNetworkEnergyDensity();
```
Provides a general interface for creating machines. Please see the coded example for more understanding. These methods are quite simple and user friendly.

## Vehicles

In order to create your own vehicle, it is first necessary to create a class extending Vehicle. This will be our base.
```java
public class CustomVehicle extends Vehicle {

	public CustomVehicle() {
		super(double topSpeed, double accelerationMultiplier, boolean shouldRotate, boolean canReverse, boolean canStep, boolean canFly, boolean canMoveOnGround, boolean airtight, ItemStack stack);
	}

}
```
Here is a breakdown of the arguments:
| Argument | Data type | Function |
|---|---|---|
| topSpeed | double | Dictates the maximum velocity vector the vehicle can reach |
| accelerationMultiplier | double | Dictates the acceleration the vehicle will use when being controlled (MUST BE GREATER THAN 1) |
| shouldRotate | boolean | Dictates whether the vehicle model should rotate with where the player looks |
| canReverse | boolean | Dictates whether the vehicle can move backwards |
| canStep | boolean | Dictates whether the vehicle should automatically jump up one-block spaces |
| canFly | boolean | Dictates whether the vehicle can fly whilst holding the spacebar |
| canMoveOnGround | boolean | Dictates whether the vehicle can move whilst on the ground |
| airtight | boolean | Dictates whether the player is safe from space exposure whilst using the vehicle |
| stack | ItemStack | The actual item the vehicle is. This is the model that will display and that players can place to get the vehicle |

It is then important to register your newly created Vehicle with Space, so that it can actually have function and work within the Vehicle ecosystem. In order to do this, use the following method:
```java
VehicleManager.registerVehicle(new CustomVehicle())
```

Where `new CustomVehicle()` is our Vehicle class we just created. This should only be accessed in a static way as shown above.

# Things to note:
* The API automatically manages breaking, clicking and all inventory-related matters for machines. The only thing required of you to do is to manage placing machines as shown in the example code here, and any auxiliary functions you want your machine to have.
* Any ItemStack provided to the registerAsMachine() method will have specialised NBT added to it. As a result it is important you test for your machine using the isMachine() method and so remembering your nbt identifier you give the item is important (Supplied as a parameter to the registerAsMachine() method)

Any recommended plugins using the Space API will be listed here as time goes on.
