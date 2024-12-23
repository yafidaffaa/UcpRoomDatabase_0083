package com.example.ucp2.dependenciesinjection

import android.content.Context
import com.example.ucp2.data.database.DatabaseToko
import com.example.ucp2.repository.LocalRepositoryBrg
import com.example.ucp2.repository.LocalRepositorySpl
import com.example.ucp2.repository.RepositoryBrg
import com.example.ucp2.repository.RepositorySpl

interface InterfaceContainerApp {
    val repositoryBrg: RepositoryBrg
    val repositorySpl: RepositorySpl
}

class ContainerApp(private val context: Context) : InterfaceContainerApp {
    override val repositoryBrg: RepositoryBrg by lazy {
        LocalRepositoryBrg(DatabaseToko.getDatabase(context).barangDao())

    }

    override val repositorySpl: RepositorySpl by lazy {
        LocalRepositorySpl(DatabaseToko.getDatabase(context).supplierDao())

    }
}