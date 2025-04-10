package org.mathieu.cleanrmapi.data.remote.responses

import kotlinx.serialization.Serializable

/**
 * Classe qui représente les informations de la réponse d'un appel API pour une localisation.
 *
 * @property id L'ID de la localisation.
 * @property name Le nom de la localisation.
 * @property type Le type de la localisation.
 * @property dimension La dimension dans laquelle se trouve la localisation.
 * @property residents La liste des personnages qui résident dans cette localisation.
 */
@Serializable
internal data class LocationResponse(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>
)