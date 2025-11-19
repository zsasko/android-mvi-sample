package com.zsasko.android.mvi.sample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zsasko.android.mvi.sample.data.intents.CommentIntent
import com.zsasko.android.mvi.sample.data.repository.CommentsRepository
import com.zsasko.android.mvi.sample.data.response.NetworkResponse
import com.zsasko.android.mvi.sample.data.state.CommentsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(val commentsRepository: CommentsRepository) :
    ViewModel() {
    private val _commentsUiState = MutableStateFlow<CommentsUiState>(CommentsUiState.Loading())
    var commentsUiState: StateFlow<CommentsUiState> = _commentsUiState

    fun handleIntent(commentIntent: CommentIntent) {
        if (commentIntent is CommentIntent.ReloadData) {
            loadData()
        }
    }

    fun loadData() {
        viewModelScope.launch {
            _commentsUiState.value = CommentsUiState.Loading()
            val commentsResponse = commentsRepository.getComments()
            when (commentsResponse) {
                is NetworkResponse.Success -> {
                    _commentsUiState.value =
                        CommentsUiState.Loaded(commentsResponse.data ?: emptyList())
                }

                is NetworkResponse.Error -> {
                    _commentsUiState.value = CommentsUiState.Error(commentsResponse.errorMessage)
                }
            }
        }
    }
}