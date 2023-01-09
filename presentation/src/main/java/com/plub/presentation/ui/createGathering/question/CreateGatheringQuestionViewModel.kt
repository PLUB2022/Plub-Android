package com.plub.presentation.ui.createGathering.question

import androidx.lifecycle.viewModelScope
import com.plub.domain.model.state.PageState
import com.plub.domain.model.state.createGathering.CreateGatheringQuestion
import com.plub.domain.model.state.createGathering.CreateGatheringQuestionPageState
import com.plub.presentation.base.BaseViewModel
import com.plub.presentation.util.PlubLogger
import com.plub.presentation.util.deepCopy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateGatheringQuestionViewModel @Inject constructor() :
    BaseViewModel<CreateGatheringQuestionPageState>(CreateGatheringQuestionPageState()) {

    val maxQuestionCount = 5
    fun updateQuestion(data: CreateGatheringQuestion, text: String) {
        uiState.value.questions.find { it.key == data.key }?.question = text
        updateUiState { uiState ->
            uiState.copy(
                needUpdateRecyclerView = false,
                isAddQuestionButtonVisible = uiState.questions.find { it.question.isBlank() } == null && uiState.questions.size != maxQuestionCount
            )
        }
    }

    fun addQuestion() {
        updateUiState { uiState ->
            val key = uiState.questions.lastOrNull()?.key ?: 0
            val emptyQuestion = CreateGatheringQuestion(
                key = key + 1,
                position = uiState.questions.size + 1
            )
            uiState.copy(
                questions = uiState.questions.plus(emptyQuestion),
                needUpdateRecyclerView = true,
                isAddQuestionButtonVisible = uiState.questions.find { it.question.isBlank() } == null && uiState.questions.size != maxQuestionCount - 1
            )
        }
    }

    fun deleteQuestion(data: CreateGatheringQuestion) {
        updateUiState { uiState ->
            val deleteIndex = uiState.questions.indexOf(data)
            val temp = uiState.questions.minus(data).deepCopy()
            updateQuestionPosition(deleteIndex, temp)
            uiState.copy(
                questions = temp,
                needUpdateRecyclerView = true,
                isAddQuestionButtonVisible = uiState.questions.find { it.question.isBlank() } == null
            )
        }
    }

    private fun updateQuestionPosition(changeIndex: Int, questions: List<CreateGatheringQuestion>) {
        var key = uiState.value.questions.lastOrNull()?.key ?: 0
        questions.forEachIndexed { index, createGatheringQuestion ->
            if (index >= changeIndex) {
                createGatheringQuestion.position = index + 1
                createGatheringQuestion.key = ++key
                createGatheringQuestion.question = questions[index].question
            }
        }
    }
}