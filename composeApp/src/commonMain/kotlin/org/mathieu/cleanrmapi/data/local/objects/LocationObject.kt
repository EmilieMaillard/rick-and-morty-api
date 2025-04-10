package org.mathieu.cleanrmapi.data.local.objects

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.mathieu.cleanrmapi.data.local.RMDatabase
import org.mathieu.cleanrmapi.data.validators.annotations.MustBeCommaSeparatedIds

/**
 * Création d'une nouvelle table pour stocker les informations sur les localisations
 *
 * @property id L'ID de la localisation.
 * @property name Le nom de la localisation.
 * @property type Le type de la localisation.
 * @property dimension La dimension dans laquelle se trouve la localisation.
 * @property residents La liste des personnages qui résident dans cette localisation.
 */
@Entity(tableName = RMDatabase.LOCATION_TABLE)
class LocationObject(
    @PrimaryKey
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    @MustBeCommaSeparatedIds
    val residents: String,
) {
}

