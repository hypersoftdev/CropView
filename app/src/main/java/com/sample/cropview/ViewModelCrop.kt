package com.sample.cropview

import android.app.Application
import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.pegasus.crop.util.bitmaps.BitmapUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 *   Developer: Sohaib Ahmed
 *   Date: 9/26/2024
 *   Profile:
 *     -> github.com/epegasus
 *     -> linkedin.com/in/epegasus
 */

class ViewModelCrop(private val application: Application) : AndroidViewModel(application) {

    private val _bitmapLiveData = MutableLiveData<Bitmap>()
    val bitmapLiveData: LiveData<Bitmap> get() = _bitmapLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = _errorLiveData

    fun getBitmap(uri: Uri) = viewModelScope.launch(Dispatchers.IO) {
        BitmapUtils.resize(uri, application)?.let {
            _bitmapLiveData.postValue(it)
        } ?: run {
            _errorLiveData.postValue("Failed to process image, try again later")
        }
    }
}