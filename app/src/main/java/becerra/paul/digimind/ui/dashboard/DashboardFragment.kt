package becerra.paul.digimind.ui.dashboard

import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import becerra.paul.digimind.R
import becerra.paul.digimind.databinding.FragmentDashboardBinding
import becerra.paul.digimind.ui.Task
import becerra.paul.digimind.ui.home.HomeFragment
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnTime.setOnClickListener{
            setTime()
        }

        binding.btnSave.setOnClickListener{
            saveTask()
        }
        return root
    }

    fun saveTask(){
        var title: String = binding.etTask.text.toString()
        var time: String = binding.btnTime.text.toString()
        var day: String = ""

        if (binding.rbDay1.isChecked)
            day = getString(R.string.day1)
        if (binding.rbDay2.isChecked)
            day = getString(R.string.day2)
        if (binding.rbDay3.isChecked)
            day = getString(R.string.day3)
        if (binding.rbDay4.isChecked)
            day = getString(R.string.day4)
        if (binding.rbDay5.isChecked)
            day = getString(R.string.day5)
        if (binding.rbDay6.isChecked)
            day = getString(R.string.day6)
        if (binding.rbDay1.isChecked)
            day = getString(R.string.day7)

        var task = Task(title, day, time)

        HomeFragment.tasks.add(task)
        Toast.makeText(context, "Se ha agregado la tarea", Toast.LENGTH_SHORT).show()

        saveJson()
    }

    fun saveJson(){
        val sharedPreferences = context?.getSharedPreferences("preferencias", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()

        val gson: Gson = Gson()

        var json = gson.toJson(HomeFragment.tasks)

        editor?.putString("tasks", json)
        editor?.apply()
    }

    fun setTime(){
        val calendar = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener{ timePicker, hour, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)

            binding.btnTime.text = SimpleDateFormat("HH:mm").format(calendar.time)
        }

        TimePickerDialog(context, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE), true).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}