/** Assignment: Assignment 3
 *  @author: Emilio Cruz and Glenn Buyce
 *  @date: 4/4/23
 */

package edu.quinnipiac.ser210.gistsummary

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.content.ContextCompat
import edu.quinnipiac.ser210.gistsummary.databinding.FragmentSettingsBinding
import yuku.ambilwarna.AmbilWarnaDialog

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        // Intended to change color of theme, but doesn't work
        _binding!!.colorPickerButton.setOnClickListener {
            openColorPicker()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // opens color picker view
    private fun openColorPicker()
    {
        val defaultColor = ContextCompat.getColor(requireActivity(), R.color.main_color)
        val awdListener = object : AmbilWarnaDialog.OnAmbilWarnaListener{
            override fun onCancel(dialog: AmbilWarnaDialog?)
            {
                // empty function
            }

            override fun onOk(dialog: AmbilWarnaDialog?, color: Int)
            {
                // non functional
            }
        }

        val awd = AmbilWarnaDialog(requireActivity(), defaultColor, awdListener)
        awd.show()
    }
}