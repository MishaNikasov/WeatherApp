package com.nikasov.weatherapp.ui.bindings

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.math.RoundingMode
import java.text.DecimalFormat

//@BindingAdapter("load_image")
//fun bindingImage(imageView : ImageView, imageUrl : String?) {
//    imageUrl?.let{
//        Glide
//            .with(imageView.context)
//            .load(imageUrl)
//            .centerCrop()
//            .dontAnimate()
//            .placeholder(R.drawable.recipe_holder)
//            .into(imageView)
//    }
//}


@BindingAdapter("double_convert")
fun bindingDoubleText(textView : TextView, double : Double?) {
    double?.let{
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        val formatDouble = df.format(double)
        textView.text = formatDouble
    }
}