package com.example.frenshichatbotandroidapp.data

import androidx.room.*
import androidx.room.RoomDatabase

/**!
 * An entity for FrenShi responses EN version
 */
@Entity
data class FrenShiENResponsesRoom(
    @PrimaryKey val id : String,
    val tag: String,
    val response: String
){

}
/**!
 * An entity for user inputs EN version
 */
@Entity
data class FrenShiENInputsRoom(
    @PrimaryKey val id : String,
    val tag: String,
    val input: String,
){

}
/**!
 * An interface implementing the Database access object for FrenShi database EN version
 */
@Dao
interface FrenShiENDataDao {
    //TODO rectify this when integrating NLP models
    //@Query("SELECT responses FROM FrenshiDataRoom WHERE tag = predictedTag")
    //fun getResponse(predictedTag: String)
    @Insert
    fun insertAllResponses(vararg frenShiENResonsesRecords: FrenShiENResponsesRoom)
    @Insert
    fun insertNewResponse(newFrenShiDataRecord:FrenShiENResponsesRoom)
    @Insert
    fun insertAllInputs(vararg frenShiENResonsesRecords: FrenShiENResponsesRoom)
    @Insert
    fun insertNewInput(newFrenshiDataRecord:FrenShiENResponsesRoom)
}
/**!
 * An abstract class implementing FrenShi database EN version
 */
@Database(entities = [FrenShiENResponsesRoom::class, FrenShiENInputsRoom::class], version = 1)
abstract class FrenShiENDatabase : RoomDatabase() {
    abstract fun FrenShiENDataDao(): FrenShiENDataDao
}