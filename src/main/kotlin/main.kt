package SparkKotlinTest

import org.apache.spark.api.java.JavaRDD
import org.apache.spark.api.java.JavaSparkContext
import org.apache.spark.sql.*
import org.apache.spark.sql.types.DataTypes
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.functions.sum
import java.util.ArrayList
import SparkKotlinTest.model.DummyDataClass


fun  main() {
    val spark = SparkSession
        .builder()
        .appName("Build a DataFrame from Scratch")
        .master("local[*]")
        .orCreate

    val stringAsList: List<Array<String>> = listOf(arrayOf("bar1.1", "bar2.1"), arrayOf("bar2.1", "bar2.2"))

    val sparkContext = JavaSparkContext(spark.sparkContext())

    val rowRDD: JavaRDD<Row> = sparkContext.parallelize(stringAsList).map{ row: Array<String> -> RowFactory.create(*row)}

    val schema: StructType = DataTypes
                    .createStructType(
                        arrayOf(
                            DataTypes.createStructField("foe1", DataTypes.StringType, false),
                            DataTypes.createStructField("foe2", DataTypes.StringType, false)))

    val df = spark.createDataFrame(rowRDD, schema).toDF()

    df.show()

    val df2 = spark.createDataFrame(
        sparkContext.parallelize(arrayListOf(RowFactory.create(1, "test"))),
        DataTypes.createStructType(
            arrayOf(
                DataTypes.createStructField("id", DataTypes.IntegerType, false ),
                DataTypes.createStructField("desc", DataTypes.StringType, false )
            )))

    df2.show()


    val testList = listOf(
        DummyDataClass(1, 2, "first"),
        DummyDataClass(2, 3, "second"),
        DummyDataClass(3, null, "third")
    )

    val df3 = spark.createDataFrame(
        testList,
        DummyDataClass::class.java
    )

    df3.show()

    val encoder: Encoder<DummyDataClass> = Encoders.bean(DummyDataClass::class.java)
    val dataset: Dataset<DummyDataClass> = spark.createDataset(testList, encoder)

    dataset.show()
    dataset.printSchema()

    dataset.select(dataset.col("id")).show()

    val listOfIntegers = IntRange(1, 100).toList()

    val rowRDDOfIntegers = sparkContext.parallelize(listOfIntegers).map{row: Int -> RowFactory.create(row)}


    val df4 = spark.createDataFrame(
        rowRDDOfIntegers,
        DataTypes.createStructType(
            arrayOf(
                DataTypes.createStructField("number", DataTypes.IntegerType, false )
            )))

    df4.show(100)

    df4.select(sum("number")).show()

}
