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
    lateinit var viewModel: SummaryViewModel
    lateinit var textInput: EditText
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val buttonUrl = view.findViewById<Button>(R.id.buttonUrl)
        val buttonText = view.findViewById<Button>(R.id.buttonText)
        textInput = view.findViewById(R.id.textInput)

        initViewModel()

        buttonUrl.setOnClickListener{
            createTextSummary(true)
            val summary: String = viewModel.textSummary?.summary?.get(0).toString()
            val action = MainFragmentDirections.actionMainFragmentToDetailFragment(summary)
            view.findNavController().navigate(action)
        }

        buttonText.setOnClickListener{
            createTextSummary(false)
            val summary: String = viewModel.textSummary?.summary?.get(0).toString()
            val action = MainFragmentDirections.actionMainFragmentToDetailFragment(summary)
            view.findNavController().navigate(action)
        }

        return view
    }

    private fun createTextSummary(isUrl: Boolean)
    {
        val specs = SummarySpecs(textInput.text.toString(), 10, true)
        viewModel.createSummary(specs, isUrl)
    }

    private fun initViewModel()
    {
        viewModel = ViewModelProvider(this).get(SummaryViewModel::class.java)
        viewModel.getCreateTextSummaryObserver().observe(viewLifecycleOwner, Observer<TextSummary?> {
            if(it != null)
            {
                Toast.makeText(requireActivity(), "SUCCESSFUL!", Toast.LENGTH_LONG).show()
            }
            else
            {
                Toast.makeText(requireActivity(), "FAILURE! CHECK ERROR", Toast.LENGTH_LONG).show()
            }
        })
    }
}