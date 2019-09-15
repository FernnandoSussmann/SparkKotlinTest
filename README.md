# SparkKotlinTest
Test/try java spark library on Kotlin.

## Why?
I wish to see if spark libraries work well in Kotlin, they were supposed to as one of the main features of kotlin is it compatibility with Java. So I will write some sample code and list my impressions so far.

### Impressions
#### The good ones
- Everything I tested so far works.
- Less verbose than equivalent Java code.
- Seems easy to translate Java and Scala examples into Kotlin code. (There is a possibility that my opinion about the Scala is biased because I have worked with this language before)


#### The bad ones
- Intellij does not recognize the correct arguments to select method. It's not a problem of the language because it runs correctly, but it annoys me.
- For some reason spark_sql dependency makes impossible to find main file after build if set as compile. The solution was not include this dependency on project build and test only submitting the resultant jarfile to spark where spark_sql already exists.

#### Conclusion
- Appears to be a good JVM alternative, but I think it should be tested with real cases examples.

## How to run
### Using IDEA
Using Intellij open the main file on ```src/main/kotlin``` and click on the play button on the main function.

### Using spark-submit
__Note__: if you don't have spark on your machine download [here](https://spark.apache.org/downloads.html) and then continue.  

Build the project using gradle and run:
```bash
spark-submit file_location/SparkKotlinTest-1.0-SNAPSHOT.jar
```

If you are running local you should put `spark/bin` in your PATH variable or run the command above on this folder.