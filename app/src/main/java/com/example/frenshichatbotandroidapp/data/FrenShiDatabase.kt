package com.example.frenshichatbotandroidapp.data
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
/**!
 * An entity for FrenShi responses
 */
@Entity
data class FrenShiresponsesRoom(
    @PrimaryKey val id : String,
    val tag: String,
    val response: String
){

}
/**!
 * An entity for user inputs
 */
@Entity
data class FrenShiInputsRoom(
    @PrimaryKey val id : String,
    val tag: String,
    val input: String,
){

}
/**!
 * An interface implementing the Database access object for FrenShi database
 */
@Dao
interface FrenshiFRDataDao {
    @Query("SELECT response FROM FrenShiresponsesRoom WHERE tag = (:predictedTag)")
    fun getResponse(predictedTag: String):String
    @Insert
    fun insertAllResponses(vararg frenShiResonses: FrenShiresponsesRoom)
    @Insert
    fun insertNewResponse(newFrenshiDataRecord:FrenShiresponsesRoom)
    @Insert
    fun insertAllInputs(vararg frenShiResonsesRecords: FrenShiInputsRoom)
    @Insert
    fun insertNewInput(newFrenshiDataRecord:FrenShiInputsRoom)
}
/**!
 * An abstract class implementing FrenShi database
 */
@Database(entities = [FrenShiresponsesRoom::class, FrenShiInputsRoom::class], version = 1)
abstract class FrenShiDatabase : RoomDatabase() {
    abstract fun FrenShiDataDao(): FrenshiFRDataDao
}
