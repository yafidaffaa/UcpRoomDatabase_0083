package com.example.ucp2.ui.navigasi

interface AlamatNavigasi {
    val route: String
}

object DestinasiHome : AlamatNavigasi {
    override val route: String = "home"
}

object DestinasiHomeSpl : AlamatNavigasi {
    override val route: String = "home_spl"
}

object DestinasiInsertSpl : AlamatNavigasi {
    override val route: String ="insert_spl"
}

object DestinasiInsertBrg : AlamatNavigasi {
    override val route: String ="insert_brg"
}

object DestinasiHomeBrg : AlamatNavigasi {
    override val route: String = "home_brg"
}

object DestinasiDetailBrg : AlamatNavigasi{
    override val route = "detail_brg"
    const val ID = "id"
    val routesWithArg = "$route/{$ID}"
}

object DestinasiUpdateBrg : AlamatNavigasi{
    override val route = "update_brg"
    const val ID = "id"
    val routesWithArg = "$route/{$ID}"
}