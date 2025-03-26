package com.assignment.animeverse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.animeverse.model.AnimeData
import com.assignment.animeverse.repository.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DataOrException<T> (
    val loading:Boolean=true,
    val data:T?=null,
    val exception:Exception?=null
)

@HiltViewModel
class AnimeViewModel @Inject constructor(private val animeRepository: AnimeRepository): ViewModel() {

    private val _animeState:MutableStateFlow<DataOrException<AnimeData>> = MutableStateFlow(
        DataOrException()
    )
    val animeState: StateFlow<DataOrException<AnimeData>> = _animeState

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val animeData=animeRepository.getAnimeData()
                _animeState.update {
                    it.copy(
                        false,
                        animeData,
                        null
                    )
                }
            }
            catch (exception:Exception) {
                _animeState.update {
                    it.copy(
                        false,
                        null,
                        exception
                    )
                }
            }
        }
    }

}