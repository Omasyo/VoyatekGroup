package com.example.voyatekgroup

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.voyatekgroup.ui.features.plantrip.PlanTripRoute
import com.example.voyatekgroup.ui.features.plantrip.SelectCityRoute
import com.example.voyatekgroup.ui.features.plantrip.SelectDateRoute
import com.example.voyatekgroup.ui.features.tripdetails.TripDetailsRoute
import kotlinx.serialization.Serializable

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController,
        startDestination = PlanTrip,
        modifier = modifier,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Up,
                spring(stiffness = Spring.StiffnessMediumLow)
            )
        },
        exitTransition =
        { fadeOut() + scaleOut(targetScale = 0.9f) },
        popEnterTransition =
        { fadeIn() + scaleIn(initialScale = 0.9f) },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Down,
                spring(stiffness = Spring.StiffnessMediumLow)
            )
        },
    ) {
        composable<PlanTrip> {
            PlanTripRoute(
                onSelectCityTap = { navController.navigate(SelectCityDialog) },
                onSelectDateTap = { navController.navigate(SelectDateDialog) },
                onTripDetailsTap = { navController.navigate(TripDetails(it)) },
                onBackTap = { navController.popBackStack() },
                viewModel = hiltViewModel()
            )
        }
        composable<SelectCityDialog> {
            val parentEntry = remember {
                navController.getBackStackEntry<PlanTrip>()
            }
            SelectCityRoute(
                onCloseTap = { navController.popBackStack() },
                viewModel = hiltViewModel(parentEntry)
            )
        }
        composable<SelectDateDialog> {
            val parentEntry = remember {
                navController.getBackStackEntry<PlanTrip>()
            }
            SelectDateRoute(
                onCloseTap = { navController.popBackStack() },
                viewModel = hiltViewModel(parentEntry)
            )
        }
        composable<TripDetails> {
            TripDetailsRoute(
                onBackTap = { navController.popBackStack() },
                viewModel = hiltViewModel()
            )
        }
    }
}

@Serializable
data object PlanTrip

@Serializable
data object SelectCityDialog

@Serializable
data object SelectDateDialog

@Serializable
data class TripDetails(val id: String)