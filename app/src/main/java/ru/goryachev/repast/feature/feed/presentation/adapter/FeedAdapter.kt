package ru.goryachev.repast.feature.feed.presentation.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.squareup.picasso.Picasso
import ru.goryachev.fieldsadapter.FieldsAdapter
import ru.goryachev.fieldsadapter.matcher.FieldViewHolder
import ru.goryachev.repast.R
import ru.goryachev.repast.feature.feed.domain.RestaurantEntity

class FeedAdapter(itemsList: List<Any>,
                  val callListener: (String) -> Unit,
                  val tipListener: (RestaurantEntity) -> Unit,
                  val starListener: (RestaurantEntity) -> Unit) : FieldsAdapter<Any>() {

    init {
        addXmlLayouter(R.layout.list_item_header) {
            matcher { it is ListItemHeader }
            creator { view, viewGroup -> HeaderItemViewHolder(view, viewGroup) }
        }

        addXmlLayouter(R.layout.list_item_restoraunt) {
            matcher { it is RestaurantEntity }
            creator { view, viewGroup -> RestaurantItemViewHolder(view, viewGroup) }
        }

        changeDataSet(itemsList)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun changeDataSet(itemsList: List<Any>) {
        setList(itemsList)
        notifyDataSetChanged()
    }

    private inner class RestaurantItemViewHolder(itemView: View, root: ViewGroup) : FieldViewHolder<RestaurantEntity>(itemView, root) {

        @SuppressLint("SetTextI18n")
        override fun performBind(data: RestaurantEntity) {
            loadPhoto(data.photoUrl)
            with(itemView) {
                findViewById<TextView>(R.id.title).text = data.title
                findViewById<RatingBar>(R.id.ratingBar).rating = data.rating
                findViewById<TextView>(R.id.ratingCount).text = "${data.rating} (${data.ratingCount})"
                findViewById<TextView>(R.id.address).text = data.address
                findViewById<TextView>(R.id.description).text = data.desc
                findViewById<ImageButton>(R.id.starBtn).setOnClickListener {starListener.invoke(data)}
                findViewById<ImageButton>(R.id.callBtn).setOnClickListener {callListener.invoke(data.phone)}
                findViewById<Button>(R.id.setTip).setOnClickListener {tipListener.invoke(data)}
            }
        }

        private fun loadPhoto(url: String){
            if (url.isEmpty()) return
            val photoView = itemView.findViewById<ImageView>(R.id.restPhoto)
            Picasso.get()
                    .load(url)
                    .fit()
                    .into(photoView)
        }
    }

    private inner class HeaderItemViewHolder(itemView: View, root: ViewGroup) : FieldViewHolder<ListItemHeader>(itemView, root) {

        @SuppressLint("SetTextI18n")
        override fun performBind(data: ListItemHeader) {
            itemView.findViewById<TextView>(R.id.headerTitle).text = data.title
            itemView.findViewById<TextView>(R.id.headerSubTitle).text = data.subTitle
        }
    }
}