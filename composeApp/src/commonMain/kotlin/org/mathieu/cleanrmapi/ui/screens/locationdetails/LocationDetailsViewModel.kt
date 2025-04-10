package org.mathieu.cleanrmapi.ui.screens.locationdetails

import org.mathieu.cleanrmapi.domain.location.LocationRepository
import org.mathieu.cleanrmapi.ui.core.ViewModel

/**
 * On donne un accès au Repository pour récupérer les données de la localisation
 * @param locationRepository Le repository pour récupérer les données de la localisation
 *
 * On utilise le ViewModel pour gérer l'état de la vue
 * on va l'utiliser pour récupérer les données d'une localisation à partir de son ID
 * Si on réussit à récupérer les données, on met à jour l'état de la vue avec les données
 * Si on échoue, on écrit un message d'erreur
 */
class LocationDetailsViewModel(
    private val locationRepository: LocationRepository

) : ViewModel<LocationDetailsState>(LocationDetailsState.Loading) {
    fun init(locationId: Int) {
        fetchData(
            source = { locationRepository.getTheLocation(id = locationId) }
        ) {
            onSuccess { detailsLocation ->
                updateState {
                    LocationDetailsState.Loaded(
                        name = detailsLocation.name,
                    )
                }
            }

            onFailure {
                updateState {
                    LocationDetailsState.Error(message = it.message ?: it.toString())
                }
            }
        }
    }
}

/**
 * Gestion de l'état de la vue
 *
 * On utilise l'objet Loading pour indiquer que la vue est en cours de chargement
 * On utilise l'objet Error pour indiquer qu'il y a eu une erreur lors du chargement des données
 * On utilise l'objet Loaded pour indiquer que les données ont été chargées avec succès
 */
sealed interface LocationDetailsState {
    data object Loading : LocationDetailsState

    data class Error(val message: String) : LocationDetailsState

    data class Loaded(
        val name: String,
    ) : LocationDetailsState
}