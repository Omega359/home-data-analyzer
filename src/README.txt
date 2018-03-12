To build this tool you need maven installed.

mvn clean package

This will build a jar containing all dependencies in the target directory. To run the tool execute

java -jar home-data-analyzer-1.0.jar < <<input>>

For example, on windows:

java -jar .\home-data-analyzer-1.0.jar < ..\src\test\resources\input.txt


The output will print to stdout.
