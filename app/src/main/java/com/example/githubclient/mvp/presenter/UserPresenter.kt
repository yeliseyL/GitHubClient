package com.example.githubclient.mvp.presenter

import com.example.githubclient.mvp.model.entity.GithubRepository
import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.model.repo.IGithubRepositoriesRepo
import com.example.githubclient.mvp.presenter.list.IRepositoryListPresenter
import com.example.githubclient.mvp.view.UserView
import com.example.githubclient.mvp.view.list.RepositoryItemView
import com.example.githubclient.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UserPresenter(private val user: GithubUser,
                    private val mainThreadScheduler: Scheduler,
                    private val repositoriesRepo: IGithubRepositoriesRepo,
                    private val router: Router,
                    val repositoriesListPresenter: RepositoriesListPresenter) :
    MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        repositoriesListPresenter.itemClickListener = { itemView ->
            val repository = repositoriesListPresenter.repositories[itemView.pos]
            router.navigateTo(Screens.RepositoryScreen(repository))
        }
    }

    fun loadData() {
        repositoriesRepo.getRepositories(user)
            ?.observeOn(mainThreadScheduler)
            ?.subscribe({ repositories ->
                updateData(repositories)
            }, {
                println("Error: ${it.message}")
            })
    }

    fun updateData(repositories: List<GithubRepository>) {
        repositoriesListPresenter.repositories.clear()
        repositoriesListPresenter.repositories.addAll(repositories)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}

class RepositoriesListPresenter : IRepositoryListPresenter {
    var repositories = mutableListOf<GithubRepository>()
    override var itemClickListener: ((RepositoryItemView) -> Unit)? = null
    override fun getCount() = repositories.size

    override fun bindView(view: RepositoryItemView) {
        val repository = repositories[view.pos]
        repository.name?.let { view.setName(it) }
    }
}

