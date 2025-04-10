package org.mathieu.cleanrmapi.ui.screens.locationdetails

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.mathieu.cleanrmapi.ui.core.composables.Screen

/**
 * Cet écran va afficher les détails d'une localisation (le nom de la localisation ainsi que la liste des personnages relative)
 *
 * @param navController Le contrôleur de navigation pour naviguer sur cette page
 * @param id L'ID de la localisation à afficher
 *
 * Utilisation du viewModel pour la récupération et la gestion des données
 * création d'un élément Texte pour afficher le nom de la localisation
 * TODO : Afficher la liste des personnages qui sont résidents dans cette localisation
 */
@Composable
fun LocationDetailsScreen(navController: NavController, id: Int) {
    Screen(
        viewModel = viewModel { LocationDetailsViewModel() },
        navController = navController,
    ){
        state, viewModel ->
        Text(modifier = Modifier.padding(16.dp), text = "Nom de la localisation")
    }
}



