package edu.quinnipiac.ser210.gistsummary

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class DetailFragment : Fragment()
{
    lateinit var textSummary: String
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if (bundle == null)
        {
            Log.e("DetailFragment", "DetailFragment did not receive summary")
            return
        }

        textSummary = DetailFragmentArgs.fromBundle(bundle).summary
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        val summaryView = view.findViewById<TextView>(R.id.summaryView)
        summaryView?.text = textSummary

        return view
    }
}