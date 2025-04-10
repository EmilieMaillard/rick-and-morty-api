package org.mathieu.cleanrmapi.domain.location

import org.mathieu.cleanrmapi.domain.character.models.Character
import org.mathieu.cleanrmapi.domain.location.models.LocationPreview

interface LocationRepository {
    /**
     * Récupération de la liste des personnages présents sur la dernière localisation
     *
     * @param locationId L'identifiant unique de la localisation
     * @return La liste des personnages présents dans la localisation sélectionnée
     */
    suspend fun getAllCharactersInLocation(locationId: Int): List<Character>


    /**
     * Récupération de la position actuelle d'un personnage
     *
     * @param id L'identifiant unique de la position
     * @return Les informations de la position actuelle
     */

    suspend fun getOneLocation(id: Int): LocationPreview
}