package org.mathieu.cleanrmapi.data.repositories

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.mathieu.cleanrmapi.common.toList
import org.mathieu.cleanrmapi.data.local.LocationDAO
import org.mathieu.cleanrmapi.data.local.objects.toDBObject
import org.mathieu.cleanrmapi.data.local.objects.toModel
import org.mathieu.cleanrmapi.data.remote.CharacterApi
import org.mathieu.cleanrmapi.data.remote.LocationApi
import org.mathieu.cleanrmapi.domain.character.models.Character
import org.mathieu.cleanrmapi.domain.location.LocationRepository
import org.mathieu.cleanrmapi.domain.location.models.LocationPreview

/**
 * Implémentation du repository Location
 *
 * Ce repository permet la communication avec la base de données locale et l'API
 * On va donc pouvoir récupérer une localisation et les personnages qui y sont présents
 */
internal class LocationRepositoryImpl (private val characterApi: CharacterApi):LocationRepository{

    /**
     * Fonction qui récupère la liste des personnages présents dans une localisation
     * Si la localisation existe dans la base de données locale, on la récupère
     * on créé une liste contenant les personnages présents dans cette localisation
     * s'il y en a, on les liste, sinon on retourne une liste vide
     */
    override suspend fun getAllCharactersInLocation(locationId: Int): List<Character> {
        val locationLocal = GetLocationObjectIfExists(locationId)

        val idList = locationLocal.residents

        return if (idList.contains(",")) {
            val characterResponse = characterApi.getCharactersFromIds(ids = idList)
            characterResponse.map { it.toDBObject().toModel() }
        } else {
            val characterResponse = characterApi.getCharacter(idList.toInt())
            characterResponse?.toDBObject()?.toModel()?.toList() ?: emptyList()
        }
    }

    /**
     * On récupère une localisation grâce à son ID
     * et on la retourne
     */
    override suspend fun getOneLocation(id: Int): LocationPreview {
        val locationLocal = GetLocationObjectIfExists(locationId = id)
        return locationLocal.toModel()
    }
}

/**
 * Fonction permettant de savoir si une localisation existe dans la base de données locale ou dans l'API
 * De ce que je comprends, on donne accès aux endpoints et aux méthodes CRUD à l'aide de l'injection de dépendances avec Koin
 * On invoque le LocationObject pour récupérer la localisation
 * On va donc essayer de la récupérer dans la base de données locale
 * Si elle n'existe pas en local, on va la récupérer via l'API
 * Sinon on va la retourner
 */
private object GetLocationObjectIfExists : KoinComponent {

    private val locationApi: LocationApi by inject()
    private val locationLocal: LocationDAO by inject()



}









