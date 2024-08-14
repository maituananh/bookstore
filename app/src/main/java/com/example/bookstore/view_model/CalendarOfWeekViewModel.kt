package com.example.bookstore.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.i_repository.ICalendarRepository
import com.example.domain.model.calendar.Calendar
import com.example.domain.model.calendar.Job
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalendarOfWeekViewModel @Inject constructor(private val iCalendarRepository: ICalendarRepository) :
    ViewModel() {
    private val _calendarList = MutableLiveData<List<Calendar>>()
    val calendarList: LiveData<List<Calendar>> = _calendarList

    private val _loading = MutableLiveData<Boolean?>()
    val loading: LiveData<Boolean?> = _loading


    fun fetchCalendar() {
        viewModelScope.launch(Dispatchers.IO) {
            iCalendarRepository.findCalendar()
                .onStart {
                    _loading.value = true
                    // start loading
                }
                .onCompletion {
                    // end loading
                    _loading.value = false
                }
                .onEach {
                    _calendarList.value = it
                    // todo get data
                }.catch {
                    // todo throw exp
                    _loading.value = false
                }
                .launchIn(viewModelScope)
        }
    }

    fun updateIsSelected(job: Job) {
        viewModelScope.launch(Dispatchers.IO) {
            iCalendarRepository.updateIsSelected(job)
        }
    }
}