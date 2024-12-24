package com.example.ucp2.ui.navigasi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ucp2.ui.view.HomeTokoView
import com.example.ucp2.ui.view.barang.DetailBrgView
import com.example.ucp2.ui.view.barang.HomeBrgView
import com.example.ucp2.ui.view.barang.InsertBrgView
import com.example.ucp2.ui.view.barang.UpdateBrgView
import com.example.ucp2.ui.view.supplier.HomeSplView
import com.example.ucp2.ui.view.supplier.InsertSplView

@Composable

fun PengelolaHalaman (
    NavController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = NavController,
        startDestination = DestinasiHome.route
    ) {
        composable(
            route = DestinasiHome.route
        ) {
            HomeTokoView(
                onAddSpl = {
                    NavController.navigate((DestinasiHomeSpl.route))
                },
                onAddBrg = {
                    NavController.navigate((DestinasiHomeBrg.route))
                },
                modifier = modifier
            )
        }

        composable(
            route = DestinasiHomeSpl.route
        ) {
            HomeSplView(
                onAddSpl = {
                    NavController.navigate((DestinasiInsertSpl.route))
                },
                onBack = {
                    NavController.popBackStack()
                },
                modifier = modifier
            )
        }

        composable(
            route = DestinasiInsertSpl.route
        ) {
            InsertSplView(
                onBack = {
                    NavController.popBackStack()
                },
                onNavigate = {
                    NavController.popBackStack()
                },
                modifier = modifier,
            )
        }

        composable(
            route = DestinasiHomeBrg.route
        ) {
            HomeBrgView(
                onDetailClick = {id ->
                    NavController.navigate("${DestinasiDetailBrg.route}/$id")
                    println(
                        "PengelolaHalaman: id = $id"
                    )
                },
                onAddBrg = {
                    NavController.navigate((DestinasiInsertBrg.route))
                },
                onBack = {
                    NavController.popBackStack()
                },
                modifier = modifier
            )
        }

        composable(
            route = DestinasiInsertBrg.route
        ){
            InsertBrgView(
                onBack = {
                    NavController.popBackStack()
                },
                onNavigate = {
                    NavController.popBackStack()
                },
                modifier = modifier,
            )
        }

        composable(
            DestinasiDetailBrg.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailBrg.ID){
                    type = NavType.StringType
                }
            )
        ){
            val nim = it.arguments?.getString(DestinasiDetailBrg.ID)
            nim?.let {id ->
                DetailBrgView(
                    onBack = {
                        NavController.popBackStack()
                    },
                    onEditClick = {
                        NavController.navigate("${DestinasiUpdateBrg.route}/$it")
                    },
                    modifier = modifier,
                    onDeleteClick = {
                        NavController.popBackStack()
                    }
                )
            }
        }

        composable(
            DestinasiUpdateBrg.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateBrg.ID){
                    type = NavType.StringType
                }
            )
        ){
            UpdateBrgView(
                onBack = {
                    NavController.popBackStack()
                },
                onNavigate = {
                    NavController.popBackStack()
                },
                modifier = modifier,
            )
        }
    }
}