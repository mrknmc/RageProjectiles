Angry Birds project for the Informatics 1 OOP tutorial
======================================================

![World](http://f.cl.ly/items/2f0Z3K2X3930050D2Y0p/World.jpg)

### GameHandler
 * Main Method is in GameHandler.java.
 * Initialises world and starts it.
 * Also contains wait method used elsewhere.
 
### World
 * Gets user input from Animator class.
 * Keeps track of world status.
 * Passes world status to Animator.
 
### Animator
 * Gets mouse input from user.
 * Paints world to screen.
 
### Component
 * Superclass of Projectile, Obstruction and Target
 * Each component has a position, width, height and corners
 
### Projectile
 * Attributes: speed, image, size.
 * Keeps track of projectile's status.
 * TODO: Subclasses for big/slow and small/fast projectiles.
 
### Obstruction
 * Attributes: Hits, shape, size, colour.
 
### Target
 * Attributes: Alive, shape, size, colour.
 * Keeps track of target's status.
 
### Velocity
 * Attributes: Xcomponent, Ycomponent.
 * Calculates speed and angle of travel.
