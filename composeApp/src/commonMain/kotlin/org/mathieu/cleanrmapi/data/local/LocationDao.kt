package org.mathieu.cleanrmapi.data.local

import androidx.room.Dao
import androidx.room.Query
import org.mathieu.cleanrmapi.data.local.objects.LocationObject

/**
 * Ce DAO gère toutes les opérations CRUD pour les éléments de localisation.
 * On va pouvoir récupérer une localisations par son ID. Cette fonction est utilisée dans le repository
 */
@Dao
interface LocationDAO {

    @Query("select * from ${RMDatabase.LOCATION_TABLE} where id = :id")
    suspend fun getOneLocation(id: Int): LocationObject?

}