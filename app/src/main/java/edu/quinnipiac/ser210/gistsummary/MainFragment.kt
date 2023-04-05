/** Assignment: Assignment 3
 *  @author: Emilio Cruz and Glenn Buyce
 *  @date: 4/4/23
 */

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
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import edu.quinnipiac.ser210.gistsummary.databinding.FragmentMainBinding

class MainFragment : Fragment()
{
    lateinit var textInput: EditText

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        // user input text
        textInput = _binding!!.textInput

        // navigates to detail fragment passing textInput as arg
        _binding!!.buttonUrl.setOnClickListener{
            val action = MainFragmentDirections.actionMainFragmentToDetailFragment(textInput.text.toString())
            this.findNavController().navigate(action)
        }

        // navigates to detail fragment passing textInput as arg
        _binding!!.buttonText.setOnClickListener{
            val action = MainFragmentDirections.actionMainFragmentToDetailFragment(textInput.text.toString())
            this.findNavController().navigate(action)
        }

        return binding.root
    }
}