package com.example.bookstore.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.i_repository.ICalendarRepository
import com.example.domain.model.calendar.Calendar
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalendarOfWeekViewModel @Inject constructor(private val iCalendarRepository: ICalendarRepository) :
    ViewModel() {
    private val _calendarList = MutableLiveData<List<Calendar>>()
    val calendarList: LiveData<List<Calendar>> = _calendarList

    fun fetchCalendar() {
        viewModelScope.launch(Dispatchers.Main) {
            _calendarList.value = iCalendarRepository.findCalendar()
        }
    }
}