package org.mathieu.cleanrmapi.domain.location.models

/**
 * la classe représente la position actuelle du personnage
 *
 * @property id Représente l'ID unique de la position actuelle
 * @property name Représente le nom de la position actuelle
 */
data class LocationPreview(
    val id: Int,
    val name: String
)