package becerra.paul.digimind.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import becerra.paul.digimind.databinding.FragmentHomeBinding
import becerra.paul.digimind.ui.Task
import becerra.paul.digimind.ui.TaskAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null


    companion object{
        var tasks: ArrayList<Task> = ArrayList<Task>()
        var first = true
    }
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val gridView: GridView = binding.gridview

        if (first) {
            fillTasks()
            first = false
        }


        val adapter = TaskAdapter(root.context, tasks)

        gridView.adapter = adapter

        return root
    }

    fun fillTasks(){
        tasks.add(Task("tarea 1", "lunes", "15:00"))
        tasks.add(Task("tarea 2", "lunes", "15:00"))
        tasks.add(Task("tarea 3", "lunes", "15:00"))
        tasks.add(Task("tarea 4", "lunes", "15:00"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}