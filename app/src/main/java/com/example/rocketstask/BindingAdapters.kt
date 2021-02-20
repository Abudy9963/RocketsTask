package com.example.rocketstask

import android.media.Image
import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.rocketstask.detail.DetailViewModel
import com.example.rocketstask.model.Rocket
import com.example.rocketstask.overview.OverviewViewModel
import com.example.rocketstask.overview.PhotoGridAdapter

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
                .load(imgUri)
                .apply(RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image))
                .into(imgView)


    }
}
    @BindingAdapter("listData")
    fun bindRecyclerView(recyclerView: RecyclerView, data: List<Rocket>?) {
        val adapter = recyclerView.adapter as PhotoGridAdapter
        adapter.differ.submitList(data)
    }

@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: OverviewViewModel.RocketApiStatus?) {
    when (status) {
         OverviewViewModel.RocketApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        OverviewViewModel.RocketApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        OverviewViewModel.RocketApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("detailSpiStatus")
fun bindDetailStatus(statusImageView: ImageView, status: DetailViewModel.DetailApiStatus?) {
    when (status) {
        DetailViewModel.DetailApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        DetailViewModel.DetailApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        DetailViewModel.DetailApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("sliderList")
fun bindList(slider: ImageSlider,list :List<String>?){
    val data = mutableListOf<SlideModel>()
    if (list != null) {
        for (i in list ){

                 data.add(SlideModel(i))
        }
    }
   slider.setImageList(data, ScaleTypes.CENTER_CROP)
}
