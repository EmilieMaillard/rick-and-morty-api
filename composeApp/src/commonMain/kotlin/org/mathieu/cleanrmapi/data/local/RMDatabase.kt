package org.mathieu.cleanrmapi.data.local

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import org.mathieu.cleanrmapi.data.local.objects.CharacterObject
import org.mathieu.cleanrmapi.data.local.objects.EpisodeObject
import org.mathieu.cleanrmapi.data.local.objects.LocationObject

/**
 * C'est le fichier de la base de données Room.
 * Pour effectuer une nouvelle migration, j'ai incrémenter la version de la base de données
 * Concernant les entités, j'ai ajouté la nouvelle entité "LocationObject"
 * J'ai également ajouté le DAO "LocationDAO" pour gérer les opérations de la base de données
 * La table "location_table" a été créée pour stocker les données de localisation dans la base
 */
@Database(
    entities = [
        CharacterObject::class,
        EpisodeObject::class,
        LocationObject::class
    ],
    version = 2,
    exportSchema = false
)

/**
 * Ajout du DAO "LocationDAO" pour gérer les opérations de la base de données
 * La table "location_table" a été créée pour stocker les données de localisation dans la base
 */
@ConstructedBy(AppDatabaseConstructor::class)
abstract class RMDatabase: RoomDatabase() {

    abstract fun characterDAO(): CharacterDAO
    abstract fun episodeDAO(): EpisodeDAO
    abstract fun locationDAO(): LocationDAO

    companion object {
        const val CHARACTER_TABLE = "character_table"
        const val EPISODE_TABLE = "episode_table"
        const val LOCATION_TABLE = "location_table"
    }

}

// The Room compiler generates the `actual` implementations.
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<RMDatabase> {
    override fun initialize(): RMDatabase
}

/**
 * Cette fonction permet de créer la migration pour créer la base de données,
 * Mais j'ai ajouté une méthode qui permet de faire en sorte que si une migration
 * définie n'est pas trouvée si il y a un changement de version du schéma de la base
 * de données, il devra détruire les données existantes et reconstruire la base à
 * partir d'un nouveau schéma.
 * C'est cette méthode : .fallbackToDestructiveMigration()
 */
fun getRoomDatabase(
    builder: RoomDatabase.Builder<RMDatabase>
): RMDatabase = builder
    .addMigrations()
    .fallbackToDestructiveMigrationOnDowngrade(true)
    .fallbackToDestructiveMigration(true)
    .setDriver(BundledSQLiteDriver())
    .setQueryCoroutineContext(Dispatchers.IO)
    .build()