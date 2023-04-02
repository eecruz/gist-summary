package edu.quinnipiac.ser210.gistsummary

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class DetailFragment : Fragment()
{
    lateinit var viewModel: SummaryViewModel
    lateinit var input: String
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if (bundle == null)
        {
            Log.e("DetailFragment", "DetailFragment did not receive summary")
            return
        }

        input = DetailFragmentArgs.fromBundle(bundle).inputText
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        initViewModel()
        createTextSummary()

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val summaryView = view.findViewById<TextView>(R.id.summaryView)
        viewModel.setLiveData()
        summaryView.text = viewModel.getSummary()

        viewModel.getCreateTextSummaryObserver().observe(viewLifecycleOwner) { newValue ->
            summaryView.text = newValue?.summary.toString()
        }
    }

    private fun createTextSummary()
    {
        viewModel.createSummary(input)
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