package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.TokoApp
import com.example.ucp2.ui.viewmodel.barang.DetailBrgViewModel
import com.example.ucp2.ui.viewmodel.barang.HomeBrgViewModel
import com.example.ucp2.ui.viewmodel.barang.InsertBrgViewModel
import com.example.ucp2.ui.viewmodel.barang.UpdateBrgViewModel
import com.example.ucp2.ui.viewmodel.supplier.HomeSplViewModel
import com.example.ucp2.ui.viewmodel.supplier.InsertSplViewModel

object PenyediaViewModel {

    val Factory = viewModelFactory {
        initializer {
            HomeTokoViewModel(
                TokoApp().ContainerApp.repositorySpl,
                TokoApp().ContainerApp.repositoryBrg
            )
        }

        initializer {
            InsertSplViewModel(
                TokoApp().ContainerApp.repositorySpl
            )
        }
        initializer {
            HomeSplViewModel(
                TokoApp().ContainerApp.repositorySpl
            )
        }

        initializer {
            InsertBrgViewModel(
                TokoApp().ContainerApp.repositoryBrg
            )
        }
        initializer {
            HomeBrgViewModel(
                TokoApp().ContainerApp.repositoryBrg
            )
        }

        initializer {
            DetailBrgViewModel(
                createSavedStateHandle(),
                TokoApp().ContainerApp.repositoryBrg,
            )
        }
        initializer {
            UpdateBrgViewModel(
                createSavedStateHandle(),
                TokoApp().ContainerApp.repositoryBrg,
            )
        }
    }

}

fun CreationExtras.TokoApp(): TokoApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TokoApp)