/** Assignment: Assignment 3
 *  @author: Emilio Cruz and Glenn Buyce
 *  @date: 4/4/23
 */

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
import edu.quinnipiac.ser210.gistsummary.databinding.FragmentDetailBinding

class DetailFragment : Fragment()
{
    lateinit var viewModel: SummaryViewModel
    lateinit var input: String

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
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

        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setLiveData()
        _binding!!.summaryView.text = viewModel.getSummary()

        // updates textview when summary is received from API
        viewModel.getCreateTextSummaryObserver().observe(viewLifecycleOwner) { newValue ->
            _binding!!.summaryView.text = newValue?.summary.toString()
        }
    }

    private fun createTextSummary()
    {
        viewModel.createSummary(input)
    }

    private fun initViewModel()
    {
        // preps data model class and sets observer for summary text view
        viewModel = ViewModelProvider(this).get(SummaryViewModel::class.java)
        viewModel.getCreateTextSummaryObserver().observe(viewLifecycleOwner, Observer<TextSummary?> {
            if(it == null)
                Toast.makeText(requireActivity(), "An unknown error has occurred", Toast.LENGTH_LONG).show()
        })
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }
}