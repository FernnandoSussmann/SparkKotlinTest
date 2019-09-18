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
- You can't build your project on Gradle if you create an UDF. I had to make a version of this project using maven to compile the last pice of code I wrote. You can find it [here](https://github.com/FernnandoSussmann/SparkKotlinTestOnMaven).
- Both Gradle and Maven projects break Intellij. Even with Maven version build being sucessful I had to finish the code by coding on vim and compiling using maven commands on terminal. 

If you want to check this bugs I described you can use [this branch](https://github.com/FernnandoSussmann/SparkKotlinTest/tree/trying_to_solve_never_ending_update_on_Intellij) for it. It's code is exactly on this point.

#### Conclusion
- I don't recommend trying to use this setup in a real use case, but I think it is worth while for learning purpouses.
- Kotlin compatibility with java libraries are impressive indead.
- If this errors be corrected in the future maybe it will be a good alternative for writing spark projects. 

## How to run
### Using Intellij IDEA
Using Intellij open the main file on ```src/main/kotlin``` and click on the play button on the main function.

### Using spark-submit
__Note__: if you don't have spark on your machine download [here](https://spark.apache.org/downloads.html) and then continue.  

Build the project using gradle and run:
```bash
spark-submit file_location/SparkKotlinTest-1.0-SNAPSHOT.jar
```

If you are running local you should put `spark/bin` in your PATH variable or run the command above on this folder.
