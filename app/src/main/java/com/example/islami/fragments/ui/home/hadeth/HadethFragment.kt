package com.example.islami.fragments.ui.home.hadeth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.islami.databinding.FragmentHadethBinding
import com.example.islami.fragments.ui.Constants
import com.example.islami.fragments.ui.hadethDetails.HadethDetailsActivity
import com.example.islami.fragments.ui.model.Hadeth

class HadethFragment :Fragment(){

    lateinit var viewBinding: FragmentHadethBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentHadethBinding.inflate(inflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        readHadethFile()
    }

    fun readHadethFile(){
        val hadethList = mutableListOf<Hadeth>()
        val inputStream = context?.assets?.open("ahadeeth.txt")
        val fileContent = inputStream?.bufferedReader().use { it?.readText() }
        val allAhadeeth = fileContent?.trim()?.split("#")
         allAhadeeth?.forEach{hadeth->
            val lines = hadeth.trim().split("\n")
            val title = lines[0]
            val newList = lines.toMutableList().apply {
                removeAt(0)
            }
            val content = newList.joinToString("\n")
            val hadeth = Hadeth(title, content)
             hadethList.add(hadeth)
        }

        showHadethList(hadethList)
    }

    private fun showHadethList(hadethList: MutableList<Hadeth>) {
        val adapter = HadethRecyclerAdapter(hadethList)
        adapter.onItemClickListener = HadethRecyclerAdapter.OnItemClickListener { item, position ->
            startHadethDetailsScreen(item)
        }
        viewBinding.hadethRecycler.adapter = adapter
    }

    private fun startHadethDetailsScreen(hadeth: Hadeth) {
        val intent = Intent(activity,HadethDetailsActivity::class.java)
        intent.putExtra(Constants.Hadeth_EXTRA,hadeth)
        startActivity(intent)
    }
}