package edu.quinnipiac.ser210.gistsummary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

class MainFragment : Fragment()
{
    lateinit var textInput: EditText
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val buttonUrl = view.findViewById<Button>(R.id.buttonUrl)
        val buttonText = view.findViewById<Button>(R.id.buttonText)
        textInput = view.findViewById(R.id.textInput)

        buttonUrl.setOnClickListener{
            val action = MainFragmentDirections.actionMainFragmentToDetailFragment(textInput.text.toString())
            view.findNavController().navigate(action)
        }

        buttonText.setOnClickListener{
            val action = MainFragmentDirections.actionMainFragmentToDetailFragment(textInput.text.toString())
            view.findNavController().navigate(action)
        }

        return view
    }
}