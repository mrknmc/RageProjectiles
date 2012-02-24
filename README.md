Angry Birds project for the Informatics 1 OOP tutorial
======================================================

### GameHandler
 * Main Method is in GameHandler.java.
 * The user is prompted for angle, speed, length difference and height difference.
 * After three guesses the program exits.
 
### World
 * Contains methods for physics calculations.
 * Communicates with Animator
 
### Animator
 * Will contain methods to draw components
 
### GameHelper - *Place Holder*.
 * Will contain methods for user input etc.
 
### Component
 * Superclass of Projectile, Obstruction and Target
 * Each component has a position, width, height and corners
 
### Projectile
 * Attributes: speed, colour, size.
 * Subclasses for big/slow and small fast projectiles.
 
### Obstruction
 * Attributes: Hits, shape, size, colour.
 
### Target
 * Attributes: Points, shape, size, colour.
