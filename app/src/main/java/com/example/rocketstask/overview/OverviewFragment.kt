package com.example.rocketstask.overview

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rocketstask.model.Rocket
import com.example.rocketstask.databinding.FragmentOverviewBinding


class OverviewFragment : Fragment() , PhotoGridAdapter.Listener {


    private val viewModel: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentOverviewBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setHasOptionsMenu(true)




        binding.photosGrid.adapter = PhotoGridAdapter(this)

        viewModel.navigateToSelectedRocket.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                this.findNavController().navigate(OverviewFragmentDirections.actionShowDetail(it.rocket_id))
                viewModel.displayRocketDetailsComplete()
            }
        })

        viewModel.navigateToWikipedia.observe(viewLifecycleOwner, Observer {
            if (null!=it){
                this.findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToWebFragment(it.wikipedia))
            viewModel.displayWebFragmentComplete()
            }
        })

        return binding.root
    }


    override fun onClick(rocket: Rocket) {
        viewModel.displayRocketDetails(rocket)
    }

    override fun onButtonClick(rocket: Rocket) {
        viewModel.displayWebFragment(rocket)
    }

}
