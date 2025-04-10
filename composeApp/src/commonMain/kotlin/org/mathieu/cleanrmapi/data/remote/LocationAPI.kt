package org.mathieu.cleanrmapi.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import org.mathieu.cleanrmapi.data.remote.responses.LocationResponse

internal class LocationApi(private val client: HttpClient){
    /**
     * Endpoint permettant de récupérer une localisation
     *
     * @param id L'ID de la localisation à récupérer.
     * @throws Exception Si la requête réussie, alors on affiche la localisation en question
     */
    suspend fun getOneLocation(id: Int): LocationResponse? = client
        .get("location/$id")
        .accept(HttpStatusCode.OK)
        .body()

}