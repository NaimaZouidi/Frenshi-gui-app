package com.example.frenshichatbotandroidapp.data
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase
/**!
 * An entity for FrenShi responses FR version
 */
@Entity
data class FrenShiFRresponsesRoom(
    @PrimaryKey val id : String,
    val tag: String,
    val response: String
){

}
/**!
 * An entity for user inputs FR version
 */
@Entity
data class FrenShiFRInputsRoom(
    @PrimaryKey val id : String,
    val tag: String,
    val input: String,
){

}
/**!
 * An interface implementing the Database access object for FrenShi database FR version
 */
@Dao
interface FrenshiFRDataDao {
    //TODO rectify this when integrating NLP models
    //@Query("SELECT responses FROM FrenshiDataRoom WHERE tag = predictedTag")
    //fun getResponse(predictedTag: String)
    @Insert
    fun insertAllResponses(vararg frenShiFRResonses: FrenShiFRresponsesRoom)
    @Insert
    fun insertNewResponse(newFrenshiDataRecord:FrenShiFRresponsesRoom)
    @Insert
    fun insertAllInputs(vararg frenShiFRResonsesRecords: FrenShiFRInputsRoom)
    @Insert
    fun insertNewInput(newFrenshiDataRecord:FrenShiFRInputsRoom)
}
/**!
 * An abstract class implementing FrenShi database FR version
 */
@Database(entities = [FrenShiFRresponsesRoom::class, FrenShiFRInputsRoom::class], version = 1)
abstract class FrenshiFRDatabase : RoomDatabase() {
    abstract fun FrenshiFRDataDao(): FrenshiFRDataDao
}
