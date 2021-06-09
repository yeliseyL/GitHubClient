package com.example.githubclient.mvp.presenter

import com.example.githubclient.mvp.model.entity.GithubRepository
import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.model.repo.IGithubRepositoriesRepo
import com.example.githubclient.mvp.view.UserView
import com.example.githubclient.mvp.view.`UserView$$State`
import com.nhaarman.mockito_kotlin.any
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.observers.TestObserver
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import ru.terrakok.cicerone.Router


class UserPresenterTest {

    private val githubRepository = listOf(
        GithubRepository("1", "Name", 1)
    )

    private lateinit var presenter: UserPresenter

    @Mock
    private lateinit var repository: IGithubRepositoriesRepo

    @Mock
    private lateinit var userViewState: `UserView$$State`

    private val mainThreadScheduler: Scheduler = TestScheduler()

    @Mock
    private lateinit var repositoriesListPresenter: RepositoriesListPresenter

    @Mock
    private lateinit var router: Router

    @Mock
    private lateinit var user: GithubUser

    @Mock
    private lateinit var userView: UserView


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = UserPresenter(user, mainThreadScheduler, repository, router, repositoriesListPresenter)
        presenter.attachView(userView)
        presenter.setViewState(userViewState)
        Mockito.`when`(repository.getRepositories(any())).thenReturn(Single.just(githubRepository))
    }

    @Test
    fun view_ShouldInitialize() {
        Mockito.verify(userView, Mockito.times(1)).init()
    }

    @Test
    fun repository_ShouldGetRepositories() {
        Mockito.verify(repository, Mockito.times(1)).getRepositories(user)
        assertEquals(githubRepository, repository.getRepositories(user)?.blockingGet())
    }

    @Test
    fun repositorySingle_WorksCorrectly() {
        val result = repository.getRepositories(user)
        val testObserver = TestObserver<List<GithubRepository>>()

        result?.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
    }
}