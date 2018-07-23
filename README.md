## Running the Application

The Shop has been implemented as a command-line application. The main method is in Application.java.
The application uses Maven and can be run with the command:

> mvn clean compile exec:java

When running, the Application will shows a list of options, Application.java has been implemented to read the input and invoke the appropriate partially implemented method.

### Design notes

- The scanner object does not allow to manage properly the number of arguments passed from the command line. A user can enter: list 1 or add 1 -2 and this would be considered valid. Scanner.hasNext cannot be used because it allows returns true. An improvement could be to use Scanner.getNextLine() and parse manually the number of arguments and their type.
- For a layered design I created 2 services: one for the product catalogue and the other for managing the cart. 
- A product is removed from the cart if quantity is equals to 0.
- Add or Removing increments or decrements by 1.
- Adding a product in the cart that is not in the catalogue results in an exception.
- Removing a product in the cart that is not in the catalogue results in an exception.
- Have added a Help command ("H") to display the commands
- Have added Javadoc
