package org.mathieu.cleanrmapi.data

import io.ktor.client.HttpClient
import org.koin.core.module.Module
import org.koin.dsl.module
import org.mathieu.cleanrmapi.data.local.CharacterDAO
import org.mathieu.cleanrmapi.data.local.EpisodeDAO
import org.mathieu.cleanrmapi.data.local.LocationDAO
import org.mathieu.cleanrmapi.data.local.RMDatabase
import org.mathieu.cleanrmapi.data.local.getRoomDatabase
import org.mathieu.cleanrmapi.data.remote.CharacterApi
import org.mathieu.cleanrmapi.data.remote.EpisodeApi
import org.mathieu.cleanrmapi.data.remote.LocationApi
import org.mathieu.cleanrmapi.data.remote.createHttpClient
import org.mathieu.cleanrmapi.data.repositories.CharacterRepositoryImpl
import org.mathieu.cleanrmapi.data.repositories.EpisodeRepositoryImpl
import org.mathieu.cleanrmapi.data.repositories.LocationRepositoryImpl
import org.mathieu.cleanrmapi.domain.character.CharacterRepository
import org.mathieu.cleanrmapi.domain.episode.EpisodeRepository
import org.mathieu.cleanrmapi.domain.location.LocationRepository

private const val RM_API_URL = "https://rickandmortyapi.com/api/"

expect val databaseBuilderModule: Module

expect val dataStoreModule: Module

/**
 * Ici, j'ai ajouté le lien de communication LocationApi qui va permettre de récupérer les localisations via l'API
 */
val remoteModule = module {

    single<HttpClient> {
        createHttpClient(
            baseUrl = RM_API_URL
        )
    }
    single { CharacterApi(get()) }
    single { EpisodeApi(get()) }
    single { LocationApi(get()) }
}

/**
 * Ici, j'ai ajouté le repository concernant les localisations qui va permettre de faire le lien entre la couche Data et la couche Domain
 * Grâce à ça, on isole l'accès aux données
 */
val repositoriesModule = module {

    single<CharacterRepository> { CharacterRepositoryImpl(get(), get(), get(), get()) }

    single<EpisodeRepository> { EpisodeRepositoryImpl(get()) }

    single<LocationRepository> { LocationRepositoryImpl(get()) }
}

/**
 * Ici, ajout du DAO LocationDAO pour (Data Access Object) qui permet de gérer les opérations CRUD
 * pour les éléments de localisation.
 */
val databaseModule = module {

    single<RMDatabase> {
        getRoomDatabase(get())
    }

    single<CharacterDAO> {
        val db: RMDatabase = get()
        db.characterDAO()
    }

    single<EpisodeDAO> {
        val db: RMDatabase = get()
        db.episodeDAO()
    }

    single<LocationDAO> {
        val db: RMDatabase = get()
        db.locationDAO()
    }

}