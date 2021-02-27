package com.oddlyspaced.viewmodeltest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.*
import com.oddlyspaced.viewmodeltest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by lazy {
        ViewModelProvider(this).get(HelloCodelabViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.edittext.doAfterTextChanged {
            viewModel.onNameChanged(it.toString())
        }

        viewModel.name.observe(this, Observer {
            binding.textView.text = "Hello $it"
        })

    }
}

class HelloCodelabViewModel: ViewModel() {

    private val _name = MutableLiveData("")
    val name: LiveData<String> = _name

    fun onNameChanged(newName: String) {
        _name.value = newName
    }
}