package com.example.acronymjorge.repository

import com.example.acronymjorge.model.AcronymResponseItem
import com.example.acronymjorge.remote.AcronymRepositoryImpl
import com.example.acronymjorge.remote.Repository
import com.example.acronymjorge.service.AcronymAPI
import com.example.acronymjorge.utils.UIEvents
import com.google.common.truth.Truth.assertThat
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class RepositoryImplTest {

    private val mockServiceApi = mockk<AcronymAPI>(relaxed = true)
    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var repository: Repository
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = AcronymRepositoryImpl(mockServiceApi)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    @Test
    fun apiCallSuccess() {
        coEvery { mockServiceApi.getAcronymMeaning("HMM") } returns mockk() {
            every { isSuccessful } returns true
            every { body() } returns listOf(mockk(), mockk(), mockk())
        }
        var state: UIEvents = UIEvents.LOADING
        val job = testScope.launch {
            repository.getAcronym("HMM") {
                state = it
            }
        }
        assertThat(state).isInstanceOf(UIEvents.SUCCESS::class.java)
        job.cancel()
    }
}