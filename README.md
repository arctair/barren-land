# Barren Land Analysis
## Requirements
Java is required to run the analysis.  
Maven is required for testing and building.  
## Testing
Execute  
```
mvn test
```  
## Building
Execute  
```
mvn compile
```  
Then inspect the target/classes folder for `Application`
## Running
Execute
```
cd target/classes
java Application <myBarrenLandInput>
```
For example:
```
$ cd target/classes
$ java Application '{"48 192 351 207", "48 392 351 407", "120 52 135 547", "260 52 275 547"}'
22816 192608
```
## Notes

### Cells vs. Boundaries
Working with shapes in a grid presents an important question:
should shapes' dimensions be expressed using the absolute edge of the shape
or by using the center of edge cells of the shape? Since this
solution performs arithmetic using the length of each side of a rectangle, the program
internally represents rectangles as the absolute boundaries of the shapes. However, the input
for the program is expressed using cell center locations; when input is passed into the
barren land solver, the boundary notation is implicitly converted from cell to boundary. 
### Breaking the Problem Down: Subtraction and Recursion 
When more than one rectangle of barren land is presented, it becomes more difficult
to find the area of arable land. Because of this simple observation, this solution reduces
the problem to solving for one rectangle of barren land at a time. When a new rectangle of barren land
is to be considered, all overlapping rectangles of arable land are found. The barren land is then
subtracted from each overlapping rectangle while the result is broken into a number of smaller rectangles.
This process is repeated recursively until all barren land has been considered.
### Reducing the Constituent Arable Rectangles
By the time all barren land has been considered, there exists a set of rectangles that describe all
remaining arable land. Sometimes groups of arable land exist disconnected from other groups. The output
is required to list the area of each of these groups. To model these groups in the program, a graph is built
representing all the rectangles and their adjacencies to each other. By initially treating each rectangle as an
independent subgraph, the program can iteratively merge all subgraphs into their distinct connected parent graphs.
After that, there is a simple operation mapping each parent graph to the total area of its constituent arable rectangles.