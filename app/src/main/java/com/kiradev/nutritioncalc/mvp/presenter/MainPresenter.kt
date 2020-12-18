package com.kiradev.nutritioncalc.mvp.presenter

import com.kiradev.nutritioncalc.mvp.view.MainView
import com.kiradev.nutritioncalc.navigation.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.SearchScreen())
    }

    fun backClick() = router.exit()
}